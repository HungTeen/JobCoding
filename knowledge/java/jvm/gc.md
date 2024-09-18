# 垃圾回收

## GC分类
* 部分收集（Partial GC）
  * 新生代收集（Minor GC / Young GC）
  * 老年代收集（Major GC / Old GC）
  * 混合收集（Mixed GC）：收集新生代和部分老年代，适用 G1 收集器。
* 整堆收集（Full GC）

## GC算法
* 标记-清除（Mark-Sweep）：性能表现一般，内存碎片太多。
* 标记-复制（Mark-Copy）：只能使用一半的内存，不适合存活对象数量多的老年代。
* 标记-整理（Mark-Compact）：适合老年代，但是效率不高。

## 对象存活判断
* 引用计数法：无法解决循环引用问题。
* 可达性分析法：从 GC Roots 开始遍历，无法回收的对象为存活对象。
### 三色标记法
* 白色：未访问过的对象。
* 灰色：访问过的对象，但是其引用对象未访问。
* 黑色：访问过的对象，且其引用对象也访问过。

在并发标记阶段，对象可能会在标记过程中发生引用关系的变化，可能会导致多标或者漏标。
* 对于多标，在后面的 GC 过程中可以将其重新标记为黑色；
* 对于漏标，可能会导致存活对象被回收，这种情况下需要重新扫描。

多标的问题不是很严重，但是漏标可能会导致NPE，必须得到解决。漏标的两个充要条件：
* 一个黑色对象引用了一个白色对象；
* 一个灰色对象不再引用白色对象。

不同的 GC 收集器对于漏标的处理方式不同：
* CMS：打破了第一个条件，使用增量更新，记录所有并发标记时引用了白色对象的黑色对象，然后在并发标记结束后重新扫描这些对象，如果这些对象引用了白色对象，则将其重新标记为灰色。
* G1：打破了第二个条件，使用原始快照（SATB）算法，关注引用的删除，当灰色不再引用白色时，将白色记录下来，保证其不被回收。

## 垃圾收集器
* Serial：单线程，新生代使用标记-复制算法。
* Serial Old：单线程，老年代使用标记-整理算法。
* ParNew：多线程版本的 Serial 收集器。
* Parallel Scavenge：JDK1.8 默认新生代收集器，使用标记-复制算法。
* Parallel Old：JDK1.8 默认老年代收集器，使用标记-整理算法。
* CMS：第一款并发收集器，使用标记-清除算法。
* G1：JDK9 默认收集器，局部使用标记-整理算法，全局使用标记-复制算法。
* ZGC：JDK11 新增收集器，使用标记-复制算法。
### CMS
CMS 是一款以获取最短回收停顿时间为目标的收集器，它是一款并发收集器，主要分为以下几个阶段：
* 初始标记（Initial Mark）：把 GC Roots 能直接关联到的对象标记为灰色。
* 并发标记（Concurrent Mark）：从 GC Roots 开始遍历，标记所有可达对象，同时记录增量更新。
* 重新标记（Remark）：修正并发标记阶段因为用户程序继续运行导致的标记变动。
* 并发清除（Concurrent Sweep）：清除未标记的对象。

<img src="/knowledge/assets/java/cms.png" width="900">

缺点如下：
* 无法处理浮动垃圾，需要在下一次 GC 时处理。
* 无法处理内存碎片，可能会导致 Full GC。
* 对 CPU 资源敏感，可能会导致用户程序性能下降。

### G1
G1 是一款以获取最大吞吐量为目标的收集器，它是一款分代收集器，主要分为以下几个阶段：
* 初始标记（Initial Mark）：把 GC Roots 能直接关联到的对象标记为灰色。
* 并发标记（Concurrent Mark）：从 GC Roots 开始遍历，标记所有可达对象。
* 最终标记（Final Mark）：修正并发标记阶段因为用户程序继续运行导致的标记变动。
* 筛选回收（Live Data Counting）：根据各个 Region 的存活对象数量，选择回收优先级高的 Region 进行回收。
  * 标记-复制：将 CSet 中存活的对象复制到新的 Region 中。
<img src="/knowledge/assets/java/g1.png" width="900">

G1 的内存模型：
* Region：G1 将堆内存划分为多个大小相等的 Region（大小为2的幂，在1M到32M之间），每个 Region 可以是 Eden、Survivor、Old 中的一种。
* Card：每个 Region 会被划分为多个大小相等的 Card，每个 Card 会记录该 Region 中对象引用的目标 Region。
* Remembered Set：每个 Region 会有一个 Remembered Set，记录了其他 Region 中引用该 Region 的对象。
* Card Table：记录了所有 Card 的引用关系。
* STAB：记录了引用的删除，当灰色不再引用白色时，将白色记录下来，保证其不被回收。
* Collection Set：每次 GC 会选择多个 Region 进行回收，这些 Region 组成了 Collection Set。

G1 提供两种 GC 模式，Young GC 和 Mixed GC：
* Young GC：选定所有 年轻代的 Region 进行回收，根据其 RSet 信息作为 GC Roots 进行回收，避免扫描整个老年代。
* Mixed GC：选定所有 年轻代的 Region 和部分 老年代的 Region 进行回收，引入 RSet 大大减少了 GC 的工作量。

### ZGC
#### CMS 与 G1 的痛点
* CMS 的 Young GC 采用 ParNew 的标记复制算法。
* G1 采用标记-复制算法。
* CMS 和 G1 在标记-复制算法的转移阶段均无法解决对象的准确定位问题，需要 STW，性能较差。

#### ZGC GC 流程
<img src="/knowledge/assets/java/zgc.png" width="900">

ZGC 流程由三个 STW 阶段和四个并发阶段组成：
* 初始标记和初始转移都只需要扫描 GC Roots，不超过 1ms。
* 再标记阶段如果耗时超过 1ms，则会再次进入并发标记阶段。

#### ZGC 关键技术
* 染色指针：在对象的地址上使用若标记位，对应不同的区域（虚拟空间换时间）。
* 读屏障：在读取堆内存时，插入读屏障，保证能正确读取到对象的最新位置。
* STAB：记录引用的删除，当灰色不再引用白色时，将白色记录下来，保证其不被回收。
* forwarding table：记录对象的移动信息，保证引用的正确性（key 为旧地址，value 为新地址）。

<img src="/knowledge/assets/java/zgc-address.png" width="900">

#### ZGC 染色流程

<img src="/knowledge/assets/java/zgc-2.png" width="900">

#### ZGC 调优
* ZGC 在内存分配不过来时会阻塞线程。
* ZGC 没有分代（JDK 21 引入了分代 ZGC）。

# 参考网址
* [全局上理解G1](https://tech.meituan.com/2016/09/23/g1.html)
* [G1实现详解](https://www.nenggz.com/md/java/jvm/java-jvm-gc-g1.html)
* [ZGC 实现原理](https://www.nenggz.com/md/java/jvm/java-jvm-gc-zgc.html)
* [ZGC 染色流程](https://javabetter.cn/jvm/gc-collector.html#zgc)
* [ZGC 染色](https://www.mianshiya.com/bank/1789931432793948162/question/1780933295295459330)