package com.parker.tank.factory;

import com.parker.tank.TankFrame;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.factory.base.BaseBullet;
import com.parker.tank.factory.base.BaseExplode;
import com.parker.tank.factory.base.BaseTank;

/**
 * @BelongsProject: tank-02
 * @BelongsPackage: com.parker.tank.factory.a
 * @Author: Parker
 * @CreateTime: 2020-08-12 23:24
 * @Description: 游戏工厂
 *
 * 抽象工厂 设计模式
 *
 */
public abstract class GameFactory {

    /**
     * 创建坦克
     * @param x x 坐标
     * @param y y 坐标
     * @param tankFrame tank画布
     * @return
     */
    public abstract BaseTank createTank(int x, int y, Dir dir, TankFrame tankFrame, TankGroup group);

    /**
     * 创建自动坦克
     * @param x x 坐标
     * @param y y 坐标
     * @param tankFrame tank画布
     * @return
     */
    public abstract BaseTank createAutoTank(int x, int y, Dir dir, TankFrame tankFrame, TankGroup group,boolean autoFlag);


    /**
     * 创建炮弹
     * @param x x 坐标
     * @param y y 坐标
     * @param tankFrame tank画布
     * @return
     */
    public abstract BaseBullet createBullet(int x, int y, Dir dir, TankFrame tankFrame, BaseTank belongTank);

    /**
     * 创建爆炸效果
     * @param x x 坐标
     * @param y y 坐标
     * @param tankFrame tank画布
     * @return
     */
    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);

}
