package com.lf.client.handler;

import com.lf.client.NettyClient;
import com.lf.common.packet.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/07
 */
@Service
@ChannelHandler.Sharable
@Slf4j
public class HeartBeatTimerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleHeartBeat(ctx);
        super.channelActive(ctx);
    }

    private void scheduleHeartBeat(ChannelHandlerContext ctx) {
        ctx.executor().schedule(() -> {
            if (ctx.channel().isActive()) {
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                log.info("Send ❤️");
                scheduleHeartBeat(ctx);
            } else {
                log.error("连接失败，正在尝试重连");
            }
        }, 5, TimeUnit.SECONDS);
    }
}
