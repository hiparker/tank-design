package com.parker.tank.entity.explode;

import com.parker.tank.Audio;
import com.parker.tank.config.ResourcesMgr;
import com.parker.tank.TankFrame;
import com.parker.tank.factory.base.BaseExplode;

import java.awt.*;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-11 20:38
 * @Description: 小 爆炸效果
 */
public class SmallExplode extends BaseExplode {

    public SmallExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;

        // 爆炸音效
        new Thread(()->{
            new Audio("static/audio/explode.wav").play();
        }).start();
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourcesMgr.explodesSmall[count++],x,y,null);
        if(count >= ResourcesMgr.explodesSmall.length){
            // 爆炸消失
            tankFrame.removeExplode(this);
        }
    }

}
