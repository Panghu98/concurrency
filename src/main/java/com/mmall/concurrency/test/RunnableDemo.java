package com.mmall.concurrency.test;

public class RunnableDemo {

    public static void main(String[] args) {
        MyRunnable runnable1 = new MyRunnable("Runnable1");
        MyRunnable runnable2 = new MyRunnable("Runnable2");
        MyRunnable runnable3 = new MyRunnable("Runnable3");


        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);
        Thread thread3 = new Thread(runnable3);
        thread1.start();
        thread2.start();
        thread3.start();

    }

}
