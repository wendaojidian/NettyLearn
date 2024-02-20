# 问题记录
## 知识点
- Netty对连接的抽象是Channel
- ctx.alloc()

> 在 Netty 中，`ctx.alloc()` 用于获取用于分配内存的 `ByteBufAllocator`。
> 这个方法返回一个与指定的 `ChannelHandlerContext` 相关联的 `ByteBufAllocator` 实例。
> `ByteBufAllocator` 用于分配内存缓冲区，以便在处理网络数据时使用。通过使用 `ctx.alloc()` 
> 返回的 `ByteBufAllocator`，可以确保在网络数据处理过程中使用与特定 `ChannelHandlerContext` 
> 相关联的适当的内存分配器，从而提高性能并避免不必要的内存分配和复制。

- 在Netty中，handler不是单例模式，对于每个新的连接，Netty都会为其创建一个新的ChannelHandler实例，这避免了线程安全问题。
- 
## QA
- Netty在收到数据之后，会回调channelRead()方法，这里第二个参数msg是一个Object，为什么Netty不直接把这个参数类型定义成ByteBuf？
- 客户端登录成功或失败后，如何把成功或者失败的标识绑定在客户端的连接上？
- 服务器如何高效避免客户端重新登录？
- 