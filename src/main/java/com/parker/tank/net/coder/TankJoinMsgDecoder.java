package com.parker.tank.net.coder;

import com.parker.tank.Dir;
import com.parker.tank.TankGroup;
import com.parker.tank.net.msg.BulletJoinMsg;
import com.parker.tank.net.msg.BulletType;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.coder
 * @Author: Parker
 * @CreateTime: 2020-08-20 19:22
 * @Description: 解码器
 */
public class TankJoinMsgDecoder extends ByteToMessageDecoder {

    private static final Map<Integer,Integer> flagMap = new HashMap<>();

    static {
        // 粘包处理 0 坦克 1 炮弹
        flagMap.put(1000,33);
        flagMap.put(2000,36);
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {

        if(in.readableBytes() < 8) return;

        in.markReaderIndex();

        int num = in.readableBytes()-4;
        int flag = in.readInt();

        // 小于flag个字节 直接出去
        if(flagMap.get(flag) != null && num < flagMap.get(flag)){
            return;
        }

        // 坦克
        if(1000 == flag){
            TankJoinMsg msg = new TankJoinMsg();
            msg.setX(in.readInt())
                    .setY(in.readInt())
                    .setDir(Dir.values()[in.readInt()])
                    .setGroup(TankGroup.values()[in.readInt()])
                    .setMoving(in.readBoolean())
                    .setId(new UUID(in.readLong(), in.readLong()))
                    .setType(TankType.values()[in.readInt()]);
            out.add(msg);
        }else if(2000 == flag){
            BulletJoinMsg msg = new BulletJoinMsg();
            msg.setId(new UUID(in.readLong(), in.readLong()))
                    .setTankId(new UUID(in.readLong(), in.readLong()))
                    .setType(BulletType.values()[in.readInt()]);
            out.add(msg);
        }

    }
}
