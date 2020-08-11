package com.parker.tank.util;

import com.parker.tank.Bullet;
import com.parker.tank.Tank;

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
        Rectangle tankPosition = tank.getPosition();
        Rectangle bulletPosition = bullet.getPosition();
        if(bulletPosition.intersects(tankPosition)){
            tank.died();
            bullet.died();
            flag = false;
        }
        return flag;
    }

}
