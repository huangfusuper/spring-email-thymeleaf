package com.byit.springemailthymeleaf.utils;

import com.byit.springemailthymeleaf.components.DistributedLocker;

import java.util.concurrent.TimeUnit;

/**
 * @author huangfu
 */
public class RedisLockUtil {
    private static DistributedLocker distributedLocker;

    public static void setDistributedLocker(DistributedLocker distributedLocker) {
        RedisLockUtil.distributedLocker = distributedLocker;
    }

    public static boolean tryLock(String lockKey) {
        return RedisLockUtil.distributedLocker.tryLock(lockKey);
    }

    public static void unlock(String lockKey) {
        RedisLockUtil.distributedLocker.unlock(lockKey);
    }

    public static boolean tryLock(String lockKey, int timeout) {
        return RedisLockUtil.distributedLocker.tryLock(lockKey,timeout);
    }

    public static boolean tryLock(String lockKey, TimeUnit unit, int timeout) {
        return RedisLockUtil.distributedLocker.tryLock(lockKey,unit,timeout);
    }
}
