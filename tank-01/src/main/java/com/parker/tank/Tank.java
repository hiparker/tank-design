package com.parker.tank;

import java.awt.*;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 15:46
 * @Description: 主战坦克
 */
public class Tank {

    /** 速度 */
    private final static int SPEED = 5;
    /** 宽高 */
    private final static int TANK_WIDTH = 50, TANK_HEIGHT = 50;

    /** XY坐标 */
    private int x , y;
    /** 坦克方向 */
    private Dir dir = Dir.DOWN;
    /** 是否是移动的状态 */
    private boolean moving = false;
    /** 画布 */
    private TankFrame tankFrame;


    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Tank(int x, int y, Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    /**
     * 坦克方向处理
     */
    public void moveHandler(){

        if(!moving){
            return;
        }

        int xT = x;
        int yT = y;

        switch (dir) {
            case LEFT:
                xT -= SPEED;
                break;
            case UP:
                yT -= SPEED;
                break;
            case RIGHT:
                xT += SPEED;
                break;
            case DOWN:
                yT += SPEED;
                break;
            default:
                break;
        }

        // 边缘处理
        if(xT < 0 || yT < TANK_HEIGHT/2|| xT > TankFrame.GAME_WIDTH-TANK_WIDTH || yT > TankFrame.GAME_HEIGHT-TANK_HEIGHT){
            return;
        }

        x = xT;
        y = yT;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,TANK_WIDTH,TANK_HEIGHT);
        g.setColor(c);

        // 坦克自动行走
        this.moveHandler();
    }

    /**
     * 开火
     */
    public void fired() {
        tankFrame.bulletList.add(new Bullet(this.x,this.y,this.dir,this.tankFrame));
    }
}
