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
- AttributeKey用法

> 创建一个AttributeKey 
> - AttributeKey<String> SESSION_ID = AttributeKey.valueOf("sessionId);（优先读取缓存，没有则新建一个）
> - AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
> 在ChannelHandlerContext上设置属性
> - channelHandlerContext.attr(LOGIN).set(true);
> 获取并使用属性
> - boolean login = channelHandlerContext.attr(LOGIN).get();
> 

- SimpleChannelInboundHandler，自动实现了类型判断和对象传递，通过泛型指定数据类型

``` java
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
    
    }
}
```

- ctx.writeAndFlash() VS ctx.channel().writeAndFlash()
> ctx.writeAndFlash()方法从当前节点开始，向前找到第一个OutBound类型的Handler，把对象往前传播，如果对象确认不需要经过其他OutBound类型的Handler处理，就是用该方法。
> ctx.channel().writeAndFlash()方法从Pipeline链的最后一个Outbound类型的Handler开始，把对象往前传播

## QA
- Netty在收到数据之后，会回调channelRead()方法，这里第二个参数msg是一个Object，为什么Netty不直接把这个参数类型定义成ByteBuf？
- 客户端登录成功或失败后，如何把成功或者失败的标识绑定在客户端的连接上？
- 服务器如何高效避免客户端重新登录？
- 