# 多线程以及高并发的处理方案
## 总体的学习路线
![并发编程学习路线](/home/panghu/IdeaProjects/concurrency/src/main/resources/pictures/并发编程学习路线.png)
## 关于多线程和高并发
* 并发：同时拥有两个或者是多个线程，如果程序在单核处理器上运行，多个线程将交替地进入或者换出内存，
这些线程是同时“存在”的，每个线程都处于执行过程中的某个状态，如果运行在多核处理器上。
此时，程序中的每个线程都将分配到一个处理器核上，引起可以同时运行。
    * 关于并行和并发的区别
    <br>并行和并发都是“同时进行”，只是并行可以同时执行</br>
    
* 高并发：高并发是互联网分布式系统架构设计中必须考虑的因素之一。
它通常是指，通过设计保证系统能够同事并行处理很多请求。
* 多线程：服务能够同事处理很多请求  提升程序性能。
多线程并非是同时执行多个任务，实则是以几乎不可能察觉到的速度不断去切换这两个任务,已达到"同时执行效果"
## 重点选讲
# 多线程的并发拓展
* 死锁：在两个或者两个以上的线程互相争夺资源而永远等待的情况，叫做死锁
* 死锁必要条件
    * 互斥条件（独占）：即某个资源在一段时间内只能由一个进程占有，不能同时被两个或两个以上的进程占有
    * 请求保持条件（不放）：进程至少已经占有一个资源，但又申请新的资源；由于该资源已被另外进程占有，此时该进程阻塞；但是，它在等待新资源之时，仍继续占用已占有的资源。还以过独木桥为例，甲乙两人在桥上相遇。甲走过一段桥面（即占有了一些资源），还需要走其余的桥面（申请新的资源），但那部分桥面被乙占有（乙走过一段桥面）。甲过不去，前进不能，又不后退；乙也处于同样的状况。
    * 不剥夺资源（不抢）：进程所获得的资源在未使用完毕之前，资源申请者不能强行地从资源占有者手中夺取资源，而只能由该资源的占有者进程自行释放。如过独木桥的人不能强迫对方后退，也不能非法地将对方推下桥，必须是桥上的人自己过桥后空出桥面（即主动释放占有资源），对方的人才能过桥。
    * 环路等待条件（圈圈）：循环等待条件。存在一个进程等待序列{P1，P2，...，Pn}，其中P1等待P2所占有的某一资源，P2等待P3所占有的某一源，......，而Pn等待P1所占有的的某一资源，形成一个进程循环等待环。就像前面的过独木桥问题，甲等待乙占有的桥面，而乙又等待甲占有的桥面，从而彼此循环等待。
## 多线程并发最佳实践
* 使用本地变量
* 使用不可变类
* 最小化锁的作用域范围：安达尔定律 S=1/(1-a+a/n)   a为并行部分所占的比例，n是并行处理的节点个数，S指的是串行比
* 使用线程池的Executors，而不是直接new Thread执行
    * sleep方法的目的是让当前线程暂停运行一段时间，而与对象锁相关的信息无影响，如果执行sleep方法时是处于持有对象锁的状态，那么睡眠时依然持有对象锁，如果执行sleep方法时不是处于持有对象锁的状态，睡眠期间也不会持有对象锁。
      同时，要调用wait方法，前提是获取了这个对象的锁。在调用wait方法时，线程会释放锁并进入等待状态。在被唤醒后，该线程会一直处于等待获取锁的状态直到它重新获取到锁，然后才可以重新恢复运行状态。
      注释也强调了，该方法应该只在获取了对象的锁之后才去调用，即wait方法应该放在synchronized(obj){}块中，否则运行期间会抛出IllegalMonitorStateException异常。
* 使用BlockingQueue实现生产—消费模式
* 使用并发集合而不是加了锁的同步集合
* 使用Semaphore创建有界的访问
* 宁可使用同步代码块，也不使用同步方法
* 避免使用静态变量（如果要使用的话就需要加上final关键字成为只读集合）（静态变量会在并发情况下产生横多的问题）
## spring与线程安全
* spring bean（bean的声明周期，作用域）：singleton(默认的scope，和springIOC的声明周期是一样的，只有在第一次注入才会被创建),prototype（每次注入的时候都会创建一个新的对象）
  无状态对象：不会因为多线程破坏对象状态（比如说DTO，VO，Service，Controller等）
## HashMap和ConcurrentHashMap
![](/home/panghu/IdeaProjects/concurrency/src/main/resources/pictures/HashMap.png)
HashMap的底层结构
* 由数组和链表组成
* 数组的长度必须为2的N次方
* 在HashMap的容量不够时，会重新创建一个新的HashMap，容量为原来的两倍，保证原来的寻址依旧可用，并且会将原来的数据插入到新的HashMap当中，这个过程调用的方法为reHash()。，这个方法并不保证线程安全，在多线程调用时可能出现死循环。而单线程情况下的reHash是没有问题的。
![](/home/panghu/IdeaProjects/concurrency/src/main/resources/pictures/HashMap2.png)