package com.parker.tank.chain.gate;

import com.parker.tank.chain.ChainStack;
import com.parker.tank.chain.GameChain;
import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.factory.TankFactory;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain.gate
 * @Author: Parker
 * @CreateTime: 2020-08-13 22:17
 * @Description: 第一关
 */
public class Gate extends GameChain {

    private int count = 0;



    @Override
    public boolean handler() {
        ChainStack.INSTANCE.put(this);

        super.remake();
        try {
            TankFactory.autoCount = 0;
            TankFactory.usualCount = 0;

            BaseGameModel gameModel = super.getGameModel();
            gameModel.builder();

            // 设置调停者
            tankFrame.setBgm(gameModel);

            // 加载地图
            super.getGateMap().getMap();

            // 卡死进程  -----------------
            while (super.state.get()){}
        }catch (Exception e){
            super.result.set(false);
        }
        return super.result.get();
    }

}
