package com.parker.tank.observer;

import com.parker.tank.Tank;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.observer
 * @Author: Parker
 * @CreateTime: 2020-08-15 00:48
 * @Description: 坦克事件接口
 *
 * Observer 观察者模式
 *
 */
public interface TankEvent {

    Tank getSource();

}
