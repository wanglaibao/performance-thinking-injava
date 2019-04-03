package com.laibao.concurrency.basic.synchronizedkeyword;

/**
 * synchronized 关键字
 * 对this加锁
 * 如果每次使用锁都要new Object(),比较麻烦,我们可以使用this代替object锁
 */
public class SynchronizedTest1 {

    private int count = 100;

    public void testMethod() {
        synchronized (this) { //
            /**
             * 任何线程要执行下面的代码，必须先拿到this锁
             * synchronized 锁定的不是代码块，而是 this 对象
             */
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

    public static void main(String[] args) {
        SynchronizedTest1 test1 = new SynchronizedTest1();
        for (int i = 0;i < 100; i++) {
            new Thread(() -> {
                test1.testMethod();
            }).start();
        }
    }
}
