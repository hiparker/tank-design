package com.parker.tank.proxy;

import com.parker.tank.chain.BaseGameChain;
import com.parker.tank.chain.GameChain;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain
 * @Author: Parker
 * @CreateTime: 2020-08-13 22:01
 * @Description: 关卡 总实现
 *
 * 责任链 的 静态代理 ，用于记录游戏总执行时间
 *
 */
public class BaseGameChainProxy extends BaseGameChain {

    private GameChain gameChain;

    /**
     * 构造函数
     */

    public BaseGameChainProxy(GameChain gameChain){
        this.gameChain = gameChain;
    }

    /**
     * 执行
     * @return
     */
    @Override
    public boolean handler() {
        long totalTimeStart = System.currentTimeMillis();

        // 执行
        this.gameChain.handler();

        long totalTimeEnd = System.currentTimeMillis();

        System.out.println();
        System.out.println("游戏 - 总执行时间(秒)："+(totalTimeEnd/1000-totalTimeStart/1000));
        System.out.println();

        return true;
    }
}
