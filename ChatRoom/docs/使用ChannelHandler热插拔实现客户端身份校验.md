# 提取登录校验逻辑，构建handler
- AuthHandler
- 添加channelRead0方法，校验登录状态，如果登录，则移除该handler，否则关闭连接
- 添加handlerRemoved方法，在该方法中，也要判断登录态，如果登录，则打印已登录，移除handler，否则为未登录关闭连接。
- 