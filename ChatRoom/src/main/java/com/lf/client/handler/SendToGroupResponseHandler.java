package com.lf.client.handler;

import com.lf.common.command.Command;
import com.lf.common.handler.service.HandlerService;
import com.lf.common.packet.SendMessageToGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/01
 */
@ChannelHandler.Sharable
@Service
public class SendToGroupResponseHandler extends SimpleChannelInboundHandler<SendMessageToGroupResponsePacket> implements HandlerService {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, SendMessageToGroupResponsePacket sendMessageToGroupResponsePacket) throws Exception {
        System.out.println(new Date() + "群聊【" + sendMessageToGroupResponsePacket.getGroupId() + "】\n"
                + "【" + sendMessageToGroupResponsePacket.getUserName() + "】：" + sendMessageToGroupResponsePacket.getMsg());
    }

    @Override
    public Byte getCommand() {
        return Command.SEND_MESSAGE_TO_GROUP_RESPONSE;
    }
}
