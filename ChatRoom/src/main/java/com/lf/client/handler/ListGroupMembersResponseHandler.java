package com.lf.client.handler;

import com.lf.packet.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/29
 */
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersResponsePacket listGroupMembersResponsePacket) throws Exception {
        if (listGroupMembersResponsePacket.getSuccess()) {
            System.out.println(listGroupMembersResponsePacket.getMsg() + "，群聊成员包括：" + listGroupMembersResponsePacket.getIdToNameMap());
        } else {
            System.out.println(listGroupMembersResponsePacket.getMsg());
        }
    }
}
