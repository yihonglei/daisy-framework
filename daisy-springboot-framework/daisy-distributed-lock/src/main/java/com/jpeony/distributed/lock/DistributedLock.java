package com.jpeony.distributed.lock;

import com.jpeony.distributed.lock.exception.DistributedLockException;

import java.util.concurrent.TimeUnit;

public interface DistributedLock {

    void lock(String key) throws DistributedLockException;

    boolean tryLock(String key) throws DistributedLockException;

    /**
     * @param lockKey 锁定 key
     * @param unit    时间单位
     * @param timeout 超时时间
     */
    void lock(String lockKey, TimeUnit unit, int timeout) throws DistributedLockException;

    /**
     * 尝试获取锁
     *
     * @param lockKey   锁定 key
     * @param unit      时间单位
     * @param waitTime  最多等待时间
     * @param leaseTime 上锁后自动释放锁时间
     */
    boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) throws DistributedLockException;

    /**
     * 释放锁
     */
    void unlock(String lockKey) throws DistributedLockException;
}
