package com.lf.packet;

import com.lf.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liufan
 * @description: 创建群聊回复消息
 * @since 2024/02/28
 */
@Data
@AllArgsConstructor
public class CreateGroupResponsePacket extends Packet{
    String msg;
    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP_RESPONSE;
    }
}
