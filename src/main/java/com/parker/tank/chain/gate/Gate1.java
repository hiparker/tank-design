package com.parker.tank.chain.gate;

import com.parker.tank.chain.ChainStack;
import com.parker.tank.chain.GameChain;
import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.faced.GameModel;
import com.parker.tank.map.Gate1Map;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain.gate
 * @Author: Parker
 * @CreateTime: 2020-08-13 22:17
 * @Description: 第一关
 */
public class Gate1 extends GameChain {

    private int count = 0;

    @Override
    public boolean handler() {
        ChainStack.INSTANCE.put(this);

        super.remake();
        try {
            // 设置调停者
            tankFrame.setBgm(new GameModel(this.getNum1()));

            // 加载地图
            Gate1Map.INSTANCE.getMap();

            // 3 秒后 停止
            int count = 1000;
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
