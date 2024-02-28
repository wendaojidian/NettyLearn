package com.lf.packet;

import com.lf.command.Command;
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
    String creator;
    List<String> userIdList;
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP;
    }
}
