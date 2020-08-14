package com.parker.tank.decortor;

import com.parker.tank.GameObject;

import java.awt.*;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.decortor
 * @Author: Parker
 * @CreateTime: 2020-08-14 20:50
 * @Description: 尾部装饰器
 */
public abstract class GODecortor extends GameObject {

    GameObject go;

    public GODecortor(GameObject go){
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }
}
