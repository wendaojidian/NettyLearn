package com.lf.serialize;

/**
 * @author liufan
 * @description: 序列化接口
 * @since 2024/02/06
 */
public interface Serializer {
    Byte getAlgorithm();

    byte[] serialize(Object object);

    <T> T deserialize(Class<T> clazz, byte[] bytes);

    Serializer DEFAULT = new JsonSerializer();

}
