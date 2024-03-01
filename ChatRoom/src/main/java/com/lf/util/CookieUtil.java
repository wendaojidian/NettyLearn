package com.lf.util;

import com.lf.attribute.AttributeConstants;
import com.lf.entity.Cookie;
import io.netty.channel.Channel;

/**
 * @author liufan
 * @description: 客户端状态工具类
 * @since 2024/02/28
 */
public class CookieUtil {
    public static void markLogin(Channel channel, Cookie cookie) {
        channel.attr(AttributeConstants.COOKIE).set(cookie);
    }

    public static boolean isLogin(Channel channel) {
        return null != channel.attr(AttributeConstants.COOKIE).get();
    }

    public static void markLogout(Channel channel) {
        channel.attr(AttributeConstants.COOKIE).set(null);
    }

    public static String getId(Channel channel) {
        return channel.attr(AttributeConstants.COOKIE).get().getUserId();
    }
}
