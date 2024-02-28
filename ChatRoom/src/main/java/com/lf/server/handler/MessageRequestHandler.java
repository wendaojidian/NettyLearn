package com.lf.server.handler;

import com.lf.attribute.AttributeConstants;
import com.lf.packet.MessageReponsePacket;
import com.lf.packet.MessageRequestPacket;
import com.lf.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author liufan
 * @description: 处理客户端发送消息的请求
 * @since 2024/02/21
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageRequestPacket messageRequestPacket) {
        System.out.println(new Date() + "服务端收到客户端消息：" + messageRequestPacket.getMsg());
        MessageReponsePacket messageReponsePacket = new MessageReponsePacket();
        if ("Server".equals(messageRequestPacket.getToUserId())) {
            messageReponsePacket.setMsg("服务端回复客户端消息【" + messageRequestPacket.getMsg() + "】");
            channelHandlerContext.channel().writeAndFlush(messageReponsePacket);
        } else {
            String toUserId = messageRequestPacket.getToUserId();
            Channel channel = SessionUtil.getChannel(toUserId);
            if (null != channel && SessionUtil.isLogin(channel)) {
                String userId = channelHandlerContext.channel().attr(AttributeConstants.SESSION).get().getUserId();
                messageReponsePacket.setMsg(userId + ": " + messageRequestPacket.getMsg());
                channel.writeAndFlush(messageReponsePacket);
            } else {
                messageReponsePacket.setMsg(messageRequestPacket.getToUserId() + "未登录");
                channelHandlerContext.channel().writeAndFlush(messageReponsePacket);
            }
        }
    }
}
