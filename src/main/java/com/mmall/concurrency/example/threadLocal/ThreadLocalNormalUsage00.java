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
public class ThreadLocalNormalUsage00 {

    public static ExecutorService executorService =
            Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        ThreadLocalNormalUsage00 threadLocalNormalUsage00 = new ThreadLocalNormalUsage00();

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

//    static SimpleDateFormat format = new SimpleDateFormat(
//            "yyyy-MM-dd hh:mm:ss");

    SimpleDateFormat format = new SimpleDateFormat(
            "yyyy-MM-dd hh:mm:ss");

    public String date(int seconds) {
        // 参数是从1970.1.1 00:00开始
        Date date = new Date(1000*seconds);
        return format.format(date);
    }

}
