package com.parker.tank;

import com.parker.tank.faced.BaseGameModel;

import java.awt.*;
import java.io.Serializable;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 16:14
 * @Description: 调停者
 *
 * 调停者（Mediator）模式
 *
 */
public abstract class GameObject implements Serializable {

    /** XY坐标 */
    protected int x , y;

    /** 宽高 将静态宽高 改为动态，但是引用比较多 暂时还是 大写的*/
    protected int width = 50, height = 50;


    /**
     * 描绘
     * @param g 画笔
     */
    public abstract void paint(Graphics g);

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

}
