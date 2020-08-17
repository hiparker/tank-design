package com.parker.tank.factory;

import com.parker.tank.Wall;
import com.parker.tank.dist.WallGroup;
import com.parker.tank.faced.BaseGameModel;

import java.awt.image.BufferedImage;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 19:59
 * @Description: 墙工厂
 */
public enum WallFactory {

    /** 实例 */
    INSTANCE;

    public Wall createWall(int x, int y, int width, int height, WallGroup group) {
        return new Wall(x,y,width,height,group);
    }

    public Wall createWall(int x, int y, int width, int height, BufferedImage image) {
        return new Wall(x,y,width,height,image);
    }

    public Wall createWallByHp(int x, int y, int width, int height,int hp, WallGroup group) {
        return new Wall(x,y,width,height,hp,group);
    }

    public Wall createWallByHp(int x, int y, int width, int height,int hp, BufferedImage image) {
        return new Wall(x,y,width,height,hp,image);
    }
}
