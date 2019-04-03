package com.laibao.concurrency.basic.synchronizedkeyword;

import java.util.concurrent.TimeUnit;

/**
 * 不要以字符串常量作为锁定对象
 *
 * 在下面的例子中， testMethod1和testMethod12其实是锁定的同一对象
 *
 * 这种情况下，还会可能与其他类库发生死锁，比如某类库中也锁定了字符串 "Hello"
 *
 * 但是无法确认源码的具体位置，所以两个 "Hello" 将会造成死锁
 *
 * 因为你的程序和你用的类库无意间使用了同意把锁
 */
public class SynchronizedTest12 {

    private String s1 = "Hello";

    private String s2 = "Hello";

    public void testMethod1() {
        synchronized (s1) {
            System.err.println(Thread.currentThread().getName()+": testMethod1 在运行啦");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName()+": testMethod1 运行结束啦");
        }
    }

    public void testMethod2() {
        synchronized (s2) {
            System.err.println(Thread.currentThread().getName()+": testMethod2 在运行啦");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName()+": testMethod2 运行结束啦");
        }
    }


    public static void main(String[] args) {
        SynchronizedTest12 test = new SynchronizedTest12();
        new Thread(() -> test.testMethod1()).start();
        new Thread(() -> test.testMethod2()).start();
        System.err.println(Thread.currentThread().getName() + "结束啦");
    }
}
