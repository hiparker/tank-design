package com.parker.tank.net.coder;

import com.parker.tank.net.msg.BaseMsg;
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
public class TankJoinMsgEncoder extends MessageToByteEncoder<BaseMsg> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, BaseMsg msg, ByteBuf byteBuf) throws Exception {
        byteBuf.writeBytes(msg.toBytes());
    }
}
