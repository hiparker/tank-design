package com.parker.tank;

import com.sun.istack.internal.NotNull;

import java.awt.*;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 19:56
 * @Description: 墙
 */
public class Wall extends GameObject{

    /** 宽高 */
    private int width,height;
    private Color color;
    /** 当前位置 */
    private Rectangle rectangle;

    public Wall(int x, int y, int width, int height, @NotNull Color c) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = c;
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
        Color c = g.getColor();
        g.setColor(color);
        g.fillRect(this.x,this.y,this.width,this.height);
        g.setColor(c);
    }
}
