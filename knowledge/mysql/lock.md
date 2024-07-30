# 全局锁
全局锁主要用于全库逻辑备份。
```bash
flush tables with read lock # 执行后，整个数据库的所有表都会被锁定，只能读不能写。

unlock tables # 释放全局锁，会话断开后，也会自动释放锁。
```

#### 既然备份数据库数据的时候，使用全局锁会影响业务，那有什么其他方式可以避免？
备份数据库的工具是 mysqldump，在使用 mysqldump 时加上 –single-transaction 参数的时候，就会在备份数据库之前先开启事务。这种方法只适用于支持「可重复读隔离级别的事务」的存储引擎。

# 表级锁
## 表锁
```bash
lock tables table_name read/write # 表级别的共享锁/独占锁

unlock tables # 释放表锁，会话断开后，也会自动释放锁。
```
## 元数据锁（MDL）
MDL 锁不需要显示的使用，是 MySQL 内部实现的一种锁机制，用来保证读写的正确性。
* 当对一个表做CRUD操作的时候，加 MDL 读锁。
* 当要对表做结构变更操作的时候，加 MDL 写锁。

#### 为什么线程 C 因为申请不到 MDL 写锁，而导致后续的申请读锁的查询操作也会被阻塞？
申请 MDL 锁的线程会排成一个队列，申请写锁的队列会优先于申请读锁的队列。

## 意向锁
意向锁相当于表的全局标记，为了快速判断表中是否有记录加锁。
* 对记录加共享锁之前，需要先加表的意向共享锁。
* 对记录加排他锁之前，需要先加表的意向排他锁。

**意向锁之间不会冲突，也不会和行锁冲突，但是会和表锁冲突。**
```bash
select ... lock in share mode # 先在表上加上意向共享锁，然后对读取的记录加共享锁

select ... for update # 先表上加上意向独占锁，然后对读取的记录加独占锁
```

## 自增锁（AUTO-INC）
自增锁是在插入自增 ID 的时候，为了避免并发插入导致 ID 重复而加的锁。

AUTO-INC 锁是特殊的表锁机制，锁不是再一个事务提交后才释放，而是再执行完插入语句后就会立即释放。

# 行级锁
## 记录锁（Record Lock）
锁住的是一条记录。
## 间隙锁（Gap Lock）
锁住的是两个记录之间的间隙，间隙锁是为了防止幻读。
## 临键锁（Next-Key Lock）
锁住的是一条记录和这个记录之前的间隙，是记录锁和间隙锁的组合。
## 插入意向锁（Insert Intention Lock）
一个事务在插入一条记录的时候，需要判断插入位置是否已被其他事务加了间隙锁（next-key lock 也包含间隙锁）。

如果有的话，插入操作就会发生阻塞，直到拥有间隙锁的那个事务提交为止（释放间隙锁的时刻），在此期间会生成一个插入意向锁，表明有事务想在某个区间插入新记录，但是现在处于等待状态。

插入意向锁是互斥的，而间隙锁是不互斥的。