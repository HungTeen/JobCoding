## 注册中心
* Nacos：阿里巴巴开源的注册中心。
* Zookeeper：Apache 开源的注册中心。
* Eureka：Netflix 开源的注册中心。
* Consul：HashiCorp 开源的注册中心。
* Etcd：Go 语言编写的注册中心，类似于 Redis。

### 对比
|        | Nacos | Zookeeper | Eureka | Consul |
|--------|-------|-----------|--------|--------|
| 语言     | Java  | Java      | Java   | Go     |
| 一致性    | Raft  | ZAB       | Raft   | Raft   |
| CAP 定理 | CP/AP | CP        | AP     | CP     |

* 支持 AP 的注册中心有：Nacos、Eureka，而其中 Nacos 的使用更加广泛。


## 配置中心
* Nacos：阿里巴巴开源的配置中心，拥有较大的社区基础，应用广泛。
* Apollo：携程开源的配置中心，功能全面，支持多种语言，使用复杂。
* Spring Cloud Config：Spring Cloud 官方提供的配置中心，与 Spring Cloud 框架集成度高。
  * 通过 Git 实现配置的版本控制。
* Zookeeper：Apache 开源的配置中心，功能简单，适合小型项目。
  * 不支持灰度发布、权限控制等功能。

### 对比
* 服务发现与注册：Nacos 支持服务注册、发现、健康检查等功能。Apollo 定位是配置中心，服务注册与发现不是强项。
* 性能：Nacos 性能较好，Apollo 性能较差，Spring Cloud Config 性能更差。
* 功能：Apollo 功能最全，Nacos 功能次之，Spring Cloud Config 功能最少。
* 易用性：Nacos 和 Spring Cloud Config 易用性好，Apollo 部署困难。

## 参考链接
* [注册中心](https://cloud.tencent.com/developer/article/2405336)
* [配置中心](https://www.mianshiya.com/bank/1797453053310402561/question/1795829113869697025)
* [技术选型](https://nacos.io/blog/faq/nacos-user-question-history15177/)
* [Zookeeper 为什么不适合配置中心](https://juejin.cn/post/7072302179008610317)