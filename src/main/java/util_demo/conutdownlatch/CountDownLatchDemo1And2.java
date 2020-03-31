package util_demo.conutdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: panghu
 * @Description:  模拟100米跑步，5名参赛人员都准备好了，就等裁判的一声令下，所有人同时开始跑步
 * 当所有人都到了终点之后，比赛结束
 * @Date: Created in 10:14 2020/3/31
 * @Modified By:
 */
public class CountDownLatchDemo1And2 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch begin = new CountDownLatch(1);

        CountDownLatch end = new CountDownLatch(5);
        ExecutorService service = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            final int no = i + 1;

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        // 模拟准备时间 球员的准备时间不得超过裁判员的打枪时间
                        Thread.sleep((long) (Math.random() * 1000));
                        System.out.println(no + "号准备完毕，等待发令枪。。。");
                        begin.await();
                        System.out.println(no + "开始冲了...");
                        // 模拟冲刺时间
                        Thread.sleep((long) (Math.random() * 5000));
                        System.out.println(no + "道到达终点。。。");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        end.countDown();
                    }
                }
            };
            service.execute(runnable);
        }
        // 模拟裁判员检查工作
        Thread.sleep(5000);
        begin.countDown();
        System.err.println("打枪了，冲冲冲....");
        end.await();
        System.err.println("所有运动员都到达了终点...");
    }
}
