package com.parker.tank.util;

import com.parker.tank.config.ResourcesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-12 11:09
 * @Description: 坦克图片
 */
public final class TankImageUtil {

    /** 获得坦克图片 */
    private static final Map<Dir, BufferedImage> tankImagesMapDefualt = new HashMap<>();
    /** 获得坦克图片 */
    private static final  Map<Dir, BufferedImage> tankImagesMapGood1 = new HashMap<>();
    private static final  Map<Dir, BufferedImage> tankImagesMapGood2 = new HashMap<>();
    /** 获得坦克图片 */
    private static final  Map<Dir, BufferedImage> tankImagesMapBad1 = new HashMap<>();
    private static final  Map<Dir, BufferedImage> tankImagesMapBad2 = new HashMap<>();

    private static final Random R = new Random();

    static {
        setTankImage();
    }

    /**
     * 私有化构造函数
     */
    private TankImageUtil(){}

    /**
     * 设置坦克默认照片
     */
    private static void setTankImage(){
        tankImagesMapGood1.put(Dir.LEFT, ResourcesMgr.goodTank1L);
        tankImagesMapGood1.put(Dir.UP,ResourcesMgr.goodTank1U);
        tankImagesMapGood1.put(Dir.RIGHT,ResourcesMgr.goodTank1R);
        tankImagesMapGood1.put(Dir.DOWN,ResourcesMgr.goodTank1D);

        tankImagesMapGood2.put(Dir.LEFT,ResourcesMgr.goodTank2L);
        tankImagesMapGood2.put(Dir.UP,ResourcesMgr.goodTank2U);
        tankImagesMapGood2.put(Dir.RIGHT,ResourcesMgr.goodTank2R);
        tankImagesMapGood2.put(Dir.DOWN,ResourcesMgr.goodTank2D);

        tankImagesMapBad1.put(Dir.LEFT,ResourcesMgr.badTank1L);
        tankImagesMapBad1.put(Dir.UP,ResourcesMgr.badTank1U);
        tankImagesMapBad1.put(Dir.RIGHT,ResourcesMgr.badTank1R);
        tankImagesMapBad1.put(Dir.DOWN,ResourcesMgr.badTank1D);

        tankImagesMapBad2.put(Dir.LEFT,ResourcesMgr.badTank2L);
        tankImagesMapBad2.put(Dir.UP,ResourcesMgr.badTank2U);
        tankImagesMapBad2.put(Dir.RIGHT,ResourcesMgr.badTank2R);
        tankImagesMapBad2.put(Dir.DOWN,ResourcesMgr.badTank2D);

        tankImagesMapDefualt.put(Dir.LEFT,ResourcesMgr.defaultTankL);
        tankImagesMapDefualt.put(Dir.UP,ResourcesMgr.defaultTankU);
        tankImagesMapDefualt.put(Dir.RIGHT,ResourcesMgr.defaultTankR);
        tankImagesMapDefualt.put(Dir.DOWN,ResourcesMgr.defaultTankD);
    }

    /**
     * 获得坦克默认照片Map
     * @param group
     */
    public static Map<Dir, BufferedImage> getTankImageMap(TankGroup group){
        if(TankGroup.BLUE.equals(group)){
            // 随机坦克颜色 动态坦克
            if(R.nextInt(100) > 80){
                return tankImagesMapBad2;
            }
            return tankImagesMapBad1;
        }else if(TankGroup.RED.equals(group)){
            // 随机坦克颜色 动态坦克
            if(R.nextInt(100) > 80){
                return tankImagesMapGood2;
            }
            return tankImagesMapGood1;
        }
        return tankImagesMapDefualt;
    }

    /**
     * 获得坦克默认照片
     * @param group
     */
    public static BufferedImage getTankImage(TankGroup group, Dir dir){
        if(TankGroup.BLUE.equals(group)){
            // 随机坦克颜色 动态坦克
            if(R.nextInt(100) > 80){
                return tankImagesMapBad2.get(dir);
            }
            return tankImagesMapBad1.get(dir);
        }else if(TankGroup.RED.equals(group)){
            // 随机坦克颜色 动态坦克
            if(R.nextInt(100) > 80){
                return tankImagesMapGood2.get(dir);
            }
            return tankImagesMapGood1.get(dir);
        }
        return tankImagesMapDefualt.get(dir);
    }

}
