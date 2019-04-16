package com.laibao.concurrency;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Slf4j
public class ConcurrencyTest {

    /**
     * 并发执行线程数
     */
    private static int threadNumTotal = 200;

    /**
     * 请求总数
     */
    private static int clientNum = 5000;

    private static int count = 0;

    private static void add() {
        count++;
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(threadNumTotal);

        final CountDownLatch countDownLatch = new CountDownLatch(clientNum);

        for (int i = 0; i < clientNum; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    add();
                    semaphore.release();
                } catch (Exception e) {
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }

}
