package com.parker.tank.fire;

import com.parker.tank.Audio;
import com.parker.tank.Bullet;
import com.parker.tank.Tank;

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
        if(tank == null || tank.getTankFrame() == null){
            return;
        }

        new Bullet(tank.getX(),tank.getY(),tank.getDir(),tank.getTankFrame(),tank);

        // 开火音效
        new Thread(()->{
            new Audio("static/audio/tank_fire.wav").play();
        }).start();
    }
}
