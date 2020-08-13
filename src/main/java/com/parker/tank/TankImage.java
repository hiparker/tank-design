package com.parker.tank;

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
public final class TankImage {

    /** 获得坦克图片 */
    private static final Map<String, BufferedImage> tankImagesMapDefualt = new HashMap<>();
    /** 获得坦克图片 */
    private static final  Map<String, BufferedImage> tankImagesMapGood1 = new HashMap<>();
    private static final  Map<String, BufferedImage> tankImagesMapGood2 = new HashMap<>();
    /** 获得坦克图片 */
    private static final  Map<String, BufferedImage> tankImagesMapBad1 = new HashMap<>();
    private static final  Map<String, BufferedImage> tankImagesMapBad2 = new HashMap<>();

    private static final Random R = new Random();

    static {
        setTankImage();
    }

    /**
     * 私有化构造函数
     */
    private TankImage(){}

    /**
     * 设置坦克默认照片
     */
    private static void setTankImage(){
        tankImagesMapGood1.put("tankL",ResourcesMgr.goodTank1L);
        tankImagesMapGood1.put("tankU",ResourcesMgr.goodTank1U);
        tankImagesMapGood1.put("tankR",ResourcesMgr.goodTank1R);
        tankImagesMapGood1.put("tankD",ResourcesMgr.goodTank1D);

        tankImagesMapGood2.put("tankL",ResourcesMgr.goodTank2L);
        tankImagesMapGood2.put("tankU",ResourcesMgr.goodTank2U);
        tankImagesMapGood2.put("tankR",ResourcesMgr.goodTank2R);
        tankImagesMapGood2.put("tankD",ResourcesMgr.goodTank2D);

        tankImagesMapBad1.put("tankL",ResourcesMgr.badTank1L);
        tankImagesMapBad1.put("tankU",ResourcesMgr.badTank1U);
        tankImagesMapBad1.put("tankR",ResourcesMgr.badTank1R);
        tankImagesMapBad1.put("tankD",ResourcesMgr.badTank1D);

        tankImagesMapBad2.put("tankL",ResourcesMgr.badTank2L);
        tankImagesMapBad2.put("tankU",ResourcesMgr.badTank2U);
        tankImagesMapBad2.put("tankR",ResourcesMgr.badTank2R);
        tankImagesMapBad2.put("tankD",ResourcesMgr.badTank2D);

        tankImagesMapDefualt.put("tankL",ResourcesMgr.defaultTankL);
        tankImagesMapDefualt.put("tankU",ResourcesMgr.defaultTankU);
        tankImagesMapDefualt.put("tankR",ResourcesMgr.defaultTankR);
        tankImagesMapDefualt.put("tankD",ResourcesMgr.defaultTankD);
    }

    /**
     * 设置坦克默认照片
     * @param group
     */
    public static Map<String, BufferedImage> getTankImage(TankGroup group){
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
}
