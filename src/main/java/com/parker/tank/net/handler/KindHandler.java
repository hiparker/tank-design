package com.parker.tank.net.handler;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.handler
 * @Author: Parker
 * @CreateTime: 2020-08-21 15:30
 * @Description: 执行器种类
 */
public interface KindHandler {

    /**
     * 获得 Msg Key Clazz
     * @return
     */
    Class<?> getKeyClazz();

}
