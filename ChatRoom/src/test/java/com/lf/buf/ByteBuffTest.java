package com.lf.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

/**
 * @author liufan
 * @description: TODO
 * @since 2024/02/02
 */
public class ByteBuffTest {
    public static void main(String[] args) {
        ByteBuf buf = ByteBufAllocator.DEFAULT.buffer(9, 100);
        print("allocate ByteBuf(9, 100)", buf);
        buf.writeBytes(new byte[] {1,2,3,4});
        print("writeBytes(1,2,3,4)", buf);
        buf.writeInt(12);
        print("writeInt(12)", buf);
        buf.writeByte(5);
        print("writeByte(5)", buf);
        buf.writeBytes(new byte[]{6});
        print("writeByte(6)", buf);

        System.out.println("getByte(3) return: " + buf.getByte(3));
        System.out.println("getInt(3) return: " + buf.getInt(4));
        System.out.println("getShort(3) return: " + buf.getShort(3));
        print("getByte()", buf);
        buf.setByte(buf.readableBytes() + 1, 0);
        print("setByte()", buf);
        System.out.println(buf.getByte(buf.readableBytes() + 1));

        byte[] dst = new byte[buf.readableBytes()];
        buf.readBytes(dst);
        print("readBytes(" + dst.length + "0", buf);





    }

    private static void print(String action, ByteBuf buf) {
        System.out.println("==============after " + action + "===============");
        System.out.println("capacity:" + buf.capacity());
        System.out.println("maxCapacity:" + buf.maxCapacity());
        System.out.println("readerIndex:" + buf.readerIndex());
        System.out.println("readableBytes:" + buf.readableBytes());
        System.out.println("isReadable:" + buf.isReadable());
        System.out.println("writerIndex:" + buf.writerIndex());
        System.out.println("writableBytes:" + buf.writableBytes());
        System.out.println("isWritable:" + buf.isWritable());
        System.out.println("maxWritableBytes:" + buf.maxWritableBytes());
        System.out.println();
    }
}
