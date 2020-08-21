package com.parker.tank.net.coder;

import com.parker.tank.net.msg.Msg;
import com.parker.tank.net.msg.MsgUtil;
import com.parker.tank.net.msg.TankJoinMsg;
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

        if(in.readableBytes() < 8) return;

        try {
            in.markReaderIndex();

            MsgType msgType = MsgType.values()[in.readInt()];
            int length = in.readInt();
            // 如果长度不够 直接退出
            if(in.readableBytes()< length) {
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
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
