package com.lf.attribute;

import io.netty.util.AttributeKey;

/**
 * @author liufan
 * @description: channel属性
 * @since 2024/02/20
 */
public interface AttributeConstants {
    AttributeKey<Boolean> LOGIN = AttributeKey.valueOf("login");

}
