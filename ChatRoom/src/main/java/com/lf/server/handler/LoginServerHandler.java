package com.lf.server.handler;

import com.lf.code.PacketCodeC;
import com.lf.packet.*;
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

        ByteBuf buf = (ByteBuf) msg;
        Packet packet = PacketCodeC.PACKET_CODEC.decode(buf);
        if (packet instanceof LoginPacket) {
            System.out.println(new Date() + "客户端开始登录");
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            if (valid(packet)) {
                System.out.println(new Date() + "登录成功");
                loginResponsePacket.setSuccess(true);
            } else {
                System.out.println(new Date() + "登录失败");
                loginResponsePacket.setSuccess(false);
            }
            //2.向客户端发送登录响应
            ByteBuf responseBuf = PacketCodeC.PACKET_CODEC.encode(ctx.alloc().buffer(), loginResponsePacket);
            ctx.channel().writeAndFlush(responseBuf);
        } else if (packet instanceof MessageRequestPacket) {
            MessageRequestPacket messageRequestPacket = (MessageRequestPacket) packet;
            System.out.println(new Date() + "： 收到客户端消息： " + messageRequestPacket.getMsg());
            MessageReponsePacket messageReponsePacket = new MessageReponsePacket();
            messageReponsePacket.setMsg("服务端回复【" + messageRequestPacket.getMsg() + "】");
            ByteBuf responseBuf = PacketCodeC.PACKET_CODEC.encode(ctx.alloc().buffer(), messageReponsePacket);
            ctx.channel().writeAndFlush(responseBuf);
        }
    }

    private boolean valid(Packet packet) {
        return true;
    }
}
