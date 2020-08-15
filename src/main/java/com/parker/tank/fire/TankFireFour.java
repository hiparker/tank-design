package com.parker.tank.fire;

import com.parker.tank.Bullet;
import com.parker.tank.Tank;
import com.parker.tank.decortor.TailWDecortor;
import com.parker.tank.dist.Dir;
import com.parker.tank.flyweight.BulletPool;

/**
 * @BelongsProject: tank-02
 * @BelongsPackage: com.parker.tank.fire
 * @Author: Parker
 * @CreateTime: 2020-08-12 16:51
 * @Description: 坦克开火 - 单发
 *
 * 策略模式 -》 具体实现使用单例模式
 *
 */
public class TankFireFour implements TankFire{

    @Override
    public void fire(Tank tank) {
        if(tank == null || tank.getGameModel() == null){
            return;
        }

        Dir[] values = Dir.values();
        for (Dir value : values) {

            tank.getGameModel().add(
                    // 原始子弹
                    /*new Bullet(
                            tank.getX(),tank.getY(),value,tank.getGameModel(),tank)*/

                    // 享元子弹
                    // 享元获取子弹
                    BulletPool.INSTANCE.getLiveBullet(tank,value)
            );

        }
        
        // 开火音效
        /*new Thread(()->{
            new Audio("static/audio/tank_fire.wav").play();
        }).start();*/
    }
}
