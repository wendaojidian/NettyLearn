package com.lf.common.packet;

import lombok.Data;

/**
 * @author liufan
 * @description: 通信过程中数据对象
 * @since 2024/02/06
 */
@Data
public abstract class Packet {
    private final Byte version = 1;

    public abstract Byte getCommand();
}
