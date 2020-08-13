package com.parker.tank;

import com.parker.tank.chain.BaseGameChain;
import com.parker.tank.chain.GameChain;
import com.parker.tank.factory.TankFrameFactory;

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

        // 调用完整责任链
        new Thread(()->{
            GameChain gameChain = new BaseGameChain();
            gameChain.handler();
        }).start();

        TankFrame tankFrame = TankFrameFactory.INSTANCE.getTankFrame();
        System.out.println("普通坦克数量["+TankFactory.usualCount+"]  自动坦克数量["+TankFactory.autoCount+"]");

        // 自动刷新 window
        while (true){
            tankFrame.repaint();
            TimeUnit.MILLISECONDS.sleep(25);
        }
    }

}
