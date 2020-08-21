package com.parker.tank.net.handler;


import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.MsgType;
import com.parker.tank.util.ClassUtils;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:25
 * @Description: 客户端执行器
 */
public enum MsgHandler {

    INSTANCE;

    private static final Map<MsgType, BaseHandler> HANDLER_MAP = new HashMap<>();

    private static final Class<?> keyClazz = TankJoinMsg.class;

    static{

        /*Set<Class<?>> classes = PackageUtil.listSubClazz(
                TankHandler.class.getPackage().getName()+".tank", true, BaseHandler.class);*/

        List<Class> classes = ClassUtils.getAllClassByInterface(
                BaseHandler.class.getPackage().getName()+".execute",BaseHandler.class);

        for (Class<?> aClass : classes) {
            // 位运算 去除抽象类
            if((aClass.getModifiers() & Modifier.ABSTRACT) != 0){
                continue;
            }

            try {
                BaseHandler baseHandler = (BaseHandler) aClass.newInstance();
                MsgType[] types = baseHandler.getTypes();
                for (MsgType type : types) {
                    HANDLER_MAP.put(type,baseHandler);
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获得执行器
     * @param msgType
     * @return
     */
    public BaseHandler getHandler(MsgType msgType){
        return HANDLER_MAP.get(msgType);
    }

}
