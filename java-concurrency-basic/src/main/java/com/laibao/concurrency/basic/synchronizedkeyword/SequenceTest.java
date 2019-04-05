package com.laibao.concurrency.basic.synchronizedkeyword;

import java.util.concurrent.atomic.AtomicInteger;

public class SequenceTest {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public int getNextValue() {
        return atomicInteger.getAndIncrement();
    }

    public static void main(String[] args) {
        SequenceTest sequenceTest = new SequenceTest();

        for (int i=0;i <= 1000;i++) {
            new Thread(() -> {
                System.err.println(sequenceTest.getNextValue());
            },"thread_"+i).start();
        }
    }

}
