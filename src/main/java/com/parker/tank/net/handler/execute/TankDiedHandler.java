package com.parker.tank.net.handler.execute;

import com.parker.tank.Tank;
import com.parker.tank.TankFrame;
import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.msg.*;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:55
 * @Description: 坦克死亡执行器
 */
public class TankDiedHandler extends BaseHandler {

    /** 执行器 状态 */
    private final MsgType[] msgTypes = new MsgType[]{MsgType.TANK_DIED};

    @Override
    public MsgType[] getTypes() {
        return this.msgTypes;
    }

    @Override
    public void execute(Msg baseMsg) {

        // 类型转换
        TankIdMsg msg;
        if(baseMsg instanceof TankIdMsg){
            msg = (TankIdMsg) baseMsg;
        }else{
            return;
        }

        // 如果 列表内无 该坦克 直接退出
        if(!TankFrame.INSTANCE.hasTank(msg.getId())){
            return;
        }


        System.out.println("坦克死亡："+msg.getId());
        // 坦克死亡
        Tank tank = TankFrame.INSTANCE.getTank(msg.getId());
        TankFrame.INSTANCE.removeTank(msg.getId());
        tank.died();
        if(TankFrame.INSTANCE.getMyTank() != null){
            if(TankFrame.INSTANCE.getMyTank().getId().equals(msg.getId())){
                TankFrame.INSTANCE.setMyTank(null);
            }
        }
    }
}
