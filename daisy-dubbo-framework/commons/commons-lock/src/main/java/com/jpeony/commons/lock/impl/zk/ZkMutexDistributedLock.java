package com.jpeony.commons.lock.impl.zk;

import com.jpeony.commons.lock.DistributedLock;
import com.jpeony.commons.lock.DistributedLockException;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.util.concurrent.TimeUnit;

/**
 * zk分布式锁实现, 注意:此锁可以重入，但是重入几次需要释放几次。
 */
public class ZkMutexDistributedLock implements DistributedLock {
    private InterProcessMutex interProcessMutex;
    private int defaultTimeoutSeconds = 30;


    @Override
    public void lock(String key) throws DistributedLockException {
        try {
            interProcessMutex.acquire();
        } catch (Exception e) {
            throw new DistributedLockException("zk加锁异常: ", e);
        }
    }

    @Override
    public boolean tryLock(String lockKey) throws DistributedLockException {
        try {
            init(lockKey);
            return interProcessMutex.acquire(defaultTimeoutSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new DistributedLockException("zk加锁异常: ", e);
        }
    }

    @Override
    public void lock(String lockKey, TimeUnit unit, int timeout) throws DistributedLockException {
        try {
            init(lockKey);
            interProcessMutex.acquire(timeout, unit);
        } catch (Exception e) {
            throw new DistributedLockException("zk加锁异常: ", e);
        }
    }

    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) throws DistributedLockException {
        try {
            init(lockKey);
            return interProcessMutex.acquire(waitTime, unit);
        } catch (Exception e) {
            throw new DistributedLockException("zk加锁异常: ", e);
        }
    }

    @Override
    public void unlock(String lockKey) throws DistributedLockException {
        try {
            init(lockKey);
            interProcessMutex.release();
        } catch (Exception e) {
            throw new DistributedLockException("zk释放锁异常: ", e);
        }
    }

    /**
     * 创建interProcessMutex 客户端
     * 因为此类是每个带 CustomerLock 注解的方法在调用的时候都会创建一个对象，因此不会出现线程安全问题。
     */
    void init(String lockKey) {
        if (interProcessMutex == null) {
            interProcessMutex = ZkMutexDistributedLockFactory.getInterProcessMutex(lockKey);
        }
    }
}
