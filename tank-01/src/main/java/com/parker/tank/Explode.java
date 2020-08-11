package com.parker.tank;

import java.awt.*;
import java.util.Random;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-11 20:38
 * @Description: 爆炸效果
 */
public class Explode {


    /** 宽高 */
    public final static int WIDTH = ResourcesMgr.tankD.getWidth(), HEIGHT = ResourcesMgr.tankD.getHeight();

    /** XY坐标 */
    private int x , y;
    /** 画布 */
    private TankFrame tankFrame;
    /** 存活状态 */
    private boolean liveFlag = true;
    /** 当前数量 */
    private int count = 0;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        // 爆炸音效
        new Thread(()->{
            new Audio("static/audio/explode.wav").play();
        }).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourcesMgr.explodes[count++],x,y,null);
        if(count >= ResourcesMgr.explodes.length){
            // 爆炸消失
            tankFrame.explodeList.remove(this);
        }
    }

}
