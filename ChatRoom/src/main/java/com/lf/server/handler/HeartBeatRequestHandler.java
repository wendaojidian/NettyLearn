package com.lf.server.handler;

import com.lf.common.command.Command;
import com.lf.common.handler.service.HandlerService;
import com.lf.common.packet.HeartBeatRequestPacket;
import com.lf.common.packet.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/07
 */
@Service
@ChannelHandler.Sharable
@Slf4j
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> implements HandlerService {
    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT_REQUEST;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, HeartBeatRequestPacket heartBeatRequestPacket) throws Exception {
        log.info("心跳❤️");
        HeartBeatResponsePacket heartBeatResponsePacket = new HeartBeatResponsePacket();
        channelHandlerContext.write(heartBeatResponsePacket);
    }
}
