package com.lf.client.init;

import com.lf.common.code.Shield;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/06
 */
@Component
@ComponentScan(basePackages = {"com.lf.client", "com.lf.common"})
public class ClientInitializer extends ChannelInitializer<NioSocketChannel> {
    @Autowired
    private ChannelDuplexHandler packetCodeHandler;
    @Autowired
    private ChannelInboundHandler loginResponseHandler;
    @Autowired
    private ChannelInboundHandler imHandler;
    @Override
    protected void initChannel(NioSocketChannel ch) {
        ch.pipeline().addLast(new Shield());
        ch.pipeline().addLast(packetCodeHandler);
        ch.pipeline().addLast(loginResponseHandler);
        ch.pipeline().addLast(imHandler);
    }
}
