package com.lf.common.util;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liufan
 * @description: 线程锁
 * @since 2024/02/22
 */
public class LockUtil {
    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final Condition CONDITION = LOCK.newCondition();

    public static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(1);

    public static void lock() {
        LOCK.lock();
    }

    public static void unlock() {
        LOCK.unlock();
    }

    public static Condition getCondition() {
        return CONDITION;
    }

    public static void await() throws InterruptedException {
        CONDITION.await();
    }

    public static void signal() {
        CONDITION.signal();
    }

    public static void signalAll() {
        CONDITION.signalAll();
    }
}
