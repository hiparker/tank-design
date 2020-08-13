package com.parker.tank;

import com.parker.tank.config.PropertiesMgr;

import java.awt.*;

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

    public void createWall(GameModel gm){

        int gameWidth = 800, gameHeight = 600;
        if(PropertiesMgr.getByInteger("gameWidth") != null){
            gameWidth = PropertiesMgr.getByInteger("gameWidth");
        }
        if(PropertiesMgr.getByInteger("gameHeight") != null){
            gameHeight = PropertiesMgr.getByInteger("gameHeight");
        }


        gm.add(new Wall(0,120,180,60, Color.LIGHT_GRAY));
        gm.add(new Wall(gameWidth-120,120,120,60, Color.LIGHT_GRAY));
    }

}
