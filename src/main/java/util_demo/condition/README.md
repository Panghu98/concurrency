# Condition作用
当线程1需要等待某个条件的时候,它就去执行
condition.await()方法,一旦执行了await()方法,线程就会进
入阻塞状态

然后通常会有另外一个线程, 假设是线程2，去执行对应的条
件,直到这个条件达成的时候,线程2就会去执行
condition.signal()方法,这时JVM就会从被阻塞的线程里找,
找到那些等待该condition的线程,当线程1就会收到可执行信
号的时候,它的线程状态就会变成Runnable可执行状态

![](.README_images/4abce70e.png)

------

##  signalAll()和signal()区别
◆signalAlI()会唤起所有的正在等待的线程
◆但是signal()是公平的,只会唤起那个等待时间最长的线程

## 注意点
实际上,如果说Lock用来代替synchronized ,那么
Condition就是用来代替相对应的Object.wait/notify的,所
以在用法和性质上,几乎都一样

await方法会自动释放持有的Lock锁,和Object.wait-样,不
需要自己手动先释放锁

调用await的时候,必须持有锁,否则会抛出异常,和
Object.wait- -样。


