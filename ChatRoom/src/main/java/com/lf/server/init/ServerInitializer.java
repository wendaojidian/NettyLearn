package com.lf.server.init;

import com.lf.common.code.Shield;
import com.lf.server.handler.IMIdleStateHandler;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/04
 */
@Component
public class ServerInitializer extends ChannelInitializer<NioSocketChannel> {
    @Autowired
    private ChannelDuplexHandler packetCodeHandler;
    @Autowired
    private ChannelInboundHandler loginRequestHandler;
    @Autowired
    private ChannelInboundHandler authHandler;
    @Autowired
    private ChannelInboundHandler imHandler;

    @Override
    protected void initChannel(NioSocketChannel ch) {
        ch.pipeline().addLast(new IMIdleStateHandler());
        ch.pipeline().addLast(new Shield());
        ch.pipeline().addLast(packetCodeHandler);
        ch.pipeline().addLast(loginRequestHandler);
        ch.pipeline().addLast(authHandler);
        ch.pipeline().addLast(imHandler);
    }
}
