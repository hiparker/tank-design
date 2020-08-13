package com.parker.tank.entity.bullet;

import com.parker.tank.TankFrame;
import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.config.ResourcesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.factory.base.BaseBullet;
import com.parker.tank.factory.base.BaseTank;

import java.awt.*;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 16:18
 * @Description: 炮弹类 小
 */
public class SmallBullet extends BaseBullet {

    /** 宽度 高度 */
    private int bulletWidth = ResourcesMgr.bulletSmallU.getWidth(), bulletHeight = ResourcesMgr.bulletSmallU.getHeight();


    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public SmallBullet(int x, int y, Dir dir, TankFrame tankFrame, BaseTank belongTank) {

        // 初始化
        this.init();

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.belongTank = belongTank;

        // 设置碰撞检测位置
        this.rectangle = new Rectangle(this.x,this.y,bulletWidth,bulletHeight);

        // 设置 子弹样式
        setBulletStyle();

        tankFrame.addBullet(this);
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
    @Override
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
    @Override
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

    @Override
    public void paint(Graphics g) {

        // 炮弹死亡移除
        if(!this.liveFlag){
            tankFrame.removeBullet(this);
        }

        switch (dir) {
            case LEFT:
                g.drawImage(ResourcesMgr.bulletSmallL,x,y,null);
                break;
            case UP:
                g.drawImage(ResourcesMgr.bulletSmallU,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourcesMgr.bulletSmallR,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourcesMgr.bulletSmallD,x,y,null);
                break;
        }

        // 子弹自动行走
        this.moveHandler();
    }

}
