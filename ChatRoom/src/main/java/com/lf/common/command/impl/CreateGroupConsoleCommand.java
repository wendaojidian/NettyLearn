package com.lf.common.command.impl;

import com.lf.common.attribute.AttributeConstants;
import com.lf.common.command.ConsoleCommand;
import com.lf.common.packet.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/28
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println(new Date() + "请输入群聊成员，用英文逗号分隔");
        String[] userList = scanner.nextLine().split(",");
        CreateGroupRequestPacket createGroupPacket = new CreateGroupRequestPacket(
                channel.attr(AttributeConstants.COOKIE).get().getUserId(), Arrays.asList(userList));
        channel.writeAndFlush(createGroupPacket);
    }
}
