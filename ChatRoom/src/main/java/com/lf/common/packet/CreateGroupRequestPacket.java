package com.lf.common.packet;

import com.lf.common.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/28
 */
@Data
@AllArgsConstructor
public class CreateGroupRequestPacket extends Packet{
    String creatorId;
    List<String> userIdList;
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP;
    }
}
