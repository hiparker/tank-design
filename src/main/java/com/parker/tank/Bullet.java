package com.parker.tank;

import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.config.ResourcesMgr;
import com.parker.tank.dist.Dir;

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
    private GameModel gm;
    /** 存活状态 */
    private boolean liveFlag = true;
    /** 当前位置 */
    private Rectangle rectangle;
    /** 归属坦克 */
    private Tank belongTank;
    /** 宽度 高度 */
    private int bulletWidth = ResourcesMgr.bulletBigU.getWidth(), bulletHeight = ResourcesMgr.bulletBigU.getHeight();


    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Bullet(int x, int y, Dir dir, GameModel gm, Tank belongTank) {

        // 初始化
        this.init();

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.belongTank = belongTank;

        // 设置碰撞检测位置
        this.rectangle = new Rectangle(this.x,this.y,bulletWidth,bulletHeight);

        // 设置 子弹样式
        setBulletStyle();

        gm.add(this);
    }

    /**
     * 初始化
     */
    public void init(){
        // 速度
        if(PropertiesMgr.getByInteger("bulletSpeed") != null){
            this.speed = PropertiesMgr.getByInteger("bulletSpeed");
        }
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
        if(x < 0 || y < bulletHeight/2 || x > TankFrame.GAME_WIDTH-bulletWidth || y > TankFrame.GAME_HEIGHT-bulletHeight){
            liveFlag = false;
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
        this.liveFlag = false;
    }

}
