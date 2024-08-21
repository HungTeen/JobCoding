## 数据结构汇总

<img src="/knowledge/assets/redis/data-structure.png" width="1000">


## String

### 分布式锁

Redis 通过 SET NX 命令可以实现分布式锁，其中 NX 表示只在 key 不存在时，才对 key 进行设置操作。

#### 为什么要设置 value？
防止误删锁，可以通过 Lua 脚本在删除时判断是否为自己的锁。
```lua
// 释放锁时，先比较锁对应的 value 值是否相等，避免锁的误释放
if redis.call("get",KEYS[1]) == ARGV[1] then
    return redis.call("del",KEYS[1])
else
    return 0
end
```

#### 为什么要设置过期时间？
防止锁的持有者在释放锁之前发生异常，导致锁无法释放，从而导致死锁。

#### 如何实现可重入锁？
获取锁时，发现锁已经被自己持有，可以增加一个计数器，每次释放锁时，计数器减一，直到计数器为 0 时，才真正释放锁。

#### 如何避免集群环境下的锁失效？
使用 RedLock 算法，通过多个 Redis 实例来实现分布式锁，只有大部分 Redis 实例获取锁成功，才算获取锁成功。
【大概不会问到】