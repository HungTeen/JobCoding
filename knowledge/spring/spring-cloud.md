## Spring Cloud 核心组件
### 服务注册与发现
* Eureka：Netflix 开源的服务注册与发现组件。
* Consul：提供服务发现和配置管理。

### 服务调用
* Ribbon：负载均衡组件。
* Feign：基于 Ribbon 和 Hystrix 的声明式远程服务调用组件。

### 服务熔断
* Hystrix：提供熔断、隔离、Fallback、监控等功能。

### 服务网关
* Zuul：提供动态路由、监控、弹性、安全等功能。
* Spring Cloud Gateway：提供路由转发、过滤器链、负载均衡等功能。

### 配置中心
* Spring Cloud Config：提供配置管理服务。

### 服务监控
* Sleuth：集成了 Zipkin，在微服务之间提供唯一的 ID，用于日志追踪。

## 参考链接
* [Spring Cloud 核心组件](https://www.mianshiya.com/bank/1797453053310402561/question/1796270754107621378)