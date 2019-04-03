package com.laibao.concurrency.basic.volatilekeyword;

import java.util.concurrent.TimeUnit;

/**
 * volatile 关键字，使一个变量在多个线程间可见
 *
 * JMM(Java Memory Model)：
 * 在JMM中，所有对象以及信息都存放在主内存中（包含堆、栈）
 * 而每个线程都有自己的独立空间，存储了需要用到的变量的副本，
 * 线程对共享变量的操作，都会在自己的工作内存中进行，然后同步给主内存
 *
 * 下面的代码中，running 是位于堆内存中的t对象的
 * 当线程thread_1开始运行的时候，会把running值从内存中读到thread_1线程的工作区，在运行过程中直接使用这个copy，并不会每次都会去读取堆内存，
 * 这样，当主线程修改running的值之后，thread_1线程感知不到，所以不会停止运行
 *
 * 使用volatile，将会强制所有线程都去堆内存中读取running的值
 *
 *
 */
public class VolatileTest0 {

    // 对比有无volatile的情况下，整个程序运行结果的区别
    private volatile boolean running = true;

    public void testMethod() {
        System.out.println("testMethod begin");

        while (running) { // 直到主线程将running设置为false，并且加上了volatile关键字的情况下,T线程才会退出
            // 在while中加入一些语句，可见性问题可能就会消失，这是因为加入语句后，CPU可能就会出现空闲，然后就会同步主内存中的内容到工作内存
            // 所以，可见性问题可能会消失
            try {
                System.out.println(Thread.currentThread().getName() + " testMethod begin");
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("testMethod end ");
    }

    public static void main(String[] args) {
        VolatileTest0 test0 = new VolatileTest0();
        new Thread(test0::testMethod, "thread_1").start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        test0.running = false;
        System.err.println(Thread.currentThread().getName() + " is end");
    }
}
