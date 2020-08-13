package com.parker.tank.util;

import com.parker.tank.config.ResourcesMgr;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.dist.WallGroup;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-12 11:09
 * @Description: 获得墙图片
 */
public final class WallmageUtil {

    /** 获得墙图片 */
    private static final Map<WallGroup, BufferedImage> wallImagesMapY = new HashMap<>();
    private static final Map<WallGroup, BufferedImage> wallImagesMapG = new HashMap<>();
    private static final Map<WallGroup, BufferedImage> wallImagesMapR = new HashMap<>();


    static {
        setTankImage();
    }

    /**
     * 私有化构造函数
     */
    private WallmageUtil(){}

    /**
     * 设置坦克默认照片
     */
    private static void setTankImage(){
        wallImagesMapY.put(WallGroup.STEEl, ResourcesMgr.wallY);
        wallImagesMapG.put(WallGroup.TREE,ResourcesMgr.wallG);
        wallImagesMapR.put(WallGroup.BRICK,ResourcesMgr.wallR);
    }

    /**
     * 获得默认照片Map
     * @param group
     */
    public static Map<WallGroup, BufferedImage> getWallImageMap(WallGroup group){
        if(WallGroup.STEEl.equals(group)){
            return wallImagesMapY;
        }else if(WallGroup.TREE.equals(group)){
            return wallImagesMapG;
        }
        return wallImagesMapR;
    }

    /**
     * 获得默认照片
     * @param group
     */
    public static BufferedImage getWallImage(WallGroup group){
        if(WallGroup.STEEl.equals(group)){
            return wallImagesMapY.get(group);
        }else if(WallGroup.TREE.equals(group)){
            return wallImagesMapG.get(group);
        }
        return wallImagesMapR.get(group);
    }

}
