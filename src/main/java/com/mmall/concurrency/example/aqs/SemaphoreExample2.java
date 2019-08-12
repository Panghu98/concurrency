package com.mmall.concurrency.example.aqs;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author panghu
 * @Title: CutDownLatchExample1
 * @ProjectName example
 * @Description: TODO
 * @date 19-2-26 上午9:42
 */
@Slf4j
public class SemaphoreExample2 {

    private static int threadCount = 20;

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final Semaphore semaphore = new Semaphore(3);


        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    //获得多个许可
                    semaphore.acquire(3);
                    test(threadNum);
                    //释放多个许可
                    semaphore.release(3);
                } catch (Exception e) {
                    log.error("Exception:{}", e);
                }
            });
        }
        //await()保证线程执行完
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        log.info("{}", threadNum);
        Thread.sleep(1000);
    }

}
