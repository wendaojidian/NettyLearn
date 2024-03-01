package com.lf.server.handler;

import com.lf.attribute.AttributeConstants;
import com.lf.util.GroupUtil;
import com.lf.packet.ListGroupMembersRequestPacket;
import com.lf.packet.ListGroupMembersResponsePacket;
import com.lf.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/29
 */
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {
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
}
