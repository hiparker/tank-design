package com.parker.tank;

import java.awt.*;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 15:46
 * @Description: TODO
 */
public class Tank {

    /** XY坐标 */
    private int x , y;
    /** 坦克方向 */
    private Dir dir = Dir.DOWN;
    /** 速度 */
    private final static int SPEED = 10;


    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    /**
     * 坦克方向处理
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
        g.fillRect(x,y,50,50);
        // 坦克自动行走
        this.tankDirectionHandler();
    }
}
