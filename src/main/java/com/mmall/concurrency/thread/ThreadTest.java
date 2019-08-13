package com.mmall.concurrency.thread;

public class ThreadTest
{
    private static void attack(){
        System.out.println("Fight");
        System.out.println("Current Thread is : "+ Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> attack());

        System.err.println("Current main thread is :" + Thread.currentThread().getName());
        t.start();
        t.join();
        t.start();
    }
}
