package com.parker.tank;

import com.parker.tank.util.ImageUtil;

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
            tankU = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/BadTank2.png"));
            tankL = ImageUtil.rotateImage(tankU,-90);
            tankR = ImageUtil.rotateImage(tankU,90);
            tankD = ImageUtil.rotateImage(tankU,180);

            bulletU = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);


            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourcesMgr.class.getClassLoader().getResourceAsStream("static/images/e"+(i+1)+".gif"));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
