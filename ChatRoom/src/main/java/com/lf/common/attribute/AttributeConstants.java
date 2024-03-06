package com.lf.common.attribute;

import com.lf.common.entity.Cookie;
import com.lf.common.entity.Session;
import io.netty.util.AttributeKey;

/**
 * @author liufan
 * @description: channel属性
 * @since 2024/02/20
 */
public interface AttributeConstants {
    AttributeKey<Boolean> LOGIN = AttributeKey.valueOf("login");

    AttributeKey<Session> SESSION = AttributeKey.valueOf("session");

    AttributeKey<Cookie> COOKIE = AttributeKey.valueOf("cookie");

}
