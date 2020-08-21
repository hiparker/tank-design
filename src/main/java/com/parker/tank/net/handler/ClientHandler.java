package com.parker.tank.net.handler;


import com.parker.tank.net.msg.TankType;
import com.parker.tank.util.PackageUtil;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:25
 * @Description: 客户端执行器
 */
public final class ClientHandler{

    private static final ClientHandler INSTANCE = new ClientHandler();

    private static final Map<TankType,BaseHandler> HANDLER_MAP = new HashMap<>();


    static{

        Set<Class<?>> classes = PackageUtil.listSubClazz(
                BaseHandler.class.getPackage().getName(), true, BaseHandler.class);
        for (Class<?> aClass : classes) {
            // 位运算 去除抽象类
            if((aClass.getModifiers() & Modifier.ABSTRACT) != 0){
                continue;
            }

            try {
                BaseHandler baseHandler = (BaseHandler) aClass.newInstance();
                TankType tankType = baseHandler.getType();
                HANDLER_MAP.put(tankType,baseHandler);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 私有化构造函数
     */
    private ClientHandler(){}

    public static ClientHandler getInstance(){
        return INSTANCE;
    }

    /**
     * 获得执行器
     * @param tankType
     * @return
     */
    public BaseHandler getHandler(TankType tankType){
        return HANDLER_MAP.get(tankType);
    }

    public static void main(String[] args) {
        //ClientHandler.getInstance().
    }

}
