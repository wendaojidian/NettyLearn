package com.lf.server.handler;

import com.lf.common.attribute.AttributeConstants;
import com.lf.common.command.Command;
import com.lf.common.handler.service.HandlerService;
import com.lf.common.util.GroupUtil;
import com.lf.common.packet.ListGroupMembersRequestPacket;
import com.lf.common.packet.ListGroupMembersResponsePacket;
import com.lf.common.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/29
 */
@Service
@ChannelHandler.Sharable
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> implements HandlerService {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersRequestPacket listGroupMembersRequestPacket) throws Exception {
        System.out.println("服务端开始处理查询群聊成员的请求...");
        String groupId = listGroupMembersRequestPacket.getGroupId();
        List<String> userIdList = GroupUtil.getMembers(groupId);
        ListGroupMembersResponsePacket listGroupMembersResponsePacket = new ListGroupMembersResponsePacket();
        if (null == userIdList) {
            listGroupMembersResponsePacket.setSuccess(false);
            listGroupMembersResponsePacket.setMsg("无群聊信息");
        } else {
            listGroupMembersResponsePacket.setSuccess(true);
            listGroupMembersResponsePacket.setMsg("查询成功");
            Map<String, String> idToNameMap = new HashMap<>(userIdList.size());
            for (String userId: userIdList) {
                idToNameMap.put(userId, SessionUtil.getChannel(userId).attr(AttributeConstants.SESSION).get().getUserName());
            }
            listGroupMembersResponsePacket.setIdToNameMap(idToNameMap);
        }
        SessionUtil.getChannel(listGroupMembersRequestPacket.getUserId()).writeAndFlush(listGroupMembersResponsePacket);
    }

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_REQUEST;
    }
}
