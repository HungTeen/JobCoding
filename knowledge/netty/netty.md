## Netty 零拷贝
### 使用直接内存
正常情况下，JVM 需要将数据从 JVM 堆内存拷贝到堆外内存进行业务执行的，这是因为：
* 操作系统并不感知 JVM 的堆内存，而且 JVM 的内存布局与操作系统所分配的是不一样的，操作系统并不会按照 JVM 的行为来读写数据。
* 同一个对象的内存地址随着 JVM GC 的执行可能会随时发生变化，例如 JVM GC 的过程中会通过压缩来减少内存碎片，这就涉及对象移动的问题了。
而 Netty 在进行 I/O 操作时都是使用的堆外内存，可以避免数据从 JVM 堆内存到堆外内存的拷贝。
### 使用 FileRegion 传输文件
FileRegion 底层封装了 FileChannel#transferTo() 方法，可以将文件缓冲区的数据直接传输到目标 Channel，避免内核缓冲区和用户态缓冲区之间的数据拷贝，这属于操作系统级别的零拷贝。
### ByteBuf 的零拷贝
* 使用 CompositeByteBuf，将多个 ByteBuf 合并为一个逻辑上的 ByteBuf ，避免了多个 ByteBuf 拼接时的内存拷贝。
* 使用 ByteBuf 的 slice 方法,避免了数据的复制。

## 相关博客
[Netty零拷贝](https://www.cnblogs.com/vipstone/p/18237139)

