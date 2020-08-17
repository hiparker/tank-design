package com.parker.tank.chain;

import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.map.GateMap;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain
 * @Author: Parker
 * @CreateTime: 2020-08-17 00:12
 * @Description: 责任链总接口
 *
 * 原先没有设计 总接口 用抽象类代替
 * 结果使用 动态代理模式 必须有一个接口才可以运行 没办法了
 *
 * cglib 对抽象类 有点没问题 暂时还没搞懂
 *
 */
public interface GameChain {

    /**
     * 执行游戏
     * @return
     */
    boolean handler();

    /**
     * 添加责任
     * @return
     */
    void add(GameChain gameChain);

    /**
     * 错误停止
     */
    void errorStop();

    /**
     * 正常停止
     */
    void successStop();

    /**
     * 重制
     */
    void remake();

    /**
     * 获得游戏模型
     */
    BaseGameModel getGameModel()  throws IllegalAccessException, InstantiationException;

    /**
     * 设置游戏模型
     */
    GameChain setGameModel(Class<?> clazz);

    /**
     * 获得地图
     */
    GateMap getGateMap();
    /**
     * 设置地图
     */
    GameChain setGateMap(GateMap gateMap);

    // ----------------

    /**
     * 获得当前责任链编号
     * @return
     */
    int getSelfNum();

    /**
     * 设置当前责任链编号
     * @param selfNum
     * @return
     */
    GameChain setSelfNum(int selfNum);

    /**
     * 获得父责任链编号
     * @param
     * @return
     */
    int getParentNum();

    /**
     * 设置父责任链编号
     * @param parentNum
     * @return
     */
    GameChain setParentNum(int parentNum);

    /**
     * 获得失败尝试次数
     * @return
     */
    int getPauseCount();

    /**
     * 设置失败尝试次数
     * @param pauseCount
     * @return
     */
    GameChain setPauseCount(int pauseCount);

    int getBadTankCount();

    GameChain setBadTankCount(int num);

    String getText();

    GameChain setText(String text);

}
