package com.parker.tank.net.handler;

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
public class FireHandler implements BaseHandler{

    /** 执行器 状态 */
    private TankType tankType = TankType.FIRE;

    @Override
    public TankType getType() {
        return this.tankType;
    }

    @Override
    public void execute(TankJoinMsg msg) {
        if(!TankFrame.INSTANCE.hasTank(msg.getId())){
            return;
        }
        System.out.println("坦克开火："+msg.getId());
        // 开火
        TankFrame.INSTANCE.getTanks(msg.getId()).fired();
    }
}