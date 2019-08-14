package com.mmall.concurrency.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo implements Runnable{

    private static  AtomicInteger a = new AtomicInteger(0);

    private void plus(){
        for (int i = 0; i < 5; i++) {
            a.incrementAndGet();
        }
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("线程"+Thread.currentThread().getName()+"执行,当前a的值为"+a.incrementAndGet());
        }
    }

    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
        Thread thread1 = new Thread(volatileDemo);
        Thread thread2 = new Thread(volatileDemo);
        Thread thread3 = new Thread(volatileDemo);
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
