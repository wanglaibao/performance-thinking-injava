package com.laibao.concurrency.basic.thread;

import java.util.concurrent.TimeUnit;

public class TickTask implements Runnable{

    private volatile int tickNumber = 1000;

    @Override
    public void run() {
        while (true) {
            synchronized (TickTask.class) {
                if (tickNumber > 0) {
                    System.err.println(Thread.currentThread().getName()+"卖出了第 "+tickNumber+" 张电影票!");

                    tickNumber--;
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
