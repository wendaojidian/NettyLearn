package com.lf.common.packet;

import com.lf.common.command.Command;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/06
 */
public class HeartBeatRequestPacket extends Packet{
    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT_REQUEST;
    }
}
