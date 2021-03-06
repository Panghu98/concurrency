package com.mmall.concurrency.thread;

import org.joda.time.DateTime;

import java.sql.Date;

public class CycleWait implements Runnable{

    private String value;

    @Override
    public void run() {
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        value = "We have data now";
    }

    public static void main(String[] args) throws InterruptedException {


        CycleWait cycleWait = new CycleWait();
        Thread t = new Thread(cycleWait);
        t.start();
        System.out.println("Value : " + cycleWait.value);
        //主线程的等待方
        while (cycleWait.value == null){
            Thread.sleep(100);
        }
        System.out.println("Value : " + cycleWait.value);
    }
}
