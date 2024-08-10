## OSI & TCP/IP
* TCP/IP 应用层
  * OSI 应用层：为计算机用户提供服务。
  * OSI 表示层：数据处理（数据编解码、加密解密、压缩解压）。
  * OSI 会话层：管理（建立、维护、重连）应用程序之间的会话。
* TCP/IP 传输层
    * OSI 传输层：为两台主机的进程之间提供通用的数据传输服务。
* TCP/IP 网络层
    * OSI 网络层：路由与寻址。
* TCP/IP 网络接口层
    * OSI 数据链路层：帧编码和误差纠正控制。
    * OSI 物理层：透明传输比特流。

### 计算机网络为什么要分层？
* 计算机网络是一个非常复杂的系统，为了解决复杂问题，将问题分解成若干个较小的问题，然后分别解决。
* 分层优点：
  * 各层独立，无需考虑其他层的实现细节。
  * 灵活性更好，可以替换某一层的实现，而不影响其他层。（如 HTTP/2.0 和 HTTP/3.0）
* 分层缺点：
  * 层次变多，可能会影响处理效率。
  * 可能有些功能重复出现，会增加系统的开销。
<img src="/knowledge/assets/network/osi.jpg" width="800">

## Protocols
### 应用层
* HTTP（HyperText Transfer Protocol）：超文本传输协议，基于 TCP 协议，端口 80。
* HTTPS（HyperText Transfer Protocol Secure）：基于 HTTP 协议，通过 SSL/TLS 加密，端口 443。
* DNS（Domain Name System）：域名系统，基于 UDP 协议，端口 53。
* FTP（File Transfer Protocol）：文件传输协议，基于 TCP 协议，端口 21。
* SMTP（Simple Mail Transfer Protocol）：简单邮件传输协议，基于 TCP 协议，端口 25。
* POP3（Post Office Protocol 3）：邮局协议，基于 TCP 协议，端口 110。
* DHCP（Dynamic Host Configuration Protocol）：动态主机配置协议，基于 UDP 协议，端口 67、68。
* SNMP（Simple Network Management Protocol）：简单网络管理协议，基于 UDP 协议，端口 161、162。
* Telnet：远程登录协议，基于 TCP 协议，端口 23。
* SSH（Secure Shell）：安全外壳协议，基于 TCP 协议，端口 22。

| Protocol | Port    | TCP/UDP |
|----------|---------|---------|
| FTP      | 21      | TCP     |
| SSH      | 22      | TCP     |
| Telnet   | 23      | TCP     |
| SMTP     | 25      | TCP     |
| DNS      | 53      | UDP     |
| DHCP     | 67、68   | UDP     |
| HTTP     | 80      | TCP     |
| POP3     | 110     | TCP     |
| SNMP     | 161、162 | UDP     |
| HTTPS    | 443     | TCP     |


### 传输层
* TCP（Transmission Control Protocol）：传输控制协议，面向连接，可靠，基于字节流。
* UDP（User Datagram Protocol）：用户数据报协议，无连接，不可靠，基于数据报。

### 网络层
* IP（Internet Protocol）：网络层协议，负责数据包的传输。
* ICMP（Internet Control Message Protocol）：网络控制消息协议，用于网络故障诊断和报告。
* NAT（Network Address Translation）：网络地址转换，用于将内网 IP 地址转换为公网 IP 地址。
* ARP（Address Resolution Protocol）：地址解析协议，用于 IP 地址和 MAC 地址的映射。
* RARP（Reverse Address Resolution Protocol）：反向地址解析协议，用于 MAC 地址和 IP 地址的映射。
* RIP（Routing Information Protocol）：路由信息协议，用于路由选择。
* OSPF（Open Shortest Path First）：开放最短路径优先协议，用于路由选择。
* BGP（Border Gateway Protocol）：边界网关协议，用于自治系统之间的路由选择。

## 网络流程
### 协议栈
* 应用程序调用 Socket 库来委托操作系统协议栈工作。
* 协议栈负责传输层和网络层的工作，将数据封装成数据包，通过网络传输。
* 网卡属于数据链路层，负责将数据包封装成帧，通过物理层传输。
<img src="/knowledge/assets/network/protocol-stack.png" width="800">

### 从输入 URL 到页面展示经历的哪些过程？
* 浏览器解析 URL，URL 中包括协议、域名或 IP、端口、资源路径等。
* 如果是域名，浏览器会查询 DNS，获取域名对应的 IP 地址。
  - DNS 会先查询浏览器缓存，然后查询操作系统缓存以及 hosts 文件，最后向本地 DNS 服务器查询。
* 如果是基于 TCP 的协议，需要先进行三次握手建立连接。
* 浏览器向服务器发送请求报文，请求报文包括请求行、请求头、请求体。
* 服务器接收到请求报文，解析请求报文并进行处理。
* 服务器向浏览器发送响应报文，响应报文包括状态行、响应头、响应体。
* 浏览器收到响应报文，解析响应报文并进行处理（如渲染 HTML 页面）。
* 浏览器不需要和服务器通信时，可以主动关闭 TCP 连接，也可以等待服务器关闭连接。

## 参考文章
* [计算机网络为什么要分层](https://www.cnblogs.com/ricklz/p/16457713.html)
* [小林Coding](https://xiaolincoding.com/network/1_base/what_happen_url.html#%E6%8C%87%E5%8D%97%E5%A5%BD%E5%B8%AE%E6%89%8B-%E5%8D%8F%E8%AE%AE%E6%A0%88)