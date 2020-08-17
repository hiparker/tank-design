package com.parker.tank.fire;

import com.parker.tank.Tank;
import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.factory.TankFrameFactory;
import com.parker.tank.flyweight.BulletPool;

/**
 * @BelongsProject: tank-02
 * @BelongsPackage: com.parker.tank.fire
 * @Author: Parker
 * @CreateTime: 2020-08-12 16:51
 * @Description: 坦克开火 - 单发
 */
public enum TankFireDefault implements TankFire{

    /**
     * 实例
     */
    INSTANCE;

    @Override
    public void fire(Tank tank) {
        BaseGameModel bgm = TankFrameFactory.INSTANCE.getTankFrame().getBgm();
        if(bgm == null){
            return;
        }

        bgm.add(

                // 原始子弹
                /*new Bullet(
                        tank.getX(),tank.getY(),tank.getDir(),tank.getGameModel(),tank)*/

                // 装饰器子弹
                /*new TailWDecortor(
                        new TailYDecortor(
                                new Bullet(
                                        tank.getX(),tank.getY(),tank.getDir(),tank.getGameModel(),tank)
                        )
                )*/

                // 享元获取子弹
                BulletPool.INSTANCE.getLiveBullet(tank,tank.getDir())

        );

        // 开火音效
        /*new Thread(()->{
            new Audio("static/audio/tank_fire.wav").play();
        }).start();*/
    }
}
