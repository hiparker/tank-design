package com.parker.tank.util;

import com.parker.tank.Bullet;
import com.parker.tank.Tank;
import com.parker.tank.dist.TankGroup;

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
        Tank bulletBelongTank = bullet.getBelongTank();
        Rectangle tankPosition = tank.getPosition();
        Rectangle bulletPosition = bullet.getPosition();
        if(bulletPosition.intersects(tankPosition)){
            // 自己不能打自己 并且 默认关闭队友伤害
            if(!tank.equals(bulletBelongTank) &&
                !tank.getGroup().equals(bulletBelongTank.getGroup())
                ){

                // 暂时设置自身无敌
                if(!tank.getGroup().equals(TankGroup.RED)){
                    tank.died();
                    bullet.died();
                    flag = false;
                }
            }
        }
        return flag;
    }

}
