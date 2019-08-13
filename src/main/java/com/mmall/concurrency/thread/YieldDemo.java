package com.mmall.concurrency.thread;

public class YieldDemo {

    public static void main(String[] args) {

        Runnable yieldTask = () -> {
            for (int i = 0; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                if (i == 5){
                    Thread.yield();
                }
            }
        };

        Thread thread1 = new Thread(yieldTask,"A");
        Thread thread2 = new Thread(yieldTask,"B");
        thread1.start();
        thread2.start();
    }

}
