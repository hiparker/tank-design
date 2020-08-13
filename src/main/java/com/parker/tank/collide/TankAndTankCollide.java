package com.parker.tank.collide;

import com.parker.tank.Bullet;
import com.parker.tank.GameObject;
import com.parker.tank.Tank;
import com.parker.tank.dist.TankGroup;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.collide
 * @Author: Parker
 * @CreateTime: 2020-08-13 17:02
 * @Description: 坦克-坦克 碰撞器实现
 */
public enum TankAndTankCollide implements Collide{

    /**
     * 单例
     */
    INSTANCE;

    @Override
    public boolean comparator(GameObject go1, GameObject go2) {
        if (go1 instanceof Tank && go2 instanceof Tank){
            Tank tank1 = (Tank) go1;
            Tank tank2 = (Tank) go2;
            Rectangle tank1Position = tank1.getPosition();
            Rectangle tank2tPosition = tank2.getPosition();
            if(tank1Position.intersects(tank2tPosition)){
                tank1.rollback();
                tank2.rollback();

                // 为了防止双方 在回滚时还继续行走 所有坦克 在碰撞时禁止走动
                // 鉴于 有自动坦克 如果当前状态时 moving 在禁止50毫秒后 放行
                // 普通坦克直接停止移动
                /*if(tank1.isAutoFlag()) tank1.stopAndDelayStart();
                else tank1.stop();

                if(tank2.isAutoFlag()) tank2.stopAndDelayStart();
                else tank2.stop();

                if(tank1.getMoveTime() > tank2.getMoveTime()){
                    tank1.rollback();
                }else{
                    tank2.rollback();
                }*/
            }
        }
        return true;
    }
}
