package com.laibao.concurrency.basic.synchronizedkeyword;

/**
 * synchronized 关键字
 *
 * synchronized 静态方法
 *
 * 锁定静态方法，其实锁定的是 java.lang.Class 对象。
 */
public class SynchronizedTest3 {

    private static int count = 100;

    public static synchronized void testMethod1() { // 等同于 synchronized (SynchronizedTest3.class) {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void testMethod2() {
        //思考一下 这里可以使用 synchronized (this) 么?为什么?
        synchronized (SynchronizedTest3.class) {
            count--;
        }
    }

    public static void main(String[] args) {
        SynchronizedTest3 test3 = new SynchronizedTest3();
        for (int i = 0;i < 100; i++) {
            new Thread(() -> {
                test3.testMethod1();
            }).start();
        }
    }
}
