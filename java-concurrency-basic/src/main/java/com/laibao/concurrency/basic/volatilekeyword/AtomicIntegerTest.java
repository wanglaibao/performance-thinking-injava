package com.laibao.concurrency.basic.volatilekeyword;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {

    AtomicInteger count = new AtomicInteger(0);

    public void testMethod() {
        for (int i = 0; i < 10000; i++) {
            count.incrementAndGet();//具有原子性
        }
    }

    public static void main(String[] args) {
        // 创建一个10个线程的list，执行任务皆是 m方法
        AtomicIntegerTest test = new AtomicIntegerTest();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(test::testMethod, "thread -" + i));
        }

        // 启动这10个线程
        threads.forEach(Thread::start);

        // join 到主线程，防止主线程先行结束
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 10个线程，每个线程执行10000次，结果应为 100000
        // 所得结果为 100000，说明AtomicInteger具备原子性
        System.out.println(test.count);
    }
}
