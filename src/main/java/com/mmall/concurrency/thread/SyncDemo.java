package com.mmall.concurrency.thread;

public class SyncDemo {
    public static void main(String... args) {
        SyncThread syncThread = new SyncThread();
        Thread A_thread1 = new Thread(syncThread, "A_thread1");
        Thread A_thread2 = new Thread(syncThread, "A_thread2");
        //B1,B2,C1,C2共用同一个对象
//        Thread B_thread1 = new Thread(new SyncThread(), "B_thread1");
//        Thread B_thread2 = new Thread(new SyncThread(), "B_thread2");
//        Thread C_thread1 = new Thread(new SyncThread(), "C_thread1");
//        Thread C_thread2 = new Thread(new SyncThread(), "C_thread2");
        Thread D_thread1 = new Thread(new SyncThread(), "D_thread1");
        Thread D_thread2 = new Thread(new SyncThread(), "D_thread2");
        Thread E_thread1 = new Thread(new SyncThread(), "E_thread1");
        Thread E_thread2 = new Thread(new SyncThread(), "E_thread2");
        A_thread1.start();
        A_thread2.start();
//        B_thread1.start();
//        B_thread2.start();
//        C_thread1.start();
//        C_thread2.start();
        D_thread1.start();
        D_thread2.start();
        E_thread1.start();
        E_thread2.start();
    }
}
