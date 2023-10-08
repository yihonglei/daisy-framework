package com.jpeony.redis.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.jpeony.redis.facade.RedisSupport;
import com.jpeony.redis.utils.SerializationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 缓存工具类
 *
 * @author yihonglei
 */
@Component
public class CacheManager {

    private static final Logger logger = LoggerFactory.getLogger(CacheManager.class);

    private static RedisSupport redisSupport;

    /**
     * 注入 RedisSupport 的实现类 RedisClient Bean
     */
    @Autowired
    public void init(RedisSupport redisSupport) {
        CacheManager.redisSupport = redisSupport;
    }

    /**
     * 设置缓存对象
     *
     * @param key
     * @param value
     * @param expTime
     */
    public static String set(final String key, final int expTime, final Object value) {
        try {
            return redisSupport.set(key, expTime, value);
        } catch (Exception e) {
            logger.error("set Object value cache error:{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 设置缓存对象
     *
     * @param key
     * @param expTime
     * @param value
     */
    public static String set(final String key, final int expTime, final String value) {
        try {
            return redisSupport.set(key, expTime, value);
        } catch (Exception e) {
            logger.error("set String value cache error:{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 存储数据到缓存中，并制定过期时间和当Key存在时是否覆盖
     *
     * @param key
     * @param value
     * @param nxxx  只能取 NX 或者 XX，如果取 NX，则只有当 key 不存在是才进行 set，如果取 XX，则只有当 key 已经存在时才进行 set
     * @param expx  只能取 EX 或者 PX，代表数据过期时间的单位，EX 代表秒，PX 代表毫秒
     * @param time  过期时间，单位是 expx 所代表的单位
     */
    public static boolean set(final String key, final Object value, final String nxxx, final String expx, final long time) {
        try {
            return redisSupport.set(key, value, nxxx, expx, time);
        } catch (Exception e) {
            logger.error("set nxxx value error:{}", e.getMessage(), e);
        }

        return true;
    }

    /**
     * 获取缓存对象，不支持 list，map
     *
     * @param key
     * @param classType
     * @param <T>
     */
    public static <T> T get(String key, Class<T> classType) {
        try {
            return redisSupport.get(key, classType);
        } catch (Exception e) {
            logger.error("get object cache error:{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 获取缓存对象，支持 list，map
     *
     * @param key
     * @param tr
     * @param <T>
     */
    public static <T> T get(final String key, TypeReference<T> tr) {
        try {
            return redisSupport.get(key, tr);
        } catch (Exception e) {
            logger.error("get list cache error:{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 删除缓存对象
     *
     * @param key
     */
    public static long delete(final String key) {
        try {
            return redisSupport.delete(key);
        } catch (Exception e) {
            logger.error("get list cache error:{}", e.getMessage(), e);
        }

        return 0L;
    }

    /**
     * Hash 里面指定字段增加值
     *
     * @param key
     * @param field
     * @param value
     */
    private static long hincrBy(final String key, final String field, final long value) {
        try {
            return redisSupport.hincrBy(key, field, value);
        } catch (Exception e) {
            logger.error("hincrBy cache error:{}", e.getMessage(), e);
        }

        return 0L;
    }

    /**
     * 设置 Hash 里面一个字段的值
     *
     * @param key
     * @param field
     * @param value
     */
    public static Long hset(String key, String field, Object value) {
        try {
            return redisSupport.hset(key, field, value);
        } catch (Exception e) {
            logger.error("hmset cache error:{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 删除一个或多个 Hash 的 field
     *
     * @param key
     * @param field
     */
    public static Long hdel(String key, String... field) {
        try {
            return redisSupport.hdel(key, field);
        } catch (Exception e) {
            logger.error("hdel cache error:{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 从 Hash 里面获取全部的域或值
     *
     * @param key
     * @param valueType
     */
    public static <T> Map<String, T> hgetAll(String key, Class<T> valueType) {
        try {
            return redisSupport.hgetAll(key, valueType);
        } catch (Exception e) {
            logger.error("hgetAll cache error:{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 从 Hash 中获取对应域的值
     *
     * @param key
     * @param field
     */
    private static byte[] hget(final String key, final String field) {
        try {
            return redisSupport.hget(key, field);
        } catch (Exception e) {
            logger.error("hget cache error:{}", e.getMessage(), e);
        }

        return SerializationUtils.EMPTY_ARRAY;
    }

    /**
     * 执行原子操作，增加一个整数
     *
     * @param key
     * @param delta 累加值，非负值
     */
    public static long incrBy(final String key, final long delta) {
        try {
            return redisSupport.incrBy(key, delta);
        } catch (Exception e) {
            logger.error("incrBy cache error:{}", e.getMessage(), e);
        }

        return 0L;
    }

    /**
     * 从队列的右边入队一个元素
     *
     * @param key
     * @param value
     */
    public static long rpush(final String key, final Object value) {
        try {
            return redisSupport.rpush(key, value);
        } catch (Exception e) {
            logger.error("rpush cache error:{}", e.getMessage(), e);
        }

        return 0L;
    }

    /**
     * 从队列的左边出队一个元素
     *
     * @param key
     * @param javaType
     */
    public static <T> T lpop(final String key, Class<T> javaType) {
        try {
            return redisSupport.lpop(key, javaType);
        } catch (Exception e) {
            logger.error("lpop cache error:{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 删除，并获取该列表中的第一个元素，或者阻塞，直到获取到元素
     *
     * @param key
     * @param timeout
     * @param javaType
     */
    public static <T> T blpop(final String key, final int timeout, Class<T> javaType) {
        try {
            return redisSupport.blpop(key, timeout, javaType);
        } catch (Exception e) {
            logger.error("blpop cache error:{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 从列表中获取指定返回的元素
     *
     * @param key
     * @param start
     * @param end
     * @param javaType
     */
    public static <T> List<T> lrange(final String key, int start, int end, Class<T> javaType) {
        try {
            return redisSupport.lrange(key, start, end, javaType);
        } catch (Exception e) {
            logger.error("lrange cache error:{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 添加一个或多个元素到集合(set)里
     *
     * @param key
     * @param values
     */
    public static long sadd4Sets(String key, Object... values) {
        try {
            return redisSupport.sadd4Sets(key, values);
        } catch (Exception e) {
            logger.error("sadd4Sets cache error:{}", e.getMessage(), e);
        }

        return 0L;
    }

    /**
     * 获取集合里面的所有元素
     *
     * @param key
     * @param javaType
     */
    public static <T> Set<T> smembers4Sets(String key, Class<T> javaType) {
        try {
            return redisSupport.smembers4Sets(key, javaType);
        } catch (Exception e) {
            logger.error("smembers4Sets cache error:{}", e.getMessage(), e);
        }

        return Collections.emptySet();
    }

    /**
     * 从集合里删除一个或多个元素
     *
     * @param key
     * @param values
     */
    public static long srem4Sets(String key, Object... values) {
        try {
            return redisSupport.srem4Sets(key, values);
        } catch (Exception e) {
            logger.error("srem4Sets cache error:{}", e.getMessage(), e);
        }

        return 0L;
    }

    /**
     * 设置超时时间
     *
     * @param key
     * @param seconds
     */
    public static long expire(final String key, final int seconds) {
        try {
            return redisSupport.expire(key, seconds);
        } catch (Exception e) {
            logger.error("expire cache error:{}", e.getMessage(), e);
        }

        return 0L;
    }

    /**
     * 判断 key 是否存在 redis
     *
     * @param key
     */
    public static Boolean exists(final String key) {
        try {
            return redisSupport.exists(key);
        } catch (Exception e) {
            logger.error("exists cache error:{}", e.getMessage(), e);
        }

        return false;
    }

    /**
     * 获取队列(List)的长度
     *
     * @param key
     */
    public static Long llen(final String key) {
        try {
            return redisSupport.llen(key);
        } catch (Exception e) {
            logger.error("llen cache error:{}", e.getMessage(), e);
        }

        return 0L;
    }

    /**
     * 设置一个 key 的值，并返回设置前的值
     *
     * @param key
     * @param value
     * @param javaType
     */
    public static <T> T getSet(String key, Object value, Class<T> javaType) {
        try {
            return redisSupport.getSet(key, value, javaType);
        } catch (Exception e) {
            logger.error("set Object value cache error:{}", e.getMessage(), e);
        }

        return null;
    }

    /**
     * 从列表中删除元素
     *
     * @param key
     * @param count
     * @param value
     */
    public static Long lrem(String key, int count, Object value) {
        try {
            return redisSupport.lrem(key, count, value);
        } catch (Exception e) {
            logger.error("lrem value cache error:{}", e.getMessage(), e);
        }

        return 0L;
    }

}
