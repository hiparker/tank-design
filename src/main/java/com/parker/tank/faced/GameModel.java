package com.parker.tank.faced;

import com.parker.tank.Audio;
import com.parker.tank.Tank;
import com.parker.tank.TankFactory;
import com.parker.tank.collide.Collide;
import com.parker.tank.collide.chain.CollideChain;
import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;

import java.awt.*;
import java.util.concurrent.TimeUnit;
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

    /** 碰撞器 责任链 */
    private final Collide collide = CollideChain.INSTANCE;
    /** 失败重试次数 */
    public AtomicInteger pauseCount = new AtomicInteger(0);
    /** hp */
    public AtomicInteger mainHp = new AtomicInteger(3);

    public GameModel(int pauseCount) {

        this.pauseCount.set(pauseCount);

        if(PropertiesMgr.getByInteger("mainHp") != null){
            mainHp.set(PropertiesMgr.getByInteger("mainHp"));
        }

        // 敌方坦克数量
        int badTankCount = PropertiesMgr.getByInteger("badTankCount");

        // 创建5个敌方坦克
        for (int i = 0; i < badTankCount; i++) {
            Tank autoTank = TankFactory.createAutoTank(50 + i * 100, 30, Dir.DOWN, this, TankGroup.BLUE);
            this.add(autoTank);
        }

        // ----

        // 单机主战坦克
        super.setMainTank(TankFactory.createTank(300,710, Dir.UP,this, TankGroup.RED));
        this.add(super.getMainTank());

        System.out.println("普通坦克数量["+TankFactory.usualCount+"]  自动坦克数量["+TankFactory.autoCount+"]");


        // 背景音乐
        new Thread(()->{
            new Audio("static/audio/war1.wav").play();
        }).start();
    }

    /**
     * 描绘
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("HP的数量："+mainHp.get()+"  剩余重试次数："+pauseCount.get(),10,40);
        g.setColor(c);

        /*Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+bulletList.size(),10,40);
        g.drawString("敌人的数量："+badTanks.size(),10,60);
        g.drawString("爆炸的数量："+explodeList.size(),10,80);
        g.setColor(c);*/

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


}
