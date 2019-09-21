package com.mmall.concurrency.example.aqs;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest3 {

    static CyclicBarrier barrier = new CyclicBarrier(2);

    public static void main(String[] args) throws Exception{
        Thread thread = new Thread(() -> {
            try {
                barrier.await();
            } catch (Exception e) {
            }
        });
        thread.start();
        thread.interrupt();
        try {
            barrier.await();
        } catch (Exception e) {
            System.out.println(barrier.isBroken());
        }
    }

}
