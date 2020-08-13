package com.parker.tank;

import java.awt.*;

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
public abstract class GameObject {

    /** XY坐标 */
    protected int x , y;

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
}
