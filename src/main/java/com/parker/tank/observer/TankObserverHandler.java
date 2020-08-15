package com.parker.tank.observer;

import com.parker.tank.Explode;
import com.parker.tank.Tank;
import com.parker.tank.observer.event.impl.tank.TankDiedEvent;
import com.parker.tank.observer.event.impl.tank.TankFireEvent;
import com.parker.tank.observer.event.impl.tank.TankMoveEvent;
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
    public void actionOnFire(TankFireEvent gameEvent) {
        // 开火
        Tank source = gameEvent.getSource();
        if(source == null){
            return;
        }

        source.fired();
        // 开火音效
        new Thread(()->{
            new AudioUtil("static/audio/tank_fire.wav").play();
        }).start();
    }

    @Override
    public void actionOnMove(TankMoveEvent gameEvent) {
        // 移动
        Tank source = gameEvent.getSource();
        if(source == null){
            return;
        }

        if(gameEvent.isMove()){
            source.setDir(gameEvent.getDir());
            source.start();
            // 移动音乐
            new Thread(()->{
                new AudioUtil("static/audio/tank_move.wav").play();
            }).start();
        }else{
            source.stop();
        }
    }

    @Override
    public void actionOnDied(TankDiedEvent gameEvent) {
        // 移动
        Tank source = gameEvent.getSource();
        if(source == null){
            return;
        }

        // 坦克阵亡新建爆炸
        Explode explode = new Explode(source.getX(), source.getY(), source.getGameModel());
        source.getGameModel().add(explode);
    }
}
