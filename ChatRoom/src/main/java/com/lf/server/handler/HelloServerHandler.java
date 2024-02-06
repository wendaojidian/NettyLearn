package com.lf.server.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/02
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(new Date() + ":服务端读取数据->" + buf.toString(StandardCharsets.UTF_8));
        System.out.println(new Date() + "：服务端写数据");
        ByteBuf writeBuf = getByteBuf(ctx);
        ctx.channel().writeAndFlush(writeBuf);
    }

    private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
        byte[] bytes = "Hi Client, I'm server".getBytes(StandardCharsets.UTF_8);
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeBytes(bytes);
        return buf;
    }

}
