package com.lf.client;

import com.lf.attribute.AttributeConstants;
import com.lf.client.handler.LoginClientHandler;
import com.lf.code.PacketCodeC;
import com.lf.packet.MessageRequestPacket;
import com.lf.util.LoginUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author liufan
 * @description: TODO
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
                        ch.pipeline().addLast(new LoginClientHandler());
                    }
                });
        connect(bootstrap, "127.0.0.1", 1000, MAX_RETRY);

    }

    private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println(new Date() + "连接成功");
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
                try {
                    Thread.sleep(10L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                if (channel.attr(AttributeConstants.LOGIN).get()) {
                    System.out.println("请输入消息发送至服务端");
                    Scanner sc = new Scanner(System.in);
                    String msg = sc.nextLine();
                    MessageRequestPacket messageRequestPacket = new MessageRequestPacket();
                    messageRequestPacket.setMsg(msg);
                    ByteBuf byteBuf = PacketCodeC.PACKET_CODEC.encode(channel.alloc().buffer(), messageRequestPacket);
                    channel.writeAndFlush(byteBuf);
                }
            }
        }).start();
    }
}
