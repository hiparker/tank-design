package com.parker.tank;

import java.awt.*;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 16:18
 * @Description: 炮弹类
 */
public class Bullet {

    /** 速度 */
    private final static int SPEED = 10;
    /** 宽度 高度 */
    private final static int WIDTH = 5,HEIGHT = 15;


    /** XY坐标 */
    private int x , y;
    /** 子弹方向 */
    private Dir dir = Dir.DOWN;

    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    /**
     * 后期可作为 跟踪弹 自动子弹方向
     * @param dir
     */
    public void setDir(Dir dir) {
        this.dir = dir;
    }

    /**
     * 子弹方向处理
     */
    public void tankDirectionHandler(){
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
    }


    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(c);

        // 子弹自动行走
        this.tankDirectionHandler();
    }

}
