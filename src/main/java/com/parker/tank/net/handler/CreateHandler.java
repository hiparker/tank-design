package com.parker.tank.net.handler;

import com.parker.tank.Tank;
import com.parker.tank.TankFactory;
import com.parker.tank.TankFrame;
import com.parker.tank.net.Client;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:55
 * @Description: 坦克创建器
 */
public class CreateHandler implements BaseHandler{

    private TankType tankType = TankType.CREATE;

    @Override
    public TankType getType() {
        return this.tankType;
    }

    @Override
    public void execute(TankJoinMsg msg) {
        if(TankFrame.INSTANCE.getMyTank().getId().equals(msg.getId()) ||
                TankFrame.INSTANCE.hasTank(msg.getId())
        ){
            return;
        }

        Tank tank = TankFactory.createTank(msg.getX(), msg.getY(), msg.getDir(),TankFrame.INSTANCE,msg.getGroup(),msg.getId());

        TankFrame.INSTANCE.addTank(tank);

        // 将自己再写回新坦克面板里
        TankJoinMsg myTankMsg = new TankJoinMsg(TankFrame.INSTANCE.getMyTank(),TankType.CREATE);
        Client.INSTANCE.send(myTankMsg);

        System.out.println("敌人："+msg.toString());
        System.out.println("自己："+myTankMsg.toString());
    }


}
