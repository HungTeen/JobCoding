# synchronized
## Mark Word
synchronized 锁升级过程主要和 Mark Word 有关，Mark Word 是对象头的一部分，用于存储对象的hashcode、GC分代年龄、锁状态标志、线程持有的锁等信息。

<img src="/knowledge/assets/java/mark-word.png" width="750">

## 锁升级流程
一图胜千言

<img src="/knowledge/assets/java/synchronized-simple.png" width="250">

<img src="/knowledge/assets/java/synchronized-details.png" width="1000">

## Object Monitor
对象监视器具有 WaitSet、EntryList、Owner、Counter 等属性。
* WaitSet：等待队列，线程调用 wait() 方法后进入等待队列。
* EntryList：阻塞队列，线程竞争锁失败后进入阻塞队列。
* Owner：持有锁的线程。
* Counter：重入次数。

# volatile
volatile 关键字用来修饰变量，表面变量是不稳定的，每次读取都会从主内存中读取。


# 问题
### 当锁状态为偏向锁的时候，Hashcode 存到哪里去了？
* HashCode 是懒加载的，调用之后才会保存到对象头中。
* 如果调用 HashCode 之前就调用了 Synchronized，锁会从偏向锁升级为重量级锁。
* 如果调用 HashCode 之后才调用 Synchronized，锁会从无锁升级为轻量级锁。
### Synchronize怎么保证原子性、可见性、有序性？
* 同一时间只有一个线程能够访问同步代码块，天然保证了原子性。
* monitorenter 和 monitorexit 指令分别具有 Load 屏障和 Store 屏障的功能，保证了可见性。
* 虽然 Synchronized 保证了有序性，但是**不能防止指令重排序**。

# 相关文章
[synchronized 优化](https://juejin.cn/post/7001483226678034439)

[偏向锁 Hashcode 存到哪里去了](https://blog.csdn.net/u014044812/article/details/124302959)

[synchronized 锁升级原理](https://www.cnblogs.com/star95/p/17542850.html)

[Java 锁与线程那些事](https://tech.youzan.com/javasuo-yu-xian-cheng-de-na-xie-shi/)

[synchronized 怎么保证可见性](https://blog.csdn.net/qq_36270361/article/details/107708132)

[读锁和写锁](https://cloud.tencent.com/developer/article/1176230)