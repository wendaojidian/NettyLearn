package com.lf.server.handler;

import com.lf.common.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/06
 */
@Slf4j
public class IMIdleStateHandler extends IdleStateHandler {
    private final static int READER_TIME = 15;
    public IMIdleStateHandler() {
        super(READER_TIME, 0, 0, TimeUnit.SECONDS);
    }

    @Override
    public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) {
        log.error(READER_TIME + "秒内未读取到数据，连接关闭");
        ctx.channel().close();
    }
}
