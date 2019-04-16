package com.laibao.concurrency.count;

import com.laibao.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@ThreadSafe
public class CountExample2 {
    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;


    private static AtomicLong atomicLong = new AtomicLong(0);

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    addLongValue();
                    semaphore.release();
                } catch (Exception ex) {
                    log.error("exception", ex.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        System.out.println();
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", atomicLong.get());
    }

    private static void addLongValue() {
        atomicLong.incrementAndGet();
    }
}
