package util_demo.conutdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: panghu
 * @Description:  模拟100米跑步，5名参赛人员都准备好了，就等裁判的一声令下，所有人同时开始跑步
 * @Date: Created in 10:14 2020/3/31
 * @Modified By:
 */
public class CountDownLatchDemo2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i + 1;

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 球员的准备时间不得超过裁判员的打枪时间
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println(no + "号准备完毕，等待发令枪。。。");
                        countDownLatch.await();
                        System.out.println(no + "开始冲了...");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }
        // 模拟裁判员检查工作
        Thread.sleep(1000);
        System.err.println("打枪了，冲冲冲....");
        countDownLatch.countDown();
    }
}
