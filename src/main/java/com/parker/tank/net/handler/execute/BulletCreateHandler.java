package com.parker.tank.net.handler.execute;

import com.parker.tank.Audio;
import com.parker.tank.Bullet;
import com.parker.tank.Tank;
import com.parker.tank.TankFrame;
import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.msg.BulletJoinMsg;
import com.parker.tank.net.msg.Msg;
import com.parker.tank.net.msg.MsgType;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:55
 * @Description: 炮弹创建器
 *
 * 针对 新加入的炮弹显示炮弹的处理 一般是由客户端来计算时间
 *
 * 这里比较繁琐 暂时不处理了
 *
 *
 */
public class BulletCreateHandler extends BaseHandler {

    /** 执行器 状态 */
    private final MsgType[] msgTypes = new MsgType[]{MsgType.BULLET_CREATE};

    @Override
    public MsgType[] getTypes() {
        return this.msgTypes;
    }

    @Override
    public void execute(Msg baseMsg) {
        // 类型转换
        BulletJoinMsg msg;
        if(baseMsg instanceof BulletJoinMsg){
            msg = (BulletJoinMsg) baseMsg;
        }else{
            return;
        }


        if(TankFrame.INSTANCE.hasBullet(msg.getId())){
            return;
        }
        System.out.println("炮弹新建："+msg.getId());
        // 炮弹新建
        Tank tank = TankFrame.INSTANCE.getTank(msg.getTankId());
        Bullet bullet = new Bullet(tank.getX(), tank.getY(), tank.getDir(), TankFrame.INSTANCE, tank, msg.getId());
        TankFrame.INSTANCE.addBullet(bullet);

        // 开火音效
        new Thread(()->{
            new Audio("static/audio/tank_fire.wav").play();
        }).start();
    }
}
