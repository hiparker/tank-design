package com.parker.tank.observer.event.impl;

import com.parker.tank.observer.event.GameEvent;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.observer.event.impl.tank
 * @Author: Parker
 * @CreateTime: 2020-08-15 13:21
 * @Description: BaseEvent
 */
public abstract class BaseGameEvent implements GameEvent {

    /**
     * 目标死亡
     */
    public void died(){
        throw new RuntimeException("目标死亡未处理");
    }

}
