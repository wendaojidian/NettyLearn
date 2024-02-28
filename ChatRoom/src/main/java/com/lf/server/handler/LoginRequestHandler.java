package com.lf.server.handler;

import com.lf.entity.Session;
import com.lf.packet.LoginPacket;
import com.lf.packet.LoginResponsePacket;
import com.lf.util.LoginUtil;
import com.lf.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author liufan
 * @description: 处理客户端请求、发送回复
 * @since 2024/02/21
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginPacket loginPacket) {
        System.out.println(new Date() + "服务端开始处理客户端登录请求......");
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        if (valid(loginPacket)) {
            System.out.println(new Date() + "登录成功，用户id为：" + loginPacket.getUserId());
            SessionUtil.bindSession(new Session(loginPacket.getUserId(), loginPacket.getUserName()), channelHandlerContext.channel());
            loginResponsePacket.setUserId(loginPacket.getUserId());
            loginResponsePacket.setUserName(loginPacket.getUserName());
            loginResponsePacket.setSuccess(true);
        } else {
            System.out.println(new Date() + "登录失败");
            loginResponsePacket.setSuccess(false);
        }
        channelHandlerContext.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginPacket loginPacket) {
        return true;
    }
}
