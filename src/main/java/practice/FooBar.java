package practice;


import org.junit.jupiter.api.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 11:19 2020/4/12
 * @Modified By:
 */
public class FooBar {

    private int n;

    public FooBar(int n) {
        this.n = n;
    }

    AtomicInteger integer = new AtomicInteger(1);

    public void foo(Runnable printFoo) throws InterruptedException {


        for (int i = 0; i < n; i++) {

            while (0 == integer.get());
            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
            integer.set(0);
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {


        for (int i = 0; i < n; i++) {
            while (1 == integer.get());
            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
            integer.set(1);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Runnable printBar = new Runnable() {
            @Override
            public void run() {
                System.out.print("bar");
            }
        };
        Runnable printFoo = new Runnable() {
            @Override
            public void run() {
                System.out.print("foo");
            }
        };

        FooBar fooBar = new FooBar(5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.foo(printFoo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.bar(printBar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
