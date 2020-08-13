package com.parker.tank.chain;

import com.parker.tank.chain.other.TitleChain;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain
 * @Author: Parker
 * @CreateTime: 2020-08-13 22:01
 * @Description: 关卡 总实现
 */
public class GateGameChain extends GameChain{

    /** 这里使用 arrayList 是为了 关卡号 正常用 LinkedList */
    private List<GameChain> gates = new ArrayList<>();
    /** 暂停次数 */
    private int pauseCount = 3;
    /** 暂停次数 - 临时 */
    private int pauseCountTemp = 3;
    /**
     * 构造函数
     */
    public GateGameChain(int pauseCount){
        this.pauseCount = pauseCount;
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
            pauseCountTemp = pauseCount;

            // 标题
            GameChain titleChain = new TitleChain().setText(this.getNum() + "-" + gate.getNum());
            titleChain.handler();

            gate.setNum1(pauseCountTemp);
            boolean handler = gate.handler();

            // 第一次 关卡任务失败
            if(!handler){
                this.recourseHandler(gate,false);
            }

            long totalTimeEnd = System.currentTimeMillis();
            System.out.println("关卡任务总执行时间(秒)："+(totalTimeEnd/1000-totalTimeStart/1000));

            // 判断执行失败
            if(pauseCountTemp <= 0){
                return false;
            }
            pauseCountTemp = pauseCount;
        }
        return true;
    }

    /**
     * 递归执行
     * @return boolean
     */
    private boolean recourseHandler(GameChain g,boolean resultFlag){
        //System.out.println("进入递归："+pauseCountTemp);
        pauseCountTemp--;
        g.setNum1(pauseCountTemp);
        boolean handlerResult = false;
        if(pauseCountTemp >= 0 && !resultFlag){
            handlerResult = g.handler();
            if(!handlerResult){
                this.recourseHandler(g,false);
            }
        }
        return handlerResult;
    }

    @Override
    public void add(GameChain gameChain){
        this.gates.add(gameChain);
    }

}
