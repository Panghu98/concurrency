package com.mmall.concurrency.example.aqs;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest {
    static CyclicBarrier c = new CyclicBarrier(2,new A());
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("1到达了");
                    c.await();
                } catch (Exception e) {
                }
                System.out.println(1);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("2到达了");
                    c.await();
                } catch (Exception e) {
                }
                System.out.println(2);
            }
        }).start();


        try {
            System.out.println("3到达了");
            c.await();
        } catch (Exception e) {

        }
        System.out.println(3);
    }

    static class A implements Runnable{

        @Override
        public void run() {
            System.err.println("run-------");
        }
    }
}

