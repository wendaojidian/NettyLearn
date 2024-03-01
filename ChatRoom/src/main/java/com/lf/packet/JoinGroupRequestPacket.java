package com.lf.packet;

import com.lf.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/28
 */
@Data
@AllArgsConstructor
public class JoinGroupRequestPacket extends Packet{
    String userId;
    String groupId;
    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
