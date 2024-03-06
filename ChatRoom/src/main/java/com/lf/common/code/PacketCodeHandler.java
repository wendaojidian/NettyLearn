package com.lf.common.code;

import com.lf.common.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/04
 */
@Service
@ChannelHandler.Sharable
public class PacketCodeHandler extends MessageToMessageCodec<ByteBuf, Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) {
        ByteBuf buf = channelHandlerContext.alloc().ioBuffer();
        PacketCodeC.PACKET_CODEC.encode(buf, packet);
        list.add(buf);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) {
        list.add(PacketCodeC.PACKET_CODEC.decode(byteBuf));
    }
}
