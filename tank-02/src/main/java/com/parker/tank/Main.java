package com.parker.tank;

import com.parker.tank.config.PropertiesMgr;

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
        int badTankCount = Integer.parseInt(PropertiesMgr.get("badTankCount"));

        TankFrame t = new TankFrame();

        // 创建5个敌方坦克
        for (int i = 0; i < badTankCount; i++) {
            t.enemyTanks.add(TankFactory.createTank(50+i*80,200,Dir.DOWN,t,TankGroup.BLUE,true));
        }

        // 自动刷新 window
        while (true){
            t.repaint();
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

}
