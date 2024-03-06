package com.lf.client.handler;

import com.lf.common.command.Command;
import com.lf.common.handler.service.HandlerService;
import com.lf.common.packet.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/29
 */
@ChannelHandler.Sharable
@Service
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> implements HandlerService {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupResponsePacket quitGroupResponsePacket) throws Exception {
        System.out.println(quitGroupResponsePacket.getMsg());
    }

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_RESPONSE;
    }
}
