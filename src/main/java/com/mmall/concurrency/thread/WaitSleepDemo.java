package com.mmall.concurrency.thread;

public class WaitSleepDemo {

    public static void main(String[] args) {
        final Object lock = new Object();
        new Thread(() -> {
            try {
                System.out.println("Thread A is waiting to get lock");
                synchronized (lock){
                    System.out.println("Thread A get lock");
                    Thread.sleep(20);
                    System.out.println("Thread A do wait method ");
                    //线程A释放锁
                    lock.wait();
                    System.out.println("Thread A is done ---");
                }
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
        }).start();


        //设置成2000,线程A的wait操作释放锁之后,主线程等待,并没有进入线程B,这个时候还是线程A先完成工作
        try{
            Thread.sleep(20);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        new Thread(() -> {
            try {
                System.out.println("Thread B is waiting to get lock");
                synchronized (lock){
                    System.out.println("Thread B get lock");
                    Thread.sleep(20);
                    lock.notifyAll();
                    //唤醒锁池中的所有线程,注意,是针对于对象,而不是线程
                    Thread.yield();
                    Thread.sleep(2000);
                    System.out.println("Thread B is done ---");
                }
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
        }).start();
    }
}
