package com.parker.tank;

import com.parker.tank.config.ResourcesMgr;

import java.awt.*;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-11 20:38
 * @Description: 大 爆炸效果
 */
public class Explode {

    /** XY坐标 */
    protected int x , y;
    /** 画布 */
    protected GameModel gm;
    /** 当前数量 */
    protected int count = 0;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;

        // 爆炸音效
        new Thread(()->{
            new Audio("static/audio/explode.wav").play();
        }).start();
    }

    /**
     * 描绘
     * @param g 画笔
     */
    public void paint(Graphics g) {
        g.drawImage(ResourcesMgr.explodesBig[count++],x,y,null);
        if(count >= ResourcesMgr.explodesBig.length){
            // 爆炸消失
            gm.removeExplode(this);
        }
    }

}
