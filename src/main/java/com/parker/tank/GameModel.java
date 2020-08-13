package com.parker.tank;

import com.parker.tank.collide.BulletAndTankCollide;
import com.parker.tank.collide.Collide;
import com.parker.tank.collide.TankAndTankCollide;
import com.parker.tank.collide.chain.CollideChain;
import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;

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
 * Model 和 View 分离
 *
 * 门面 Faced 设计模式
 *
 */
public class GameModel {



    /** 我方主战坦克 */
    private Tank myTank;

    /** 物体集合 */
    private final List<GameObject> gameObjects = new ArrayList<>();

    /** 碰撞器 责任链 */
    private final Collide collide = CollideChain.INSTANCE;

    public GameModel() {

        // 敌方坦克数量
        int badTankCount = PropertiesMgr.getByInteger("badTankCount");

        // 创建5个敌方坦克
        for (int i = 0; i < badTankCount; i++) {
            Tank autoTank = TankFactory.createAutoTank(50 + i * 80, 200, Dir.DOWN, this, TankGroup.BLUE);
            this.add(autoTank);
        }

        // ----

        // 我方坦克
        myTank = TankFactory.createTank(200,400, Dir.DOWN,this, TankGroup.RED);
        this.add(myTank);


        // 创建墙体
        WallFactory.INSTANCE.createWall(this);
    }

    /**
     * 描绘
     * @param g 画笔
     */
    public void paint(Graphics g) {
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

       /* // 敌方坦克渲染
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
        }*/

        // 子弹与坦克碰撞
        /*for (int i = 0; i < bulletList.size(); i++) {
            for (int tk = 0; tk < badTanks.size(); tk++) {
                TankUtil.collideWith(badTanks.get(tk),bulletList.get(i));
            }
            TankUtil.collideWith(myTank,bulletList.get(i));
        }*/
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
     * 添加物体
     * @param go
     */
    public void add(GameObject go) {
        this.gameObjects.add(go);
    }

    /**
     * 删除物体
     * @param go
     */
    public void remove(GameObject go) {
        this.gameObjects.remove(go);
    }

}
