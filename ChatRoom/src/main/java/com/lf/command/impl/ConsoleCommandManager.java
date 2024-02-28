package com.lf.command.impl;

import com.google.common.collect.ImmutableMap;
import com.lf.command.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author liufan
 * @description: 命令执行实现类
 * @since 2024/02/28
 */
public class ConsoleCommandManager implements ConsoleCommand {
    private static final Map<String, ConsoleCommand> COMMAND_MAP = ImmutableMap.<String, ConsoleCommand>builder()
            .put("CreateGroup", new CreateGroupConsoleCommand())
            .build();

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.println(new Date() + "请输入指令");
        String command = scanner.nextLine();
        ConsoleCommand consoleCommand = COMMAND_MAP.get(command);
        if (null != consoleCommand) {
            consoleCommand.exec(scanner, channel);
        } else {
            System.out.println("无法识别[" + command + "]指令，请重新输入");
        }
    }
}
