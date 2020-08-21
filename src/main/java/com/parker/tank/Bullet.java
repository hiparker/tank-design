package com.parker.tank;

import com.parker.tank.net.Client;
import com.parker.tank.net.msg.BulletDiedMsg;
import com.parker.tank.net.msg.MsgType;

import java.awt.*;
import java.util.UUID;

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
    private int bulletWidth = ResourcesMgr.bulletD.getWidth(), bulletHeight = ResourcesMgr.bulletD.getHeight();

    /** ID */
    private UUID id;
    /** XY坐标 */
    private int x , y;
    /** 子弹方向 */
    private Dir dir = Dir.DOWN;
    /** 画布 */
    private TankFrame tankFrame;
    /** 存活状态 */
    private boolean liveFlag = true;
    /** 当前位置 */
    private Rectangle rectangle;
    /** 归属坦克 */
    public Tank belongTank;

    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Bullet(int x, int y, Dir dir,TankFrame tankFrame,Tank belongTank,UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.belongTank = belongTank;
        this.id = id;

        // 设置碰撞检测位置
        rectangle = new Rectangle(this.x,this.y,bulletWidth,bulletHeight);

        // 设置 子弹样式
        setBulletStyle();
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
     * 后期可作为 跟踪弹 自动子弹方向
     * @param dir
     */
    public void setDir(Dir dir) {
        this.dir = dir;
    }

    /**
     * 设置 子弹样式
     */
    public void setBulletStyle(){
        switch (this.dir) {
            case LEFT:
                // 设置子弹 方向大小样式
                this.bulletWidth = this.bulletWidth ^ this.bulletHeight;
                this.bulletHeight = this.bulletWidth ^ this.bulletHeight;
                this.bulletWidth = this.bulletWidth ^ this.bulletHeight;

                // 设置子弹方向
                this.y += belongTank.TANK_HEIGHT/2 - this.bulletHeight/2;
                this.x -= this.bulletWidth/2;

                break;
            case UP:
                // 设置子弹方向
                this.x += belongTank.TANK_WIDTH/2 - this.bulletWidth/2;
                this.y -= this.bulletHeight/2;

                break;
            case RIGHT:
                // 设置子弹 方向大小样式
                this.bulletWidth = this.bulletWidth ^ this.bulletHeight;
                this.bulletHeight = this.bulletWidth ^ this.bulletHeight;
                this.bulletWidth = this.bulletWidth ^ this.bulletHeight;

                // 设置子弹方向
                this.x += + belongTank.TANK_WIDTH - this.bulletWidth/2;
                this.y += + belongTank.TANK_HEIGHT/2 - this.bulletHeight/2;

                break;
            case DOWN:
                // 设置子弹方向
                this.x += belongTank.TANK_WIDTH/2 - this.bulletWidth/2;
                this.y += belongTank.TANK_HEIGHT - this.bulletHeight/2;

                break;
            default:
                break;
        }
    }

    /**
     * 子弹方向处理
     */
    public void moveHandler(){
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
        }

        // 边缘处理
        if(x < 0 || y < bulletHeight/2 || x > TankFrame.GAME_WIDTH-bulletWidth || y > TankFrame.GAME_HEIGHT-bulletHeight){
            // 子弹死亡
            Client.INSTANCE.send(new BulletDiedMsg(this.id), MsgType.BULLET_DIED);
        }

    }


    public void paint(Graphics g) {

        // 炮弹死亡移除
        if(!this.liveFlag){
            tankFrame.bullets.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourcesMgr.bulletL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourcesMgr.bulletU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourcesMgr.bulletR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourcesMgr.bulletD,x,y,null);
                break;
        }

        // 子弹自动行走
        this.moveHandler();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void died() {
        this.liveFlag = false;
    }
}
