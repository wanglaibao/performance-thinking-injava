package com.laibao.concurrency.basic.synchronizedkeyword;

/**
 * synchronized 关键字
 *
 * 分析下面程序的输出
 */
public class SynchronizedTest4 implements Runnable{


    private int count = 10;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        SynchronizedTest4 test4 = new SynchronizedTest4();
        for (int i = 0; i < 5; i++) {
            new Thread(test4).start();
        }
    }
}


/*
    某次运行结果：
    Thread-0 count = 7
    Thread-4 count = 5
    Thread-3 count = 6
    Thread-2 count = 7
    Thread-1 count = 7
    线程重入的问题（线程执行过程中，被其他线程打断），
    因为 count-- ; System.out.println(Thread.currentThread().getName() + " count = " + count); 不是原子操作
    解决办法，保证操作原子性，加上 synchronized 关键字
 */