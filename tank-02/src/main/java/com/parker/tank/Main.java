package com.parker.tank;

import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.factory.GameFactory;
import com.parker.tank.factory.base.BaseTank;
import com.parker.tank.factory.child.DefaultFactory;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 01:32
 * @Description: 坦克 主入口
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        // 敌方坦克数量
        int badTankCount = PropertiesMgr.getByInteger("badTankCount");

        TankFrame tankFrame = new TankFrame();

        // 创建5个敌方坦克
        for (int i = 0; i < badTankCount; i++) {

            BaseTank autoTank = tankFrame.getGf().createAutoTank(50 + i * 80, 200, Dir.DOWN, tankFrame, TankGroup.BLUE, true);
            tankFrame.addBadTank(autoTank);

        }

        // 自动刷新 window
        while (true){
            tankFrame.repaint();
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

}
