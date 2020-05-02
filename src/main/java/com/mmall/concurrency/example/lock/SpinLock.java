package com.mmall.concurrency.example.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 20:03 2020/5/1
 * @Modified By:
 */
public class SpinLock {

    AtomicReference<Thread> sign = new AtomicReference<>();

    public void lock() {
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null,current)){
//            System.out.println("自旋获取失败，再次获取");
        }
    }

    public void unlock() {
        Thread current = Thread.currentThread();
        sign.compareAndSet(current,null);
    }

    public static void main(String[] args) throws InterruptedException {
        SpinLock lock = new SpinLock();
        Runnable runnable = new Runnable(){

            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "开始尝试获取自旋锁");
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到了自旋锁。。。");
                try {
                    Thread.sleep(1000);
                }catch (Exception e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                    System.err.println(Thread.currentThread().getName() + "释放锁");
                }
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }
}


// 有时候的结果是这样的，很奇怪
//Thread-0开始尝试获取自旋锁
//Thread-0获取到了自旋锁。。。
//Thread-1开始尝试获取自旋锁
//Thread-1获取到了自旋锁。。。
//Thread-0释放锁
//Thread-1释放锁
