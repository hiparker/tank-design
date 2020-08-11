package com.parker.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-11 01:09
 * @Description: 加载静态资源
 */
public class ResourcesMgr {

    /** 坦克图片 */
    public static BufferedImage tankL, tankU, tankR, tankD;
    /** 炮弹图片 */
    public static BufferedImage bulletL, bulletU, bulletR, bulletD;
    /** 爆炸图片 */
    public static BufferedImage[] explodes = new BufferedImage[16];

    static{
        try {
            tankL = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/tankL.gif"));
            tankU = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/tankU.gif"));
            tankR = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/tankR.gif"));
            tankD = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/tankD.gif"));

            bulletL = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/bulletL.gif"));
            bulletU = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/bulletU.gif"));
            bulletR = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/bulletR.gif"));
            bulletD = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/bulletD.gif"));

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/e"+(i+1)+".gif"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
