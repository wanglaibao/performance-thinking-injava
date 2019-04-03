package com.laibao.concurrency.basic.synchronizedkeyword;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 是可重入锁
 *
 * 即一个同步方法可以调用另外一个同步方法，一个线程已经拥有某个对象的锁，再次申请时仍然会得到该对象的锁
 */
public class SynchronizedTest6 {

    public synchronized void testMethod1() {
        System.out.println(Thread.currentThread().getName()+ " testMethod1 start ");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testMethod2();
    }

    public synchronized void testMethod2() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 这句话会打印，调用testMethod2时，不会发生死锁
        System.out.println(Thread.currentThread().getName()+ " testMethod2");
    }

    public static void main(String[] args) {
        SynchronizedTest6 test6 = new SynchronizedTest6();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                test6.testMethod1();
            }).start();
        }
    }
}
