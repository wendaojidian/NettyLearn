package com.lf.code;

import com.lf.command.Command;
import com.lf.packet.LoginPacket;
import com.lf.packet.Packet;
import com.lf.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author liufan
 * @description: 编解码实现类
 * @since 2024/02/06
 */
public class PacketCodeC {
    private static final int MAGIC_NUMBER = 0X12345678;
    public static ByteBuf encode(Packet packet) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        buf.writeInt(MAGIC_NUMBER);
        buf.writeByte(packet.getVersion());
        buf.writeByte(Serializer.DEFAULT.getAlgorithm());
        buf.writeByte(packet.getCommand());
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
        return buf;
    }

    public static <T extends Packet> T decode(ByteBuf buf) {
        buf.skipBytes(4);
        buf.skipBytes(1);
        byte algorithm = buf.readByte();
        byte command = buf.readByte();
        int length = buf.readInt();
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        Class<T> requestType = getRequestType(command);
        Serializer serializer = getSerializerByAlgorithm(algorithm);
        if (null != requestType && null != serializer) {
            return serializer.deserialize(requestType, bytes);
        }
        return null;
    }

    private static <T extends Packet> Class<T> getRequestType(byte command) {
        if (command == Command.LOGIN_REQUEST) {
            return (Class<T>) LoginPacket.class;
        }
        return null;
    }

    private static Serializer getSerializerByAlgorithm(byte algorithm) {
        if (algorithm == Serializer.DEFAULT.getAlgorithm()) {
            return Serializer.DEFAULT;
        }
        return null;
    }
}
