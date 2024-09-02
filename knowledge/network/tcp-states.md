## TCP 状态机

<img src="/knowledge/assets/network/tcp-states.png" width="750">

## 三次握手
<img src="/knowledge/assets/network/tcp-connect.png" width="600">

### 第一次握手丢失（服务端未收到 SYN）怎么办？
客户端重传 SYN（根据 `tcp_syn_retries`），直到收到服务端的 SYN + ACK 或者达到重传次数上限。
### 第二次握手丢失（客户端未收到 SYN + ACK）怎么办？
* 客户端重传 SYN（根据 `tcp_syn_retries`），直到收到服务端的 SYN + ACK 或者达到重传次数上限。
* 服务端重传 SYN + ACK（根据 `tcp_synack_retries`），直到收到客户端的 ACK 或者达到重传次数上限。
### 第三次握手丢失（服务端未收到 ACK）怎么办？
服务端重传 SYN + ACK（根据 `tcp_synack_retries`），直到收到客户端的 ACK 或者达到重传次数上限。

## 四次挥手
<img src="/knowledge/assets/network/tcp-disconnect.png" width="600">

### 两种关闭连接的方式？
* close()：同时关闭发送和接受数据的通道，FIN_WAIT2 只能持续一定时间（`tcp_fin_timeout`）。
* shutdown()：只关闭发送数据的通道，未接收到 FIN 会一直等待。
### 第一次挥手丢失（服务端未收到 FIN）怎么办？
客户端重传 FIN（根据 `tcp_orphan_retries`），直到收到服务端的 ACK 或者达到重传次数上限。
### 第二次挥手丢失（客户端未收到 ACK）怎么办？
客户端重传 FIN（根据 `tcp_orphan_retries`），直到收到服务端的 ACK 或者达到重传次数上限。
### 第三次挥手丢失（客户端未收到 FIN）怎么办？
服务端重传 FIN（根据 `tcp_orphan_retries`），直到收到客户端的 ACK 或者达到重传次数上限。
### 第四次挥手丢失（服务端未收到 ACK）怎么办？
* 服务端重传 FIN（根据 `tcp_orphan_retries`），直到收到客户端的 ACK 或者达到重传次数上限。
* 客户端收到 FIN 后，会**重置 2 MSL 的定时器**。

## Socket 编程
<img src="/knowledge/assets/network/tcp-socket.png" width="600">

## 参考链接
* [TCP 状态机](https://blog.csdn.net/shimazhuge/article/details/5682177)