package com.lf.common.util;

import com.lf.common.attribute.AttributeConstants;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

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
        Attribute<Boolean> login = channel.attr(AttributeConstants.LOGIN);
        return null != login.get() && login.get();
    }
}
