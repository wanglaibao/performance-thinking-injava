package com.laibao.concurrency.basic.lock;

import java.util.concurrent.TimeUnit;

/**
 * ReentrantLock可以用于替代synchronized
 *
 * 本例中由于testMethod1锁定this，只有testMethod1执行完毕的时候，testMethod2才能执行
 *
 * 这里复习synchronized最原始的定义
 */
public class ReentrantLockTest0 {

    public synchronized void testMethod1() {
        System.out.println(Thread.currentThread().getName() + " : testMethod1 ...");
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }

    public synchronized void testMethod2() {
        System.out.println(Thread.currentThread().getName() + " : testMethod2...");
    }

    public static void main(String[] args) {
        ReentrantLockTest0 reentrantLock0 = new ReentrantLockTest0();

        //testMethod1 已经执行，被thread_1占有锁this
        new Thread(reentrantLock0::testMethod1, "thread_1").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 锁已经被其他线程占用，testMethod1执行完毕后，才会执行
        new Thread(reentrantLock0::testMethod2, "thread_2").start();
    }
}
