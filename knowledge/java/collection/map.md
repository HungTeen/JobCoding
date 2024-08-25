## ConcurrentHashMap
### 数据结构
### 操作
#### get()
不需要加锁，通过 volatile 关键字保证了可见性。
#### size()
* Java 8 之前。
* Java 8 使用了类似于 LongAdder 的方式统计元素个数，性能更好。
## 参考链接
* [ConcurrentHashMap 的 size() 方法](https://www.cnblogs.com/jimoer/p/13625368.html)