package com.parker.tank.factory;

import com.parker.tank.StringG;
import com.parker.tank.chain.ChainStack;
import com.parker.tank.chain.BaseGameChain;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.factory
 * @Author: Parker
 * @CreateTime: 2020-08-15 14:48
 * @Description: 关卡结束
 */
public enum GateFactory {

    INSTANCE;

    /**
     * 关卡 错误结束
     */
    public void chainErrorGameOver(){
        if(TankFrameFactory.INSTANCE.getTankFrame().getBgm() == null){
            return;
        }

        TankFrameFactory.INSTANCE.getTankFrame().getBgm().add(new StringG(100,100,"GAME OVER!"));

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 结束责任链
            BaseGameChain gc = ChainStack.INSTANCE.peek();
            if(gc != null){
                gc.errorStop();
            }
        }).start();
    }

    /**
     * 关卡 正常结束
     */
    public void chainSuccessGameOver(){
        if(TankFrameFactory.INSTANCE.getTankFrame().getBgm() == null){
            return;
        }

        TankFrameFactory.INSTANCE.getTankFrame().getBgm().add(new StringG(100,100,"GAME SUCCESS!"));

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 结束责任链
            BaseGameChain gc = ChainStack.INSTANCE.peek();
            if(gc != null){
                gc.successStop();
            }
        }).start();
    }

}
