package com.parker.tank.util;

import com.parker.tank.Bullet;
import com.parker.tank.Tank;
import com.parker.tank.TankGroup;
import com.parker.tank.net.Client;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;

import java.awt.*;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank.util
 * @Author: Parker
 * @CreateTime: 2020-08-11 16:03
 * @Description: 坦克工具类
 */
public final class TankUtil {

    /**
     * 私有化构造函数
     */
    private TankUtil(){}

    /**
     * 碰撞检测
     * @param tank
     */
    public static boolean collideWith(Tank tank, Bullet bullet) {
        boolean flag = true;
        Tank bulletBelongTank = bullet.belongTank;
        Rectangle tankPosition = tank.getPosition();
        Rectangle bulletPosition = bullet.getPosition();
        if(bulletPosition.intersects(tankPosition)){
            // 自己不能打自己
            if(!tank.equals(bulletBelongTank)){

                // 暂时设置自身无敌
                //if(!tank.getGroup().equals(TankGroup.RED)){
                // 坦克死亡
                Client.INSTANCE.send(new TankJoinMsg(tank, TankType.DIED));
                // 子弹死亡

                flag = false;
                    /*tank.died();
                    bullet.died();*/

                //}
            }
        }
        return flag;
    }

    /**
     * 坦克自身 碰撞检测
     * @param tank1 tank2
     */
    public static boolean collideWithTank(Tank tank1, Tank tank2) {
        boolean flag = true;
        Rectangle tank1Position = tank1.getPosition();
        Rectangle tank2Position = tank2.getPosition();
        if(tank1Position.intersects(tank2Position)){
           flag = false;
        }
        return flag;
    }

}
