package com.lf.common.packet;

import com.lf.common.command.Command;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/07
 */
public class HeartBeatResponsePacket extends Packet{
    @Override
    public Byte getCommand() {
        return Command.HEART_BEAT_RESPONSE;
    }
}
