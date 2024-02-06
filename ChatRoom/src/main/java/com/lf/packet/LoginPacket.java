package com.lf.packet;

import com.lf.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liufan
 * @description: 登录数据包
 * @since 2024/02/06
 */
@Data
@AllArgsConstructor
public class LoginPacket extends Packet{
    private Integer userId;
    private String userName;
    private String passWord;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
