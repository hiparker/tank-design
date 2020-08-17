package com.parker.tank.chain;

import com.parker.tank.TankFrame;
import com.parker.tank.faced.BaseGameModel;
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
 * 起到Adapter作用
 *
 */
public abstract class BaseGameChain implements GameChain {

    protected TankFrame tankFrame = TankFrameFactory.INSTANCE.getTankFrame();

    /** 执行状态 */
    protected AtomicBoolean state = new AtomicBoolean(true);
    /** 执行结果状态 */
    protected AtomicBoolean result = new AtomicBoolean(true);
    /** 执行结果状态 */
    protected AtomicBoolean forceState = new AtomicBoolean(false);
    /** 当前责任链编号 */
    protected int selfNum;
    /** 父责任链编号 */
    protected int parentNum;
    /** 失败重试次数 */
    protected int pauseCount;
    /** 敌方坦克数量 */
    protected int badTankCount;
    /** 关卡位置 */
    protected int gatePosition;

    protected String text;

    private Class<?> gameModelClazz;
    private GateMap gateMap;

    /**
     * 执行游戏
     * @return
     */
    @Override
    public abstract boolean handler();

    @Override
    public boolean handlerLoad(int count) {
        throw new RuntimeException("未重写该方法");
    }

    /**
     * 添加责任
     * @return
     */
    @Override
    public void add(GameChain gameChain){
        throw new RuntimeException("未初始化添加功能！");
    }


    /**
     * 错误停止
     */
    @Override
    public void errorStop() {
        result.set(false);
        state.set(false);
        forceState.set(false);
    }
    /**
     * 正常停止
     */
    @Override
    public void successStop() {
        result.set(true);
        state.set(false);
        forceState.set(false);
    }
    /**
     * 强制退出
     */
    @Override
    public void forceEnd() {
        result.set(false);
        state.set(false);
        forceState.set(true);
    }
    /**
     * 重制
     */
    @Override
    public void remake() {
        result.set(true);
        state.set(true);
        forceState.set(false);
    }
    @Override
    public boolean getForceState() {
        return forceState.get();
    }

    /**
     * 关卡当前位置
     * @param gatePosition
     */
    public void setGatePosition(int gatePosition) {
        this.gatePosition = gatePosition;
    }

    /**
     * 获得敌方Tank数量
     * @return
     */
    @Override
    public int getBadTankCount() {
        return badTankCount;
    }

    /**
     * 设置敌方坦克数量
     * @param badTankCount
     * @return
     */
    @Override
    public GameChain setBadTankCount(int badTankCount) {
        this.badTankCount = badTankCount;
        return this;
    }

    /**
     * 获得游戏模型 - 反射
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    @Override
    public BaseGameModel createGameModel() throws IllegalAccessException, InstantiationException {
        BaseGameModel baseGameModel = (BaseGameModel) gameModelClazz.newInstance();
        // 设置失败重试次数
        baseGameModel.setTempNum(this.getPauseCount());
        return baseGameModel;
    }

    public BaseGameModel getGameModel(){
        throw new RuntimeException("未实现获取游戏模型类");
    }

    /**
     * 设置游戏模型
     * @param clazz
     * @return
     */
    @Override
    public GameChain setGameModel(Class<?> clazz){
        this.gameModelClazz = clazz;
        return this;
    }

    /**
     * 获得地图
     * @return
     */
    @Override
    public GateMap getGateMap() {
        return gateMap;
    }

    /**
     * 设置地图
     * @param gateMap
     * @return
     */
    @Override
    public GameChain setGateMap(GateMap gateMap) {
        this.gateMap = gateMap;
        return this;
    }

    /**
     * 获得当前责任链编号
     * @return
     */
    @Override
    public int getSelfNum() {
        return selfNum;
    }

    /**
     * 设置当前责任链编号
     * @param selfNum
     * @return
     */
    @Override
    public GameChain setSelfNum(int selfNum) {
        this.selfNum = selfNum;
        return this;
    }

    /**
     * 获得父责任链编号
     * @param
     * @return
     */
    @Override
    public int getParentNum() {
        return parentNum;
    }

    /**
     * 设置父责任链编号
     * @param parentNum
     * @return
     */
    @Override
    public GameChain setParentNum(int parentNum) {
        this.parentNum = parentNum;
        return this;
    }

    /**
     * 获得失败尝试次数
     * @return
     */
    @Override
    public int getPauseCount() {
        return pauseCount;
    }

    /**
     * 设置失败尝试次数
     * @param pauseCount
     * @return
     */
    @Override
    public GameChain setPauseCount(int pauseCount) {
        this.pauseCount = pauseCount;
        return this;
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public GameChain setText(String text) {
        this.text = text;
        return this;
    }


}
