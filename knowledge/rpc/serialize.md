## 序列化与反序列化

### 模块划分
<img src="/knowledge/assets/rpc/serialize.png" width="900">

* IDL（Interface Description Language）：接口描述语言，与平台无关。
* IDL Compiler：将 IDL 编译转换为各个语言支持的动态库。

### JDK 序列化
* 不支持跨语言。
* 序列化后的字节流比较大，效率不高。

### Thrift
* Thrift 由 Facebook 开发，不仅是一个序列化协议，更是一个轻量 RPC 框架。
* 具有很高的性能，但与框架强依赖。

### Protobuf
* 标准的 IDL 和 IDL 编译器，对开发者友好。
* 序列化数据简单紧凑（长度是 XML 的 1/3）。
* 解析速度非常快（是 XML 的 20-100 倍）。
* 动态库友好，反序列化只需一行代码。
* 使用比较复杂（需要编写 IDL 等）。

### ProtoStuff
* 使得 Protobuf 更加易用，无需编写 IDL。

### Kryo
* 主要支持 Java 语言。
* 性能很好。

### Hessian2
* Dubbo 默认序列化方式。
* 跨语言、性能优秀。

### Avro
* 支持多种语言。
* 使用复杂，Java API 不友好。

### 性能对比
<img src="/knowledge/assets/rpc/benchmark.png" width="900">

## 参考链接
* [序列化与反序列化](https://tech.meituan.com/2015/02/26/serialization-vs-deserialization.html)
* [序列化框架选型对比](https://juejin.cn/post/6974565210161954829#heading-9)