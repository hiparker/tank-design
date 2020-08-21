package com.parker.tank.net.coder;

import com.parker.tank.Dir;
import com.parker.tank.TankGroup;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.coder
 * @Author: Parker
 * @CreateTime: 2020-08-20 21:37
 * @Description: 编解码器 单元测试
 */
public class TankJoinMsgCoderTest{


    @Test
    public void encoder(){

        // 测试编码器
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new TankJoinMsgEncoder());

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(
                5,10, Dir.LEFT, TankGroup.BLUE,true,id, TankType.FIRE
        );

        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf) ch.readOutbound();

        int x = buf.readInt();
        int y = buf.readInt();
        Dir dir = Dir.values()[buf.readInt()];
        TankGroup group = TankGroup.values()[buf.readInt()];
        boolean moving = buf.readBoolean();
        UUID uuid = new UUID(buf.readLong(),buf.readLong());

        assertEquals(5,x);
        assertEquals(10,y);
        assertEquals(Dir.LEFT,dir);
        assertEquals(TankGroup.BLUE,group);
        assertEquals(true,moving);
        assertEquals(id,uuid);

    }


    @Test
    public void decoder(){
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new TankJoinMsgDecoder());

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(
                5,10, Dir.LEFT, TankGroup.BLUE,true,id, TankType.MOVE
        );

        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(msg.toBytes());

        ch.writeInbound(buf.duplicate());

        TankJoinMsg joinMsg = ch.readInbound();

        assertEquals(msg.getX(),joinMsg.getX());
        assertEquals(msg.getY(),joinMsg.getY());
        assertEquals(msg.getDir(),joinMsg.getDir());
        assertEquals(msg.getGroup(),joinMsg.getGroup());
        assertEquals(msg.isMoving(),joinMsg.isMoving());
        assertEquals(id,joinMsg.getId());
    }

}