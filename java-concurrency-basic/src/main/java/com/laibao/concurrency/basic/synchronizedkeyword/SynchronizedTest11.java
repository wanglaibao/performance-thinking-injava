package com.laibao.concurrency.basic.synchronizedkeyword;

import java.util.concurrent.TimeUnit;

/**
 * 锁定某个对象lock，如果lock属性发生变化，不影响锁的使用
 * 但是如果lock指向了另一个对象，则锁定的对象发生变化，
 * 所以锁对象通常要设置为 final类型，保证引用不可以变
 */
public class SynchronizedTest11 {

    private Object lock = new Object();

    public void testMethod() {
        synchronized (lock) {
            while (true) {
                System.out.println(Thread.currentThread().getName());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedTest11 test = new SynchronizedTest11();
        new Thread(test::testMethod, "线程1").start();
        try {
            TimeUnit.SECONDS.sleep(30);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread2 = new Thread(test::testMethod, "线程2");
        // 改变锁引用, 线程2也有机会运行，否则一直都是线程1 运行
        test.lock = new Object();
        thread2.start();
    }
}
