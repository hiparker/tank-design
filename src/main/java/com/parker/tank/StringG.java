package com.parker.tank;

import com.sun.istack.internal.NotNull;

import java.awt.*;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 19:56
 * @Description: 字符串
 */
public class StringG extends GameObject{

    private String str;
    private Color color;

    public StringG(int x, int y, String str) {
        this.x = x;
        this.y = y;
        this.str = str;
    }

    public StringG(int x, int y, String str,@NotNull Color color) {
        this.x = x;
        this.y = y;
        this.str = str;
        this.color = color;
    }


    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        if(color != null){
            g.setColor(color);
        }else{
            g.setColor(Color.WHITE);
        }
        g.drawString(this.str,this.x,this.y);
        g.setColor(c);
    }
}
