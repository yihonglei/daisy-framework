package com.jpeony.redis.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jpeony.redis.exception.SerializationException;
import com.jpeony.redis.facade.RedisSupport;
import com.jpeony.redis.property.RedisProperties;
import com.jpeony.redis.serializer.Serializer;
import com.jpeony.redis.serializer.SerializerFactory;
import com.jpeony.redis.serializer.impl.Jackson2JsonSerializer;
import com.jpeony.redis.utils.SerializationUtils;
import org.apache.commons.pool2.impl.BaseObjectPoolConfig;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisClusterException;

import java.io.IOException;
import java.util.*;

/**
 * @author yihonglei
 */
public class RedisClient implements RedisSupport {

    private static final Logger logger = LoggerFactory.getLogger(RedisClient.class);

    private static final int DEFAULT_MAX_TOTAL = 1024;

    private static final int DEFAULT_MAX_IDLE = 50;

    private static final int DEFAULT_MIN_IDLE = 20;

    private static final int CONNECTION_TIMEOUT = 1500;

    private static final int SO_TIMEOUT = 2000;

    private static final int MAX_REDIRECTIONS = 2;

    private static final long MIN_EVICTABLE_IDLE_TIME_MILLIS = 1000L * 60L * 30L;

    private static final long TIME_BETWEEN_EVICTION_RUNS_MILLIS = 1000L * 60L * 10;

    private static final String DEFAULT_SERIALIZER = "jackson";

    private JedisCluster jc;

    private byte[] prefix;

    private Serializer<Object> serializer;

    private String cluster;

    private String system;

    public RedisClient(RedisProperties param) {
        this(param.getCluster(), param.getPrefix(), param.getPassword(), param.getMaxIdle(), param.getMaxTotal(),
                param.getMinIdle(), param.getConnectionTimeout(), param.getSoTimeout(), param.getMaxAttempts(),
                param.getMinEvictableIdleTimeMillis(), param.getTimeBetweenEvictionRunsMillis(), param.getEvictionPolicyClassName(), param.getSerializer());
    }

    public RedisClient(RedisProperties param, String system) {
        this(param.getCluster(), param.getPrefix(), param.getPassword(), param.getMaxIdle(), param.getMaxTotal(),
                param.getMinIdle(), param.getConnectionTimeout(), param.getSoTimeout(), param.getMaxAttempts(),
                param.getMinEvictableIdleTimeMillis(), param.getTimeBetweenEvictionRunsMillis(), param.getEvictionPolicyClassName(), param.getSerializer());
        this.system = system;
    }

    /**
     * 重载构造函数,提供默认参数
     */
    public RedisClient(String cluster, String prefix, String password) {
        this(cluster, prefix, password, DEFAULT_MAX_IDLE, DEFAULT_MAX_TOTAL, DEFAULT_MIN_IDLE, CONNECTION_TIMEOUT,
                SO_TIMEOUT, MAX_REDIRECTIONS, MIN_EVICTABLE_IDLE_TIME_MILLIS, TIME_BETWEEN_EVICTION_RUNS_MILLIS,
                BaseObjectPoolConfig.DEFAULT_EVICTION_POLICY_CLASS_NAME, DEFAULT_SERIALIZER);
    }

    /**
     * @param cluster           格式为ip:port,ip:port
     * @param prefix            redis-cluster全局KEY前缀
     * @param password          密码
     * @param maxIdle           最多能保留多少个空闲对象
     * @param maxTotal          最多能保留多少对象
     * @param minIdle           最少有多少个空闲对象
     * @param connectionTimeout 连接超时
     * @param soTimeout         返回值的超时时间
     * @param maxAttempts       出现异常最大重试次数
     */
    public RedisClient(String cluster, String prefix, String password, int maxIdle, int maxTotal, int minIdle,
                       int connectionTimeout, int soTimeout, int maxAttempts, long minEvictableIdleTimeMillis,
                       long timeBetweenEvictionRunsMillis, String evictionPolicyClassName, String serializer) {
        try {
            if (cluster == null || cluster.trim().length() == 0) {
                throw new JedisClusterException("cluster str is null.");
            }

            if (prefix == null || prefix.trim().length() == 0) {
                throw new JedisClusterException("prefix is null.");
            }
            this.cluster = cluster;

            String[] hostAndPort = cluster.split(",");
            Set<HostAndPort> jedisClusterNodesSet = new HashSet<HostAndPort>();
            for (String hp : hostAndPort) {
                String[] hap = hp.split(":");
                jedisClusterNodesSet.add(new HostAndPort(hap[0], Integer.parseInt(hap[1])));
            }
            GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
            genericObjectPoolConfig.setMaxIdle(maxIdle);
            genericObjectPoolConfig.setMaxTotal(maxTotal);
            genericObjectPoolConfig.setMinIdle(minIdle);
            genericObjectPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
            genericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
            genericObjectPoolConfig.setEvictionPolicyClassName(evictionPolicyClassName);
            jc = new JedisCluster(jedisClusterNodesSet, connectionTimeout, soTimeout, maxAttempts, password, genericObjectPoolConfig);
            this.prefix = SerializationUtils.encode(prefix);
            this.serializer = SerializerFactory.create(serializer);
        } catch (Exception e) {
            logger.error("redis client init error", e);
        }
    }

    @Override
    public String set(final String key, int expTime, final Object value) {
        return jc.setex(getKey(key), expTime, serializer.serialize(value));
    }

    @Override
    public String setnxxx(final String key, final Object value, final String nxxx, final long time) {
        return jc.set(getKey(key), serializer.serialize(value), nxxx.getBytes(), "EX".getBytes(), time);
    }

    @Override
    public <T> T getSet(final String key, final Object value, Class<T> javaType) {
        byte[] data = jc.getSet(getKey(key), serializer.serialize(value));
        return serializer.deserialize(data, javaType);
    }

    @Override
    public boolean set(final String key, final Object value, final String nxxx, final String expx, final long time) {
        return jc.set(getKey(key), serializer.serialize(value), nxxx.getBytes(), expx.getBytes(), time) != null;
    }

    @Override
    public <T> T get(final String key, Class<T> javaType) {
        return serializer.deserialize(jc.get(getKey(key)), javaType);
    }

    /**
     * 当 SerializerType 不等于 DEFAULT_SERIALIZER 时, 使用该方法会抛出异常
     */
    @Override
    public <T> T get(final String key, TypeReference<T> tr) {
        byte[] value = jc.get(getKey(key));
        if (serializer instanceof Jackson2JsonSerializer) {
            return ((Jackson2JsonSerializer) serializer).deserialize(value, tr);
        } else {
            // This method does not belong to this type of serialization
            throw new SerializationException("serializer type error");
        }
    }

    @Override
    public long delete(final String key) {
        return jc.del(getKey(key));
    }

    private byte[] getKey(String key) {
        byte[] rawKey = SerializationUtils.encode(key);
        if (!this.hasPrefix()) {
            return rawKey;
        }

        byte[] prefixedKey = Arrays.copyOf(this.prefix, this.prefix.length + rawKey.length);
        System.arraycopy(rawKey, 0, prefixedKey, this.prefix.length, rawKey.length);
        return prefixedKey;
    }

    /**
     * getKey 的逆操作 获取原始 key 值
     */
    private String getOriginalKey(byte[] key) {
        if (!this.hasPrefix()) {
            return new String(key);
        }

        int keyLen = key.length - this.prefix.length;
        byte[] originalKey = new byte[keyLen];
        System.arraycopy(key, this.prefix.length, originalKey, 0, keyLen);
        return new String(originalKey);
    }

    @Override
    public byte[] hget(final String key, final String field) {
        return jc.hget(getKey(key), getKey(field));
    }

    @Override
    public long hincrBy(final String key, final String field, final long value) {
        return jc.hincrBy(getKey(key), getKey(field), value);
    }

    @Override
    public long incrBy(final String key, final long delta) {
        return jc.incrBy(getKey(key), delta);
    }

    @Override
    public long expire(final String key, final int seconds) {
        return jc.expire(getKey(key), seconds);
    }

    @Override
    public long rpush(final String key, final Object value) {
        return jc.rpush(getKey(key), serializer.serialize(value));
    }

    @Override
    public <T> T lpop(final String key, Class<T> javaType) {
        return serializer.deserialize(jc.lpop(getKey(key)), javaType);
    }

    @Override
    public <T> T blpop(final String key, final int timeout, Class<T> javaType) {
        List<byte[]> list = jc.blpop(timeout, getKey(key));
        if (list == null || list.isEmpty()) {
            return null;
        }

        return serializer.deserialize(list.get(1), javaType);
    }

    @Override
    public Long lpush(final String key, Object value) {
        return jc.lpush(getKey(key), serializer.serialize(value));
    }

    @Override
    public <T> List<T> lrange(final String key, int start, int end, Class<T> javaType) {
        List<byte[]> list = jc.lrange(getKey(key), start, end);
        List<T> res = new ArrayList<>(list.size());
        for (byte[] data : list) {
            res.add(serializer.deserialize(data, javaType));
        }

        return res;
    }

    @Override
    public Long sadd4Sets(final String key, final Object... values) {
        if (values == null) {
            return -1L;
        }

        byte[][] valBytes = new byte[values.length][];
        for (int i = 0; i < values.length; i++) {
            valBytes[i] = serializer.serialize(values[i]);
        }

        return jc.sadd(getKey(key), valBytes);
    }

    @Override
    public <T> Set<T> smembers4Sets(final String key, Class<T> javaType) {
        Set<byte[]> resultSet = jc.smembers(getKey(key));
        Set<T> result = new HashSet<T>();
        if (resultSet == null) {
            return result;
        } else {
            for (byte[] b : resultSet) {
                result.add(serializer.deserialize(b, javaType));
            }
        }

        return result;
    }

    @Override
    public Long srem4Sets(final String key, final Object... values) {
        if (values == null) {
            return -1L;
        }

        byte[][] valBytes = new byte[values.length][];
        for (int i = 0; i < values.length; i++) {
            valBytes[i] = serializer.serialize(values[i]);
        }

        return jc.srem(getKey(key), valBytes);
    }

    private <T extends Collection<?>> T deserializeValues(Collection<byte[]> rawValues, Class<T> type) {
        if (rawValues == null) {
            return null;
        }

        Object values = List.class.isAssignableFrom(type) ? new ArrayList(rawValues.size()) : new LinkedHashSet(rawValues.size());
        Iterator i$ = rawValues.iterator();

        while (i$.hasNext()) {
            byte[] bs = (byte[]) i$.next();
            ((Collection) values).add(serializer.deserialize(bs, Object.class));
        }

        return (T) values;
    }

    private boolean hasPrefix() {
        return this.prefix != null && this.prefix.length > 0;
    }

    @Override
    public Boolean exists(String key) {
        return jc.exists(getKey(key));
    }

    public void shutdown() {
        try {
            jc.close();
        } catch (IOException e) {
            logger.error("jedis pool shutdown error = {}", e);
        }
    }

    @Override
    public long hdel(String key) {
        return jc.hlen(getKey(key));
    }

    @Override
    public long hdel(String key, String... field) {
        if (field.length > 0) {
            byte[][] fieldArray = new byte[field.length][];
            for (int i = 0; i < field.length; i++) {
                fieldArray[i] = getKey(field[i]);
            }
            return jc.hdel(getKey(key), fieldArray);
        } else {
            throw new SerializationException("ERR wrong number of arguments for 'hdel' command");
        }
    }

    /**
     * 返回哈希表 key 中，所有的域和值
     */
    @Override
    public Map<String, Object> hgetAll(String key) {
        return hgetAll(key, Object.class);
    }

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中
     */
    @Override
    public String hmset(String key, Map<String, Object> map) {
        return jc.hmset(getKey(key), serializerMap(map));
    }

    /**
     * 返回哈希表 key 中所有域的值
     */
    @Override
    public <T> List<T> hvals(String key) {
        Collection<byte[]> hvals = jc.hvals(getKey(key));
        return (List) deserializeValues(hvals, List.class);
    }

    /**
     * 返回哈希表 key 中的所有域
     */
    @Override
    public Set<String> hkeys(String key) {
        Set<byte[]> hkeys = jc.hkeys(getKey(key));
        Set<String> res = new HashSet<>(hkeys.size());
        for (byte[] k : hkeys) {
            res.add(getOriginalKey(k));
        }

        return res;
    }


    private Map<byte[], byte[]> serializerMap(Map<String, Object> map) {
        if (map == null) {
            return SerializationUtils.EMPTY_MAP;
        }
        Map<byte[], byte[]> res = new HashMap<>(map.size());
        try {
            Set<Map.Entry<String, Object>> entries = map.entrySet();
            for (Map.Entry<String, Object> e : entries) {
                res.put(getKey(e.getKey()), serializer.serialize(e.getValue()));
            }
        } catch (Exception e) {
            throw new SerializationException("Could not encode map: " + e.getMessage(), e);
        }

        return res;
    }

    @Override
    public Long setnx(String key, Object value) {
        return jc.setnx(getKey(key), serializer.serialize(value));
    }

    @Override
    public Long llen(String key) {
        return jc.llen(getKey(key));
    }

    @Override
    public Long lrem(String key, long count, Object value) {
        return jc.lrem(getKey(key), count, serializer.serialize(value));
    }

    @Override
    public Long sadd(String key, Object... values) {
        return sadd4Sets(key, values);
    }

    @Override
    public Long scard(String key) {
        return jc.scard(getKey(key));
    }

    @Override
    public Boolean sismember(String key, Object member) {
        return jc.sismember(getKey(key), serializer.serialize(member));
    }

    @Override
    public <T> Set<T> smembers(String key, Class<T> javaType) {
        return smembers4Sets(key, javaType);
    }

    @Override
    public Long srem(String key, Object... values) {
        return srem4Sets(key, values);
    }

    @Override
    public Long zadd(String key, Object member, double score) {
        return jc.zadd(getKey(key), score, serializer.serialize(member));
    }

    @Override
    public Long zadd(String key, Map<Object, Double> scoreMembers) {
        if (scoreMembers == null) {
            return -1L;
        }
        Map<byte[], Double> sm = new HashMap<>(scoreMembers.size());
        Set<Map.Entry<Object, Double>> entries = scoreMembers.entrySet();
        for (Map.Entry<Object, Double> entry : entries) {
            sm.put(serializer.serialize(entry.getKey()), entry.getValue());
        }

        return jc.zadd(getKey(key), sm);
    }

    @Override
    public Long zcard(String key) {
        return jc.zcard(getKey(key));
    }

    @Override
    public Long zcount(String key, Double min, Double max) {
        return jc.zcount(getKey(key), min, max);
    }

    @Override
    public Long zrem(String key, Object... member) {
        if (member == null) {
            return -1L;
        }
        byte[][] valBytes = new byte[member.length][];
        for (int i = 0; i < member.length; i++) {
            valBytes[i] = serializer.serialize(member[i]);
        }

        return jc.zrem(getKey(key), valBytes);
    }

    @Override
    public Long hset(String key, String field, Object value) {
        return jc.hset(getKey(key), getKey(field), serializer.serialize(value));
    }

    @Override
    public <T> T hget(String key, String field, Class<T> valueType) {
        return serializer.deserialize(this.hget(key, field), valueType);
    }

    @Override
    public <T> Map<String, T> hgetAll(String key, Class<T> valueType) {
        Map<byte[], byte[]> map = jc.hgetAll(getKey(key));
        Map<String, T> res = new HashMap<>(map.size());
        Set<Map.Entry<byte[], byte[]>> entries = map.entrySet();
        for (Map.Entry<byte[], byte[]> e : entries) {
            res.put(getOriginalKey(e.getKey()), serializer.deserialize(e.getValue(), valueType));
        }

        return res;
    }

    @Override
    public Long hlen(String key) {
        return jc.hlen(getKey(key));
    }

    @Override
    public <T> List<T> hmget(String key, Class<T> valueType, String... field) {
        if (field.length > 0) {
            byte[][] fieldArray = new byte[field.length][];
            for (int i = 0; i < field.length; i++) {
                fieldArray[i] = getKey(field[i]);
            }
            List<T> res = new ArrayList<>(field.length);
            List<byte[]> values = jc.hmget(getKey(key), fieldArray);
            for (byte[] v : values) {
                res.add(serializer.deserialize(v, valueType));
            }
            return res;
        } else {
            throw new SerializationException("ERR wrong number of arguments for 'hmget' command");
        }
    }

    @Override
    public Long hsetnx(String key, String field, Object value) {
        return jc.hsetnx(getKey(key), getKey(field), serializer.serialize(value));
    }

    @Override
    public Long expireAt(String key, long timestamp) {
        return jc.expireAt(getKey(key), timestamp);
    }

    @Override
    public Long ttlTime(String key) {
        return jc.ttl(getKey(key));
    }

    @Override
    public <T> List<T> zrange(String key, long start, long end, Class<T> valueType) {
        Set<byte[]> set = jc.zrange(getKey(key), start, end);
        List<T> result = new ArrayList<>(set.size());
        for (byte[] data : set) {
            result.add(serializer.deserialize(data, valueType));
        }

        return result;
    }

    @Override
    public <T> List<T> zrevrange(String key, long start, long end, Class<T> valueType) {
        Set<byte[]> set = jc.zrevrange(getKey(key), start, end);
        List<T> result = new ArrayList<>(set.size());
        for (byte[] data : set) {
            T deserialize = serializer.deserialize(data, valueType);
            result.add(deserialize);
        }

        return result;
    }

    @Override
    public Long zrevrank(String key, Object member) {
        return jc.zrevrank(getKey(key), serializer.serialize(member));
    }

    @Override
    public Long zrank(String key, Object member) {
        return jc.zrank(getKey(key), serializer.serialize(member));
    }

    @Override
    public void publishMsg(String channel, String message) {
        jc.publish(channel, message);
    }

    @Override
    public void subscribeChannel(JedisPubSub jedisPubSub, final String... channel) {
        jc.subscribe(jedisPubSub, channel);
    }
}
