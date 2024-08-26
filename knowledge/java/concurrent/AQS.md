## AbstractQueuedSynchronizer
### 核心数据结构
* state: 由 volatile 修饰，根据不同的锁类型，state 有不同的含义。
  - 对于 ReentrantLock，state 表示锁的重入次数。
  - 对于 Semaphore，state 表示可用的许可数。
* Node: 阻塞队列的基本结构，包含了线程的引用和等待状态。
* CLH 队列: 由 Node 节点构成的双向链表，用于存储等待锁的线程。
  - 双向链表便于删除被取消或中断的结点。

<img src="/knowledge/assets/java/clh-queue-state.png" width="750">

### 公平锁和非公平锁（存疑？）
* 公平锁：线程按照申请锁的顺序来获取锁，即需要按照 CLH 队列的顺序来获取锁。
* 非公平锁：已经排入 CLH 队列的线程只有头部结点释放锁后，后续结点才能获取锁，但是新的线程可以插队（如果插队失败，则也要排队）。

## ReentrantLock

### ReentrantLock & synchronized

|      | synchronized                | ReentrantLock     |
|------|-----------------------------|-------------------|
| 实现机制 | Monitor                     | AQS               |
| 锁类型  | 非公平锁                        | 公平锁/非公平锁          |
| 可重入性 | 支持                          | 支持                |
| 条件队列 | 通过 wait() / notify()，支持 1 个 | 通过 Condition，支持多个 |
| 锁的释放 | 自动释放                        | 需要手动释放            |
| 灵活性  | 不灵活                         | 支持响应中断、超时         |

## 参考链接
* [AQS 原理和应用](https://tech.meituan.com/2019/12/05/aqs-theory-and-apply.html)
* [AQS 核心机制](https://www.mianshiya.com/bank/1789249312885223425/question/1780933295018635266)