package com.mmall.concurrency.test;

import java.util.concurrent.Callable;

public class MyCallable implements Callable {
    @Override
    public Object call() throws Exception {
        String value = "test";
        System.out.println("ready to work");
        Thread.sleep(5000);
        System.out.println("task done");
        return value;
    }
}
