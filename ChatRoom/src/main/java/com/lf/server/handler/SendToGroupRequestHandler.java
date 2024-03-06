package com.lf.server.handler;

import com.lf.common.command.Command;
import com.lf.common.handler.service.HandlerService;
import com.lf.common.packet.SendMessageToGroupRequestPacket;
import com.lf.common.packet.SendMessageToGroupResponsePacket;
import com.lf.common.util.GroupUtil;
import com.lf.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import org.springframework.stereotype.Service;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/01
 */
@Service
@ChannelHandler.Sharable
public class SendToGroupRequestHandler extends SimpleChannelInboundHandler<SendMessageToGroupRequestPacket> implements HandlerService {
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

    @Override
    public Byte getCommand() {
        return Command.SEND_MESSAGE_TO_GROUP_REQUEST;
    }
}
