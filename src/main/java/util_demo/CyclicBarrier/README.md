# CyclicBarrier循环栅栏

CyclicBarrier循环栅栏和CountDownLatch很类似,都能阻塞
一组线程

当有大量线程相互配合,分别计算不同任务,并且需要最后统
-汇总的时候,我们可以使用CyclicBarrier。CyclicBarrier可
以构造一个集结点，当某一个线程执行完毕,它就会到集结点
等待,直到所有线程都到了集结点,那么该栅栏就被撤销,所
有线程再统一出发,继续执行剩下的任务。

## CyclicBarrier和CountDownLatch的区别

作用不同: CyclicBarrier要等固定数量的线程都到达 了栅栏位
置才能继续执行，而CountDownLatch只需等待数字到0 ,也
就是说, CountDownLatch用于事件,但是CyclicBarrier是用
于线程的。
>在一个线程中可以进行多次countDown


可重用性不同: CountDownLatch在倒数到0并触发门]闩打开
后,就不能再次使用了,除非新建新的实例;而CyclicBarrier
可以重复使用。
>都到位就位的时候，CyclicBarrier可以去运行一个Runnable(可以不运行，构造方法)


