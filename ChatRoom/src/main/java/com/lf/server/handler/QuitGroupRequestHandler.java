package com.lf.server.handler;

import com.lf.util.GroupUtil;
import com.lf.packet.QuitGroupRequestPacket;
import com.lf.packet.QuitGroupResponsePacket;
import com.lf.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/29
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, QuitGroupRequestPacket quitGroupRequestPacket) throws Exception {
        String groupId = quitGroupRequestPacket.getGroupId();
        String userId = quitGroupRequestPacket.getUserId();
        QuitGroupResponsePacket quitGroupResponsePacket = new QuitGroupResponsePacket();
        if (GroupUtil.quitGroup(groupId, userId)) {
            quitGroupResponsePacket.setSuccess(true);
            quitGroupResponsePacket.setMsg("退出群聊成功");
        } else {
            quitGroupResponsePacket.setSuccess(false);
            quitGroupResponsePacket.setMsg("群聊不存在，退出失败");
        }
        SessionUtil.getChannel(userId).writeAndFlush(quitGroupResponsePacket);
    }
}
