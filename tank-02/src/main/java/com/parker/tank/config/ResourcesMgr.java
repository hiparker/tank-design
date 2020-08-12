package com.parker.tank.config;

import com.parker.tank.util.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-11 01:09
 * @Description: 加载静态资源
 *
 * 单例模式
 *
 */
public class ResourcesMgr {

    /** 坦克图片 */
    public static BufferedImage defaultTankL, defaultTankU, defaultTankR, defaultTankD;
    public static BufferedImage goodTank1L, goodTank1U, goodTank1R, goodTank1D;
    public static BufferedImage goodTank2L, goodTank2U, goodTank2R, goodTank2D;
    public static BufferedImage badTank1L, badTank1U, badTank1R, badTank1D;
    public static BufferedImage badTank2L, badTank2U, badTank2R, badTank2D;

    /** 炮弹图片 大*/
    public static BufferedImage bulletBigL, bulletBigU, bulletBigR, bulletBigD;
    /** 炮弹图片 小*/
    public static BufferedImage bulletSmallL, bulletSmallU, bulletSmallR, bulletSmallD;

    /** 爆炸图片 大*/
    public static BufferedImage[] explodesBig = new BufferedImage[16];
    /** 爆炸图片 小 */
    public static BufferedImage[] explodesSmall = new BufferedImage[10];


    static{
        try {
            defaultTankU = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/tankU.gif"));
            defaultTankL = ImageUtil.rotateImage(defaultTankU,-90);
            defaultTankR = ImageUtil.rotateImage(defaultTankU,90);
            defaultTankD = ImageUtil.rotateImage(defaultTankU,180);

            goodTank1U = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/GoodTank1.png"));
            goodTank1L = ImageUtil.rotateImage(goodTank1U,-90);
            goodTank1R = ImageUtil.rotateImage(goodTank1U,90);
            goodTank1D = ImageUtil.rotateImage(goodTank1U,180);

            goodTank2U = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/GoodTank2.png"));
            goodTank2L = ImageUtil.rotateImage(goodTank2U,-90);
            goodTank2R = ImageUtil.rotateImage(goodTank2U,90);
            goodTank2D = ImageUtil.rotateImage(goodTank2U,180);

            badTank1U = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/BadTank1.png"));
            badTank1L = ImageUtil.rotateImage(badTank1U,-90);
            badTank1R = ImageUtil.rotateImage(badTank1U,90);
            badTank1D = ImageUtil.rotateImage(badTank1U,180);

            badTank2U = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/BadTank2.png"));
            badTank2L = ImageUtil.rotateImage(badTank2U,-90);
            badTank2R = ImageUtil.rotateImage(badTank2U,90);
            badTank2D = ImageUtil.rotateImage(badTank2U,180);

            bulletBigU = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/bulletU.png"));
            bulletBigL = ImageUtil.rotateImage(bulletBigU,-90);
            bulletBigR = ImageUtil.rotateImage(bulletBigU,90);
            bulletBigD = ImageUtil.rotateImage(bulletBigU,180);

            bulletSmallU = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/bulletU.gif"));
            bulletSmallL = ImageUtil.rotateImage(bulletSmallU,-90);
            bulletSmallR = ImageUtil.rotateImage(bulletSmallU,90);
            bulletSmallD = ImageUtil.rotateImage(bulletSmallU,180);


            // 大爆炸
            for (int i = 0; i < 16; i++) {
                explodesBig[i] = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/e"+(i+1)+".gif"));
            }

            // 小爆炸
            for (int i = 0; i < 10; i++) {
                explodesSmall[i] = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/"+i+".gif"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
