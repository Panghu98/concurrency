package util_demo.condition;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: panghu
 * @Description: 演示使用Condition实现生产者消费者模式
 * @Date: Created in 18:40 2020/3/31
 * @Modified By:
 */
public class ConditionDemo2 {

    private int queueSize = 10;
    private PriorityQueue<Integer>  queue = new PriorityQueue<>(queueSize);
    private Lock lock = new ReentrantLock();
    /**
     * 未满
     */
    private Condition notFull = lock.newCondition();
    /**
     * 不为空
     */
    private Condition notEmpty = lock.newCondition();

    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        System.out.println("队列空，等待数据....");
                        notEmpty.await();
                    }
                    Thread.sleep(100);
                    // 进行消费
                    queue.poll();
                    // 唤醒所有生产线程
                    notFull.signalAll();
                    System.out.println("从队列中取走了一个数据，队列中还剩" + queue.size()+ "个元素");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }


    class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        System.out.println("队列满，等待消费..");
                        notFull.await();
                    }
                    Thread.sleep(100);
                    // 进行消费
                    queue.add(1);
                    // 唤醒所有生产线程
                    notEmpty.signalAll();
                    System.out.println("从队列中增加，队列中还剩" + queue.size() + "个元素");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        ConditionDemo2 conditionDemo2 = new ConditionDemo2();
        Consumer consumer = conditionDemo2.new Consumer();
        Producer producer = conditionDemo2.new Producer();
        consumer.start();
        producer.start();
    }

}
