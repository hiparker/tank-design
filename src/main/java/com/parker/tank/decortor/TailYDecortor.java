package com.parker.tank.decortor;

import com.parker.tank.GameObject;

import java.awt.*;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.decortor
 * @Author: Parker
 * @CreateTime: 2020-08-14 20:50
 * @Description: 尾部装饰 白色
 */
public class TailYDecortor extends GODecortor {

    public TailYDecortor(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.setX(go.getX());
        this.setY(go.getY());
        go.paint(g);
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawLine(go.getX()+ go.getWidth(),go.getY() + go.getHeight(),go.getX() ,go.getY() );
        g.setColor(color);
    }
}