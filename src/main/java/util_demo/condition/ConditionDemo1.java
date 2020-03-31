package util_demo.condition;

import com.mmall.concurrency.thread.ReentrantLockDemo;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: panghu
 * @Description: 演示Condition的基本用法
 * @Date: Created in 13:25 2020/3/31
 * @Modified By:
 */
public class ConditionDemo1 {

    ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    void method1() throws InterruptedException {
        lock.lock();
        try {
            System.out.println("条件不满足，开始await...");
            condition.await();
            System.out.println("条件满足，开始执行后续的任务...");
        }finally {
            lock.unlock();
        }
    }

    void method2() {
        lock.lock();
        try {
            System.out.println("准备工作完成，唤醒其他线程");
            condition.signal();
        }finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ConditionDemo1 conditionDemo1 = new ConditionDemo1();
        new Thread(() -> {
            try {
                conditionDemo1.method1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        Thread.sleep(2000);
        conditionDemo1.method2();
    }

}
