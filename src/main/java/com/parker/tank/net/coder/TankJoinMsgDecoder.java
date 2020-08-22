package com.parker.tank.net.coder;

import com.parker.tank.net.msg.Msg;
import com.parker.tank.net.msg.MsgUtil;
import com.parker.tank.net.msg.MsgType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.coder
 * @Author: Parker
 * @CreateTime: 2020-08-20 19:22
 * @Description: 解码器
 */
public class TankJoinMsgDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        // ⭐️⭐️⭐️⭐️⭐️
        // type int 为4个字节
        // length int 为4个字节
        // 如果消息不满足上面这个两个条件 直接不处理
        if(in.readableBytes() < 8) return;


        // 标记读取位置 ⭐️⭐️⭐️⭐️⭐️
        in.markReaderIndex();

        int typeIndex = in.readInt();
        MsgType msgType = MsgType.values()[typeIndex];
        int length = in.readInt();
        // 如果消息体长度不够 直接退出
        if(in.readableBytes()< length) {
            // 回到标记读取位置 ⭐️⭐️⭐️⭐️⭐️
            // 什么时候 消息读全了 什么时候再继续往后执行
            in.resetReaderIndex();
            return;
        }

        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        Msg msg = null;
        Class<?> msgClazz = MsgUtil.INSTANCE.getMsgClazz(msgType);
        if(msgClazz != null){
            msg =(Msg) msgClazz.newInstance();
        }

        msg.parse(bytes);
        msg.setType(msgType);
        out.add(msg);
    }
}
