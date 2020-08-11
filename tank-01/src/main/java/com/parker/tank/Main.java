package com.parker.tank;

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
        TankFrame t = new TankFrame();

        // 创建5个敌方坦克
        for (int i = 0; i < 5; i++) {
            t.enemyTanks.add(new Tank(50+i*80,200,Dir.DOWN,t,TankGroup.BLUE,true));
        }

        // 自动刷新 window
        while (true){
            t.repaint();
            TimeUnit.MILLISECONDS.sleep(50);
        }
    }

}
