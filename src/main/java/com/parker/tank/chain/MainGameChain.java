package com.parker.tank.chain;

import com.parker.tank.chain.gate.Gate;
import com.parker.tank.chain.other.CoverChain;
import com.parker.tank.chain.other.ErrorOverChain;
import com.parker.tank.chain.other.SuccessOverChain;
import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.faced.GameModel;
import com.parker.tank.map.Gate1Map;
import com.parker.tank.map.Gate2Map;
import com.parker.tank.proxy.GateChainProxy;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain
 * @Author: Parker
 * @CreateTime: 2020-08-13 22:01
 * @Description: 关卡 总实现
 */
public class MainGameChain extends BaseGameChain {

    private final List<GameChain> gates = new LinkedList<>();

    private GateGameChain gateGameChain;

    /**
     * 构造函数
     */

    public MainGameChain(){
        // 加载封面责任 ----------
        this.add(new CoverChain());

        // 加载关卡责任链 --------
        int count = 3;
        if(PropertiesMgr.getByInteger("pauseCount") != null){
            count = PropertiesMgr.getByInteger("pauseCount");
        }

        // 关卡责任链加载 （关卡使用动态代理 来记录执行时间）
        gateGameChain = new GateGameChain(count);

        GameChain gate1Proxy = GateChainProxy.INSTANCE.createGate(
                new Gate()
        );
        gate1Proxy.setGameModel(GameModel.class);
        gate1Proxy.setGateMap(Gate1Map.INSTANCE);
        gate1Proxy.setSelfNum(1);
        gate1Proxy.setBadTankCount(8);

        GameChain gate2Proxy = GateChainProxy.INSTANCE.createGate(
                new Gate()
        );
        gate2Proxy.setGameModel(GameModel.class);
        gate2Proxy.setGateMap(Gate2Map.INSTANCE);
        gate2Proxy.setSelfNum(2);
        gate2Proxy.setBadTankCount(10);

        gateGameChain.add(
                gate1Proxy
        );
        gateGameChain.add(
                gate2Proxy
        );

        this.add(gateGameChain.setSelfNum(1));

    }

    /**
     * 执行
     * @return
     */
    @Override
    public boolean handler() {
        ChainStack.INSTANCE.put(this);
        super.remake();

        boolean handler;
        try {

            // 重制 关卡
            for (GameChain gate : gates) {
                // 如果是关卡类型的责任链 则加入 关卡位置
                if(gate instanceof GateGameChain){
                    ((GateGameChain) gate).setGatePosition(1);
                }
            }

            for (GameChain gate : gates) {
                handler = gate.handler();
                // 关卡任务失败
                if(!handler){
                    break;
                }
            }

            // 卡死进程  -----------------
            while (super.state.get()){}
        }catch (Exception e){
            super.result.set(false);
        }

        // 强制退出
        if(super.forceState.get()){
            System.out.println("强制退出主责任链");
            return super.result.get();
        }

        // 游戏成功结束
        if(super.result.get()){
            new SuccessOverChain().handler();
            return super.result.get();
        }

        // 游戏错误结束
        new ErrorOverChain().handler();

        return super.result.get();
    }

    /**
     * 执行 回档
     * @return
     */
    public boolean handlerLoad(int gatePosition) {
        System.out.println("进入回档责任链");
        ChainStack.INSTANCE.put(this);
        super.remake();

        boolean handler;
        try {
            // 拷贝责任链 去掉开头
            List<GameChain> gatesLoad = new ArrayList<>();
            gatesLoad.addAll(gates);
            for (int i = 0; i < gatesLoad.size(); i++) {
                if(gatesLoad.get(i) instanceof CoverChain){
                    gatesLoad.remove(gatesLoad.get(i));
                }
            }
            for (GameChain gate : gatesLoad) {

                // 如果是关卡类型的责任链 则加入 关卡位置
                if(gate instanceof GateGameChain){
                    ((GateGameChain) gate).setGatePosition(gatePosition);
                }

                handler = gate.handler();
                // 关卡任务失败
                if(!handler){
                    break;
                }
            }

            // 卡死进程  -----------------
            while (super.state.get()){}
        }catch (Exception e){
            super.result.set(false);
        }

        // 强制退出
        if(super.forceState.get()){
            return super.result.get();
        }

        // 游戏成功结束
        if(super.result.get()){
            System.out.println("强制退出主回档责任链");
            new SuccessOverChain().handler();
            return super.result.get();
        }

        // 游戏错误结束
        new ErrorOverChain().handler();

        return super.result.get();
    }


    @Override
    public void add(GameChain gameChain){
        this.gates.add(gameChain);
    }


    public GateGameChain getGateGameChain(){
        return gateGameChain;
    }


}
