package com.mmall.concurrency.thread;

/**
 * @author panghu
 */
public class VolatileFeaturesExample {

     long v1 =0L;

    /**
     * 对单个的普通变量的写用同一个锁同步
     * @param l
     */
    public synchronized void set(long l){
        v1 = l;
    }

    public void getAndIncrement(){
        long temp = get();
        temp += 1L;
        set(temp);
    }

    /**
     * 对单个的普通变量的读用同一个锁同步
     * @return
     */
    public synchronized long get(){
        return v1;
    }



}
