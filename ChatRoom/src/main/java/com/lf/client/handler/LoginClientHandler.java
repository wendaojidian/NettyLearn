package com.lf.client.handler;

import com.lf.attribute.AttributeConstants;
import com.lf.code.PacketCodeC;
import com.lf.packet.LoginPacket;
import com.lf.packet.LoginResponsePacket;
import com.lf.packet.MessageReponsePacket;
import com.lf.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

/**
 * @author liufan
 * @description: 客户端登录请求的handler
 * @since 2024/02/06
 */
public class LoginClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println(new Date() + " 客户端开始登录");
        Packet packet = new LoginPacket(UUID.randomUUID().toString(), "LiuFan", "123456");
        ByteBuf buf = PacketCodeC.PACKET_CODEC.encode(ctx.alloc().buffer(), packet);
        // 写数据
        ctx.channel().writeAndFlush(buf);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf buf = (ByteBuf) msg;
        Packet packet = PacketCodeC.PACKET_CODEC.decode(buf);
        if (packet instanceof LoginResponsePacket) {
            if (((LoginResponsePacket)packet).getSuccess()) {
                System.out.println(new Date() + " 登录成功");
                ctx.channel().attr(AttributeConstants.LOGIN).set(true);
            } else {
                System.out.println(new Date() + " 登录失败");
                ctx.channel().attr(AttributeConstants.LOGIN).set(false);
            }
        } else if (packet instanceof MessageReponsePacket) {
            System.out.println("收到服务端回复消息：" + ((MessageReponsePacket)packet).getMsg());
        }

    }


}
