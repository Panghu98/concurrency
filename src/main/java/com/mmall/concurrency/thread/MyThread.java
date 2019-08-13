package com.mmall.concurrency.thread;

public class MyThread extends Thread {

    private String name;

    public MyThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("Thread start : "+ this.name + " i = "+i);
        }
    }
}
