package util_demo.conutdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: panghu
 * @Description: 工厂中，质检，5个工人检查，所有人都认为通过，才通过
 * @Date: Created in 23:02 2020/3/30
 * @Modified By:
 */
public class CountDownLatchDemo1 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            // 工人的编号
            final int no = i + 1;
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println(no + "完成了检查。。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        countDownLatch.countDown();
                    }
                }
            };
            service.execute(runnable);
        }
        System.err.println("等待5个人检查完毕。。。。。");
        // 这个应该在主线程里面执行，意思是等所有的执行完毕
        countDownLatch.await();
        System.err.println("所有人都完成了工作。。。。");
    }
}
