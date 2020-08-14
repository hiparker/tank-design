package com.parker.tank.event;

import com.parker.tank.Tank;
import com.parker.tank.dist.Dir;
import com.parker.tank.factory.TankFrameFactory;
import com.parker.tank.observer.TankEvent;
import com.parker.tank.observer.TankEventImpl;
import com.parker.tank.observer.TankObserver;
import com.parker.tank.observer.TankObserverHandler;
import com.parker.tank.util.AudioUtil;

import java.util.Arrays;
import java.util.List;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.event
 * @Author: Parker
 * @CreateTime: 2020-08-15 01:02
 * @Description: 系统事件观察者
 */
public enum ObserverSystemEvent {

    INSTANCE;

    private List<TankObserver> tankObservers = Arrays.asList(new TankObserverHandler());

    public void mainTankFireHandler(Tank tank){
        if(tank == null){
            return;
        }
        TankEvent tankEvent = new TankEventImpl(tank);
        for (TankObserver tankObserver : tankObservers) {
            tankObserver.actionOnFire(tankEvent);
        }
    }

    public void mainTankMoveHandler(Tank tank, boolean bL, boolean bU, boolean bR, boolean bD){
        if(tank == null){
            return;
        }

        boolean flag = false;
        if(bL || bU || bR || bD){
            if(bL) tank.setDir(Dir.LEFT);
            if(bU) tank.setDir(Dir.UP);
            if(bR) tank.setDir(Dir.RIGHT);
            if(bD) tank.setDir(Dir.DOWN);

            flag = true;
        }
        TankEvent tankEvent = new TankEventImpl(tank);
        for (TankObserver tankObserver : tankObservers) {
            tankObserver.actionOnMove(flag,tankEvent);
        }
    }

}
