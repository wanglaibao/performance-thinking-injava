package com.laibao.concurrency.basic.thread;

import java.util.concurrent.TimeUnit;

public class ThreadJoinTest {

    public static void main(String[] args) throws Exception {
        System.err.println(Thread.currentThread().getName()+" 开始执行了啦");

        Thread thread = new Thread(() -> {
            System.err.println(Thread.currentThread().getName()+" 开始执行了啦");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.err.println(Thread.currentThread().getName()+" 执行结束了啦");
        },"thread_123");
        thread.start();
        thread.join();
        System.err.println(Thread.currentThread().getName()+" 执行结束了啦");
    }
}
