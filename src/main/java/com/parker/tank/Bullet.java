package com.parker.tank;

import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.config.ResourcesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.faced.GameModel;
import com.parker.tank.flyweight.BulletPool;

import java.awt.*;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 16:18
 * @Description: 炮弹类
 */
public class Bullet extends GameObject {

    /** 速度 */
    private int speed = 10;
    /** 子弹方向 */
    private Dir dir = Dir.DOWN;
    /** 调停者 */
    private BaseGameModel gm;
    /** 存活状态 */
    private boolean liveFlag = true;
    /** 当前位置 */
    private Rectangle rectangle;
    /** 归属坦克 */
    private Tank belongTank;


    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Bullet(int x, int y, Dir dir, BaseGameModel gm, Tank belongTank) {

        // 初始化
        this.init();

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.belongTank = belongTank;

        // 设置碰撞检测位置
        this.rectangle = new Rectangle(this.x,this.y,this.width,this.height);

        // 设置 子弹样式
        setBulletStyle();
    }

    /**
     * 重制子弹
     * @param x
     * @param y
     * @param dir
     */
    public void revertBullet(int x, int y, Dir dir, BaseGameModel gm, Tank belongTank) {

        // 初始化
        this.init();
        this.liveFlag = true;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.belongTank = belongTank;
        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
        this.rectangle.width = this.width;
        this.rectangle.height = this.height;

        // 设置 子弹样式
        setBulletStyle();
    }


    /**
     * 初始化
     */
    public void init(){
        // 速度
        if(PropertiesMgr.getByInteger("bulletSpeed") != null){
            this.speed = PropertiesMgr.getByInteger("bulletSpeed");
        }
        this.width = ResourcesMgr.bulletBigU.getWidth();
        this.height = ResourcesMgr.bulletBigU.getHeight();
    }

    /**
     * 设置 子弹样式
     */
    public void setBulletStyle(){
        switch (this.dir) {
            case LEFT:
                // 设置子弹 方向大小样式
                this.width = this.width ^ this.height;
                this.height = this.width ^ this.height;
                this.width = this.width ^ this.height;

                // 设置子弹方向
                this.y += belongTank.height/2 - this.height/2;
                this.x -= this.width/2;

                break;
            case UP:
                // 设置子弹方向
                this.x += belongTank.width/2 - this.width/2;
                this.y -= this.height/2;

                break;
            case RIGHT:
                // 设置子弹 方向大小样式
                this.width = this.width ^ this.height;
                this.height = this.width ^ this.height;
                this.width = this.width ^ this.height;

                // 设置子弹方向
                this.x += + belongTank.width - this.width/2;
                this.y += + belongTank.height/2 - this.height/2;

                break;
            case DOWN:
                // 设置子弹方向
                this.x += belongTank.width/2 - this.width/2;
                this.y += belongTank.height - this.height/2;

                break;
        }
    }

    /**
     * 子弹方向处理
     */
    public void moveHandler(){
        switch (dir) {
            case LEFT:
                x -= this.speed;
                break;
            case UP:
                y -= this.speed;
                break;
            case RIGHT:
                x += this.speed;
                break;
            case DOWN:
                y += this.speed;
                break;
        }

        // 边缘处理
        if(x < 0 || y < height/2 || x > TankFrame.GAME_WIDTH-width || y > TankFrame.GAME_HEIGHT-height){
            this.died();
        }

    }

    /**
     * 描绘
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {

        // 炮弹死亡移除
        if(!this.liveFlag){
            gm.remove(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourcesMgr.bulletBigL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourcesMgr.bulletBigU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourcesMgr.bulletBigR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourcesMgr.bulletBigD,x,y,null);
                break;
        }

        // 子弹自动行走
        this.moveHandler();
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
    public Tank getBelongTank() {
        return belongTank;
    }

    /**
     * 炮弹死亡
     */
    public void died() {

        if(!this.liveFlag){
            return;
        }

        this.liveFlag = false;
        // 归还子弹
        BulletPool.INSTANCE.revertBullet(this);
    }

    // -----------


    public BaseGameModel getGm() {
        return gm;
    }


}
