package com.byit.springemailthymeleaf.components;

import java.util.concurrent.TimeUnit;

/**
 * redis分布式锁使用帮助器
 * @author huangfu
 */
public interface DistributedLocker {
    /**
     * 加锁
     * @param lockKey
     * @return 是否加锁成功
     */
    boolean tryLock(String lockKey);

    /**
     * 解锁
     * @param lockKey
     * @return 是否加锁成功
     */
    void unlock(String lockKey);

    /**
     * 带超时时间的加锁
     * @param lockKey
     * @param timeout
     * @return 是否加锁成功
     */
    boolean tryLock(String lockKey, int timeout);

    /**
     * 带单位的超时时间的加锁
     * @param lockKey
     * @param unit
     * @param timeout
     * @return 是否加锁成功
     */
    boolean tryLock(String lockKey, TimeUnit unit , int timeout);
}
