package com.parker.tank.net.handler;

import com.parker.tank.Audio;
import com.parker.tank.Tank;
import com.parker.tank.TankFrame;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:55
 * @Description: 坦克创建器
 */
public class MoveHandler implements BaseHandler{

    /** 执行器 状态 */
    private TankType tankType = TankType.MOVE;

    @Override
    public TankType getType() {
        return this.tankType;
    }

    @Override
    public void execute(TankJoinMsg msg) {
        if(!TankFrame.INSTANCE.hasTank(msg.getId())){
            return;
        }
        System.out.println("坦克移动："+msg.getId());
        // 从坦克移动
        Tank tank = TankFrame.INSTANCE.getTanks(msg.getId());
        tank.setDir(msg.getDir());
        tank.setMoving(msg.isMoving());

        // 移动音乐
        new Thread(()->{
            new Audio("static/audio/tank_move.wav").play();
        }).start();
    }
}
