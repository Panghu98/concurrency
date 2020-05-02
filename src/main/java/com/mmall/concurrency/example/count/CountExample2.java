package com.mmall.concurrency.example.count;

import com.mmall.concurrency.annoations.NotThreadSafe;
import com.mmall.concurrency.annoations.ThreadSafe;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author panghu
 * @Title: CountExample2
 * @Description: TODO
 * @date 19-2-16 上午9:06
 */
@Slf4j
@ThreadSafe
public class CountExample2 {

    /**
     * 请求总数
     */
    public static int clientTotal = 5000;

    /**
     * 同事并发执行的线程数
     */
    public static int threadTotal = 200;

    /**
     * 计数值
     */
    private static final AtomicInteger atomicCount = new AtomicInteger(0);

    private static Integer count = 0;



    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            // 原子类++操作
            atomicCount.incrementAndGet();
            count++;
        };

        ExecutorService executorService = Executors.newFixedThreadPool(threadTotal);
        for (int i = 0; i < clientTotal; i++) {
            executorService.execute(runnable);
        }

        // 使得主线程在累加完之后查看累加结果，也可以用CountDownLatch
        Thread.sleep(1000);
        System.out.println("原子类多线程累加的结果 " + atomicCount.get());
        System.out.println("普通变量多线程累加的结果 " + count);

    }
}
