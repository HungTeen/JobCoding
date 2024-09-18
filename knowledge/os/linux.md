# Linux 常用命令
## 网络相关命令
### 连通性和时延如何查看？
通过 `ping` 命令可以测试与目标主机的网络连通情况。 

<img src="/knowledge/assets/os/linux-ping.png" width="750">

### Socket 信息如何查看？
通过 `netstat` 和 `ss` 命令可以查看 socket、网络协议栈、路由表等信息。

`netstat -nlp` 和 `ss -tnlp` 查看监听的端口。

<img src="/knowledge/assets/os/linux-netstat-ss.png" width="750">

`netstat -s` 和 `ss -s` 

<img src="/knowledge/assets/os/linux-netstat-ss-s.png" width="480">

### 网络配置如何看？
通过 `ifconfig` 和 `ip` 命令可以查看系统网络接口信息，包括IP 地址、子网掩码、MAC 地址、网关地址、MTU 大小、网口的状态以及网络包收发的统计信息。

<img src="/knowledge/assets/os/linux-ifconfig-ip.png" width="750">

### 没有互联网连接怎么办？
* 使用 `ifconfig` 命令检查网络接口是否正常，并查看 ip 地址是否正确。
* 检查默认路由是否设置正确，使用 `route -n` 或 `ip route` 命令查看路由表。
* 检查 DNS 是否正常，使用 `cat /etc/resolv.conf` 查看 DNS 配置。
* 使用 `ping` 命令测试网络连通性。
* 考虑使用 `systemctl restart network` 重启网络服务。

## 资源相关命令
### top
`top` 命令可以实时查看系统资源使用情况，包括 CPU、内存、进程等（还可以看僵尸进程）。
* `top -p pid` 查看指定进程的资源使用情况。
### free
`free` 命令可以查看系统内存使用情况。
### df
`df` 命令可以查看磁盘使用情况。
### iostat
`iostat` 命令可以查看系统 CPU 和磁盘 IO 使用情况。
### netstat
`netstat` 命令可以查看网络连接情况。
### lsof
`lsof` 命令可以查看系统打开的文件和网络连接情况。
* `lsof -i :8080` 查看指定端口的进程打开的文件。
* `lsof -u user` 查看指定用户打开的文件。

## 权限相关命令
### chmod
`chmod` 命令可以修改文件或目录的权限，权限分别是用户、组、其他用户。
* `chmod 777 file` 将文件的权限设置为 777。

### chown
`chown` 命令可以修改文件或目录的所有者。
* `chown user:group file` 将文件的所有者设置为 user，组设置为 group。

# Linux 系统调用
## IO 相关
### select
* 将待监听的 Socket 加入文件描述符集合中，调用 select 函数。
* 集合拷贝到内核空间，内核遍历集合，检查是否有事件发生并进行标记（可读或可写）。
* 将集合拷贝回用户空间，用户遍历集合，处理事件。
### poll
* 与 select 类似，突破了 select 文件描述符数量限制，但是没有突破系统文件描述符限制。
* 采用链表存储文件描述符，遍历链表，检查是否有事件发生。
* 性能略优于 select。
### epoll
* epoll_create 创建 epoll 实例。
* 将需要监控的 Socket 使用 epoll_ctl 函数加入 epoll 集合中（操作系统使用**红黑树**来维护）。
* epoll 使用链表来维护就绪事件，Socket 事件到达会通过回调加入这个链表。
* 使用 epoll_wait 函数获取就绪事件。
###  边缘触发 & 水平触发
* 边缘触发：被监控的 Socket 有事件发生时，只会通知一次，不会重复通知。**一般和非阻塞 IO 一起使用**。
* 水平触发：被监控的 Socket 有事件发生时，会一直通知，直到事件处理完毕。
* select 和 poll 只支持水平触发。
* epoll 默认是水平触发，也支持边缘触发。

# 参考链接
* [Linux 命令](https://xiaolincoding.com/os/9_linux_cmd/linux_network.html)