package com.lf.server.handler;

import com.lf.code.PacketCodeC;
import com.lf.packet.LoginResponsePacket;
import com.lf.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;

/**
 * @author liufan
 * @description: 服务端响应登录请求handler
 * @since 2024/02/06
 */
public class LoginServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //1.获取并解析入参
        System.out.println(new Date() + "客户端开始登录");
        ByteBuf buf = (ByteBuf) msg;
        Packet loginPacket = PacketCodeC.PACKET_CODEC.decode(buf);
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (valid(loginPacket)) {
            System.out.println(new Date() + "登录成功");
            loginResponsePacket.setSuccess(true);
        } else {
            System.out.println(new Date() + "登录失败");
            loginResponsePacket.setSuccess(false);
        }
        //2.向客户端发送登录响应
        ByteBuf responseBuf = PacketCodeC.PACKET_CODEC.encode(ctx.alloc().buffer(), loginResponsePacket);
        ctx.channel().writeAndFlush(responseBuf);
    }

    private boolean valid(Packet packet) {
        return true;
    }
}
