package com.parker.tank.observer.event.impl.tank;

import com.parker.tank.Tank;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.GameObjectState;
import com.sun.istack.internal.NotNull;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.observer
 * @Author: Parker
 * @CreateTime: 2020-08-15 00:57
 * @Description: 坦克事件
 */
public class TankMoveEvent extends TankEvent {

    private boolean isMove;
    private Dir dir;

    public TankMoveEvent(@NotNull Tank tank,@NotNull Dir dir, boolean isMove){
        this.tank = tank;
        this.isMove = isMove;
        this.dir = dir;
    }

    public boolean isMove() {
        return isMove;
    }

    public Dir getDir() {
        return dir;
    }
}
