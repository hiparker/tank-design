package com.parker.tank;

import java.awt.*;
import java.util.Random;

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
    public final static int TANK_WIDTH = ResourcesMgr.tankD.getWidth(), TANK_HEIGHT = ResourcesMgr.tankD.getHeight();

    /** XY坐标 */
    private int x , y;
    /** 坦克方向 */
    private Dir dir = Dir.DOWN;
    /** 是否是移动的状态 */
    private boolean moving = false;
    /** 画布 */
    private TankFrame tankFrame;
    /** 存活状态 */
    private boolean liveFlag = true;
    /** 当前位置 */
    private Rectangle rectangle;
    /** 坦克分组 */
    public TankGroup group;
    /** 自动模式 */
    private boolean autoFlag = false;

    private Random random = new Random();

    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Tank(int x, int y, Dir dir,TankFrame tankFrame,TankGroup group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;

        // 设置碰撞检测位置
        rectangle = new Rectangle(this.x,this.y,TANK_WIDTH,TANK_HEIGHT);
    }

    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Tank(int x, int y, Dir dir,TankFrame tankFrame,TankGroup group,boolean autoFlag) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.autoFlag = autoFlag;

        // 设置碰撞检测位置
        rectangle = new Rectangle(this.x,this.y,TANK_WIDTH,TANK_HEIGHT);
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

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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
        }

        // 边缘处理
        if(xT < 0 || yT < TANK_HEIGHT/2|| xT > TankFrame.GAME_WIDTH-TANK_WIDTH || yT > TankFrame.GAME_HEIGHT-TANK_HEIGHT){
            return;
        }

        x = xT;
        y = yT;
    }

    public void paint(Graphics g) {
        // 坦克阵亡
        if(!liveFlag){
            tankFrame.enemyTanks.remove(this);
            return;
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourcesMgr.tankL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourcesMgr.tankU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourcesMgr.tankR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourcesMgr.tankD,x,y,null);
                break;
        }

        // 坦克自动行走
        this.moveHandler();

        // 设置坦克随机开炮 与 行走
        if(this.autoFlag){

            // 随机开炮 几率暂定 5%
            int randomBullet = random.nextInt(100);
            if(randomBullet > 95){
                this.fired();
            }

        }
    }

    /**
     * 开火
     */
    public void fired() {
        tankFrame.bulletList.add(new Bullet(this.x,this.y,this.dir,this.tankFrame,this));
    }

    public void died() {
        this.liveFlag = false;
    }
}
