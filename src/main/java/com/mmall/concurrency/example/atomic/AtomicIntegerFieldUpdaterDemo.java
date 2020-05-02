package com.mmall.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 9:05 2020/5/2
 * @Modified By:
 */
public class AtomicIntegerFieldUpdaterDemo implements Runnable{

    static Candidate tom;

    static Candidate peter;

    public static AtomicIntegerFieldUpdater<Candidate> scoreUpdater =
            AtomicIntegerFieldUpdater.newUpdater(Candidate.class,"score");

    @Override
    public void run() {
        for (int i = 0; i < 10000; i++) {
            peter.score++;
            scoreUpdater.getAndIncrement(tom);
        }
    }

    public static class Candidate{
        // updater要求进行升级的类型必须是使用volatile修饰的
        volatile int score;
    }


    public static void main(String[] args) throws InterruptedException {
        tom=new Candidate() ;
        peter=new Candidate( ) ;
        AtomicIntegerFieldUpdaterDemo r = new
                AtomicIntegerFieldUpdaterDemo() ;
        Thread t1 = new Thread(r) ;
        Thread t2 = new Thread(r) ;
        t1.start( ) ;
        t2.start( ) ;
        t1.join();
        t2.join();
        System.out.println("普通的变量：" + peter.score);
        tom.score++;
        // 使用tom.score和scoreUpdater.get(tom)结果一致
        System.out.println("升级的变量：" + tom.score + " - " +scoreUpdater.get(tom));
    }
}
