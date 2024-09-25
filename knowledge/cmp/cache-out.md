## LRU
采用链表实现，每次访问一个元素，将其移动到链表头部，当链表满时，将链表尾部元素删除。传统 LRU 无法避免两个问题：
* 缓存预读失效造成的命中率下降。
* 缓存污染，即缓存中的数据长时间不被访问，但是由于其在缓存中，导致其一直存在。
  Linux 和 MySQL 中的 LRU 为了避免这种情况，采用了**冷热数据分离**和**提高热数据门槛**的思想。
### Linux 内核中的 LRU
* 实现了两个 LRU 队列，一个是 Active 队列，一个是 Inactive 队列。
* 预读的数据先进入 Inactive 队列头部
* 当 Inactive 队列中的数据被访问**第 2 次**时（防止缓存污染），将其移动到 Active 队列头部。
* 当 Active 队列满时，将 Active 队列尾部的数据移动到 Inactive 队列头部。

### MySQL 中的 LRU
* 将一个 LRU 队列分为两个部分，一个是 old 区域，一个是 young 区域。
* 预读的数据先进入 young 区域。
* 一个 young 区域的数据被访问**第 2 次**且停留时间超过 1 秒时，将其移动到 old 区域。
* 其他和 Linux 类似。

### Redis 中的 LRU
* 每个 key 都有一个 LRU 时间戳，每次访问 key 时，将时间戳更新为当前时间。
* 每次会抽取一定数量的 key，将时间戳最小的 key 删除。

## LFU
### Redis 中的 LFU
* 每个 key 都有一个 24 bit 的计数器（同LRU），前 16 bit 为访问时间，后 8 bit 为访问次数。
* 每次访问 key 时，根据访问时间和当前时间，更新访问次数。
* 每次会抽取一定数量的 key，将访问次数最小的 key 删除。
### Caffeine 中的 LFU
[W-Tiny-LFU](/knowledge/local-cache/caffeine.md)

## 参考链接
