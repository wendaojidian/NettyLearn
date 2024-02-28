package com.lf.client.handler;

import com.lf.packet.MessageReponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author liufan
 * @description: 发送消息、处理服务端消息回复
 * @since 2024/02/21
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageReponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageReponsePacket messageReponsePacket) {
        System.out.println(messageReponsePacket.getMsg());
    }
}
