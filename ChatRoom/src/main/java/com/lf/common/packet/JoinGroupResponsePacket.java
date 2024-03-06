package com.lf.common.packet;

import com.lf.common.command.Command;
import lombok.Data;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/28
 */
@Data
public class JoinGroupResponsePacket extends Packet{
    Boolean success;
    String msg;
    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_RESPONSE;
    }
}
