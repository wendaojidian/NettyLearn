package com.lf.common.handler.service.impl;

import com.lf.common.handler.service.HandlerService;
import com.lf.common.handler.service.HandlerServiceFactory;
import com.lf.common.packet.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/01
 */
@ChannelHandler.Sharable
@Service
public class ImHandler extends SimpleChannelInboundHandler<Packet> {
    @Autowired
    private HandlerServiceFactory handlerServiceFactory;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Packet packet) throws Exception {
        HandlerService handlerService = handlerServiceFactory.getService(packet.getCommand());
        ((ChannelInboundHandler) handlerService).channelRead(channelHandlerContext, packet);
    }
}
