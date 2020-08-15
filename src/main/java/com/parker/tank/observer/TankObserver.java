package com.parker.tank.observer;

import com.parker.tank.observer.event.impl.tank.TankDiedEvent;
import com.parker.tank.observer.event.impl.tank.TankFireEvent;
import com.parker.tank.observer.event.impl.tank.TankMoveEvent;
import com.sun.istack.internal.NotNull;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.observer
 * @Author: Parker
 * @CreateTime: 2020-08-15 00:49
 * @Description: 坦克观察者
 */
public interface TankObserver{

    /**
     * 开火
     * @param gameEvent
     */
    void actionOnFire(@NotNull TankFireEvent gameEvent);

    /**
     * 移动
     * @param gameEvent
     */
    void actionOnMove(@NotNull TankMoveEvent gameEvent);

    /**
     * 死亡
     * @param gameEvent
     */
    void actionOnDied(@NotNull TankDiedEvent gameEvent);

}
