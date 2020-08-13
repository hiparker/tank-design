package com.parker.tank.factory.base;

import com.parker.tank.TankFrame;

import java.awt.*;

/**
 * @BelongsProject: tank-02
 * @BelongsPackage: com.parker.tank.factory.base
 * @Author: Parker
 * @CreateTime: 2020-08-12 23:18
 * @Description: BaseExplode
 */
public abstract class BaseExplode {

    /** XY坐标 */
    protected int x , y;
    /** 画布 */
    protected TankFrame tankFrame;
    /** 当前数量 */
    protected int count = 0;

    /**
     * 描绘
     * @param g 画笔
     */
    abstract public void paint(Graphics g);

}
