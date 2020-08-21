package com.parker.tank.net.handler;

import com.parker.tank.Tank;
import com.parker.tank.TankFactory;
import com.parker.tank.TankFrame;
import com.parker.tank.net.Client;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:55
 * @Description: 坦克创建器
 */
public class RemoveHandler implements BaseHandler{

    private TankType tankType = TankType.REMOVE;

    @Override
    public TankType getType() {
        return this.tankType;
    }

    @Override
    public void execute(TankJoinMsg msg) {
        if(!TankFrame.INSTANCE.hasTank(msg.getId())){
            return;
        }
        System.out.println("坦克退出："+msg.getId());
        // 从坦克列表中移除
        TankFrame.INSTANCE.removeTanks(msg.getId());
    }
}
