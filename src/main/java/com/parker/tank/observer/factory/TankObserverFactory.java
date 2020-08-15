package com.parker.tank.observer.factory;

import com.parker.tank.Tank;
import com.parker.tank.dist.Dir;
import com.parker.tank.observer.TankObserver;
import com.parker.tank.observer.event.impl.tank.TankDiedEvent;
import com.parker.tank.observer.event.impl.tank.TankFireEvent;
import com.parker.tank.observer.event.impl.tank.TankMoveEvent;

import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.event
 * @Author: Parker
 * @CreateTime: 2020-08-15 01:02
 * @Description: 系统事件观察者
 */
public enum TankObserverFactory {

    INSTANCE;

    private List<TankObserver> tankObservers = Arrays.asList(new com.parker.tank.observer.TankObserverHandler());

    public void tankDiedHandler(Tank tank){
        if(tank == null){
            return;
        }
        TankDiedEvent tankEvent = new TankDiedEvent(tank);
        for (TankObserver tankObserver : tankObservers) {
            tankObserver.actionOnDied(tankEvent);
        }
    }

    public void mainTankFireHandler(Tank tank){
        if(tank == null){
            return;
        }
        TankFireEvent tankEvent = new TankFireEvent(tank);
        for (TankObserver tankObserver : tankObservers) {
            tankObserver.actionOnFire(tankEvent);
        }
    }

    public void mainTankMoveHandler(Tank tank, boolean bL, boolean bU, boolean bR, boolean bD){
        if(tank == null){
            return;
        }

        Dir dir = null;
        boolean flag = false;
        if(bL || bU || bR || bD){
            if(bL) dir = Dir.LEFT;
            if(bU) dir = Dir.UP;
            if(bR) dir = Dir.RIGHT;
            if(bD) dir = Dir.DOWN;
            flag = true;
        }
        TankMoveEvent tankMoveEvent = new TankMoveEvent(tank,dir,flag);
        for (TankObserver tankObserver : tankObservers) {
            tankObserver.actionOnMove(tankMoveEvent);
        }
    }

}
