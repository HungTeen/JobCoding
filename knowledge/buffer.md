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
* binlog cache: Server 层的缓冲区，缓存即将写入 binlog 文件的日志。
* redo log buffer: InnoDB 存储引擎的缓冲区，缓存即将写入 redo log 文件的日志，保证事务的持久性。
* Buffer Pool: Innodb存储引擎设计的缓冲池，缓存索引页、数据页、undo 页等。

|  对比  |         redo log buffer         |    binlog cache     |
|:----:|:-------------------------------:|:-------------------:|
|  作用  |              故障恢复               |      备份恢复、主从同步      |
|  个数  |              只有一个               |      每个事务都分配一个      |
|  格式  |       Buffer Pool某页某处的修改        | Statement/Row/Mixed |
| 文件处理 |  环形缓冲区，满了会阻塞导致Buffer Pool脏页写回   |     写满了换新文件继续写      |
| 落盘时机 | 后台线程每1秒/事务提交时（配置文件）/正常关闭/容量超过一半 |       n个事务提交        |

Buffer Pool 脏页写回磁盘时机：
* Buffer Pool 已满。
* Checkpoint 到达。
* MySQL 空闲时（没有进行RDB快照和AOF重写）。
* redo log buffer 已满。

## Caffeine
Caffeine 为了减少锁的竞争，使用 WAL 技术，执行读写操作时，先写到缓冲区，后面异步批量执行。
* Read Buffer: 使用 Ring Buffer。
* Write Buffer: 使用阻塞队列
* Ring Buffer: 对线程取 Hash ，值相同的线程共用一个 Ring Buffer。