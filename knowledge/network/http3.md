## HTTP/3 有什么新特性
* 使用基于 UDP 协议的 QUIC 协议，不再基于 TCP 协议。
* 无队头阻塞。
* 更快地连接建立，最快可以达到 0RTT 的效果。
* 连接迁移没有成本。

## 无队头阻塞
* UDP 并不关心数据包到达的顺序，因此不存在队头阻塞问题。
* QUIC 协议会保证数据包的可靠传输
* 单调递增的序列号
  - 重传的序号一定大于超时的序号。
  - 减少歧义，使得计算的 RTT 更加准确。

## 更快地连接建立
* HTTP/2.0 在进行 TCP 三次握手（内核协议栈实现的传输层）时，不能进行 TLS 握手（OpenSSL 库实现的表示层）。
* 而 HTTP/3 基于 QUIC 协议（包含了 TLS）可以同时进行 TLS 握手和连接建立。
* 初次连接时，由于 QUIC 使用 TLS 1.3，只需一个 RTT 即可建立连接和秘钥协商。
* 再第二次连接时，数据包可以和连接信息、TLS 信息一起发送，最快可以达到 0RTT 的效果。

<img src="/knowledge/assets/network/http3-0rtt.png" width="900">

## HTTP/3.0 帧格式
* 帧头只包含帧类型和数据长度。
* 使用 QPACK 算法维护头部信息。
  - QUIC 有两个特殊的单向流，分别用于发送和接收头部信息。
  - QPACK Encoder Stream 用于通知对端头部信息的变化。
  - QPACK Decoder Stream 用于通知变化已经更新到字典。
  - 静态表内容扩充到 91 项。

#### 为什么不用 HTTP/2.0 的 HPACK 算法
* HPACK 适用于保证顺序的 TCP 协议，但是 QUIC 无需保证顺序。
* QUIC 中使用 HPACK 可能会导致队头阻塞。

## 参考链接
* [从 HTTP/1 到 HTTP/3 ](https://dbwu.tech/posts/http_evolution/#quic)