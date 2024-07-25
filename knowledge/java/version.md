# 全局总结
## Java 1
## Java 5
## Java 6
* synchronized加锁引入了优化，锁从无锁->偏向锁->轻量级锁->重量级锁随着竞争程度逐步升级。
## Java 7
## Java 8
* HashMap中当冲突链长度达到8，并且容量大于64时，会将链表转换成红黑树。
* 引入性能更好的读写锁StampedLock。
* Parallel Scavenge 和 Parallel Old 成为默认的垃圾回收器。
## Java 9
* G1 成为默认的垃圾回收器。
## Java 11
* 引入 ZGC（试验阶段）。
## Java 15
## Java 17
## Java 18
* 取消了偏向锁。
## Java 21
* 引入分代 ZGC。
## Java 22
## Java 23
# 细节总结
## GC
### Java 11
* 引入 ZGC（试验阶段）。
### Java 15
* ZGC 可以正式使用。
## Lock
### Java 5
* Condition接口。
### Java 6
* synchronized加锁引入了优化，锁从无锁->偏向锁->轻量级锁->重量级锁随着竞争程度逐步升级。

