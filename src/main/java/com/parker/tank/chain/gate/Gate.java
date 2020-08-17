package com.parker.tank.chain.gate;

import com.parker.tank.chain.ChainStack;
import com.parker.tank.chain.BaseGameChain;
import com.parker.tank.faced.BaseGameModel;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain.gate
 * @Author: Parker
 * @CreateTime: 2020-08-13 22:17
 * @Description: 第一关
 */
public class Gate extends BaseGameChain {

    private int count = 0;

    private BaseGameModel gameModel;

    @Override
    public boolean handler() {
        ChainStack.INSTANCE.put(this);

        super.remake();
        try {
            gameModel = this.createGameModel();
            gameModel.builder();

            // 设置调停者
            tankFrame.setBgm(gameModel);

            // 构建地图
            super.getGateMap()
                    .builderWall()
                    .builderSpecial()
                    .builderMine()
                    .builder(this.getBadTankCount());

            // 卡死进程  -----------------
            while (super.state.get()){}
        }catch (Exception e){
            super.result.set(false);
        }
        return super.result.get();
    }

    @Override
    public BaseGameModel getGameModel() {
        return this.gameModel;
    }
}
