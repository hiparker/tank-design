package com.parker.tank.observer.event;


import com.parker.tank.GameObject;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.observer.event
 * @Author: Parker
 * @CreateTime: 2020-08-15 12:23
 * @Description: Observer 观察者模式
 */
public interface GameEvent {

    /**
     * 源
     * @return
     */
    GameObject getSource();

}
