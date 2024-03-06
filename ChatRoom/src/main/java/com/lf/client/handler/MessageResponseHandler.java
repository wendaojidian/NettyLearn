package com.lf.client.handler;

import com.lf.common.command.Command;
import com.lf.common.handler.service.HandlerService;
import com.lf.common.packet.MessageReponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

/**
 * @author liufan
 * @description: 发送消息、处理服务端消息回复
 * @since 2024/02/21
 */
@ChannelHandler.Sharable
@Service
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageReponsePacket> implements HandlerService {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, MessageReponsePacket messageReponsePacket) {
        System.out.println(messageReponsePacket.getMsg());
    }

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
