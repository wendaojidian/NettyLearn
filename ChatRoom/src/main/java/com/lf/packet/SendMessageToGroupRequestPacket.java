package com.lf.packet;

import com.lf.command.Command;
import lombok.Data;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/29
 */
@Data
public class SendMessageToGroupRequestPacket extends Packet{
    String groupId;
    String userId;
    String msg;
    @Override
    public Byte getCommand() {
        return Command.SEND_MESSAGE_TO_GROUP_REQUEST;
    }
}
