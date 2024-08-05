## select/poll/epoll
### select
* 将已连接的 Socket 放到一个文件描述集合中
* 调用 select 函数，将文件描述符集合拷贝到内核空间
* 内核遍历文件描述符集合，检查是否有事件发生，并进行标记
* 将标记的文件描述符集合拷贝到用户空间
* 用户遍历文件描述符集合，处理事件

整个过程发生了两次拷贝，两次遍历，效率低下。

### poll
* select 使用 BitsMap 来存储文件描述符集合，最大只能处理 1024 个文件描述符。
* poll 使用动态数组（以链表组织）来存储文件描述符集合，没有数量限制。

### epoll
* 内核中维护了一个红黑树，保存了所有待检测的 socket。
* epoll 内核维护了一个链表，保存了所有发生事件的 socket。
* epoll_wait 函数直接返回发生事件的 socket，避免了遍历寻找。

### 触发方式
* select 和 poll 只支持水平触发
* epoll 支持水平触发和边缘触发，默认是水平触发。
#### 边缘触发
* 当被监控的 Socket 描述符上有可读事件发生时，服务器端只会从 epoll_wait 中苏醒一次，即使进程没有调用 read 函数从内核读取数据，也依然只苏醒一次，因此我们程序要保证一次性将内核缓冲区的数据读取完；
* 一般效率更高，搭配非阻塞 IO 使用；
#### 水平触发
当被监控的 Socket 上有可读事件发生时，服务器端不断地从 epoll_wait 中苏醒，直到内核缓冲区数据被 read 函数读完才结束，目的是告诉我们有数据需要读取；