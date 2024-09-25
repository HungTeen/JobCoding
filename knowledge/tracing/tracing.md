## 链路追踪
* Zipkin：Twitter 开源的分布式实时链路追踪系统。
* Jaeger：Uber 开源的分布式链路追踪系统。
* SkyWalking：Apache 开源的应用系统监控和分析工具。

以上方案通过与 Spring Cloud Sleuth 集成，可以实现链路追踪。

### 对比
<img src="/knowledge/assets/tracing/tracing-cmp.png" width="900">

* Zipkin 和 Jaeger 是通过拦截请求实现的，代码侵入性强；SkyWalking 是通过字节码增强实现的，代码侵入性低。
* SkyWalking 性能最好，pinpoint 性能最差。

### 原理
* 同一个请求的调用使用相同的 Trace ID。
* 每个服务都会记录一个 Parent ID，表示是被哪个服务调用的。
* 具有相同 Parent ID 的服务调用，通过 Span ID 来区分调用顺序。
* 通过在日志中埋点，可以实现链路追踪。

<img src="/knowledge/assets/tracing/tracing-method.png" width="900">

## 参考链接
* [链路追踪](https://www.mianshiya.com/bank/1797453053310402561/question/1796163389530177538)
* [链路追踪技术选型](https://developer.aliyun.com/article/1143845)
* [链路追踪原理](https://github.com/CoderLeixiaoshuai/java-eight-part/blob/master/docs/distributed/%E5%8E%9F%E6%9D%A510%E5%BC%A0%E5%9B%BE%E5%B0%B1%E5%8F%AF%E4%BB%A5%E6%90%9E%E6%87%82%E5%88%86%E5%B8%83%E5%BC%8F%E9%93%BE%E8%B7%AF%E8%BF%BD%E8%B8%AA%E7%B3%BB%E7%BB%9F%E5%8E%9F%E7%90%86.md)