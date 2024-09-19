## Java 5
* 新增 `AtomicReference` 类。
* `Condition` 接口。
## Java 6
* synchronized加锁引入了优化，锁从无锁->偏向锁->轻量级锁->重量级锁随着竞争程度逐步升级。
## Java 7
* 引入`ForkJoinPool`。
## Java 8
* interface 中的方法可以被 default 和 static 修饰。
* `@FunctionalInterface` 函数式接口，提供函数式编程。
* Lambda 表达式，使得代码更加简洁。
* `Stream` 流式编程。
* `CompletableFuture` 异步编程。
* `Optional` 类更优雅的解决 NPE 问题。
* `HashMap`中当冲突链长度达到8，并且容量大于64时，会将链表转换成红黑树。
* 引入性能更好的读写锁`StampedLock`。
* Parallel Scavenge 和 Parallel Old 成为默认的垃圾回收器。
## Java 9
* G1 成为默认的垃圾回收器。
* JShell 轻量级交互式编程。
* 模块化系统。
* 快速创建不可变集合，`List.of()`。
* `String` 存储结构优化，改用 `byte[]` 存储。
* 接口私有方法。
* 进程 API，`ProcessHandle` 可以对 JVM 进程进行管理。
* 响应式流？[Reactive Streams](https://www.cnblogs.com/IcanFixIt/p/7245377.html)
* 变量句柄？
## Java 10
* 引入了局部变量类型推断，`var`。
## Java 11
* 引入 ZGC（试验阶段）。
## Java 14
* switch 增强，使用 `->`，防止漏写 `break`。
## Java 15
* ZGC 转正。
* 文本块，`"""`。
### Java 16
* `instanceof` 模式匹配。
* `Record` 类。
## Java 17
* 增强的伪随机数生成器。
* 密封类？
## Java 18
* 取消了偏向锁。
* 默认字符集改为 UTF-8。
## Java 21
* 引入分代 ZGC。
* 记录模式匹配。
* switch 模式匹配。
* 虚拟线程。
## Java 22
* 隐式声明类和默认主方法。
* 未命名变量，通过 `_` 表示。【未转正】
* `super()` 前导语句。
* 作用值域，类似于 `ThreadLocal`，是针对虚拟线程的优化。
* 
## Java 23

## 参考链接
* [Java 22 新特性](https://juejin.cn/post/7348625988140711986)
* [Java 23 新特性](https://blog.csdn.net/cheng_fu/article/details/140365668)