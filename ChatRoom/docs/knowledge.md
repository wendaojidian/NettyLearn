# �����¼
## ֪ʶ��
- Netty�����ӵĳ�����Channel
- ctx.alloc()

> �� Netty �У�`ctx.alloc()` ���ڻ�ȡ���ڷ����ڴ�� `ByteBufAllocator`��
> �����������һ����ָ���� `ChannelHandlerContext` ������� `ByteBufAllocator` ʵ����
> `ByteBufAllocator` ���ڷ����ڴ滺�������Ա��ڴ�����������ʱʹ�á�ͨ��ʹ�� `ctx.alloc()` 
> ���ص� `ByteBufAllocator`������ȷ�����������ݴ��������ʹ�����ض� `ChannelHandlerContext` 
> ��������ʵ����ڴ���������Ӷ�������ܲ����ⲻ��Ҫ���ڴ����͸��ơ�

- ��Netty�У�handler���ǵ���ģʽ������ÿ���µ����ӣ�Netty����Ϊ�䴴��һ���µ�ChannelHandlerʵ������������̰߳�ȫ���⡣
- AttributeKey�÷�

> ����һ��AttributeKey 
> - AttributeKey<String> SESSION_ID = AttributeKey.valueOf("sessionId);�����ȶ�ȡ���棬û�����½�һ����
> - AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
> ��ChannelHandlerContext����������
> - channelHandlerContext.attr(LOGIN).set(true);
> ��ȡ��ʹ������
> - boolean login = channelHandlerContext.attr(LOGIN).get();
> 

- SimpleChannelInboundHandler���Զ�ʵ���������жϺͶ��󴫵ݣ�ͨ������ָ����������

``` java
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) {
    
    }
}
```

## QA
- Netty���յ�����֮�󣬻�ص�channelRead()����������ڶ�������msg��һ��Object��ΪʲôNetty��ֱ�Ӱ�����������Ͷ����ByteBuf��
- �ͻ��˵�¼�ɹ���ʧ�ܺ���ΰѳɹ�����ʧ�ܵı�ʶ���ڿͻ��˵������ϣ�
- ��������θ�Ч����ͻ������µ�¼��
- 