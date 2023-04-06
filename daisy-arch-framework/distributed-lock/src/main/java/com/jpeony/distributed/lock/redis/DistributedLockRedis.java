package com.jpeony.distributed.lock.redis;

import com.jpeony.distributed.lock.DistributedLock;
import com.jpeony.distributed.lock.exception.DistributedLockException;
import com.jpeony.distributed.lock.utils.SpringBeanUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author yihonglei
 */
public class DistributedLockRedis implements DistributedLock {

    private RedissonClient redissonClient;

    public DistributedLockRedis() {
        this.redissonClient = SpringBeanUtils.getBean(RedissonClient.class);
    }

    /**
     * 默认超时时间 30s
     */
    @Override
    public void lock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.lock();
    }

    /**
     * 未获取到锁直接返回 false
     */
    @Override
    public boolean tryLock(String key) {
        RLock lock = redissonClient.getLock(key);
        return lock.tryLock();
    }

    @Override
    public void lock(String lockKey, TimeUnit unit, int leaseTime) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.lock(leaseTime, unit);
    }

    /**
     * 尝试获取锁(会转变为同步锁)
     *
     * @param lockKey   锁定 key
     * @param unit      时间单位
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     */
    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) throws DistributedLockException {
        RLock lock = redissonClient.getLock(lockKey);
        try {
            return lock.tryLock(waitTime, leaseTime, unit);
        } catch (Exception e) {
            throw new DistributedLockException("redis加锁异常: ", e);
        }
    }

    /**
     * 释放锁
     */
    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        if (lock != null && lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }

}
