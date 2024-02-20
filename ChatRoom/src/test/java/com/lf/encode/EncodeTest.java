package com.lf.encode;

import com.alibaba.fastjson.JSON;
import com.lf.buf.ByteBuffTest;
import com.lf.code.PacketCodeC;
import com.lf.packet.LoginPacket;
import com.lf.packet.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import org.junit.Test;
import org.springframework.stereotype.Service;

/**
 * @author liufan
 * @description: ±‡Ω‚¬Î≤‚ ‘
 * @since 2024/02/06
 */
@Service
public class EncodeTest {
    @Test
    public void testEncode() {
        Packet packet = new LoginPacket("1", "lf", "password");
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer();
        PacketCodeC.PACKET_CODEC.encode(buf, packet);
        ByteBuffTest.print("encode", buf);
        Packet loginPacket = PacketCodeC.PACKET_CODEC.decode(buf);
        ByteBuffTest.print("decode", buf);
        System.out.println(JSON.toJSONString(loginPacket));
    }



}
