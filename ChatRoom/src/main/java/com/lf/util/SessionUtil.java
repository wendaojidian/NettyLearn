package com.lf.util;

import com.lf.attribute.AttributeConstants;
import com.lf.entity.Session;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liufan
 * @description: 服务端保存用户登录态工具类
 * @since 2024/02/27
 */
public class SessionUtil {
    private static final Map<String, Channel> USER_INFO_MAP = new ConcurrentHashMap<>();

    private static final Map<String, ChannelGroup> CHANNEL_GROUP_MAP = new ConcurrentHashMap<>();
    public static void bindSession(Session session, Channel channel) {
        USER_INFO_MAP.put(session.getUserId(), channel);
        channel.attr(AttributeConstants.SESSION).set(session);
    }

    public static Channel getChannel(String userId) {
        return USER_INFO_MAP.get(userId);
    }

    public static void unBindSession(Session session, Channel channel) {
        USER_INFO_MAP.remove(session.getUserId());
        channel.attr(AttributeConstants.SESSION).set(null);
    }

    public static boolean isLogin(Channel channel) {
        return null != channel.attr(AttributeConstants.SESSION).get();
    }

    public static String getUserName(Channel channel) {
        return channel.attr(AttributeConstants.SESSION).get().getUserName();
    }
}
