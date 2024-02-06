package com.lf.encode;

import com.alibaba.fastjson.JSON;
import com.lf.buf.ByteBuffTest;
import com.lf.code.PacketCodeC;
import com.lf.packet.LoginPacket;
import com.lf.packet.Packet;
import io.netty.buffer.ByteBuf;
import org.junit.Test;

/**
 * @author liufan
 * @description: ±‡Ω‚¬Î≤‚ ‘
 * @since 2024/02/06
 */
public class EncodeTest {
    @Test
    public void testEncode() {
        Packet packet = new LoginPacket(1, "lf", "password");
        ByteBuf buf = PacketCodeC.encode(packet);
        ByteBuffTest.print("encode", buf);
        LoginPacket loginPacket = PacketCodeC.decode(buf);
        ByteBuffTest.print("decode", buf);
        System.out.println(JSON.toJSONString(loginPacket));
    }



}
