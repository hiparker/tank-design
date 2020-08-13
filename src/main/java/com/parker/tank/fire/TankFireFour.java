package com.parker.tank.fire;

import com.parker.tank.Audio;
import com.parker.tank.dist.Dir;
import com.parker.tank.factory.base.BaseTank;

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
    public void fire(BaseTank tank) {
        if(tank == null || tank.getTankFrame() == null){
            return;
        }

        Dir[] values = Dir.values();
        for (Dir value : values) {
            tank.getTankFrame().getGf().createBullet(
                    tank.getX(),tank.getY(),value,tank.getTankFrame(),tank);
        }
        
        // 开火音效
        new Thread(()->{
            new Audio("static/audio/tank_fire.wav").play();
        }).start();
    }
}
