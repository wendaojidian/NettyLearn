package com.lf.client.handler;

import com.lf.attribute.AttributeConstants;
import com.lf.code.PacketCodeC;
import com.lf.packet.LoginPacket;
import com.lf.packet.LoginResponsePacket;
import com.lf.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.UUID;

/**
 * @author liufan
 * @description: 处理服务端登录响应
 * @since 2024/02/21
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + " 客户端开始登录");
        Packet packet = new LoginPacket(UUID.randomUUID().toString(), "LiuFan", "123456");
        ctx.channel().writeAndFlush(packet);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) {
        if (loginResponsePacket.getSuccess()) {
            System.out.println(new Date() + " 登录成功");
            channelHandlerContext.channel().attr(AttributeConstants.LOGIN).set(true);
        } else {
            System.out.println(new Date() + " 登录失败");
            channelHandlerContext.channel().attr(AttributeConstants.LOGIN).set(false);
        }
    }
}
