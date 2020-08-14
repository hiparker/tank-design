package com.parker.tank.observer;

import com.parker.tank.Tank;
import com.sun.istack.internal.NotNull;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.observer
 * @Author: Parker
 * @CreateTime: 2020-08-15 00:57
 * @Description: 坦克事件
 */
public class TankEventImpl implements TankEvent{

    private Tank tank;

    public TankEventImpl(@NotNull Tank tank){
        this.tank = tank;
    }

    @Override
    public Tank getSource() {
        return this.tank;
    }
}
