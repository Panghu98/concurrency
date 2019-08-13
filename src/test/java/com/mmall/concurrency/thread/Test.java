package com.mmall.concurrency.thread;

/**
 * @author panghu
 * @title: Test
 * @projectName example
 * @date 19-7-8 下午9:55
 */
public class Test {

    private static String A = "A";
    private static String B = "B";

    public static void main(String[] args) {
        deadlock();
    }

    private static void deadlock(){
        Thread thread1 = new Thread(() -> {
            synchronized (A){
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    System.err.println("出错了");
                }
                synchronized (B){
                    System.out.println("线程1运行");
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (B){
                synchronized (A){
                    System.out.println("线程2运行");
                }
            }
        });

        thread1.start();
        thread2.start();
    }

}
