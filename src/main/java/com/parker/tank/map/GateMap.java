package com.parker.tank.map;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.map
 * @Author: Parker
 * @CreateTime: 2020-08-14 17:05
 * @Description: 地图构建器
 *
 * builder 构建器设计模式
 *
 */
public interface GateMap {

    /**
     * 构建墙
     * @return
     */
    GateMap builderWall();

    /**
     * 构建鸟巢
     * @return
     */
    GateMap builderSpecial();

    /**
     * 构建地雷
     * @return
     */
    GateMap builderMine();

    /**
     * 开始构建
     * @return
     */
    GateMap builder(int badCount);

}
