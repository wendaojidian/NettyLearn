package com.lf.server.handler;

import com.lf.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liufan
 * @description: 服务端登录校验handler
 * @since 2024/02/22
 */
@ChannelHandler.Sharable
@Service
@Slf4j
public class AuthHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info(new Date() + "服务端开始校验用户登录态...");
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
