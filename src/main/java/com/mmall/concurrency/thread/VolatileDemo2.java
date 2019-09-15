package com.mmall.concurrency.thread;

public class VolatileDemo2 implements Runnable{

    private static int a = 0;

    private static synchronized void plus(){
            a++;
    }

    @Override
    public void run() {
        for (int i = 0; i < 500; i++) {
            plus();
        }
    }

    public static void main(String[] args) {
        VolatileDemo2 volatileDemo = new VolatileDemo2();
        Thread thread1 = new Thread(volatileDemo);
        Thread thread2 = new Thread(volatileDemo);
        Thread thread3 = new Thread(volatileDemo);
        Thread thread4 = new Thread(volatileDemo);
        Thread thread5 = new Thread(volatileDemo);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        System.out.println("线程"+Thread.currentThread().getName()+"执行,当前a的值为"+a);
        System.err.println(a);
        System.out.println("线程"+Thread.currentThread().getName()+"执行,当前a的值为"+a);

    }

}
