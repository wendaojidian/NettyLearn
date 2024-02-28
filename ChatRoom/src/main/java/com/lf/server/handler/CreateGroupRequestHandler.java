package com.lf.server.handler;

import com.lf.packet.CreateGroupRequestPacket;
import com.lf.packet.CreateGroupResponsePacket;
import com.lf.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.Date;
import java.util.UUID;

/**
 * @author liufan
 * @description: 服务端接收客户端创建群聊请求处理handler
 * @since 2024/02/28
 */
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CreateGroupRequestPacket createGroupPacket) {
        ChannelGroup channelGroup = new DefaultChannelGroup(channelHandlerContext.executor());
        for (String id: createGroupPacket.getUserIdList()) {
            channelGroup.add(SessionUtil.getChannel(id));
        }
        CreateGroupResponsePacket createGroupResponsePacket =
                new CreateGroupResponsePacket(new Date() + createGroupPacket.getCreator() +
                        "创建群聊，群聊id：" + UUID.randomUUID() + "\n群聊成员包括：" + createGroupPacket.getUserIdList().toString());
        channelGroup.writeAndFlush(createGroupResponsePacket);

    }
}
