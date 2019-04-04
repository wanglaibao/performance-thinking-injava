package com.laibao.concurrency.basic.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock替代synchronized
 */

public class ReentrantLockTest1 {

    private final Lock lock = new ReentrantLock();

    public void testMethod1() {
        System.out.println(Thread.currentThread().getName() + " : testMethod1 ...");
        try{
            lock.lock(); // 相当于 synchronized
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(i);
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }finally {
            lock.unlock(); // 使用完毕后，必须手动释放锁
            // 不同于synchronized，抛出异常后，不会自动释放锁，需要我们在finally中释放此锁
        }
    }

    public void testMethod2() {
        try {
            lock.lock();// 相当于 synchronized
            for (int i = 0;i < 100;i++) {
                System.out.println(Thread.currentThread().getName() + " : testMethod2...");
            }
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantLockTest1 reentrantLock1 = new ReentrantLockTest1();

        //testMethod1 已经执行，被thread_1占有锁this
        new Thread(reentrantLock1::testMethod1, "thread_1").start();

//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        // 锁已经被其他线程占用，testMethod1执行完毕后，才会执行
        new Thread(reentrantLock1::testMethod2, "thread_2").start();
    }
}
