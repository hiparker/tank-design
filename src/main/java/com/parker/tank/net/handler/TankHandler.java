package com.parker.tank.net.handler;


import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.handler.KindHandler;
import com.parker.tank.net.handler.tank.CreateHandler;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;
import com.parker.tank.util.ClassUtils;
import com.parker.tank.util.PackageUtil;

import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 09:25
 * @Description: 客户端执行器
 */
public class TankHandler implements KindHandler {

    private static final Map<TankType, BaseHandler> HANDLER_MAP = new HashMap<>();

    private static final Class<?> keyClazz = TankJoinMsg.class;

    static{

        /*Set<Class<?>> classes = PackageUtil.listSubClazz(
                TankHandler.class.getPackage().getName()+".tank", true, BaseHandler.class);*/

        List<Class> classes = ClassUtils.getAllClassByInterface(CreateHandler.class.getPackage().getName(),BaseHandler.class);

        for (Class<?> aClass : classes) {
            // 位运算 去除抽象类
            if((aClass.getModifiers() & Modifier.ABSTRACT) != 0){
                continue;
            }

            try {
                BaseHandler baseHandler = (BaseHandler) aClass.newInstance();
                TankType tankType = baseHandler.getTankType();
                HANDLER_MAP.put(tankType,baseHandler);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获得执行器
     * @param tankType
     * @return
     */
    public BaseHandler getHandler(TankType tankType){
        return HANDLER_MAP.get(tankType);
    }

    /**
     * 获得 Msg Key Clazz
     * @return
     */
    @Override
    public Class<?> getKeyClazz() {
        return keyClazz;
    }
}
