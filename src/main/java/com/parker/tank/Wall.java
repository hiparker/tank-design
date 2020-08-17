package com.parker.tank;

import com.parker.tank.dist.WallGroup;
import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.factory.TankFrameFactory;
import com.parker.tank.util.WallmageUtil;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 19:56
 * @Description: 墙
 */
public class Wall extends GameObject{

    private int speed = 5;
    private int sx = 0, sy = 0;
    private int offX = 0,offY = 0;
    private boolean offFlag = false;
    private int hp = 1;
    private WallGroup group;
    /** 当前位置 */
    private Rectangle rectangle;
    private transient BufferedImage bufferedImage;

    public Wall(int x, int y, int width, int height, WallGroup group) {
        this.x = x;
        this.y = y;
        this.sx = x;
        this.sy = y;
        this.width = width;
        this.height = height;
        this.group = group;
        // 设置碰撞检测位置
        this.rectangle = new Rectangle(x,y,width,height);
    }

    public Wall(int x, int y, int width, int height, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.sx = x;
        this.sy = y;
        this.width = width;
        this.height = height;
        this.group = WallGroup.BRICK;
        this.bufferedImage = image;
        // 设置碰撞检测位置
        this.rectangle = new Rectangle(x,y,width,height);
    }

    public Wall(int x, int y, int width, int height,int hp,WallGroup group) {
        this.x = x;
        this.y = y;
        this.sx = x;
        this.sy = y;
        this.width = width;
        this.height = height;
        this.group = group;
        this.hp = hp;
        // 设置碰撞检测位置
        this.rectangle = new Rectangle(x,y,width,height);
    }

    public Wall(int x, int y, int width, int height, int hp, BufferedImage image) {
        this.x = x;
        this.y = y;
        this.sx = x;
        this.sy = y;
        this.width = width;
        this.height = height;
        this.group = WallGroup.BRICK;
        this.bufferedImage = image;
        this.hp = hp;
        // 设置碰撞检测位置
        this.rectangle = new Rectangle(x,y,width,height);
    }

    public void setOffX(int offX) {
        this.offX = offX;
        this.offFlag = true;
    }

    public void setOffY(int offY) {
        this.offY = offY;
        this.offFlag = true;
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

        if(hp <= 0){
            if(TankFrameFactory.INSTANCE.getTankFrame().getBgm() != null){
                TankFrameFactory.INSTANCE.getTankFrame().getBgm().remove(this);
            }
        }

        BufferedImage temp = bufferedImage;
        if(temp == null){
            temp = WallmageUtil.getWallImage(group);
        }

        if (!this.offFlag){
            g.drawImage(temp,this.x,this.y,this.width,this.height,null);
        }else{
            if(this.offY > 0){
                g.drawImage(temp,this.x,this.y+=this.speed,this.width,this.height,null);
                if(this.y >= this.sy+this.offY){
                    this.offFlag = false;
                }
            }else if(this.offY > 0){
                g.drawImage(temp,this.x,this.x+=this.speed,this.width,this.height,null);
                if(this.x >= this.sx+this.offX){
                    this.offFlag = false;
                }
            }
        }

    }

    public void subHp(){
        this.hp--;
    }

    // ---

}
