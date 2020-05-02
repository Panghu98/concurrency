package com.mmall.concurrency.example.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 9:45 2020/5/2
 * @Modified By:
 */
public class LongAdderDemo {

    public static void main(String[] args) {
        LongAdder counter = new LongAdder () ;
        ExecutorService service = Executors
                . newFixedThreadPool (20) ;
        Long start = System.currentTimeMillis();
        for(int i=0;i<10000;i++){
            service.submit(new Task(counter));
        }
        service.shutdown();
        while (!service.isTerminated()){

        }
        Long end = System.currentTimeMillis();
        System.out.println(counter.sum());
        System.out.println("LongAdder耗时：" + (end - start));
    }

    private static class Task implements Runnable{

        private LongAdder counter;

        public Task(LongAdder counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                counter.increment();
            }
        }
    }
}
