package com.parker.tank.net.handler;


import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.handler.KindHandler;
import com.parker.tank.net.handler.bullet.CreateHandler;
import com.parker.tank.net.msg.BulletJoinMsg;
import com.parker.tank.net.msg.BulletType;
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
public class BulletHandler implements KindHandler {

    private static final Map<BulletType, BaseHandler> HANDLER_MAP = new HashMap<>();

    private static final Class<?> keyClazz = BulletJoinMsg.class;

    static{

        /*Set<Class<?>> classes = PackageUtil.listSubClazz(
                BulletHandler.class.getPackage().getName()+".bullet", true, BaseHandler.class);*/

        List<Class> classes = ClassUtils.getAllClassByInterface(CreateHandler.class.getPackage().getName(),BaseHandler.class);

        for (Class<?> aClass : classes) {
            // 位运算 去除抽象类
            if((aClass.getModifiers() & Modifier.ABSTRACT) != 0){
                continue;
            }

            try {
                BaseHandler baseHandler = (BaseHandler) aClass.newInstance();
                BulletType bulletType = baseHandler.getBulletType();
                HANDLER_MAP.put(bulletType,baseHandler);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 获得执行器
     * @param bulletType
     * @return
     */
    public BaseHandler getHandler(BulletType bulletType){
        return HANDLER_MAP.get(bulletType);
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
