package com.lf.server;

import com.lf.server.init.ServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author liufan
 * @description: 服务端启动类
 * @since 2024/01/17
 */
@Component
@SpringBootApplication
@Slf4j
@ComponentScan(basePackages = {"com.lf.common", "com.lf.server"})
public class NettyServer {
    private ServerInitializer serverInitializer;

    @PostConstruct
    private void start() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(this.serverInitializer);
        bind(serverBootstrap, 1000);
    }

    public static void main(String[] args) {
        SpringApplication.run(NettyServer.class, args);
    }

    private static void bind(ServerBootstrap serverBootstrap, int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                log.info("端口[" + port + "]绑定成功");
            } else {
                bind(serverBootstrap, port + 1);
            }
        });
    }

    @Autowired
    private void setServerInitializer(ServerInitializer serverInitializer) {
        this.serverInitializer = serverInitializer;
    }
}
