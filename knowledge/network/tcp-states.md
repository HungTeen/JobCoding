## TCP 状态机

<img src="/knowledge/assets/network/tcp-states.png" width="750">

## 三次握手
<img src="/knowledge/assets/network/tcp-connect.png" width="600">

### 握手丢失怎么办？
#### 第一次握手丢失（服务端未收到 SYN）怎么办？
客户端重传 SYN（根据 `tcp_syn_retries`），直到收到服务端的 SYN + ACK 或者达到重传次数上限。
#### 第二次握手丢失（客户端未收到 SYN + ACK）怎么办？
* 客户端重传 SYN（根据 `tcp_syn_retries`），直到收到服务端的 SYN + ACK 或者达到重传次数上限。
* 服务端重传 SYN + ACK（根据 `tcp_synack_retries`），直到收到客户端的 ACK 或者达到重传次数上限。
#### 第三次握手丢失（服务端未收到 ACK）怎么办？
服务端重传 SYN + ACK（根据 `tcp_synack_retries`），直到收到客户端的 ACK 或者达到重传次数上限。

### 如何优化三次握手？
#### TCP Fast Open
通过 `tcp_fastopen` 开启，可以选择在客户端还是服务端开启。
* 第一次连接时，客户端发送 SYN 和数据。
* 服务端收到 SYN 后，会计算一个 Cookie，下次客户端连接时，带上 Cookie 校验即可。
* 服务端收到 SYN 和 Cookie 后，直接发送 SYN + ACK 和数据。

## 四次挥手
<img src="/knowledge/assets/network/tcp-disconnect.png" width="600">

### 两种关闭连接的方式？
* close()：同时关闭发送和接受数据的通道，FIN_WAIT2 只能持续一定时间（`tcp_fin_timeout`）。
* shutdown()：只关闭发送数据的通道，未接收到 FIN 会一直等待。

### 挥手丢失怎么办
#### 第一次挥手丢失（服务端未收到 FIN）怎么办？
客户端重传 FIN（根据 `tcp_orphan_retries`），直到收到服务端的 ACK 或者达到重传次数上限。
#### 第二次挥手丢失（客户端未收到 ACK）怎么办？
客户端重传 FIN（根据 `tcp_orphan_retries`），直到收到服务端的 ACK 或者达到重传次数上限。
#### 第三次挥手丢失（客户端未收到 FIN）怎么办？
服务端重传 FIN（根据 `tcp_orphan_retries`），直到收到客户端的 ACK 或者达到重传次数上限。
#### 第四次挥手丢失（服务端未收到 ACK）怎么办？
* 服务端重传 FIN（根据 `tcp_orphan_retries`），直到收到客户端的 ACK 或者达到重传次数上限。
* 客户端收到 FIN 后，会**重置 2 MSL 的定时器**。
#### TIME_WAIT 状态的作用？
* 防止历史连接的数据包影响新连接。
* 尽量保证被动关闭的一方能够正确关闭。

### 如何优化四次挥手？
* `tcp_max_tw_buckets`：TIME_WAIT 连接的最大数量，超过这个连接后，无需等待 2 MSL 时间，直接关闭连接。
* 设置 socket 选项，直接发送 RST 终止连接，无需四次挥手。
#### TIME_WAIT 复用
只适用于**主动关闭**的一方，可以通过以下方式复用 TIME_WAIT 状态的连接：
* `tcp_tw_reuse`：允许复用 TIME_WAIT 状态的连接。
* `tcp_timestamps`：防止历史连接的数据包影响新连接。、
* 不建议开启 `tcp_tw_recycle`，有大坑。

## Socket 编程
<img src="/knowledge/assets/network/tcp-socket.png" width="600">

### 半连接队列
* 全连接队列大小由 `somaxconn`、`tcp_max_syn_backlog` 和 `backlog` 决定，比较复杂。

### 全连接队列
* 全连接队列大小由 `somaxconn` 和 `backlog` 决定，取**最小值**。
* 可以使用 `ss -lnt` 查看全连接队列大小。

#### 全连接队列满了怎么办？
由 `tcp_abort_on_overflow` 决定，如果为 1，则**拒绝**新连接；如果为 0，则**忽略**新连接。



## 参考链接
* [TCP 状态机](https://blog.csdn.net/shimazhuge/article/details/5682177)
* [小心 tcp_tw_recycle 有坑](https://xiaolincoding.com/network/3_tcp/tcp_optimize.html)