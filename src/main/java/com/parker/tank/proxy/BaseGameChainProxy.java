package com.parker.tank.proxy;

import com.parker.tank.chain.ChainStack;
import com.parker.tank.chain.GameChain;
import com.parker.tank.chain.GateGameChain;
import com.parker.tank.chain.gate.Gate;
import com.parker.tank.chain.other.CoverChain;
import com.parker.tank.chain.other.ErrorOverChain;
import com.parker.tank.chain.other.SuccessOverChain;
import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.faced.GameModel;
import com.parker.tank.map.Gate1Map;
import com.parker.tank.map.Gate2Map;

import java.util.LinkedList;
import java.util.List;

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
public class BaseGameChainProxy extends GameChain {

    private GameChain gameChai;

    /**
     * 构造函数
     */

    public BaseGameChainProxy(GameChain gameChain){
        this.gameChai = gameChain;
    }

    /**
     * 执行
     * @return
     */
    @Override
    public boolean handler() {
        long totalTimeStart = System.currentTimeMillis();

        // 执行
        this.gameChai.handler();

        long totalTimeEnd = System.currentTimeMillis();
        System.out.println("游戏 - 总执行时间(秒)："+(totalTimeEnd/1000-totalTimeStart/1000));
        return true;
    }
}
