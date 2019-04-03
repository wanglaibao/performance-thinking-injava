package com.laibao.concurrency.basic.synchronizedkeyword;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 是可重入锁
 *
 * 子类调用父类的同步方法，是否也是可重入的？
 *
 * 答案是可以重入的
 */
public class SynchronizedTest7 {

    protected synchronized void testMethod1() {
        System.out.println("testMethod begin");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("testMethod end");
    }

    public static void main(String[] args) {
        SynchronizedTest7 test7 = new SynchronizedTest8();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                test7.testMethod1();
            }).start();
        }
    }

}

class SynchronizedTest8 extends SynchronizedTest7 {

    public synchronized void testMethod2() {
        System.out.println("child testMethod2 begin");
        System.out.println("child testMethod2 begin");
        super.testMethod1();
        System.out.println("child testMethod2 end");
        System.out.println("child testMethod2 end");
    }

}


