package com.parker.tank.fire;

import com.parker.tank.Bullet;
import com.parker.tank.Dir;
import com.parker.tank.Tank;

/**
 * @BelongsProject: tank-02
 * @BelongsPackage: com.parker.tank.fire
 * @Author: Parker
 * @CreateTime: 2020-08-12 16:51
 * @Description: 坦克开火 - 单发
 */
public class TankFireFour implements TankFire{
    @Override
    public void fire(Tank tank) {
        if(tank == null || tank.tankFrame == null){
            return;
        }
        tank.tankFrame.bulletList.add(new Bullet(tank.getX(),tank.getY(),Dir.LEFT,tank.tankFrame,tank));
        tank.tankFrame.bulletList.add(new Bullet(tank.getX(),tank.getY(),Dir.UP,tank.tankFrame,tank));
        tank.tankFrame.bulletList.add(new Bullet(tank.getX(),tank.getY(),Dir.RIGHT,tank.tankFrame,tank));
        tank.tankFrame.bulletList.add(new Bullet(tank.getX(),tank.getY(),Dir.DOWN,tank.tankFrame,tank));
    }
}
