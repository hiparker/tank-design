package com.parker.tank.observer;

import com.sun.istack.internal.NotNull;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.observer
 * @Author: Parker
 * @CreateTime: 2020-08-15 00:49
 * @Description: 坦克观察者
 */
public interface TankObserver {

    /**
     * 开火
     * @param tankEvent
     */
    void actionOnFire(@NotNull TankEvent tankEvent);

    /**
     * 移动
     * @param tankEvent
     */
    void actionOnMove(boolean flag,@NotNull TankEvent tankEvent);

}
