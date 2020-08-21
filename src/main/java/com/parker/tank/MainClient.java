package com.parker.tank;

import com.parker.tank.TankFrame;
import com.parker.tank.net.Client;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 01:32
 * @Description: 坦克 主入口
 */
public class MainClient {


    public static void main(String[] args) throws InterruptedException {

        TankFrame t = TankFrame.INSTANCE.init();

        /*// 创建5个敌方坦克
        for (int i = 0; i < 5; i++) {
            t.enemyTanks.add(TankFactory.createTank(50+i*80,200,Dir.DOWN,t,TankGroup.BLUE,true));
        }*/

        // 自动刷新 window
        new Thread(()-> {
            while(true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.repaint();
            }
        }).start();


        // 客户端
        Client.INSTANCE.connect();
    }

}
