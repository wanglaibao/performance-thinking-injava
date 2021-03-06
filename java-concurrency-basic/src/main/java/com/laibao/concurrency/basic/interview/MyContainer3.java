package com.laibao.concurrency.basic.interview;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 面试题（淘宝？）
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到达5时，线程2给出提示并结束
 */
public class MyContainer3 {

    private List<Object> list = new ArrayList<>();

    public void add(Object element) {
        list.add(element);
    }

    public int size() {
        return list.size();
    }

    public static void main(String[] args) {
        MyContainer3 container = new MyContainer3();

        final Object lock = new Object();

        // 先启动thread_2线程，让thread_2线程进入等待状态
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("thread_2 启动");
                if (container.size() != 5) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("监测到容器长度为5，线程2立即退出");
                lock.notify();
            }
        }, "thread_2").start();


        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i < 10; i++) {
                    container.add(new Object());
                    System.out.println("add " + i);
                    // 当长度为5时，通知 thread_2 进行退出
                    if (container.size() == 5) {
                        lock.notify(); // notify 不会释放锁，即便通知thread_2，thread_2也获取不到锁
                        // 可以再wait一下，将锁释放，再让thread_2通知thread_1继续执行
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "thread_1").start();

    }
}

/*
    使用wait和notify
    wait()与notify() 方法的调用必须在同步代码块中
    wait会释放锁，notify不会释放锁
    锁定对象a，调用a.wait() 方法，当前线程就会进入等待状态，然后释放锁。
    当某线程调用 a.notify() / a.notifyAll()， 叫醒在a对象等待的所有线程
 */