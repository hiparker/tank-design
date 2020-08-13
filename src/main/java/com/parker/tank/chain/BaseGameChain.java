package com.parker.tank.chain;

import com.parker.tank.chain.gate.Gate1;
import com.parker.tank.chain.gate.GateTest;
import com.parker.tank.chain.other.CoverChain;
import com.parker.tank.chain.other.OverChain;
import com.parker.tank.chain.other.TitleChain;
import com.parker.tank.config.PropertiesMgr;

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
        gateGameChain.add(new Gate1().setNum(1));
        gateGameChain.add(new GateTest().setNum(2));

        this.add(gateGameChain.setNum(1));

        // 游戏结束
        this.add(new OverChain());
    }

    /**
     * 执行
     * @return
     */
    @Override
    public boolean handler() {
        ChainStack.INSTANCE.put(this);
        boolean flag = true;
        for (GameChain gate : gates) {

            long totalTimeStart = System.currentTimeMillis();
            boolean handler = gate.handler();
            long totalTimeEnd = System.currentTimeMillis();
            System.out.println("总执行时间(秒)："+(totalTimeEnd/1000-totalTimeStart/1000));

            // 第一次 关卡任务失败
            // 第一次 关卡任务失败
            if(!handler){
                flag = false;
                break;
            }
        }

        // 游戏结束
        if(!flag){
            gates.get(gates.size()-1).handler();
        }
        return true;
    }


    @Override
    public void add(GameChain gameChain){
        this.gates.add(gameChain);
    }

}
