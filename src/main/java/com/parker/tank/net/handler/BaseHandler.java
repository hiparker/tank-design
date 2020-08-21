package com.parker.tank.net.handler;

import com.parker.tank.net.msg.Msg;
import com.parker.tank.net.msg.MsgType;

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
    public abstract MsgType[] getTypes();



    /**
     * 坦克执行
     *
     * @param baseMsg
     */
    public abstract void execute(Msg baseMsg);


}
