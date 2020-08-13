package com.parker.tank.fire;

import com.parker.tank.Tank;

/**
 * @BelongsProject: tank-02
 * @BelongsPackage: com.parker.tank.fire
 * @Author: Parker
 * @CreateTime: 2020-08-12 16:47
 * @Description: 坦克开火
 *
 * 策略模式
 *
 */
public interface TankFire {

    /**
     * 坦克开火
     *  策略模式 可以指定多种开火方式
     */
    void fire(Tank tank);

}
