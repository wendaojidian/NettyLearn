package com.lf.common.packet;

import com.lf.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/28
 *
 */
@Data
@AllArgsConstructor
public class QuitGroupRequestPacket extends Packet{
    String userId;
    String groupId;
    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}
