package com.lf.command;

/**
 * @author liufan
 * @description: 指令常量
 * @since 2024/02/06
 */
public interface Command {
    Byte LOGIN_REQUEST = 1;

    Byte LOGIN_RESPONSE = 2;

    Byte MESSAGE_REQUEST = 3;

    Byte MESSAGE_RESPONSE = 4;
}
