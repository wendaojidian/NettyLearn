package com.lf.packet;

import com.lf.command.Command;
import lombok.Data;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/29
 */
@Data
public class SendMessageToGroupResponsePacket extends Packet{
    String groupId;
    String userName;
    String msg;
    @Override
    public Byte getCommand() {
        return Command.SEND_MESSAGE_TO_GROUP_RESPONSE;
    }
}
