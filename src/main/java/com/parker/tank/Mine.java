package com.parker.tank;

import com.parker.tank.config.ResourcesMgr;
import com.parker.tank.dist.WallGroup;
import com.parker.tank.faced.BaseGameModel;
import com.sun.istack.internal.NotNull;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 19:56
 * @Description: 墙
 */
public class Mine extends GameObject{

    /** 存活状态 */
    private boolean liveFlag = true;
    /** 当前位置 */
    private Rectangle rectangle;
    /** 调停者 */
    private BaseGameModel gm;
    /** 当前数量 */
    protected int count = 0;

    public Mine(int x, int y, int width, int height, BaseGameModel gm) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gm = gm;
        // 设置碰撞检测位置
        this.rectangle = new Rectangle(x,y,width,height);
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

    @Override
    public void paint(Graphics g) {

        // 地雷死亡移除
        if(!this.liveFlag){
            gm.remove(this);
        }

        BufferedImage image = ResourcesMgr.mines[count++];
        this.rectangle.width = image.getWidth();
        this.rectangle.height = image.getHeight();

        if(count >= ResourcesMgr.mines.length){
            count = 0;
        }
        g.drawImage(image,this.x,this.y,this.width,this.height,null);
    }

    /**
     * 地雷死亡
     */
    public void died() {
        this.liveFlag = false;
    }

    public BaseGameModel getGm() {
        return gm;
    }
}
