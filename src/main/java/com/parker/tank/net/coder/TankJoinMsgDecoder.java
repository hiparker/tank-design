package com.parker.tank.net.coder;

import com.parker.tank.Dir;
import com.parker.tank.TankGroup;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

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

        // 小于33 个字节 直接出去
        if(in.readableBytes() < 33){
            return;
        }

        TankJoinMsg msg = new TankJoinMsg();

        msg.setX(in.readInt())
                .setY(in.readInt())
                .setDir(Dir.values()[in.readInt()])
                .setGroup(TankGroup.values()[in.readInt()])
                .setMoving(in.readBoolean())
                .setId(new UUID(in.readLong(), in.readLong()))
                .setType(TankType.values()[in.readInt()]);

        out.add(msg);

    }
}
