package com.parker.tank.observer;

import com.parker.tank.Explode;
import com.parker.tank.Tank;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.factory.GateFactory;
import com.parker.tank.factory.TankFactory;
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

        source.getGameModel().remove(source);

        // 坦克阵亡新建爆炸
        Explode explode = new Explode(source.getX(), source.getY(), source.getGameModel());
        source.getGameModel().add(explode);

        // 如果是主战坦克
        if(TankGroup.RED.equals(source.getGroup())){
            int mainTankHp = source.getGameModel().subMainTankHP();
            // 如果小于0 则该关卡失败
            if(mainTankHp < 0){
                // 关卡错误结束
                GateFactory.INSTANCE.chainErrorGameOver();
            }else{
            // 如果还有生命值则在 复活点复活
                source.getGameModel().setMainTank(TankFactory.createTank(300,710, Dir.UP,source.getGameModel(), TankGroup.RED));
            }
        }else{
            int badTankCount = source.getGameModel().subBadTankCount();
            // 如果小于0 则该关卡失败
            if(badTankCount <= 0){
                // 关卡错误结束
                GateFactory.INSTANCE.chainSuccessGameOver();
            }
        }
    }
}
