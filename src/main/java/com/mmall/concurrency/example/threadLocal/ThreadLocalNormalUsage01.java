package com.mmall.concurrency.example.threadLocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 9:05 2020/5/1
 * @Modified By:
 */
public class ThreadLocalNormalUsage01 {

    public static ExecutorService executorService =
            Executors.newFixedThreadPool(10);

    public static void main(String[] args) throws InterruptedException {
        ThreadLocalNormalUsage01 threadLocalNormalUsage00 = new ThreadLocalNormalUsage01();

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(threadLocalNormalUsage00.date(finalI));
                }
            });
        }
        executorService.shutdown();

    }


    public String date(int seconds) {
        // 参数是从1970.1.1 00:00开始
        Date date = new Date(1000*seconds);
        SimpleDateFormat simpleDateFormat = threadLocal.get();
        System.err.println(simpleDateFormat);
        return simpleDateFormat.format(date);
    }

    public static ThreadLocal<SimpleDateFormat> threadLocal = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"));

}
