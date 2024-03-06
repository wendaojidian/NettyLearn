package com.lf.client.handler;

import com.lf.common.command.Command;
import com.lf.common.handler.service.HandlerService;
import com.lf.common.packet.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/28
 */
@ChannelHandler.Sharable
@Service
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> implements HandlerService {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupResponsePacket createGroupResponsePacket) throws Exception {
        System.out.println(createGroupResponsePacket.getMsg());
    }

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
