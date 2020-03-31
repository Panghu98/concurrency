package util_demo.CyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: panghu
 * @Description: 演示 CyclicBarrier
 * @Date: Created in 19:55 2020/3/31
 * @Modified By:
 */
public class CyclicBarrierDemo1 {

    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(5, new Runnable() {
            @Override
            public void run() {
                System.err.println("到了5个人，大家统一出发........");
            }
        });

        for (int i = 0; i < 10; i++) {
            // 凑齐五个就出发，可重复使用
            new Thread(new Task(i,barrier)).start();
        }

    }

    static class Task implements Runnable{

        private int no;
        private CyclicBarrier barrier;

        public Task(int no, CyclicBarrier barrier) {
            this.no = no;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + no + "前往集合地点");
            try {
                Thread.sleep((long) (Math.random()*10000));
                System.out.println("线程"+ no +"到达现场，开始等待...");
                barrier.await();
                System.err.println("线程" + no + "出发了");
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}
