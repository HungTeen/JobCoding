## Nacos 架构
<img src="/knowledge/assets/nacos/nacos-architecture.png" width="1000">

## Nacos 注册流程

<img src="/knowledge/assets/nacos/nacos-register.png" width="1600">

### 服务注册发起

* 通过 NamingService 调用注册接口，根据传入的参数（服务名、IP 地址、端口等）组装实例信息。
* 组装注册请求，向 Nacos 发起远程调用，如果是集群模式，请求会发送到集群中的一个节点。
  - 集群模式下，后台线程会先拿到集群服务列表，随机选择一个节点发送请求。
  - 这样**可以做到负载均衡，避免单点故障（保证高可用）**。

### Nacos 路由转发
* 当 Nacos 结点发现请求不是发给自己的时候，会将请求转发给集群中的其他节点。
  - 根据请求的内容计算出 distroTag，然后对其取模，获得对应的 Nacos 节点。
  - 如果请求的节点是自己，就直接处理请求，否则转发给对应的节点。

#### 为什么还要转发？不是客户端负载均衡过了吗？

### Nacos 实例注册

<img src="/knowledge/assets/nacos/nacos-resolve.png" width="900">

* Nacos 会将实例信息保存在 ConcurrentHashMap 中，key 是服务名，value 是一个 CopyOnWriteArrayList。
* Nacos 将向客户端同步的任务交给后台线程处理，这样可以避免阻塞主线程。
* Nacos 一秒后会将实例信息同步到其他节点，这样可以保证集群数据的一致性。

## Distro 设计思想
Distro 是集 Gossip 和 Eureka 协议的优点并加以优化后出现的。
* **平等机制**：每个节点都是平等的，都可以处理写请求。
* **健康检查机制**：每个节点只保存一部分数据，每个节点都会定时检查自己负责的数据是否健康。
* **本地读机制**：每个节点都单独的处理读请求，不需要转发给其他节点。
  - 及时响应，不需要转发给其他节点。
  - 保证高可用，即使出现了分区，也可以正常处理读请求，网络恢复后也可以通过健康检查达到数据一致性。
* **新节点加入机制**：新节点加入时，会轮询所有实例并进行全量数据拉取。
* **路由转发机制**：当请求不是发给自己的时候，会将请求转发给集群中的其他节点。

## Nacos 健康检查
Nacos 中包含两种服务：非持久化服务和持久化服务。
* 非持久化服务：服务实例注册后需要定期向 Nacos 节点发送心跳，否则会在过期后被剔除。
* 持久化服务：Nacos 节点会主动向服务实例发起健康检查，并对其健康结果进行标记。
## 参考链接
* [Nacos 架构原理](https://www.cnblogs.com/jackson0714/p/nacos_register.html)
* [备用地址](http://www.passjava.cn)
* [Distro 协议](https://nacos.io/docs/ebook/ktwggk/)