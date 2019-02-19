package com.mmall.concurrency.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author panghu
 * @Title: AtomicExample5
 * @ProjectName cuncurrency_demo
 * @Description: TODO
 * @date 19-2-16 上午10:22
 */
@Slf4j
public class AtomicExample5 {
    /**
     * AtomicIntegerFieldUpdater这个类用于原子性的修改
     */
    private static AtomicIntegerFieldUpdater<AtomicExample5> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicExample5.class,"count");

    /**
     * 必须适应volatile修饰，并且还不能是static
     */
    @Getter
    public volatile int count = 100;

    public static void main(String[] args) {

        AtomicExample5 example5 = new AtomicExample5();

         if (updater.compareAndSet(example5,100,120)){
             log.info("update success 1,{}",example5.getCount());
        }
        if(updater.compareAndSet(example5,100,120)){
            log.info("update success 1,{}",example5.getCount());
        }else{
            log.info("update success failed,{}",example5.getCount());

        }
    }

}
