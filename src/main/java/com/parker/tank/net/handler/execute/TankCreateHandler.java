package com.parker.tank.net.handler.execute;

import com.parker.tank.Tank;
import com.parker.tank.TankFactory;
import com.parker.tank.TankFrame;
import com.parker.tank.TankGroup;
import com.parker.tank.net.Client;
import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.msg.Msg;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.MsgType;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:55
 * @Description: 坦克创建执行器
 */
public class TankCreateHandler extends BaseHandler {

    /** 执行器 状态 */
    private final MsgType[] msgTypes = new MsgType[]{MsgType.TANK_CREATE};

    @Override
    public MsgType[] getTypes() {
        return this.msgTypes;
    }

    @Override
    public void execute(Msg baseMsg) {

        // 类型转换
        TankJoinMsg msg;
        if(baseMsg instanceof TankJoinMsg){
            msg = (TankJoinMsg) baseMsg;
        }else{
            return;
        }

        // 如果是自身 或者 tanks列表内有改坦克 则不创建
        if(TankFrame.INSTANCE.getMyTank().getId().equals(msg.getId()) ||
                TankFrame.INSTANCE.hasTank(msg.getId())
            ) return;

        Tank tank = TankFactory.createTank(msg.getX(), msg.getY(), msg.getDir(),TankFrame.INSTANCE, TankGroup.BLUE,msg.getId());

        TankFrame.INSTANCE.addTank(tank);

        // 将自己再写回新坦克面板里
        TankJoinMsg myTankMsg = new TankJoinMsg(TankFrame.INSTANCE.getMyTank());
        Client.INSTANCE.send(myTankMsg,MsgType.TANK_CREATE);

        System.out.println("敌人："+msg.toString());
        System.out.println("自己："+myTankMsg.toString());
    }


}
