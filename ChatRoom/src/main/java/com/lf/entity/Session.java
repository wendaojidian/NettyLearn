package com.lf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author liufan
 * @description: 服务端保存登录客户信息
 * @since 2024/02/27
 */
@Data
@AllArgsConstructor
public class Session {
    String userId;
    String userName;
}
