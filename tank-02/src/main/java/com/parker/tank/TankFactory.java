package com.parker.tank;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-11 17:09
 * @Description: 坦克工厂
 *
 * 工厂模式
 *
 */
public final class TankFactory {

    private TankFactory(){};

    public static Tank createTank(int x, int y, Dir dir,TankFrame tankFrame,TankGroup group) {
        Tank tank = new Tank(x, y, dir, tankFrame, group);
        tank.setFutureTank(new Tank(x, y, dir, tankFrame, group));
        return tank;
    }

    public static Tank createTank(int x, int y, Dir dir,TankFrame tankFrame,TankGroup group,boolean autoFlag) {
        Tank tank = new Tank(x, y, dir, tankFrame, group,autoFlag);
        tank.setFutureTank(new Tank(x, y, dir, tankFrame, group,autoFlag));
        return tank;
    }



}
