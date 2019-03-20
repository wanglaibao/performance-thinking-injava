package com.laibao.concurrency;

import lombok.extern.slf4j.Slf4j;
import net.jcip.annotations.NotThreadSafe;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * 对于下面的例子，我们可以看到，每次输出的结果都是小于5000，并且值不是固定的，
 * 也就是发生了所谓的线程安全问题，如果信号量semaphore 修改为1，
 * 则每次只产生一个线程，不会发生线程安全问题
 */
@Slf4j
@NotThreadSafe
public class MapExample {

    // 请求总数
    public static int clientTotal = 5000;

    // 同时并发执行的线程数
    public static int threadTotal = 200;

    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for (int i = 0; i < clientTotal; i++) {
            final int count = i;
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    func(count);
                    semaphore.release();
                } catch (Exception ex) {
                    log.error("exception", ex.getMessage());
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("size:{}", map.size());
    }

    private static void func(int threadNum) {
        map.put(threadNum, threadNum);
    }
}
