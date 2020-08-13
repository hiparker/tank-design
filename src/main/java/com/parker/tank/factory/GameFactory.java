package com.parker.tank.factory;

import com.parker.tank.TankFrame;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.factory
 * @Author: Parker
 * @CreateTime: 2020-08-13 21:54
 * @Description: 抽象工厂
 */
public abstract class GameFactory {

    /**
     * 创建封面
     */
    public abstract void createCover(TankFrame tankFrame);

    /**
     * 创建游戏
     */
    public abstract void createGame(TankFrame tankFrame);

}
