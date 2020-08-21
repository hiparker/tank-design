package com.parker.tank.net.msg;


import com.parker.tank.net.handler.KindHandler;
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
public enum  Msg {

    INSTANCE;

    private static final Map<Class<?>, KindHandler> HANDLER_MAP = new HashMap<>();

    static{
        List<Class> classes = ClassUtils.getAllClassByInterface(KindHandler.class);

        /*Set<Class<?>> classes = PackageUtil.listSubClazz(
                KindHandler.class.getPackage().getName(), false, KindHandler.class);*/
        for (Class<?> aClass : classes) {
            // 位运算 去除抽象类
            if((aClass.getModifiers() & Modifier.ABSTRACT) != 0){
                continue;
            }

            try {
                KindHandler kindHandler = (KindHandler) aClass.newInstance();
                Class<?> clazz = kindHandler.getKeyClazz();
                HANDLER_MAP.put(clazz,kindHandler);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获得执行器
     * @param clazz
     * @return
     */
    public KindHandler getHandler(Class<?> clazz){
        return HANDLER_MAP.get(clazz);
    }

}
