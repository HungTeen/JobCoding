# Buffer
Buffer出现在了很多知识点中，比较杂乱，在此做一个整理和对比。

## Redis
* AOF 缓冲区: Redis 写操作命令执行完会将其写进 AOF 缓冲区，通过写回策略（3种）落盘。
* AOF 重写缓冲区: 在重写 AOF 期间，当 Redis 执行完一个写命令之后，它会同时将这个写命令写入到 「AOF 缓冲区」和 「AOF 重写缓冲区」。
* replication_buffer: 缓存即将发送的主从同步的命令。
* repl_backlog_buffer: 在主服务器进行命令传播时，不仅会将写命令发送给从服务器，还会将写命令写入到 repl_backlog_buffer 缓冲区里，因此 这个缓冲区里会保存着最近传播的写命令。

|   对比    | replication_buffer  | repl_backlog_buffer |
|:-------:|:-------------------:|:-------------------:|
|  出现阶段   |      增量复制&全量复制      |        增量复制         |
|   个数    |      与从节点一一对应       |      一个主节点只有一个      |
| 缓冲区满的处理 | 断开连接，删除缓存，重新连接会全量复制 |     环形结构，覆盖起始位置     |

## MySQL
* binlog: 
* redo log: 
* undo log: 
* Buffer Pool: Innodb存储引擎设计的缓冲池，缓存索引页、数据页、undo 页等。

|   对比    | replication_buffer  | repl_backlog_buffer |
|:-------:|:-------------------:|:-------------------:|
|  出现阶段   |      增量复制&全量复制      |        增量复制         |
|   个数    |      与从节点一一对应       |      一个主节点只有一个      |
| 缓冲区满的处理 | 断开连接，删除缓存，重新连接会全量复制 |     环形结构，覆盖起始位置     |

## Caffeine
Caffeine 为了减少锁的竞争，使用 WAL 技术，执行读写操作时，先写到缓冲区，后面异步批量执行。
* Read Buffer: 使用 Ring Buffer。
* Write Buffer: 使用阻塞队列
* Ring Buffer: 对线程取 Hash ，值相同的线程共用一个 Ring Buffer。