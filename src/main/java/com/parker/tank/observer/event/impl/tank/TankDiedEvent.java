package com.parker.tank.observer.event.impl.tank;

import com.parker.tank.Tank;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.observer
 * @Author: Parker
 * @CreateTime: 2020-08-15 00:57
 * @Description: 坦克事件
 */
public class TankDiedEvent extends TankEvent {

    public TankDiedEvent(Tank tank){
        super.tank = tank;
    }

}