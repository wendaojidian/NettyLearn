package com.lf.common.command.impl;

import com.lf.common.command.ConsoleCommand;
import com.lf.common.packet.ListGroupMembersRequestPacket;
import com.lf.common.util.CookieUtil;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/28
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println("请输入groupId:");
        String groupId = scanner.nextLine();
        ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket(groupId, CookieUtil.getId(channel));
        channel.writeAndFlush(listGroupMembersRequestPacket);
    }
}
