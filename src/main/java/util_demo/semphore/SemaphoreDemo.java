package util_demo.semphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 12:47 2020/3/31
 * @Modified By:
 */
public class SemaphoreDemo  {

    static Semaphore semaphore = new Semaphore(5,true);

    public static void main(String[] args) {

        Task runnable = new Task();


        ExecutorService service = Executors.newFixedThreadPool(50);
        for (int i = 0; i < 100; i++) {
            service.execute(runnable);
        }

        service.shutdown();
    }

    static class Task implements Runnable{
        @Override
        public void run() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "获得了许可证");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "释放了许可证");
            semaphore.release();
            System.out.println("===============");
        }
    }

}
