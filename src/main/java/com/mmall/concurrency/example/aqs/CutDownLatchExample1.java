package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author panghu
 * @Title: CutDownLatchExample1
 * @ProjectName example
 * @Description: TODO
 * @date 19-2-26 上午9:42
 */
@Slf4j
public class CutDownLatchExample1 {

    private static int threadCount = 200;

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (Exception e) {
                    log.error("Exception:{}", e);
                } finally {
                    //根据需求放在不同的地方
                    countDownLatch.countDown();
                }
            });
        }
        //await()保证线程执行完
        countDownLatch.await();
        log.error("===============finish=======================");
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNum);
        Thread.sleep(100);
    }

}
