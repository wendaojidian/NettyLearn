package com.lf.common.packet;

import com.lf.common.command.Command;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author liufan
 * @description: 服务端登录响应请求
 * @since 2024/02/06
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class LoginResponsePacket extends Packet{
    private String userId;
    private String userName;
    private Boolean success;
    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
