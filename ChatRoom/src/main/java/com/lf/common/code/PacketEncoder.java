package com.lf.common.code;

import com.lf.common.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author liufan
 * @description: 编码handler
 * @since 2024/02/21
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) {
        PacketCodeC.PACKET_CODEC.encode(byteBuf, packet);
    }
}
