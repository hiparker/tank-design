package com.parker.tank.chain;

import com.parker.tank.chain.gate.Gate;
import com.parker.tank.chain.other.CoverChain;
import com.parker.tank.chain.other.OverChain;
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
 */
public class BaseGameChain extends GameChain{

    private List<GameChain> gates = new LinkedList<>();


    /**
     * 构造函数
     */

    public BaseGameChain(){
        // 加载封面责任 ----------
        this.add(new CoverChain());

        // 加载关卡责任链 --------
        int count = 3;
        if(PropertiesMgr.getByInteger("pauseCount") != null){
            count = PropertiesMgr.getByInteger("pauseCount");
        }


        GateGameChain gateGameChain = new GateGameChain(count);
        gateGameChain.add(new Gate()
                .setGameModel(GameModel.class)
                .setGateMap(Gate1Map.INSTANCE)
                .setNum(1)
        );
        gateGameChain.add(new Gate()
                .setGameModel(GameModel.class)
                .setGateMap(Gate2Map.INSTANCE)
                .setNum(2)
        );

        this.add(gateGameChain.setNum(1));

    }

    /**
     * 执行
     * @return
     */
    @Override
    public boolean handler() {
        ChainStack.INSTANCE.put(this);
        for (GameChain gate : gates) {

            long totalTimeStart = System.currentTimeMillis();
            boolean handler = gate.handler();
            long totalTimeEnd = System.currentTimeMillis();
            System.out.println("总执行时间(秒)："+(totalTimeEnd/1000-totalTimeStart/1000));

            // 关卡任务失败
            if(!handler){
                break;
            }
        }

        // 游戏结束
        new OverChain().handler();

        return true;
    }


    @Override
    public void add(GameChain gameChain){
        this.gates.add(gameChain);
    }

}
