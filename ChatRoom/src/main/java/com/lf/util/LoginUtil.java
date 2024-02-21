package com.lf.util;

import com.lf.attribute.AttributeConstants;
import io.netty.channel.Channel;

/**
 * @author liufan
 * @description: 登录态校验
 * @since 2024/02/20
 */
public class LoginUtil {
    public static void markLogin(Channel channel) {
        channel.attr(AttributeConstants.LOGIN).set(true);
    }

    public static boolean isLogin(Channel channel) {
        return channel.attr(AttributeConstants.LOGIN).get();
    }
}
