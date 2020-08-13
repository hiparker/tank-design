package com.parker.tank.collide;

import com.parker.tank.GameObject;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.collide
 * @Author: Parker
 * @CreateTime: 2020-08-13 16:52
 * @Description: 碰撞检测
 */
public interface Collide {

    void comparator(GameObject go1,GameObject go2);

}
