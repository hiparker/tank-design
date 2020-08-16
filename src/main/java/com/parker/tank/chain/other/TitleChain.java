package com.parker.tank.chain.other;

import com.parker.tank.StringG;
import com.parker.tank.chain.ChainStack;
import com.parker.tank.chain.BaseGameChain;
import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.faced.TitleModel;

import java.util.concurrent.TimeUnit;


/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain
 * @Author: Parker
 * @CreateTime: 2020-08-14 00:06
 * @Description: 封面
 */
public class TitleChain extends BaseGameChain {

    @Override
    public boolean handler() {
        ChainStack.INSTANCE.put(this);
        super.remake();
        try {
            BaseGameModel gameModel = new TitleModel();
            gameModel.builder();

            // 开始标题运作
            tankFrame.setBgm(gameModel);

            int gameWidth = 800, gameHeight = 600;
            if(PropertiesMgr.getByInteger("gameWidth") != null){
                gameWidth = PropertiesMgr.getByInteger("gameWidth");
            }
            if(PropertiesMgr.getByInteger("gameHeight") != null){
                gameHeight = PropertiesMgr.getByInteger("gameHeight");
            }

            // 背景图
            BaseGameModel bgm = tankFrame.getBgm();
            // 文字
            bgm.add(new StringG(gameWidth/2-35,gameHeight/2-100,this.getText()));

            // 2 秒后 停止
            int count = 2;
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.successStop();
            }).start();

            // 卡死进程  -----------------
            while (super.state.get()){}
        }catch (Exception e){
            super.result.set(false);
        }
        return super.result.get();
    }
}
