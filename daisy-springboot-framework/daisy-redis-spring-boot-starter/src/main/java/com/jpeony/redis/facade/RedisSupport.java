package com.jpeony.redis.facade;


import com.fasterxml.jackson.core.type.TypeReference;
import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author yihonglei
 */
public interface RedisSupport {

    /**
     * 设置缓存对象
     *
     * @param key
     * @param value
     * @param expTime
     * @return
     */
    String set(final String key, final int expTime, final Object value);


    /**
     * 设置缓存对象
     *
     * @param key
     * @param value
     * @param nxxx
     * @param time
     */
    boolean set(final String key, final Object value, final String nxxx, final String expx, final long time);

    /**
     * 将key设置值为value，如果key不存在，这种情况下等同SET命令。
     * 当key存在时，什么也不做。
     *
     * @param key
     * @param value
     * @return 1 如果key被设置了
     * 0 如果key没有被设置
     */
    Long setnx(final String key, final Object value);

    /**
     * 获取缓存对象，不支持list，map
     *
     * @param key
     * @param classType
     * @param <T>
     * @return
     */
    <T> T get(String key, Class<T> classType);

    /**
     * 获取缓存对象，支持list，map
     *
     * @param key
     * @param tr
     * @param <T>
     * @return
     */
    <T> T get(final String key, TypeReference<T> tr);

    /**
     * 删除缓存对象
     *
     * @param key
     * @return
     */
    long delete(final String key);

    long hincrBy(final String key, final String field, final long value);

    byte[] hget(final String key, final String field);

    /**
     * @param key
     * @param delta 累加值，非负值
     * @return
     */
    long incrBy(final String key, final long delta);

    long rpush(final String key, final Object value);

    <T> T lpop(final String key, Class<T> javaType);

    <T> T blpop(final String key, final int timeout, Class<T> javaType);

    /**
     * 返回存储在 key 的列表里指定范围内的元素。
     */
    <T> List<T> lrange(final String key, int start, int end, Class<T> javaType);

    long expire(final String key, final int seconds);

    Boolean exists(final String key);

    /**
     * 删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     */
    long hdel(String key, String... field);

    long hdel(String key);

    /**
     * 返回哈希表 key 中，所有的域和值
     */
    Map<String, Object> hgetAll(String key);

    /**
     * 同时将多个 field-value (域-值)对设置到哈希表 key 中
     */
    String hmset(String key, Map<String, Object> map);

    /**
     * 返回哈希表 key 中所有域的值
     */
    <T> List<T> hvals(String key);

    /**
     * 返回哈希表 key 中的所有域
     */
    Set<String> hkeys(String key);

    /**
     * 与smembers()一致
     *
     * @param key
     * @param javaType
     * @param <T>
     * @return
     */
    <T> Set<T> smembers4Sets(final String key, Class<T> javaType);

    <T> T getSet(final String key, final Object value, Class<T> javaType);

    /**
     * 和 sadd效果一样
     *
     * @param key
     * @param values
     * @return
     */
    Long sadd4Sets(final String key, final Object... values);

    Long srem4Sets(final String key, final Object... values);

    /**
     * 放入数据
     *
     * @param key
     * @param value
     * @return
     */
    Long lpush(final String key, final Object value);

    /**
     * 返回存储在 key 里的list的长度。
     * 如果 key 不存在，那么就被看作是空list，并且返回长度为 0。
     *
     * @param key
     * @return
     */
    Long llen(String key);

    /**
     * 从存于 key 的列表里移除前 count 次出现的值为 value 的元素。
     * 如果list里没有存在key就会被当作空list处理，所以当 key 不存在的时候，这个命令会返回 0。
     *
     * @param key
     * @param count
     * @param value
     * @return
     */
    Long lrem(String key, long count, Object value);

    /**
     * 将一个或多个 member 元素加入到集合 key 当中，已经存在于集合的 member 元素将被忽略。
     * 假如 key 不存在，则创建一个只包含 member 元素作成员的集合。
     * 当 key 不是集合类型时，返回一个错误。
     */
    Long sadd(String key, Object... values);

    /**
     * 集合的基数(元素的数量),如果key不存在,则返回 0.
     *
     * @param key
     * @return
     */
    Long scard(String key);

    /**
     * 返回成员 member 是否是存储的集合 key的成员.
     *
     * @param key
     * @param member
     * @return
     */
    Boolean sismember(String key, Object member);

    /**
     * 返回集合 key 中的所有成员。不存在的 key 被视为空集合。
     *
     * @param key
     * @param valueType
     * @param <T>
     * @return
     */
    <T> Set<T> smembers(String key, Class<T> valueType);

    /**
     * 移除集合 key 中的一个或多个 member 元素，不存在的 member 元素会被忽略。
     *
     * @param key
     * @param values
     * @return
     */
    Long srem(String key, Object... values);

    /**
     * 将一个member 元素及其 score 值加入到有序集 key 当中。
     *
     * @param key
     * @param member
     * @param score
     * @return
     */
    Long zadd(String key, Object member, double score);

    /**
     * 将一个或多个 member 元素及其 score 值加入到有序集 key 当中。
     *
     * @param key
     * @param scoreMembers
     * @return
     */
    Long zadd(String key, Map<Object, Double> scoreMembers);

    /**
     * 返回key的有序集元素个数。
     *
     * @param key
     * @return
     */
    Long zcard(String key);

    /**
     * 返回有序集key中，score值在min和max之间(默认包括score值等于min或max)的成员。
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    Long zcount(String key, Double min, Double max);

    /**
     * 移除有序集 key 中的一个或多个成员，不存在的成员将被忽略。
     *
     * @param key
     * @param member
     * @return
     */
    Long zrem(String key, Object... member);

    /**
     * 设置 key 指定的哈希集中指定字段的值。
     * 如果 key 指定的哈希集不存在，会创建一个新的哈希集并与 key 关联。
     * 如果字段在哈希集中存在，它将被重写。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hset(String key, String field, Object value);

    /**
     * 返回哈希表 key 中给定域 field 的值。
     *
     * @param key
     * @param field
     * @param valueType
     * @param <T>
     * @return
     */
    <T> T hget(String key, String field, Class<T> valueType);

    /**
     * 返回 key 指定的哈希集中所有的字段和值。
     *
     * @param key
     * @param valueType
     * @param <T>
     * @return
     */
    <T> Map<String, T> hgetAll(String key, Class<T> valueType);

    String setnxxx(final String key, final Object value, final String nxxx, final long time);

    /**
     * 返回 key 指定的哈希集包含的字段的数量。
     *
     * @param key
     * @return
     */
    Long hlen(String key);

    /**
     * 返回哈希表 key 中，一个或多个给定域的值。
     *
     * @param key
     * @param valueType 值的类型
     * @param field
     * @param <T>
     * @return
     */
    <T> List<T> hmget(String key, Class<T> valueType, String... field);

    /**
     * 将哈希表 key 中的域 field 的值设置为 value ，当且仅当域 field 不存在。若域 field 已经存在，该操作无效。
     * 设置成功，返回 1 。
     * 如果给定域已经存在且没有操作被执行，返回 0 。
     *
     * @param key
     * @param field
     * @param value
     * @return
     */
    Long hsetnx(String key, String field, Object value);

    /**
     * 设置key的过期时间
     *
     * @param key
     * @param timestamp UNIX时间戳(unix timestamp)。
     * @return
     */
    Long expireAt(String key, long timestamp);


    /**
     * 获取Key的剩余生存时间
     *
     * @param key
     * @return
     */
    Long ttlTime(String key);

    /**
     * 返回有序集 key 中，指定区间内的成员。
     * 其中成员的位置按 score 值递增(从小到大)来排序。
     *
     * @param key
     * @param start
     * @param end
     * @param valueType
     * @param <T>
     * @return
     */
    <T> List<T> zrange(String key, long start, long end, Class<T> valueType);

    /**
     * 返回有序集key中，指定区间内的成员。
     * 其中成员的位置按score值递减(从大到小)来排列。
     * 具有相同score值的成员按字典序的反序排列。
     *
     * @param key
     * @param start
     * @param end
     * @param valueType
     * @param <T>
     * @return
     */
    <T> List<T> zrevrange(String key, long start, long end, Class<T> valueType);

    /**
     * 返回有序集key中成员member的排名，
     * 其中有序集成员按score值从大到小排列。排名以0为底。
     *
     * @param key
     * @param member
     * @return
     */
    Long zrevrank(String key, Object member);

    /**
     * 返回有序集key中成员member的排名。
     * 其中有序集成员按score值递增(从小到大)顺序排列。排名以0为底。
     *
     * @param key
     * @param member
     * @return
     */
    Long zrank(String key, Object member);

    void publishMsg(String channel, String message);

    void subscribeChannel(JedisPubSub jedisPubSub, final String... channel);
}
