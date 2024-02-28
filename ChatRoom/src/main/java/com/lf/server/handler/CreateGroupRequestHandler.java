package com.lf.server.handler;

import com.lf.attribute.AttributeConstants;
import com.lf.packet.CreateGroupRequestPacket;
import com.lf.packet.CreateGroupResponsePacket;
import com.lf.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        List<String> userList = new ArrayList<>();
        List<String> userIdList = createGroupPacket.getUserIdList();
        userIdList.add(createGroupPacket.getCreatorId());
        for (String id: createGroupPacket.getUserIdList()) {
            Channel channel = SessionUtil.getChannel(id);
            if (null != channel) {
                channelGroup.add(channel);
                userList.add(channel.attr(AttributeConstants.SESSION).get().getUserName());
            }
        }
        String groupId = UUID.randomUUID().toString();
        CreateGroupResponsePacket createGroupResponsePacket =
                new CreateGroupResponsePacket(new Date() + createGroupPacket.getCreatorId() +
                        "创建群聊，群聊id：" + groupId + "\n群聊成员包括：" + userList);
        channelGroup.writeAndFlush(createGroupResponsePacket);

    }
}
