package com.lf.client.handler;

import com.lf.packet.SendMessageToGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/01
 */
public class SendToGroupResponseHandler extends SimpleChannelInboundHandler<SendMessageToGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SendMessageToGroupResponsePacket sendMessageToGroupResponsePacket) throws Exception {
        System.out.println(new Date() + "群聊【" + sendMessageToGroupResponsePacket.getGroupId() + "】\n"
                + "【" + sendMessageToGroupResponsePacket.getUserName() + "】：" + sendMessageToGroupResponsePacket.getMsg());
    }
}
