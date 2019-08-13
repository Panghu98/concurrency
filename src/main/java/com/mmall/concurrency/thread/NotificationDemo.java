package com.mmall.concurrency.thread;

public class NotificationDemo {

    private volatile boolean go = false;

    public static void main(String[] args) throws InterruptedException {
        final NotificationDemo test = new NotificationDemo();

        Runnable waitTask = new Runnable() {
            @Override
            public void run() {
                try{
                    test.shouldGo();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " finished Execution");
            }
        };
        Runnable notifyTask = new Runnable() {
            @Override
            public void run() {
                test.go();
                System.out.println(Thread.currentThread().getName() + " finished Execution");
            }
        };
        Thread t1 = new Thread(waitTask, "WT1");
        Thread t2 = new Thread(waitTask, "WT2");
        Thread t3 = new Thread(waitTask, "WT3");
        Thread t4 = new Thread(notifyTask,"NT1");

        //starting all waiting thread
        t1.start();
        t2.start();
        t3.start();

        //pause to ensure all waiting thread started successfully
        Thread.sleep(200);

        //starting notifying thread
        t4.start();
    }

    /**
     * wait and notify can only be called from synchronized method or bock
     */
    private synchronized void shouldGo() throws InterruptedException{
        //为false进入等待状态
        while (!go){
            System.out.println(Thread.currentThread() + " is going to wait on this object");
            //release lock and reacquires on wakeup
            wait();
            System.out.println(Thread.currentThread()+" is woken up");
        }
        //resetting condition
        go = false;
        //notifyAll加上这里go=true的话,所有的都会执行finished Execution,将while改成if也是同样的效果
    }

    /**
     * both shouldGo() and go() are locked on current object referenced by "this" keyword
     */
    private synchronized void go(){
        while (!go){
            System.out.println(Thread.currentThread() + " is going to notify one or all thread waiting on this object");
            //making condition true for waiting thread
            go = true;
            //notify(); // 三个等待池中的线程会随机唤醒一个
            // 所有等待线程都会被唤醒,但是因为shouldGo方法加锁和内部的训话结构,只有一个能够finished Execution
            notifyAll();
        }
    }

}
