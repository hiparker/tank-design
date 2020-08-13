package com.parker.tank;

import com.parker.tank.dist.WallGroup;
import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.faced.GameModel;
import com.sun.istack.internal.NotNull;

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

    public Wall createWall(int x, int y, int width, int height, @NotNull WallGroup group, BaseGameModel gm) {
        return new Wall(x,y,width,height,group,gm);
    }

    public Wall createWall(int x, int y, int width, int height, @NotNull BufferedImage image, BaseGameModel gm) {
        return new Wall(x,y,width,height,image,gm);
    }

    public Wall createWallByHp(int x, int y, int width, int height,int hp, @NotNull WallGroup group, BaseGameModel gm) {
        return new Wall(x,y,width,height,hp,group,gm);
    }

    public Wall createWallByHp(int x, int y, int width, int height,int hp, @NotNull BufferedImage image, BaseGameModel gm) {
        return new Wall(x,y,width,height,hp,image,gm);
    }
}
