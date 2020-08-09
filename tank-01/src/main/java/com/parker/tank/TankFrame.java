package com.parker.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 01:14
 * @Description: 坦克 认识 Frame
 */
public class TankFrame extends Frame{

    private static final String TITLE = "坦克大战 v1.0.0";

    public TankFrame(){
        // 可见
        this.setVisible(true);
        // 设置窗口大小
        this.setSize(800,600);
        // 禁止改变大小
        this.setResizable(false);
        // 设置标题
        this.setTitle(TITLE);

        // window 监听器
        this.addWindowListener(new WindowAdapter() {

            // 监听 关闭事件 关闭当前java程序
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(200,200,50,50);
    }
}
