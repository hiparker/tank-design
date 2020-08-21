package com.parker.tank.net.handler.execute;

import com.parker.tank.TankFrame;
import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.msg.Msg;
import com.parker.tank.net.msg.TankIdMsg;
import com.parker.tank.net.msg.MsgType;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:55
 * @Description: 坦克开火执行器
 */
public class TankFireHandler extends BaseHandler {

    /** 执行器 状态 */
    private final MsgType[] msgTypes = new MsgType[]{MsgType.TANK_FIRE};

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

        if(!TankFrame.INSTANCE.hasTank(msg.getId())){
            return;
        }
        System.out.println("坦克开火："+msg.getId());
        // 开火
        TankFrame.INSTANCE.getTank(msg.getId()).fired();
    }
}
