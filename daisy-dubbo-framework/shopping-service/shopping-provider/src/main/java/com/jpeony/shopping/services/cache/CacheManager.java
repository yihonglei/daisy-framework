package com.jpeony.shopping.services.cache;

import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class CacheManager {

    @Autowired
    private RedissonClient redissonClient;

    public String checkCache(String key){
        try {
            RBucket rBucket = redissonClient.getBucket(key);
            return rBucket.get().toString();
        }catch (Exception e){
            return null;
        }
    }

    public void setCache(String key,String val,int expire){
        RBucket rBucket = redissonClient.getBucket(key);
        rBucket.set(val);
        rBucket.expire(expire, TimeUnit.DAYS);
    }

    public void expire(String key,int expire){
        RBucket rBucket = redissonClient.getBucket(key);
        rBucket.expire(expire,TimeUnit.DAYS);
    }
}
