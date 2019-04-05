package com.laibao.concurrency.basic.thread;

public class DoubleClickSingleTon {

    private static volatile DoubleClickSingleTon instance;

    private DoubleClickSingleTon() {}

    public DoubleClickSingleTon getInstance() {
        if (instance == null) {
            synchronized (DoubleClickSingleTon.class) {
                if (instance == null) {
                    instance = new DoubleClickSingleTon();
                }
            }
        }
        return instance;
    }
}
