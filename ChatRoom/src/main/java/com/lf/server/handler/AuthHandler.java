package com.lf.server.handler;

import com.lf.attribute.AttributeConstants;
import com.lf.util.LoginUtil;
import com.lf.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author liufan
 * @description: 服务端登录校验handler
 * @since 2024/02/22
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (SessionUtil.isLogin(ctx.channel())) {
            System.out.println(new Date() + ": 登录校验通过");
            ctx.pipeline().remove(this);
        } else {
            System.out.println(new Date() + ": 用户未登录，强制登出");
            ctx.channel().close();
        }
        super.channelRead(ctx, msg);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if (SessionUtil.isLogin(ctx.channel())) {
            System.out.println(new Date() + ": 用户已登录，登录校验handler移除");
        } else {
            System.out.println(new Date() + ": 登录校验失败，连接关闭");
        }
        super.handlerRemoved(ctx);
    }
}
