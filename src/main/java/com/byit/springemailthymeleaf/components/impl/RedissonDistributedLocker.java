package com.byit.springemailthymeleaf.components.impl;

import com.byit.springemailthymeleaf.components.DistributedLocker;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import java.util.concurrent.TimeUnit;

/**
 * @author huangfu
 */
public class RedissonDistributedLocker implements DistributedLocker {

    private RedissonClient redissonClient;

    public RedissonDistributedLocker(RedissonClient redissonClient) {
        this.redissonClient = redissonClient;
    }

    @Override
    public boolean tryLock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        return lock.tryLock();
    }

    @Override
    public void unlock(String lockKey) {
        RLock lock = redissonClient.getLock(lockKey);
        lock.unlock();
    }

    @Override
    public boolean tryLock(String lockKey, int timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        boolean lockFlag = false;
        try {
            lockFlag = lock.tryLock(timeout, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        return lockFlag;
    }

    @Override
    public boolean tryLock(String lockKey, TimeUnit unit, int timeout) {
        RLock lock = redissonClient.getLock(lockKey);
        boolean lockFlag = false;
        try {
            lockFlag = lock.tryLock(timeout, unit);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        return lockFlag;
    }
}
