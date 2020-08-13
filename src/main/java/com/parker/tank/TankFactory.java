package com.parker.tank;

import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 12:08
 * @Description: 坦克简单工厂
 */
public final class TankFactory {

    private TankFactory(){}

    /**
     * 创建坦克
     * @param x x 坐标
     * @param y y 坐标
     * @param gm tank画布
     * @return
     */
    public static Tank createTank(int x, int y, Dir dir, GameModel gm, TankGroup group) {
        Tank tank = new Tank(x, y, dir, gm, group);
        tank.setFutureTank(new Tank(x, y, dir, gm, group));
        return tank;
    }

    /**
     * 创建自动坦克
     * @param x x 坐标
     * @param y y 坐标
     * @param gm tank画布
     * @return
     */
    public static Tank createAutoTank(int x, int y, Dir dir,GameModel gm,TankGroup group,boolean autoFlag) {
        Tank tank = new Tank(x, y, dir, gm, group,autoFlag);
        tank.setFutureTank(new Tank(x, y, dir, gm, group,autoFlag));
        return tank;
    }

}
