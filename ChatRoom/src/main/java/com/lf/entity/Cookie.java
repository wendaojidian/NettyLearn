package com.lf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liufan
 * @description: 客户端保存的用户信息
 * @since 2024/02/28
 */
@Data
@AllArgsConstructor
public class Cookie {
    String userId;
    String userName;
}
