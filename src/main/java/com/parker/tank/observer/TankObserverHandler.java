package com.parker.tank.observer;

import com.parker.tank.Tank;
import com.parker.tank.factory.TankFrameFactory;
import com.parker.tank.util.AudioUtil;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.observer
 * @Author: Parker
 * @CreateTime: 2020-08-15 00:50
 * @Description: 坦克事件观察者
 */
public class TankObserverHandler implements TankObserver {

    @Override
    public void actionOnFire(TankEvent tankEvent) {
        Tank source = tankEvent.getSource();
        source.fired();
        // 开火音效
        new Thread(()->{
            new AudioUtil("static/audio/tank_fire.wav").play();
        }).start();
    }

    @Override
    public void actionOnMove(boolean flag,TankEvent tankEvent) {
        Tank source = tankEvent.getSource();
        if(flag){
            source.start();
            // 移动音乐
            new Thread(()->{
                new AudioUtil("static/audio/tank_move.wav").play();
            }).start();
        }else{
            source.stop();
        }
    }
}
