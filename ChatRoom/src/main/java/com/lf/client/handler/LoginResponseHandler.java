package com.lf.client.handler;

import com.lf.common.entity.Cookie;
import com.lf.common.packet.LoginPacket;
import com.lf.common.packet.LoginResponsePacket;
import com.lf.common.packet.Packet;
import com.lf.common.util.CookieUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

/**
 * @author liufan
 * @description: 处理服务端登录响应
 * @since 2024/02/21
 */
@ChannelHandler.Sharable
@Service
@Slf4j
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        log.info("请输入用户名...");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        log.info("请输入密码...");
        String password = sc.nextLine();
        Packet packet = new LoginPacket(UUID.randomUUID().toString(), userName, password);
        ctx.channel().writeAndFlush(packet);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) {
        try {
            if (loginResponsePacket.getSuccess()) {
                System.out.println(new Date() + " 登录成功，用户id为：" + loginResponsePacket.getUserId());
                CookieUtil.markLogin(channelHandlerContext.channel(),
                        new Cookie(loginResponsePacket.getUserId(), loginResponsePacket.getUserName()));
            } else {
                System.out.println(new Date() + " 登录失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端链接已关闭");
    }
}
