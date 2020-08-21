package com.parker.tank.net.handler;

import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 00:21
 * @Description: 执行器
 */
public interface BaseHandler {

    /**
     * 获得执行器类型
     * @return
     */
    TankType getType();

    /**
     * 执行
     * @param msg
     */
    void execute(TankJoinMsg msg);

}
