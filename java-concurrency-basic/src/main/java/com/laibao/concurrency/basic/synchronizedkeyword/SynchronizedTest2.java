package com.laibao.concurrency.basic.synchronizedkeyword;

/**
 * synchronized 关键字
 *
 * synchronized 成员方法【实例方法】
 */
public class SynchronizedTest2 {

    private int count = 100;

    /**
     *  等同于 synchronized (this)
     */
    public synchronized void testMethod() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        SynchronizedTest2 test2 = new SynchronizedTest2();
        for (int i = 0;i < 100; i++) {
            new Thread(() -> {
                test2.testMethod();
            }).start();
        }
    }
}
