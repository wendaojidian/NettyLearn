package com.lf.common.command;

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

    Byte CREATE_GROUP = 5;

    Byte CREATE_GROUP_RESPONSE = 6;

    Byte LIST_GROUP_MEMBERS_REQUEST = 7;
    Byte LIST_GROUP_MEMBERS_RESPONSE = 8;
    Byte JOIN_GROUP_REQUEST = 9;
    Byte JOIN_GROUP_RESPONSE = 10;
    Byte QUIT_GROUP_REQUEST = 11;
    Byte QUIT_GROUP_RESPONSE = 12;
    Byte SEND_MESSAGE_TO_GROUP_REQUEST = 13;
    Byte SEND_MESSAGE_TO_GROUP_RESPONSE = 14;
    Byte HEART_BEAT_REQUEST = 15;
    Byte HEART_BEAT_RESPONSE = 16;
}
