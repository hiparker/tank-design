package com.parker.tank.net.handler.execute;

import com.parker.tank.Audio;
import com.parker.tank.Tank;
import com.parker.tank.TankFrame;
import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.msg.*;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:55
 * @Description: 坦克移动执行器
 */
public class TankMoveHandler extends BaseHandler {

    /** 执行器 状态 */
    private final MsgType[] msgTypes = new MsgType[]{MsgType.TANK_MOVE};

    @Override
    public MsgType[] getTypes() {
        return this.msgTypes;
    }

    @Override
    public void execute(Msg baseMsg) {

        // 类型转换
        TankMoveMsg msg;
        if(baseMsg instanceof TankMoveMsg){
            msg = (TankMoveMsg) baseMsg;
        }else{
            return;
        }

        if(!TankFrame.INSTANCE.hasTank(msg.getId())){
            return;
        }
        System.out.println("坦克移动："+msg.getId());
        // 从坦克移动
        Tank tank = TankFrame.INSTANCE.getTank(msg.getId());
        tank.setMoving(msg.isMoving());
        if(msg.isMoving()){
            tank.setDir(msg.getDir());
        }

        // 移动音乐
        new Thread(()->{
            new Audio("static/audio/tank_move.wav").play();
        }).start();
    }
}
