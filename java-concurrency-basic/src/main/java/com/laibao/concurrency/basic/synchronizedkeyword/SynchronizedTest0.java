package com.laibao.concurrency.basic.synchronizedkeyword;

/**
 * synchronized 关键字
 * 这个锁是 互斥锁
 * 对某个对象加锁
 */
public class SynchronizedTest0 {

    private int count = 100;

    private final Object lock = new Object();

    public void testMethod() {
        synchronized (lock) {
            /**
             * 任何线程要执行下面的代码，都必须先拿到lock锁，锁信息记录在堆内存对象中的，不是在栈引用中
             * 如果lock已经被锁定，其他线程再进入时，就会进行阻塞等待
             * 所以 synchronized 是互斥锁
             */
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
        // 当代码块执行完毕后，锁就会被释放，然后被其他线程获取
    }

    public static void main(String[] args) {
        SynchronizedTest0 test0 = new SynchronizedTest0();
        for (int i = 0;i < 100; i++) {
            new Thread(() -> {
                test0.testMethod();
            }).start();
        }
    }
}
