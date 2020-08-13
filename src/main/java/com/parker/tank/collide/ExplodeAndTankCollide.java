package com.parker.tank.collide;

import com.parker.tank.Bullet;
import com.parker.tank.Explode;
import com.parker.tank.GameObject;
import com.parker.tank.Tank;
import com.parker.tank.dist.TankGroup;

import java.awt.*;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.collide
 * @Author: Parker
 * @CreateTime: 2020-08-13 17:02
 * @Description: 爆炸物-坦克 碰撞器实现
 *
 * 理论上将 爆炸物 碰上 坦克 坦克也会直接爆炸
 *
 */
public enum ExplodeAndTankCollide implements Collide{

    /**
     * 单例
     */
    INSTANCE;

    @Override
    public boolean comparator(GameObject go1, GameObject go2) {
        boolean flag = true;
        if (go1 instanceof Explode && go2 instanceof Tank){
            Explode explode = (Explode) go1;
            Tank tank = (Tank) go2;

            Rectangle tankPosition = tank.getPosition();
            Rectangle explodePosition = explode.getPosition();
            if(explodePosition.intersects(tankPosition)){

                // 暂时设置自身无敌
                if(!tank.getGroup().equals(TankGroup.RED)){
                    tank.died();
                    flag = false;
                }
            }
        }else if(go1 instanceof Tank && go2 instanceof Explode){
            return this.comparator(go2,go1);
        }
        return flag;
    }
}
