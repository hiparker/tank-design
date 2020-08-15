package com.parker.tank.collide;

import com.parker.tank.Bullet;
import com.parker.tank.GameObject;
import com.parker.tank.Tank;
import com.parker.tank.config.PropertiesMgr;
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

    /** 队友伤害 */
    private boolean enableTameHarm = PropertiesMgr.getByBoolean("enableTameHarm");

    /** 自身无敌 */
    private boolean enableMainTankInvincibly = PropertiesMgr.getByBoolean("enableMainTankInvincibly");


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

                boolean overFlag = false;

                // 队友伤害
                if(enableTameHarm){
                    // 自身无敌
                    if(!enableMainTankInvincibly){
                        overFlag = true;
                    }else{
                        // 暂时设置自身无敌
                        if(!tank.getGroup().equals(TankGroup.RED)){
                            overFlag = true;
                        }
                    }
                }else{
                    if(!tank.equals(bulletBelongTank) &&
                            !tank.getGroup().equals(bulletBelongTank.getGroup())
                    ){
                        // 自身无敌
                        if(!enableMainTankInvincibly){
                            overFlag = true;
                        }else{
                            // 暂时设置自身无敌
                            if(!tank.getGroup().equals(TankGroup.RED)){
                                overFlag = true;
                            }
                        }
                    }
                }

                if(overFlag){
                    tank.died();
                    bullet.died();
                    flag = false;
                }

            }
        }else if(go1 instanceof Tank && go2 instanceof Bullet){
            return this.comparator(go2,go1);
        }
        return flag;
    }
}
