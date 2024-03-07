package com.lf.client;

import com.lf.common.command.ConsoleCommand;
import com.lf.common.command.impl.ConsoleCommandManager;
import com.lf.common.util.CookieUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author liufan
 * @description: 客户端
 * @since 2024/01/17
 */
@Component
@SpringBootApplication
public class NettyClient {
    private static final int MAX_RETRY = 5;

    @Autowired
    private ChannelInitializer clientInitializer;

    @PostConstruct
    public void start() {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        //1.指定线程模型
        bootstrap.group(workerGroup)
                //2.指定io类型
                .channel(NioSocketChannel.class)
                //3.IO处理逻辑
                .handler(clientInitializer);
        connect(bootstrap, "127.0.0.1", 1000, MAX_RETRY);

    }
    public static void main(String[] args) {
        SpringApplication.run(NettyClient.class, args);
    }

    public void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                startConsoleThread(((ChannelFuture)future).channel());
            } else if (retry == 0){
                System.out.println("重试次数已用完，连接失败");
            } else {
                int order = MAX_RETRY - retry + 1;
                int delay = 1 << order;
                System.out.println(new Date() + "连接失败，第" + order + "次重连...");
                bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry-1), delay, TimeUnit.SECONDS);
            }
        });
    }

    private void startConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                Scanner scanner = new Scanner(System.in);
                if (CookieUtil.isLogin(channel)) {
                    ConsoleCommand consoleCommand = new ConsoleCommandManager();
                    consoleCommand.exec(scanner, channel);
                }
            }
        }).start();
    }
}
