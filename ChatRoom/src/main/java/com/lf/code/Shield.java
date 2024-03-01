package com.lf.code;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author liufan
 * @description: 魔数校验handler
 * @since 2024/02/21
 */
public class Shield extends LengthFieldBasedFrameDecoder {
    private static final int OFFSET = 7;
    private static final int FIELD_LENGTH = 4;
    public Shield() {
        super(Integer.MAX_VALUE, OFFSET, FIELD_LENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {
        int magicNumber = buf.getInt(buf.readerIndex());
//        System.out.println("[魔数校验]-" + magicNumber + "-" + PacketCodeC.MAGIC_NUMBER);
        if(magicNumber != PacketCodeC.MAGIC_NUMBER) {
            ctx.channel().close();
            System.out.println("魔数校验失败");
            return null;
        }
        return super.decode(ctx, buf);
    }
}
