package com.parker.tank.observer.event.impl.tank;

import com.parker.tank.Tank;
import com.parker.tank.observer.event.GameEvent;
import com.parker.tank.observer.event.impl.BaseGameEvent;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.observer.event.impl.tank
 * @Author: Parker
 * @CreateTime: 2020-08-15 13:21
 * @Description: TankBaseEvent
 */
public abstract class TankEvent extends BaseGameEvent implements GameEvent {

    protected Tank tank;

    @Override
    public Tank getSource() {
        return this.tank;
    }

}
