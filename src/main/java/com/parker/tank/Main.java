package com.parker.tank;

import com.parker.tank.chain.BaseGameChain;
import com.parker.tank.chain.GameChain;
import com.parker.tank.factory.TankFrameFactory;
import com.parker.tank.proxy.BaseGameChainProxy;

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
            // 静态代理 BaseGameChain 责任链
            GameChain gameChain = new BaseGameChainProxy(
                    new BaseGameChain()
            );
            gameChain.handler();
        }).start();

        TankFrame tankFrame = TankFrameFactory.INSTANCE.getTankFrame();

        // 自动刷新 window
        while (true){
            tankFrame.repaint();
            TimeUnit.MILLISECONDS.sleep(25);
        }
    }

}
