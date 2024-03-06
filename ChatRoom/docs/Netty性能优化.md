# 1.无状态的Handler改为单例模式（自己实现或依赖于Spring） @ChannelHandler.Sharable，有状态可以通过channel().attr()添加
# 2.编解码操作合并到一个Handler当中
# 3.合并冗余Handler
# 4.耗时操作（IO密集型等）线程池完成。
