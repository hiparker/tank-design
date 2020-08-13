package com.parker.tank.collide;

import com.parker.tank.GameObject;
import com.parker.tank.Tank;
import com.parker.tank.Wall;

import java.awt.*;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.collide
 * @Author: Parker
 * @CreateTime: 2020-08-13 17:02
 * @Description: 坦克-墙体 碰撞器实现
 */
public enum TankAndWallCollide implements Collide{

    /**
     * 单例
     */
    INSTANCE;

    @Override
    public boolean comparator(GameObject go1, GameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Wall){
            Tank tank = (Tank) go1;
            Wall wall = (Wall) go2;
            Rectangle tank1Position = tank.getPosition();
            Rectangle tank2tPosition = wall.getPosition();
            if(tank1Position.intersects(tank2tPosition)){
                tank.rollback();
            }
        }else if (go1 instanceof Wall && go2 instanceof Tank){
            return this.comparator(go2,go1);
        }
        return true;
    }
}
