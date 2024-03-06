package com.lf.common.code;

import com.lf.common.command.Command;
import com.lf.common.packet.*;
import com.lf.common.serialize.Serializer;
import io.netty.buffer.ByteBuf;

/**
 * @author liufan
 * @description: 编解码实现类
 * @since 2024/02/06
 */
public class PacketCodeC {
    public static final int MAGIC_NUMBER = 0X12345678;
    public final static PacketCodeC PACKET_CODEC = new PacketCodeC();
    private PacketCodeC() {}

    public void encode(ByteBuf buf, Packet packet) {
        byte[] bytes = Serializer.DEFAULT.serialize(packet);
        buf.writeInt(MAGIC_NUMBER);
        buf.writeByte(packet.getVersion());
        buf.writeByte(Serializer.DEFAULT.getAlgorithm());
        buf.writeByte(packet.getCommand());
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);
    }

    public <T extends Packet> T decode(ByteBuf buf) {
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
        System.out.println("Unsupported requestType");
        return null;
    }

    private <T extends Packet> Class<T> getRequestType(byte command) {
        if (command == Command.LOGIN_REQUEST) {
            return (Class<T>) LoginPacket.class;
        } else if (command == Command.LOGIN_RESPONSE) {
            return (Class<T>) LoginResponsePacket.class;
        } else if (command == Command.MESSAGE_REQUEST) {
            return (Class<T>) MessageRequestPacket.class;
        } else if (command == Command.MESSAGE_RESPONSE) {
            return (Class<T>) MessageReponsePacket.class;
        } else if (command == Command.CREATE_GROUP) {
            return (Class<T>) CreateGroupRequestPacket.class;
        } else if (command == Command.CREATE_GROUP_RESPONSE) {
            return (Class<T>) CreateGroupResponsePacket.class;
        } else if (command == Command.LIST_GROUP_MEMBERS_REQUEST) {
            return (Class<T>) ListGroupMembersRequestPacket.class;
        } else if (command == Command.LIST_GROUP_MEMBERS_RESPONSE) {
            return (Class<T>) ListGroupMembersResponsePacket.class;
        } else if (command == Command.JOIN_GROUP_REQUEST) {
            return (Class<T>) JoinGroupRequestPacket.class;
        } else if (command == Command.JOIN_GROUP_RESPONSE) {
            return (Class<T>) JoinGroupResponsePacket.class;
        } else if (command == Command.QUIT_GROUP_REQUEST) {
            return (Class<T>) QuitGroupRequestPacket.class;
        } else if (command == Command.QUIT_GROUP_RESPONSE) {
            return (Class<T>) QuitGroupResponsePacket.class;
        } else if (command == Command.SEND_MESSAGE_TO_GROUP_REQUEST) {
            return (Class<T>) SendMessageToGroupRequestPacket.class;
        } else if (command == Command.SEND_MESSAGE_TO_GROUP_RESPONSE) {
            return (Class<T>) SendMessageToGroupResponsePacket.class;
        } else {
            System.out.println("不支持的命令类型");
        }
        return null;
    }

    private Serializer getSerializerByAlgorithm(byte algorithm) {
        if (algorithm == Serializer.DEFAULT.getAlgorithm()) {
            return Serializer.DEFAULT;
        }
        return null;
    }
}
