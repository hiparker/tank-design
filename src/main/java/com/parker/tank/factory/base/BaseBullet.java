package com.parker.tank.factory.base;

import com.parker.tank.TankFrame;
import com.parker.tank.dist.Dir;

import java.awt.*;

/**
 * @BelongsProject: tank-02
 * @BelongsPackage: com.parker.tank.factory.base
 * @Author: Parker
 * @CreateTime: 2020-08-12 23:55
 * @Description: BaseBullet
 *
 * 钩子函数 - 》 模版方法 （设计模式）
 *
 */
public abstract class BaseBullet {

    /** 速度 */
    protected int speed = 10;
    /** XY坐标 */
    protected int x , y;
    /** 子弹方向 */
    protected Dir dir = Dir.DOWN;
    /** 画布 */
    protected TankFrame tankFrame;
    /** 存活状态 */
    protected boolean liveFlag = true;
    /** 当前位置 */
    protected Rectangle rectangle;
    /** 归属坦克 */
    protected BaseTank belongTank;
    /** 宽度 高度 */
    protected int bulletWidth = 0, bulletHeight = 0;

    /**
     * 设置炮弹样式
     */
    abstract public void setBulletStyle();

    /**
     * 子弹移动处理
     */
    public void moveHandler(){
        throw new RuntimeException("子弹无移动处理");
    }

    /**
     * 描绘
     * @param g 画笔
     */
    public void paint(Graphics g){
        throw new RuntimeException("子弹无描绘处理");
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
     * 获得归属坦克
     * @return
     */
    public BaseTank getBelongTank() {
        return belongTank;
    }

    /**
     * 炮弹死亡
     */
    public void died() {
        this.liveFlag = false;
    }
}
