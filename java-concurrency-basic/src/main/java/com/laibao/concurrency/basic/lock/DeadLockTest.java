package com.laibao.concurrency.basic.lock;

import java.util.concurrent.TimeUnit;

/**
 * 死锁示例
 */
public class DeadLockTest {

    private Object lock1 = new Object();

    private  Object lock2 = new Object();

    public void testMethod1() {
        synchronized (lock1) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.err.println(Thread.currentThread().getName()+" : testMethod1");
            }
        }
    }

    public void testMethod2() {
        synchronized (lock2) {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock1) {
                System.err.println(Thread.currentThread().getName()+" : testMethod2");
            }
        }
    }

    public static void main(String[] args) {
        DeadLockTest test = new DeadLockTest();
        new Thread(() -> {
            test.testMethod1();
        },"thread_1").start();

        new Thread(() -> {
            test.testMethod2();
        },"thread_2").start();

    }
}
