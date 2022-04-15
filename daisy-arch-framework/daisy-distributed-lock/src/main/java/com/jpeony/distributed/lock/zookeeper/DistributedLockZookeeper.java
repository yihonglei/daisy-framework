package com.jpeony.distributed.lock.zookeeper;

import com.jpeony.distributed.lock.DistributedLock;
import com.jpeony.distributed.lock.exception.DistributedLockException;

import java.util.concurrent.TimeUnit;

/**
 * @author yihonglei
 */
public class DistributedLockZookeeper implements DistributedLock {

    @Override
    public void lock(String key) throws DistributedLockException {

    }

    @Override
    public boolean tryLock(String key) throws DistributedLockException {

        return false;
    }

    @Override
    public void lock(String lockKey, TimeUnit unit, int timeout) throws DistributedLockException {

    }

    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, int waitTime, int leaseTime) throws DistributedLockException {

        return false;
    }

    @Override
    public void unlock(String lockKey) throws DistributedLockException {

    }

}
