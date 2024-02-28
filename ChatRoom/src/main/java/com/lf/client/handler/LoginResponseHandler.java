package com.lf.client.handler;

import com.lf.entity.Cookie;
import com.lf.packet.LoginPacket;
import com.lf.packet.LoginResponsePacket;
import com.lf.packet.Packet;
import com.lf.util.CookieUtil;
import com.lf.util.LockUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

/**
 * @author liufan
 * @description: 处理服务端登录响应
 * @since 2024/02/21
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("请输入用户名...");
        Scanner sc = new Scanner(System.in);
        String userName = sc.nextLine();
        System.out.println("请输入密码...");
        String password = sc.nextLine();
        Packet packet = new LoginPacket(UUID.randomUUID().toString(), userName, password);
        System.out.println(new Date() + " 客户端开始登录");
        ctx.channel().writeAndFlush(packet);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, LoginResponsePacket loginResponsePacket) {
        try {
            if (loginResponsePacket.getSuccess()) {
                System.out.println(new Date() + " 登录成功");
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
