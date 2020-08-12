package com.parker.tank.factory.child;

import com.parker.tank.TankFrame;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.entity.bullet.BigBullet;
import com.parker.tank.entity.bullet.SmallBullet;
import com.parker.tank.entity.explode.BigExplode;
import com.parker.tank.entity.explode.SmallExplode;
import com.parker.tank.entity.tank.Tank;
import com.parker.tank.factory.GameFactory;
import com.parker.tank.factory.base.BaseBullet;
import com.parker.tank.factory.base.BaseExplode;
import com.parker.tank.factory.base.BaseTank;

/**
 * @BelongsProject: tank-02
 * @BelongsPackage: com.parker.tank.factory.child
 * @Author: Parker
 * @CreateTime: 2020-08-12 23:26
 * @Description: 默认工厂
 */
public class SmallFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tankFrame, TankGroup group) {
        BaseTank tank = new Tank(x, y, dir, tankFrame, group);
        tank.setFutureTank(new Tank(x, y, dir, tankFrame, group));
        return tank;
    }

    @Override
    public BaseTank createAutoTank(int x, int y, Dir dir,TankFrame tankFrame,TankGroup group,boolean autoFlag) {
        BaseTank tank = new Tank(x, y, dir, tankFrame, TankGroup.DEFAULT,autoFlag);
        tank.setFutureTank(new Tank(x, y, dir, tankFrame, TankGroup.DEFAULT,autoFlag));
        return tank;
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tankFrame, BaseTank belongTank) {
        return new SmallBullet(x,y,dir,tankFrame,belongTank);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new SmallExplode(x,y,tankFrame);
    }

}
