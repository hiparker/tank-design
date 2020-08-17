package com.parker.tank.chain;

import com.parker.tank.Main;
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
public class GateGameChain extends BaseGameChain {

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
        for (int i = 0; i < gates.size(); i++) {
            // 如果小于传入关卡位置 直接跳过
            if(i < this.gatePosition-1){
                continue;
            }

            GameChain gate = gates.get(i);
            pauseCountTemp = pauseCount;

            // 标题
            GameChain titleChain = new TitleChain().setText(this.getSelfNum() + " - " + gate.getSelfNum());
            titleChain.handler();

            // 设置
            gate.setPauseCount(pauseCountTemp-1);
            // 设置父编号
            gate.setParentNum(this.getSelfNum());

            boolean handler = gate.handler();
            pauseCountTemp--;
            // 强制终止责任链
            if(gate.getForceState()){
                return handler;
            }
            // 第一次 关卡任务失败
            if(!handler){
                this.recourseHandler(gate,false);
            }

            // 判断执行失败
            if(pauseCountTemp <= 0){
                // 唤醒父责任链
                Main.GAMECHAIN.errorStop();
                return false;
            }
            pauseCountTemp = pauseCount;
            
        }
        // 唤醒父责任链
        Main.GAMECHAIN.successStop();
        return true;
    }

    /**
     * 递归执行
     * @return boolean
     */
    private boolean recourseHandler(GameChain g, boolean resultFlag){
        //System.out.println("进入递归："+pauseCountTemp);
        pauseCountTemp--;
        g.setPauseCount(pauseCountTemp);
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
