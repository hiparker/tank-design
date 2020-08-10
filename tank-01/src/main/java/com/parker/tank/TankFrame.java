package com.parker.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
    private int x = 200;
    private int y = 200;
    private final static int SPEED = 10;
    private Dir dir = Dir.RIGHT;

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

        // window 按键监听
        this.addKeyListener(new MyKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        g.fillRect(x,y,50,50);

        // 坦克自动行走
        this.tankDirectionHandler();
    }

    /**
     * 坦克方向处理
     */
    public void tankDirectionHandler(){
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
    }

    /**
     * 内部类 窗口键盘监听事件
     */
    class MyKeyListener extends KeyAdapter {

        private boolean bL = false;
        private boolean bU = false;
        private boolean bR = false;
        private boolean bD = false;

        /**
         * 键盘按键事件处理
         * @param flag
         * @param e
         */
        public void KeyEventHandler(boolean flag,KeyEvent e){
            // 键盘按下时 操作
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    bL = flag;
                    break;
                case KeyEvent.VK_UP:
                    bU = flag;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = flag;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = flag;
                    break;
                default:
                    break;
            }

            // 处理坦克方向
            setMainTankDir();

        }

        /**
         * 设置主战坦克方向
         */
        private void setMainTankDir() {
            // 左
            if(bL) dir = Dir.LEFT;
            // 上
            if(bU) dir = Dir.UP;
            // 右
            if(bR) dir = Dir.RIGHT;
            // 下
            if(bD) dir = Dir.DOWN;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            // 键盘按下时 操作
            this.KeyEventHandler(true,e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            // 键盘按下时 操作
            this.KeyEventHandler(false,e);
        }
    }


}
