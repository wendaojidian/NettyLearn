package com.lf.server.handler;

import com.lf.packet.SendMessageToGroupRequestPacket;
import com.lf.packet.SendMessageToGroupResponsePacket;
import com.lf.util.GroupUtil;
import com.lf.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/01
 */
public class SendToGroupRequestHandler extends SimpleChannelInboundHandler<SendMessageToGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SendMessageToGroupRequestPacket sendMessageToGroupRequestPacket) throws Exception {
        String groupId = sendMessageToGroupRequestPacket.getGroupId();
        ChannelGroup channelGroup = GroupUtil.getChannelGroup(sendMessageToGroupRequestPacket.getGroupId());
        SendMessageToGroupResponsePacket sendMessageToGroupResponsePacket = new SendMessageToGroupResponsePacket();
        sendMessageToGroupResponsePacket.setGroupId(groupId);
        sendMessageToGroupResponsePacket.setMsg(sendMessageToGroupRequestPacket.getMsg());
        sendMessageToGroupResponsePacket.setUserName(SessionUtil.getUserName(channelHandlerContext.channel()));
        channelGroup.writeAndFlush(sendMessageToGroupResponsePacket);
    }
}
