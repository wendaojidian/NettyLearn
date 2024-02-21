package com.lf.packet;

import com.lf.command.Command;
import lombok.Data;

/**
 * @author liufan
 * @description: 消息发送体
 * @since 2024/02/20
 */
@Data
public class MessageRequestPacket extends Packet{
    private String msg;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
