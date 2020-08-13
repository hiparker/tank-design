package com.parker.tank.collide.chain;

import com.parker.tank.GameObject;
import com.parker.tank.collide.BulletAndTankCollide;
import com.parker.tank.collide.Collide;
import com.parker.tank.collide.TankAndTankCollide;

import java.util.LinkedList;
import java.util.List;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.collide.chain
 * @Author: Parker
 * @CreateTime: 2020-08-13 19:24
 * @Description: 责任链 碰撞检测
 */
public enum  CollideChain implements Collide {

    /** 单例 实例*/
    INSTANCE;

    private final List<Collide> collides = new LinkedList<>();

    CollideChain(){
        this.add(BulletAndTankCollide.INSTANCE);
        this.add(TankAndTankCollide.INSTANCE);
    }

    public void add(Collide collide){
        this.collides.add(collide);
    }

    @Override
    public void comparator(GameObject go1, GameObject go2) {
        for (Collide collide : collides) {
            collide.comparator(go1,go2);
        }
    }
}
