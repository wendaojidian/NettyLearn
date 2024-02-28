package com.lf.command;

import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author liufan
 * @description: 命令执行接口
 * @since 2024/02/28
 */
public interface ConsoleCommand {
    void exec(Scanner scanner, Channel channel);
}
