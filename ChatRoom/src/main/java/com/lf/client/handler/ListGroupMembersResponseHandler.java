package com.lf.client.handler;

import com.lf.common.command.Command;
import com.lf.common.handler.service.HandlerService;
import com.lf.common.packet.ListGroupMembersResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.springframework.stereotype.Service;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/29
 */
@ChannelHandler.Sharable
@Service
public class ListGroupMembersResponseHandler extends SimpleChannelInboundHandler<ListGroupMembersResponsePacket> implements HandlerService {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ListGroupMembersResponsePacket listGroupMembersResponsePacket) throws Exception {
        if (listGroupMembersResponsePacket.getSuccess()) {
            System.out.println(listGroupMembersResponsePacket.getMsg() + "，群聊成员包括：" + listGroupMembersResponsePacket.getIdToNameMap());
        } else {
            System.out.println(listGroupMembersResponsePacket.getMsg());
        }
    }

    @Override
    public Byte getCommand() {
        return Command.LIST_GROUP_MEMBERS_RESPONSE;
    }
}
