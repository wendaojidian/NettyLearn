package com.lf.client.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/01/17
 */
public class HelloClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + "客户端写出数据");
        ByteBuf buf = getByteBuf(ctx);
        ctx.channel().writeAndFlush(buf);
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date() + "客户端读取数据->" + buf.toString(StandardCharsets.UTF_8));
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        ByteBuf buf = ctx.alloc().buffer();
        byte[] bytes = "Hello Netty".getBytes(StandardCharsets.UTF_8);
        buf.writeBytes(bytes);
        return buf;
    }
}
