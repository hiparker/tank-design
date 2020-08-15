package com.parker.tank.collide;

import com.parker.tank.GameObject;
import com.parker.tank.Mine;
import com.parker.tank.Tank;

import java.awt.*;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.collide
 * @Author: Parker
 * @CreateTime: 2020-08-13 17:02
 * @Description: 炮弹-坦克 碰撞器实现
 */
public enum MineAndTankCollide implements Collide{

    /**
     * 单例
     */
    INSTANCE;

    @Override
    public boolean comparator(GameObject go1, GameObject go2) {
        boolean flag = true;
        if (go1 instanceof Mine && go2 instanceof Tank){
            Mine mine = (Mine) go1;
            Tank tank = (Tank) go2;

            Rectangle tankPosition = tank.getPosition();
            Rectangle minePosition = mine.getPosition();
            if(minePosition.intersects(tankPosition)){
                tank.died();
                mine.died();
                flag = false;
            }
        }else if(go1 instanceof Tank && go2 instanceof Mine){
            return this.comparator(go2,go1);
        }
        return flag;
    }
}
