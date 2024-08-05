## Core Components
### Bootstrap
* Bootstrap 通常使用 connect 方法连接远程服务器，也可以使用 bind 方法作为 UDP 通信的一端。
* ServerBootstrap 使用 bind 方法绑定本地端口，等待客户端的连接。
* Bootstrap 需要设置一个 EventLoopGroup，用于处理所有的事件，一个 Channel 类型，用于处理数据的读写。
* ServerBootstrap 需要设置两个 EventLoopGroup，一个用于处理客户端的连接，一个用于处理客户端的数据读写。

### Channel
Channel 是 Netty 对网络操作的抽象类，一旦客户端成功连接服务端就会创建一个 Channel对其绑定。
* NioSocketChannel：基于 NIO 的 TCP 连接，对应 Socket。
* NioServerSocketChannel：基于 NIO 的 TCP 服务器，对应 ServerSocket。

### EventLoop
EventLoop 负责监听网络事件并调用事件处理器进行相关 IO 操作。
* EventLoop 负责注册到其上的 Channel 的所有 IO 操作。
* EventLoopGroup 包含多个 EventLoop，每个 EventLoop 可以包含多个 Channel。
* EventLoop 和 Thread 是一一对应的，一个 EventLoop 在其生命周期内只和一个 Thread 绑定。

### ChannelHandler
ChannelHandler 是具体的消息处理类，用于处理发送和接收的数据。
* 一个Channel包含一个ChannelPipeline，ChannelPipeline 包含多个 ChannelHandler。
* ChannelHandler 可以通过 ChannelPipeline 添加到 Channel 中，用于处理 Channel 的数据。

### ByteBuf
ByteBuf 是 Netty 对字节数据的抽象类，提供了多种方法用于操作字节数据，它对 Java NIO ByteBuffer 的封装。
* 可扩展性：ByteBuf 可以自动扩容缩容，不需要手动调整容量。
* 零拷贝优化：减少拷贝次数，提高性能。
* 池化：ByteBuf 可以通过内存池来重用内存，减少内存分配和释放的开销。
* 更安全的内存释放：ByteBuf 通过引用计数来管理内存的释放，避免内存泄漏。
