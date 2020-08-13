package com.parker.tank.collide;

import com.parker.tank.Bullet;
import com.parker.tank.GameObject;
import com.parker.tank.Tank;
import com.parker.tank.dist.TankGroup;

import java.awt.*;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.collide
 * @Author: Parker
 * @CreateTime: 2020-08-13 17:02
 * @Description: 炮弹-坦克 碰撞器实现
 */
public enum BulletAndTankCollide implements Collide{

    /**
     * 单例
     */
    INSTANCE;

    @Override
    public boolean comparator(GameObject go1, GameObject go2) {
        boolean flag = true;
        if (go1 instanceof Bullet && go2 instanceof Tank){
            Bullet bullet = (Bullet) go1;
            Tank tank = (Tank) go2;

            Tank bulletBelongTank = bullet.getBelongTank();
            Rectangle tankPosition = tank.getPosition();
            Rectangle bulletPosition = bullet.getPosition();
            if(bulletPosition.intersects(tankPosition)){
                // 自己不能打自己 并且 默认关闭队友伤害
                if(!tank.equals(bulletBelongTank) &&
                    !tank.getGroup().equals(bulletBelongTank.getGroup())
                    ){

                    // 暂时设置自身无敌
                    if(!tank.getGroup().equals(TankGroup.RED)){
                        tank.died();
                        bullet.died();
                        flag = false;
                    }
                }
            }
        }else if(go1 instanceof Tank && go2 instanceof Bullet){
            return this.comparator(go2,go1);
        }
        return flag;
    }
}
