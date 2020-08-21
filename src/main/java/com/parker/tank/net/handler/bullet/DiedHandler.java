package com.parker.tank.net.handler.bullet;

import com.parker.tank.Bullet;
import com.parker.tank.TankFrame;
import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.msg.BulletJoinMsg;
import com.parker.tank.net.msg.BulletType;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:55
 * @Description: 坦克死亡处理器
 */
public class DiedHandler extends BaseHandler {

    /** 执行器 状态 */
    private BulletType bulletType = BulletType.DIED;

    @Override
    public BulletType getBulletType() {
        return this.bulletType;
    }

    @Override
    public void execute(BulletJoinMsg msg) {
        if(!TankFrame.INSTANCE.hasBullet(msg.getId())){
            return;
        }
        System.out.println("炮弹死亡："+msg.getId());
        // 坦克死亡
        Bullet bullet = TankFrame.INSTANCE.getBullet(msg.getId());
        TankFrame.INSTANCE.removeBullet(msg.getId());
        bullet.died();
    }
}
