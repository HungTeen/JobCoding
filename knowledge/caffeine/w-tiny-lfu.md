## W-TinyLFU
* W 指的是 Window Cache
* Tiny 指的是用很小的内存和维护代价来实现 LFU。

### 解决了什么问题？
* 传统 LFU 算法不能解决稀疏流量的问题，很多 key 在短时间内被频繁访问，但后面就不再访问了，这些 key 很难过期。
* 传统 LFU 维护开销太大，每个 key 都要维护一段时间内被访问的次数。

## TinyLFU
TinyLFU 是一种对访问频次进行估计的算法，它可以用很少的内存区近似估计每个 key 的访问频次。

### Count-Min Sketch（CMS）
CMS 是一种类似于 Bloom Filter 的数据结构，它可以用来估计每个 key 的访问频次。
* 通过多个 hash 函数，将 key 映射到各个数组的对应位置，每个位置的值加 1。
* 通过多个 hash 函数，查询 key 对应数组位置的访问频次，取最小值。
* 如果数组长度为 n，hash 函数的个数为 m，那么需要的内存空间为 n * m。

Caffeine 对 CMS 进行了内存上的优化，如下：
* 将多个数组变成了一个数组，数组长度取 2 的幂次方（类似于 `HashMap`）。
* 使用 `long` 类型来存储频次，而不是 `int` 类型。
* 使用 4 个 bit 来计算频次，所以一个 `long` 类型可以存储 16 个计数器。
  * 当计数器的总和达到一定阈值时，会对**所有计数器进行减半**的操作。
* 使用 4 个 hash 函数，故可将 16个计数器分为 4 个组，每个组有 4 个计数器。

<img src="/knowledge/assets/caffeine/cms.png" width="900">

## Window Cache（类似于垃圾回收）
整个缓存被分为 Main Cache（99%） 和 Window Cache（1%） 两部分，其中 Main Cache 又被分为 Protected（80%） 和 Probation（20%）两部分。
* Window Cache 满的时候会将最旧的缓存弹出，尝试放入 Probation 中。
* Protected 满的时候会将最旧的缓存弹出，尝试放入 Probation 中。
* Probation 中的缓存访问频率达到一定阈值后，会被放入 Protected 中。

### 放入 Probation 中的判断
* 尝试放入 Probation 中的缓存为候选者，Probation 中最旧的缓存为受害者。
* 受害者访问频率高，则淘汰候选者。
* 候选者访问频率高，且候选者频率不低于5，则淘汰受害者。
* 否则随机淘汰候选者和受害者。

## 参考链接
* [Caffeine 核心原理](https://www.jianshu.com/p/3c6161e5337b)