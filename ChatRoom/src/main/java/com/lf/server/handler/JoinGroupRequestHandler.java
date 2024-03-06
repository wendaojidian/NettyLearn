package com.lf.server.handler;

import com.lf.common.command.Command;
import com.lf.common.handler.service.HandlerService;
import com.lf.common.util.GroupUtil;
import com.lf.common.packet.JoinGroupRequestPacket;
import com.lf.common.packet.JoinGroupResponsePacket;
import com.lf.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/29
 */
@Service
@ChannelHandler.Sharable
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> implements HandlerService {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, JoinGroupRequestPacket joinGroupRequestPacket) throws Exception {
        String groupId = joinGroupRequestPacket.getGroupId();
        String userId = joinGroupRequestPacket.getUserId();
        JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
        if (GroupUtil.joinGroup(groupId, userId)) {
            joinGroupResponsePacket.setSuccess(true);
            joinGroupResponsePacket.setMsg("加入成功");
        } else {
            joinGroupResponsePacket.setSuccess(false);
            joinGroupResponsePacket.setMsg("群聊不存在，加入失败");
        }
        SessionUtil.getChannel(userId).writeAndFlush(joinGroupResponsePacket);
    }

    @Override
    public Byte getCommand() {
        return Command.JOIN_GROUP_REQUEST;
    }
}
