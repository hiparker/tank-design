package com.parker.tank.faced;

import com.parker.tank.util.AudioUtil;
import com.parker.tank.Tank;
import com.parker.tank.factory.TankFactory;
import com.parker.tank.collide.Collide;
import com.parker.tank.collide.chain.CollideChain;
import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;

import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 15:35
 * @Description: GameModel
 *
 * Model 和 View 分离
 *
 * 门面 Faced 设计模式
 *
 */
public class GameModel extends BaseGameModel{

    /**
     * 内部类
     */
    static class GameModelBudiler{

    }

    /** 碰撞器 责任链 */
    private final Collide collide = CollideChain.INSTANCE;
    /** 失败重试次数 */
    public AtomicInteger pauseCount = new AtomicInteger(0);
    /** hp */
    public AtomicInteger mainHp = new AtomicInteger(3);
    /** 敌方坦克数量 */
    public AtomicInteger badTankCount = new AtomicInteger(0);

    public GameModel() {

    }

    /**
     * 开始构建
     * @return
     */
    @Override
    public BaseGameModel builder(){

        this.pauseCount.set(super.getTempNum());

        if(PropertiesMgr.getByInteger("mainHp") != null){
            mainHp.set(PropertiesMgr.getByInteger("mainHp")-1);
        }

        // ----
        // 单机主战坦克
        super.setMainTank(TankFactory.createTank(300,710, Dir.UP,this, TankGroup.RED));

        // 背景音乐
        this.audioUtil = new AudioUtil("static/audio/war1.wav");
        this.musicThread = new Thread(()->{
            audioUtil.loop();
        });
        super.startMusic();

        return this;
    }

    /**
     * 描绘
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString(
                "HP的数量："+mainHp.get()+" , 剩余重试次数："+pauseCount.get()+" , 敌人的数量："+badTankCount.get()+" , 全部消灭后过关！"
                ,10,40);
        g.setColor(c);

        // 物体渲染
        for (int i = 0; i < this.gameObjects.size(); i++) {
            this.gameObjects.get(i).paint(g);
        }

        // 碰撞检测
        for (int i = 0; i < this.gameObjects.size(); i++) {
            for (int j = i+1; j < this.gameObjects.size(); j++) {
                this.collide.comparator(this.gameObjects.get(i),this.gameObjects.get(j));
            }
        }
    }

    /**
     * 创建敌方坦克
     * @param count
     */
    public void createBadTank(int count){
        this.badTankCount.set(count);
        // 创建5个敌方坦克
        for (int i = 0; i < count; i++) {
            Tank autoTank = TankFactory.createAutoTank(50 + i * 100, 30, Dir.DOWN, this, TankGroup.BLUE);
            this.add(autoTank);
        }
    }

    /**
     * 敌方坦克 消亡记录
     * @return
     */
    public int subBadTankCount(){
        return badTankCount.decrementAndGet();
    }


    /**
     * 主战坦克 减HP
     * @return
     */
    public int subMainTankHP(){
        return mainHp.decrementAndGet();
    }
}
