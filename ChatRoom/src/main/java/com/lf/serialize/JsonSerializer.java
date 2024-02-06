package com.lf.serialize;

import com.alibaba.fastjson.JSON;

/**
 * @author liufan
 * @description: Json序列化
 * @since 2024/02/06
 */
public class JsonSerializer implements Serializer{
    @Override
    public Byte getAlgorithm() {
        return Algorithm.JSON_SERIALIZER;
    }

    @Override
    public byte[] serialize(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T deserialize(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
