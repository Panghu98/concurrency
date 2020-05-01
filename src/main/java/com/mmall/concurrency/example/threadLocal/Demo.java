package com.mmall.concurrency.example.threadLocal;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 16:22 2020/4/12
 * @Modified By:
 */
public class Demo {

    private static ThreadLocal<String> stringThreadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Runnable runnable = () -> {
            System.out.println("线程名称：" + Thread.currentThread().getName());
            stringThreadLocal.set(Thread.currentThread().getName());
            System.out.println("ThreadLocal中存放的值是 = " + stringThreadLocal.get());
        };
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.execute(runnable);
        executorService.shutdownNow();
    }

}
