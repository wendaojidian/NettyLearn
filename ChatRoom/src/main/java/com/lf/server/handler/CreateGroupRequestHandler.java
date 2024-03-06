package com.lf.server.handler;

import com.lf.common.attribute.AttributeConstants;
import com.lf.common.command.Command;
import com.lf.common.handler.service.HandlerService;
import com.lf.common.util.GroupUtil;
import com.lf.common.packet.CreateGroupRequestPacket;
import com.lf.common.packet.CreateGroupResponsePacket;
import com.lf.common.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @author liufan
 * @description: 服务端接收客户端创建群聊请求处理handler
 * @since 2024/02/28
 */
@Service
@ChannelHandler.Sharable
public class CreateGroupRequestHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> implements HandlerService {
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
        GroupUtil.bindChannelGroup(groupId, channelGroup);
        GroupUtil.createGroup(groupId, userIdList);
        // 保存groupId->members的映射
        CreateGroupResponsePacket createGroupResponsePacket =
                new CreateGroupResponsePacket(new Date() + createGroupPacket.getCreatorId() +
                        "创建群聊，群聊id：" + groupId + "\n群聊成员包括：" + userList);
        channelGroup.writeAndFlush(createGroupResponsePacket);
    }

    @Override
    public Byte getCommand() {
        return Command.CREATE_GROUP;
    }
}
