package com.parker.tank.net.handler.execute;

import com.parker.tank.Bullet;
import com.parker.tank.TankFrame;
import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.msg.BulletDiedMsg;
import com.parker.tank.net.msg.Msg;
import com.parker.tank.net.msg.MsgType;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:55
 * @Description: 炮弹死亡处理器
 */
public class BulletDiedHandler extends BaseHandler {

    /** 执行器 状态 */
    private final MsgType[] msgTypes = new MsgType[]{MsgType.BULLET_DIED};

    @Override
    public MsgType[] getTypes() {
        return this.msgTypes;
    }

    @Override
    public void execute(Msg baseMsg) {
        // 类型转换
        BulletDiedMsg msg;
        if(baseMsg instanceof BulletDiedMsg){
            msg = (BulletDiedMsg) baseMsg;
        }else{
            return;
        }

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
