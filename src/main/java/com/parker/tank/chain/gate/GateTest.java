package com.parker.tank.chain.gate;

import com.parker.tank.chain.ChainStack;
import com.parker.tank.chain.BaseGameChain;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain.gate
 * @Author: Parker
 * @CreateTime: 2020-08-13 22:17
 * @Description: 第一关
 */
public class GateTest extends BaseGameChain {


    @Override
    public boolean handler() {
        ChainStack.INSTANCE.put(this);
        super.remake();
        try {
            //TODO:关卡执行

            // 3 秒后 停止
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.errorStop();
            }).start();


            // 卡死进程  -----------------
            while (super.state.get()){}
        }catch (Exception e){
            super.result.set(false);
        }
        return super.result.get();
    }

}
