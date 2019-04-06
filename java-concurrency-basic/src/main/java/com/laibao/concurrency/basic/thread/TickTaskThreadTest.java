package com.laibao.concurrency.basic.thread;

public class TickTaskThreadTest {

    public static void main(String[] args) {
        TickTask tickTask = new TickTask();

        new Thread(() -> {
                tickTask.run();
            },"thread_1").start();
        new Thread(() -> {
            tickTask.run();
        },"thread_2").start();


        new Thread(() -> {
            tickTask.run();
        },"thread_3").start();

        new Thread(() -> {
            tickTask.run();
        },"thread_4").start();

        new Thread(() -> {
            tickTask.run();
        },"thread_5").start();

    }
}
