package com.lf.common.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 * @author liufan
 * @description: 魔数校验handler
 * @since 2024/02/21
 */
@Slf4j
public class Shield extends LengthFieldBasedFrameDecoder {
    private static final int OFFSET = 7;
    private static final int FIELD_LENGTH = 4;
    public Shield() {
        super(Integer.MAX_VALUE, OFFSET, FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
        int magicNumber = buf.getInt(buf.readerIndex());
        if(magicNumber != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            log.error("魔数校验失败");
            return null;
        }
        return super.decode(ctx, buf);
    }
}
