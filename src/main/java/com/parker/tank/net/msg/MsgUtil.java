package com.parker.tank.net.msg;

import java.util.HashMap;
import java.util.Map;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.msg
 * @Author: Parker
 * @CreateTime: 2020-08-22 01:15
 * @Description: 信息工具类
 */
public enum MsgUtil {

    INSTANCE;

    private final Map<MsgType,Class<?>> msgMap = new HashMap<>();

    MsgUtil() {
        msgMap.put(MsgType.BULLET_CREATE,BulletJoinMsg.class);
        msgMap.put(MsgType.BULLET_DIED,BulletDiedMsg.class);
        msgMap.put(MsgType.TANK_CREATE,TankJoinMsg.class);
        msgMap.put(MsgType.TANK_DIED,TankIdMsg.class);
        msgMap.put(MsgType.TANK_FIRE,TankIdMsg.class);
        msgMap.put(MsgType.TANK_REMOVE,TankIdMsg.class);
        msgMap.put(MsgType.TANK_MOVE,TankMoveMsg.class);
    }

    /**
     * 获得 消息类
     * @param type
     * @return
     */
    public Class<?> getMsgClazz(MsgType type){
        return this.msgMap.get(type);
    }

    public static void main(String[] args) {
        //MsgUtil.INSTANCE.getMsgClazz(MsgType.BULLET_CREATE);
    }

}
