package com.parker.tank;

import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.util.TankUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 15:35
 * @Description: GameModel
 *
 * MVC分离
 *
 * 门面/调停者 设计模式
 *
 */
public class GameModel {

    /** 子弹集合 */
    private final java.util.List<Bullet> bulletList = new ArrayList<>();

    /** 爆炸集合 */
    private final java.util.List<Explode> explodeList = new ArrayList<>();

    /** 我方主战坦克 */
    private final Tank myTank = TankFactory.createTank(200,400, Dir.DOWN,this, TankGroup.RED);

    /** 敌方坦克 */
    private final List<Tank> badTanks = new ArrayList<>();

    public GameModel() {

        // 敌方坦克数量
        int badTankCount = PropertiesMgr.getByInteger("badTankCount");

        // 创建5个敌方坦克
        for (int i = 0; i < badTankCount; i++) {
            Tank autoTank = TankFactory.createAutoTank(50 + i * 80, 200, Dir.DOWN, this, TankGroup.BLUE, true);
            this.addBadTank(autoTank);
        }

    }

    /**
     * 描绘
     * @param g 画笔
     */
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+bulletList.size(),10,40);
        g.drawString("敌人的数量："+badTanks.size(),10,60);
        g.drawString("爆炸的数量："+explodeList.size(),10,80);
        g.setColor(c);

        // 坦克自动行走
        myTank.paint(g);

        // 敌方坦克渲染
        for (int i = 0; i < badTanks.size(); i++) {
            badTanks.get(i).paint(g);
        }

        // 子弹自动行走
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        // 坦克爆炸
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
        }

        // 子弹与坦克碰撞
        for (int i = 0; i < bulletList.size(); i++) {
            for (int tk = 0; tk < badTanks.size(); tk++) {
                TankUtil.collideWith(badTanks.get(tk),bulletList.get(i));
            }
            TankUtil.collideWith(myTank,bulletList.get(i));
        }
    }

    // ---------------------------------------------------------

    /**
     * 获得主战坦克
     * @return
     */
    public Tank getMainTank(){
        return myTank;
    }

    /**
     * 添加爆炸
     * @param be
     */
    public void addExplode(Explode be){
        this.explodeList.add(be);
    }
    /**
     * 删除爆炸
     * @param be
     */
    public void removeExplode(Explode be){
        this.explodeList.remove(be);
    }
    /**
     * 添加炮弹
     * @param bb
     */
    public void addBullet(Bullet bb){
        this.bulletList.add(bb);
    }
    /**
     * 删除炮弹
     * @param be
     */
    public void removeBullet(Bullet be){
        this.bulletList.remove(be);
    }
    /**
     * 添加坦克
     * @param bt
     */
    public void addBadTank(Tank bt){
        this.badTanks.add(bt);
    }
    /**
     * 删除坦克
     * @param bt
     */
    public void removeBadTank(Tank bt){
        this.badTanks.remove(bt);
    }

}
