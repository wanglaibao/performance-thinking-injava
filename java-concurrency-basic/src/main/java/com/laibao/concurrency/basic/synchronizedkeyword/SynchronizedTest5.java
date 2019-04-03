package com.laibao.concurrency.basic.synchronizedkeyword;

import java.util.concurrent.TimeUnit;

/**
 * 同步方法和非同步方法是否可以同时调用？
 * 答案是肯定可以的
 */
public class SynchronizedTest5 {

    public synchronized void testMethod1() {
        System.out.println(Thread.currentThread().getName() + " testMethod1 begin");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " testMethod1 end");
    }

    public void testMethod2() {
        System.out.println(Thread.currentThread().getName() + " testMethod2 begin");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " testMethod2 end");
    }

    public static void main(String[] args) {
        SynchronizedTest5 test5 = new SynchronizedTest5();
        new Thread(test5::testMethod1).start();
        new Thread(test5::testMethod2).start();
    }
}
