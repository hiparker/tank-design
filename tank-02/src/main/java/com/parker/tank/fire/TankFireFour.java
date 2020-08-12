package com.parker.tank.fire;

import com.parker.tank.Audio;
import com.parker.tank.Bullet;
import com.parker.tank.Dir;
import com.parker.tank.Tank;

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
public enum  TankFireFour implements TankFire{

    /**
     * 实例
     */
    INSTANCE;

    @Override
    public void fire(Tank tank) {
        if(tank == null || tank.tankFrame == null){
            return;
        }

        Dir[] values = Dir.values();
        for (Dir value : values) {
            new Bullet(tank.getX(),tank.getY(),value,tank.tankFrame,tank);
        }
        
        // 开火音效
        new Thread(()->{
            new Audio("static/audio/tank_fire.wav").play();
        }).start();
    }
}
