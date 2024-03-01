package com.lf.packet;

import com.lf.command.Command;
import lombok.Data;

import java.util.Map;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/28
 */
@Data
public class ListGroupMembersResponsePacket extends Packet{
    Map<String, String> idToNameMap;
    String msg;
    Boolean success;
    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
