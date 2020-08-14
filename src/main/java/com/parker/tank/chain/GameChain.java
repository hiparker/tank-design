package com.parker.tank.chain;

import com.parker.tank.TankFrame;
import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.faced.GameModel;
import com.parker.tank.factory.TankFrameFactory;
import com.parker.tank.map.GateMap;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain
 * @Author: Parker
 * @CreateTime: 2020-08-13 21:57
 * @Description: 游戏责任链
 *
 * 关卡
 *
 */
public abstract class GameChain {

    protected TankFrame tankFrame = TankFrameFactory.INSTANCE.getTankFrame();

    /** 执行状态 */
    protected AtomicBoolean state = new AtomicBoolean(true);
    /** 执行结果状态 */
    protected AtomicBoolean result = new AtomicBoolean(true);
    protected int num;
    protected int num1;
    protected int num2;
    protected String text;

    private Class<?> gameModelClazz;
    private GateMap gateMap;

    /**
     * 执行游戏
     * @return
     */
    public abstract boolean handler();

    /**
     * 添加责任
     * @return
     */
    public void add(GameChain gameChain){
        throw new RuntimeException("为初始化添加功能！");
    }


    /**
     * 停止
     */
    public void errorStop() {
        result.set(false);
        state.set(false);
    }
    /**
     * 停止
     */
    public void successStop() {
        result.set(true);
        state.set(false);
    }
    /**
     * 重制
     */
    protected void remake() {
        result.set(true);
        state.set(true);
    }

    public int getNum() {
        return num;
    }

    public GameChain setNum(int num) {
        this.num = num;
        return this;
    }

    public int getNum1() {
        return num1;
    }

    public GameChain setNum1(int num1) {
        this.num1 = num1;
        return this;
    }

    public int getNum2() {
        return num2;
    }

    public GameChain setNum2(int num2) {
        this.num2 = num2;
        return this;
    }

    public String getText() {
        return text;
    }

    public GameChain setText(String text) {
        this.text = text;
        return this;
    }
    public BaseGameModel getGameModel() throws IllegalAccessException, InstantiationException {
        BaseGameModel baseGameModel = (BaseGameModel) gameModelClazz.newInstance();
        baseGameModel.setTempNum(this.getNum1());
        return baseGameModel;
    }
    public GameChain setGameModel(Class<?> clazz){
        this.gameModelClazz = clazz;
        return this;
    }

    public GateMap getGateMap() {
        return gateMap;
    }

    public GameChain setGateMap(GateMap gateMap) {
        this.gateMap = gateMap;
        return this;
    }
}
