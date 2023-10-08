package com.mhjy.util;

import com.google.common.cache.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
public class YYGCacheManager {

    /** 缓存项最大数量 */
    private static final long GUAVA_CACHE_SIZE = 100000;

    /** 缓存时间：分钟 */
    private static final long GUAVA_CACHE_DAY = 120;

    /** 缓存操作对象 */
    private static LoadingCache<Long, String> GLOBAL_CACHE = null;

    static {
        try {
            GLOBAL_CACHE = loadCache(new CacheLoader<Long, String>() {
                @Override
                public String load(Long key) throws Exception {
                    // 处理缓存键不存在缓存值时的处理逻辑
                    return "";
                }
            });
        } catch (Exception e) {
            log.error("初始化Guava Cache出错", e);
        }
    }

    /**
     * 全局缓存设置
     *
     * 缓存项最大数量：100000
     * 缓存有效时间（天）：10
     *
     *
     * @param cacheLoader
     * @return
     * @throws Exception
     */
    private static LoadingCache<Long, String> loadCache(CacheLoader<Long, String> cacheLoader) throws Exception {
        LoadingCache<Long, String> cache = CacheBuilder.newBuilder()
                //缓存池大小，在缓存项接近该大小时， Guava开始回收旧的缓存项
                .maximumSize(GUAVA_CACHE_SIZE)
                //设置时间对象没有被读/写访问则对象从内存中删除(在另外的线程里面不定期维护)
                .expireAfterAccess(GUAVA_CACHE_DAY, TimeUnit.MINUTES)
                // 设置缓存在写入之后 设定时间 后失效
                .expireAfterWrite(GUAVA_CACHE_DAY, TimeUnit.MINUTES)
                //移除监听器,缓存项被移除时会触发
                .removalListener(new RemovalListener<Long, String>() {
                    @Override
                    public void onRemoval(RemovalNotification<Long, String> rn) {
                        //逻辑操作
                    }
                })
                //开启Guava Cache的统计功能
                .recordStats()
                .build(cacheLoader);
        return cache;
    }

    /**
     * 设置缓存值
     * 注: 若已有该key值，则会先移除(会触发removalListener移除监听器)，再添加
     *
     * @param key
     * @param value
     */
    public static void put(Long key, String value) {
        try {
            GLOBAL_CACHE.put(key, value);
        } catch (Exception e) {
            log.error("设置缓存值出错", e);
        }
    }

    /**
     * 批量设置缓存值
     *
     * @param map
     */
    public static void putAll(Map<? extends Long, ? extends String> map) {
        try {
            GLOBAL_CACHE.putAll(map);
        } catch (Exception e) {
            log.error("批量设置缓存值出错", e);
        }
    }

    /**
     * 获取缓存值
     * 注：如果键不存在值，将调用CacheLoader的load方法加载新值到该键中
     *
     * @param key
     * @return
     */
    public static String get(Long key) {
        String token = "";
        try {
            token = GLOBAL_CACHE.get(key);
        } catch (Exception e) {
            log.error("获取缓存值出错", e);
        }
        return token;
    }

    /**
     * 移除缓存
     *
     * @param key
     */
    public static void remove(Long key) {
        try {
            GLOBAL_CACHE.invalidate(key);
        } catch (Exception e) {
            log.error("移除缓存出错", e);
        }
    }

    /**
     * 批量移除缓存
     *
     * @param keys
     */
    public static void removeAll(Iterable<Long> keys) {
        try {
            GLOBAL_CACHE.invalidateAll(keys);
        } catch (Exception e) {
            log.error("批量移除缓存出错", e);
        }
    }

    /**
     * 清空所有缓存
     */
    public static void removeAll() {
        try {
            GLOBAL_CACHE.invalidateAll();
        } catch (Exception e) {
            log.error("清空所有缓存出错", e);
        }
    }

    /**
     * 获取缓存项数量
     *
     * @return
     */
    public static long size() {
        long size = 0;
        try {
            size = GLOBAL_CACHE.size();
        } catch (Exception e) {
            log.error("获取缓存项数量出错", e);
        }
        return size;
    }
}