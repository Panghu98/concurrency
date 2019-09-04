package com.mmall.concurrency.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class VolatileDemo3 implements Runnable{

    private volatile static int a = 0;

    private void plus(){
        for (int i = 0; i < 100; i++) {
            a++;
            System.out.println(Thread.currentThread()+" "+a);
        }
    }

    @Override
    public void run() {
        plus();
    }

    public static void main(String[] args) {
        VolatileDemo3 volatileDemo = new VolatileDemo3();
        Thread thread1 = new Thread(volatileDemo);
        Thread thread2 = new Thread(volatileDemo);
        Thread thread3 = new Thread(volatileDemo);
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
