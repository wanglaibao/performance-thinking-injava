package com.laibao.concurrency.basic.synchronizedkeyword;

import java.util.concurrent.TimeUnit;

/**
 * 对业务写方法加锁，而对业务读方法不加锁，
 * 容易出现 脏读问题
 * <p>
 * 因为，在执行写的过程中，因为读操作没有加锁，所以读会读取到写未改完的脏数据
 * 解决办法，给读写都加锁
 */
public class SynchronizedAccount {

    /**
     * 银行账户名称
     */
    private String name;

    /**
     * 银行账余额
     */
    private double balance;


    public synchronized void set(String name, double balance) {
        this.name = name;
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.balance = balance;
    }

    public synchronized double getBalance() {
        return this.balance;
    }

    public static void main(String[] args) {
        SynchronizedAccount account = new SynchronizedAccount();

        new Thread(() -> account.set("金戈铁马", 100.0)).start();
        System.out.println(account.getBalance()); // 0.0
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(account.getBalance()); // 100.0
    }
}
