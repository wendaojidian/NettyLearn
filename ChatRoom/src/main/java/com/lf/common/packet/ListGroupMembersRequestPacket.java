package com.lf.common.packet;

import com.lf.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/28
 */
@Data
@AllArgsConstructor
public class ListGroupMembersRequestPacket extends Packet{
    String groupId;
    String userId;
    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
