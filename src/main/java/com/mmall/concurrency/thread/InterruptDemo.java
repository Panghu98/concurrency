package com.mmall.concurrency.thread;

public class InterruptDemo {
    public static void main(String[] args) throws InterruptedException {
        Runnable interruptTask = new Runnable() {
            int i = 0;
            @Override
            public void run() {
                try {
                    //在正常运行任务时,经常进行本线程的中断标志,如果被设置了终端标志就自行停止线程
                    while (!Thread.currentThread().isInterrupted()){
                        //休眠100ms
                        Thread.sleep(100);
                        i++;
                        System.out.println(Thread.currentThread().getName() + " (" + Thread.currentThread().getState() + ") loop " + i);
                    }
                }catch (InterruptedException e){
                    //在调用阻塞方法时正确处理InterruptedException异常。（例如，catch异常后就结束线程。）
                    System.out.println(Thread.currentThread().getName() + " (" + Thread.currentThread().getState()
                                        + ") catch InterruptedException.");

                    // 如果执行下面这段代码的话，线程还是执行状态
//                    System.err.println("执行死循环=====");
//                    while (true) {
//                        int i = 1;
//                    }
                }
            }
        };
        Thread t1 = new Thread(interruptTask,"t1");
        System.out.println(t1.getName() +" ("+t1.getState()+") is new.");

        // 启动“线程t1”
        t1.start();
        System.out.println(t1.getName() +" ("+t1.getState()+") is started.");

        // 主线程休眠300ms，然后主线程给t1发“中断”指令。
        Thread.sleep(600);
        t1.interrupt();
        System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted.");

        // 主线程休眠300ms，然后查看t1的状态。
        Thread.sleep(300);
        System.out.println(t1.getName() +" ("+t1.getState()+") is interrupted now.");

    }
}
