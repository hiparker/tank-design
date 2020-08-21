package com.parker.tank.net.handler;

import com.parker.tank.net.msg.BulletJoinMsg;
import com.parker.tank.net.msg.BulletType;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 00:21
 * @Description: 执行器
 */
public abstract class BaseHandler {

    /**
     * 获得坦克执行器类型
     * @return
     */
    public TankType getTankType(){
        throw new RuntimeException("未实现类型方法");
    }

    /**
     * 获得炮弹执行器类型
     * @return
     */
    public BulletType getBulletType(){
        throw new RuntimeException("未实现类型方法");
    }

    /**
     * 坦克执行
     *
     * @param msg
     */
    public void execute(TankJoinMsg msg) {
        throw new RuntimeException("未实现执行器方法");
    }

    /**
     * 子弹执行
     * @param msg
     */
    public void execute(BulletJoinMsg msg){
        throw new RuntimeException("未实现执行器方法");
    }

}
