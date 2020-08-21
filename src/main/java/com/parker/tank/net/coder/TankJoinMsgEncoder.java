package com.parker.tank.net.coder;

import com.parker.tank.net.msg.Msg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.coder
 * @Author: Parker
 * @CreateTime: 2020-08-20 19:22
 * @Description: 编码器
 */
public class TankJoinMsgEncoder extends MessageToByteEncoder<Msg> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Msg msg, ByteBuf buf) throws Exception {
        // 消息头
        buf.writeInt(msg.getType().ordinal());
        byte[] bytes = msg.toBytes();
        // 消息长度
        buf.writeInt(bytes.length);
        // 消息体
        buf.writeBytes(bytes);
    }
}
