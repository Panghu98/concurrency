package com.mmall.concurrency.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo2 implements Runnable{

    private static int a = 0;

    private static synchronized void plus(){
            a++;
            System.out.println("线程"+Thread.currentThread().getName()+"执行,当前a的值为"+a);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            plus();
        }
    }

    public static void main(String[] args) {
        VolatileDemo2 volatileDemo = new VolatileDemo2();
        Thread thread1 = new Thread(volatileDemo);
        Thread thread2 = new Thread(volatileDemo);
        Thread thread3 = new Thread(volatileDemo);
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
