package com.lf.command.impl;

import com.lf.attribute.AttributeConstants;
import com.lf.command.ConsoleCommand;
import com.lf.packet.SendMessageToGroupRequestPacket;
import com.lf.util.CookieUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/03/01
 */
public class SendMessageToGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入groupId和消息内容，用空格分隔，消息内容中不能有空格");
        String[] msg = scanner.nextLine().split(" ");
        String groupId = msg[0];
        String message = msg[1];
        SendMessageToGroupRequestPacket sendMessageToGroupRequestPacket = new SendMessageToGroupRequestPacket();
        sendMessageToGroupRequestPacket.setGroupId(groupId);
        sendMessageToGroupRequestPacket.setUserId(CookieUtil.getId(channel));
        sendMessageToGroupRequestPacket.setMsg(message);
        channel.writeAndFlush(sendMessageToGroupRequestPacket);
    }
}
