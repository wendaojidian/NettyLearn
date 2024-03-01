package com.lf.server.handler;

import com.lf.util.GroupUtil;
import com.lf.packet.JoinGroupRequestPacket;
import com.lf.packet.JoinGroupResponsePacket;
import com.lf.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/29
 */
public class JoinGroupRequestHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
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
}
