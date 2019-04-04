package com.laibao.concurrency.basic.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 和 synchronized 的区别
 *
 * ReentrantLock 可以进行尝试锁定 tryLock 这样无法锁定、或者在指定时间内无法锁定，线程可以决定是否继续等待
 */
public class ReentrantLockTest2 {

    private final Lock lock = new ReentrantLock();

    public void testMethod1() {
        lock.lock(); // 相当于 synchronized
        try {
            for (int i = 0; i < 20; i++) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(i);
            }
        } finally {
            lock.unlock(); // 使用完毕后，必须手动释放锁
            // 不同于synchronized，抛出异常后，不会自动释放锁，需要我们在finally中释放此锁
        }
    }

    public void testMethod2() {
        // 尝试获取锁，返回true拿到了
        boolean flag = lock.tryLock();
        if (flag) {
            // lock.tryLock(5, TimeUnit.SECONDS) // 等5s内还没拿到就返回false
            System.out.println("testMethod2...");
        } else {
            System.out.println("testMethod2 没拿到锁");
        }
        if (flag) {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest2 reentrantLock = new ReentrantLockTest2();
        new Thread(reentrantLock::testMethod1, "thread_1").start(); // testMethod1 已经执行，被thread_1占有锁this
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(reentrantLock::testMethod2, "thread_2").start(); // 锁已经被其他线程占用，testMethod1执行完毕后才会执行
    }
}
