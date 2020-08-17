package com.parker.tank.factory;

import com.parker.tank.Tank;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.faced.GameModel;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 12:08
 * @Description: 坦克简单工厂
 */
public final class TankFactory {

    /**
     * 普通坦克数量
     */
    public static int usualCount = 0;
    /**
     * 自动坦克数量
     */
    public static int autoCount = 0;

    private TankFactory(){}

    /**
     * 创建坦克
     * @param x x 坐标
     * @param y y 坐标
     * @return
     */
    public static Tank createTank(int x, int y, Dir dir, TankGroup group) {
        Tank tank = new Tank(x, y, dir, group);
        usualCount++;
        return tank;
    }

    /**
     * 创建自动坦克
     * @param x x 坐标
     * @param y y 坐标
     * @return
     */
    public static Tank createAutoTank(int x, int y, Dir dir,TankGroup group) {
        Tank tank = new Tank(x, y, dir, group,true);
        autoCount++;
        return tank;
    }

}
