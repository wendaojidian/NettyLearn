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
- 
## QA
- Netty���յ�����֮�󣬻�ص�channelRead()����������ڶ�������msg��һ��Object��ΪʲôNetty��ֱ�Ӱ�����������Ͷ����ByteBuf��
- �ͻ��˵�¼�ɹ���ʧ�ܺ���ΰѳɹ�����ʧ�ܵı�ʶ���ڿͻ��˵������ϣ�
- ��������θ�Ч����ͻ������µ�¼��
- 