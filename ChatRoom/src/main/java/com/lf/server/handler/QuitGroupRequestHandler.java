package com.lf.server.handler;

import com.lf.common.command.Command;
import com.lf.common.handler.service.HandlerService;
import com.lf.common.util.GroupUtil;
import com.lf.common.packet.QuitGroupRequestPacket;
import com.lf.common.packet.QuitGroupResponsePacket;
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
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> implements HandlerService {
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

    @Override
    public Byte getCommand() {
        return Command.QUIT_GROUP_REQUEST;
    }
}
