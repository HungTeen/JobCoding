## 线程
### 线程生命周期
* `NEW`：新建状态，线程对象已经创建，但还未调用 `start()` 方法。
* `RUNNABLE`：可运行状态，JVM 不区分就绪和运行状态，只要线程能够执行，就是可运行状态。
* `BLOCKED`：阻塞状态，线程在等待锁的时候，会进入阻塞状态。
* `WAITING`：等待状态，线程需要等待其他线程通知或唤醒。
* `TIMED_WAITING`：超时等待状态，等待一段时间后线程会自动恢复。
* `TERMINATED`：终止状态，线程执行完毕或者发生异常。

<img src="/knowledge/assets/java/thread-life.png" width="750">

## 线程池
### 线程池的创建方式
* 通过 `ThreadPoolExecutor` 类直接创建。
* 通过 `ForkJoinPool` 类直接创建。
* 通过 `Excuters` 工厂类创建。

### `ThreadPoolExecutor`
`ThreadPoolExecutor` 是一个通用的线程池，它的任务是将多个任务分配给多个线程执行。
* CoreSize：核心线程数，线程池中始终保持的线程数。
* MaxSize：最大线程数，线程池中最大的线程数。
* KeepAliveTime：线程空闲时间，超过该时间的线程会被回收。
* `BlockingQueue`：阻塞队列，用于存放等待执行的任务。
  * **阻塞队列满之后，如果提交新任务，会开新的线程（core < max）执行新任务，而不是阻塞队列中的任务**。
* `RejectedExecutionHandler`：拒绝策略，当线程池满了，无法继续执行任务时的处理策略。
  * `DiscardPolicy`：直接丢弃任务。
  * `DiscardOldestPolicy`：丢弃队列中最老的任务。
  * `CallerRunsPolicy`：由调用线程执行任务。
  * `AbortPolicy`：抛出异常。

#### 常见`ThreadPoolExecutor`的创建方式
* `FixedThreadPool`：使用 `LinkedBlockingQueue`，固定大小的线程池，核心线程数和最大线程数相等。
* `SingleThreadPool`：使用 `LinkedBlockingQueue`，单线程线程池，核心线程数和最大线程数都为 1 。
* `CachedThreadPool`：使用 `SynchronousQueue`，核心线程数为 0 ，最大线程数为`Integer.MAX_VALUE`。
* `ScheduledThreadPool`：使用 `DelayedWorkQueue`，支持定时和周期性任务执行，每次执行都是延时时间最靠前的任务。
* 还可以使用 `PriorityBlockingQueue`、`ArrayBlockingQueue` 等队列来实现不同的线程池。

### `ForkJoinPool`
`ForkJoinPool` 是一个专用于实现分治任务的线程池，旨在高效的执行递归可分解的任务，充分利用多核处理器的并行性。
* 工作窃取：当一个线程的任务执行完毕后，会去其他线程的队列中窃取任务执行。
* 分而治之：将一个大任务分解成多个小任务，然后将这些小任务分配给多个线程执行。
* 工作窃取队列：每个线程都有一个工作窃取队列，用于存放自己待执行的任务。
* `ForkJoinTask`：只有实现该类的递归任务才能被线程池执行，通常用`RecursiveTask`和`RecursiveAction`实现。

## 相关文章
* [ForkJoinPool](https://blog.csdn.net/qq_40592590/article/details/132627368)