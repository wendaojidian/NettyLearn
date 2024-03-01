package com.lf.client;

import com.lf.attribute.AttributeConstants;
import com.lf.client.handler.*;
import com.lf.code.PacketCodeC;
import com.lf.code.PacketDecoder;
import com.lf.code.PacketEncoder;
import com.lf.code.Shield;
import com.lf.command.ConsoleCommand;
import com.lf.command.impl.ConsoleCommandManager;
import com.lf.command.impl.LoginConsoleCommand;
import com.lf.packet.MessageRequestPacket;
import com.lf.server.handler.AuthHandler;
import com.lf.util.CookieUtil;
import com.lf.util.LockUtil;
import com.lf.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author liufan
 * @description: 客户端
 * @since 2024/01/17
 */
@Service
public class NettyClient {
    private static final int MAX_RETRY = 5;

    public static void main(String[] args) {
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        //1.指定线程模型
        bootstrap.group(workerGroup)
                //2.指定io类型
                .channel(NioSocketChannel.class)
                //3.IO处理逻辑
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
//                        ch.pipeline().addLast(new LifeCycleTestHandler());
                        ch.pipeline().addLast(new Shield());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new CreateGroupResponseHandler());
                        ch.pipeline().addLast(new ListGroupMembersResponseHandler());
                        ch.pipeline().addLast(new JoinGroupResponseHandler());
                        ch.pipeline().addLast(new QuitGroupResponseHandler());
                        ch.pipeline().addLast(new SendToGroupResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        connect(bootstrap, "127.0.0.1", 1000, MAX_RETRY);

    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
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

    private static void startConsoleThread(Channel channel) {
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
