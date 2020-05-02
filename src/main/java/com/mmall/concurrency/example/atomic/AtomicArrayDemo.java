package com.mmall.concurrency.example.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 22:21 2020/5/1
 * @Modified By:
 */
public class AtomicArrayDemo {

    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);
        Incrementer incrementer = new Incrementer(atomicIntegerArray) ;
        Decrementer decrementer = new Decrementer(atomicIntegerArray) ;

        Thread[] threadsIncrementer = new Thread[100];
        Thread[] threadsDecrementer = new Thread[100];
        for (int i = 0; i < 100; i++) {
            threadsDecrementer[i] = new Thread(decrementer);
            threadsIncrementer[i] = new Thread(incrementer);
            threadsDecrementer[i].start();
            threadsIncrementer[i].start();

        }

        for(int i=0;i<100;i++){
            try {
                threadsDecrementer[i].join() ;
                threadsIncrementer[i].join() ;
            } catch (InterruptedException  e) {
                e. printStackTrace() ;
            }
        }

        //正常情况下应该计算结果都是0 ，加了100次，减了100次
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            if (atomicIntegerArray.get(i) != 0) {
                System.err.println("发现错误，其数组下标为 " + i);
            }
        }

        System.out.println("运行结束");

    }
}

class Decrementer implements Runnable{

    private AtomicIntegerArray array;

    public Decrementer(AtomicIntegerArray atomicIntegerArray) {
        this.array = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndDecrement(i);
        }
    }
}

class Incrementer implements Runnable{

    private AtomicIntegerArray array;

    public Incrementer(AtomicIntegerArray atomicIntegerArray) {
        this.array = atomicIntegerArray;
    }

    @Override
    public void run() {
        for (int i = 0; i < array.length(); i++) {
            array.getAndIncrement(i);
        }
    }
}
