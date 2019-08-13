package com.mmall.concurrency.thread;

public class ThreadDemo {

    public static void main(String[] args) {
        MyThread thread1 = new MyThread("Thread1");
        MyThread thread2 = new MyThread("Thread2");
        MyThread thread3 = new MyThread("Thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }

}
