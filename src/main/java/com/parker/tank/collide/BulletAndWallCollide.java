package com.parker.tank.collide;

import com.parker.tank.*;
import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.factory.TankFrameFactory;

import java.awt.*;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.collide
 * @Author: Parker
 * @CreateTime: 2020-08-13 17:02
 * @Description: 坦克-墙体 碰撞器实现
 */
public enum BulletAndWallCollide implements Collide{

    /**
     * 单例
     */
    INSTANCE;

    @Override
    public boolean comparator(GameObject go1, GameObject go2) {
        if (go1 instanceof Bullet && go2 instanceof Wall){
            Bullet bullet = (Bullet) go1;
            Wall wall = (Wall) go2;
            Rectangle bulletPosition = bullet.getPosition();
            Rectangle walltPosition = wall.getPosition();
            if(bulletPosition.intersects(walltPosition)){
                BaseGameModel bgm = TankFrameFactory.INSTANCE.getTankFrame().getBgm();
                wall.subHp();
                // 如何和墙体相撞 子弹死亡 并爆炸
                bullet.died();
                // 坦克阵亡新建爆炸
                Explode explode = new Explode(bullet.getX(), bullet.getY());
                bgm.add(explode);
            }
        }else if (go1 instanceof Wall && go2 instanceof Bullet){
            return this.comparator(go2,go1);
        }
        return true;
    }

}
