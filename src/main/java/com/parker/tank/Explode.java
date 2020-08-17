package com.parker.tank;

import com.parker.tank.config.ResourcesMgr;
import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.factory.TankFrameFactory;
import com.parker.tank.util.AudioUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-11 20:38
 * @Description: 大 爆炸效果
 */
public class Explode extends GameObject {

    /** XY坐标 */
    protected int x , y;
    /** 当前位置 */
    private Rectangle rectangle;
    /** 当前数量 */
    protected int count = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;

        // 设置碰撞检测位置
        this.rectangle = new Rectangle(x,y,0,0);

        // 爆炸音效
        new Thread(()->{
            new AudioUtil("static/audio/explode.wav").play();
        }).start();
    }

    /**
     * 获得当前位置（用于碰撞检测）
     * @return
     */
    public Rectangle getPosition(){
        rectangle.x = this.x;
        rectangle.y = this.y;
        return rectangle;
    }

    /**
     * 描绘
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        BufferedImage image = ResourcesMgr.explodesBig[count++];
        this.rectangle.width = image.getWidth();
        this.rectangle.height = image.getHeight();

        g.drawImage(image,x,y,null);
        if(count >= ResourcesMgr.explodesBig.length){
            // 爆炸消失
            if(TankFrameFactory.INSTANCE.getTankFrame().getBgm() != null){
                TankFrameFactory.INSTANCE.getTankFrame().getBgm().remove(this);
            }
        }
    }


}
