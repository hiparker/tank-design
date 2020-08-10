package com.parker.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 01:14
 * @Description: 坦克 认识 Frame
 */
public class TankFrame extends Frame{

    private static final String TITLE = "坦克大战 v1.0.0";
    /** 游戏画布宽高 */
    public static final int GAME_WIDTH = 800,GAME_HEIGHT = 600;

    /** 子弹集合 */
    List<Bullet> bulletList = new ArrayList<Bullet>();

    /** 我方主战坦克 */
    Tank myTank = new Tank(200,400,Dir.DOWN,this);

    /** 地方坦克 */
    List<Tank> enemyTanks = new ArrayList<Tank>();

    public TankFrame(){
        // 可见
        this.setVisible(true);
        // 设置窗口大小
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
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

    Image offScreenImage = null;
    // 双通道缓存解决闪烁问题
    @Override
    public void update(Graphics g) {
        if(offScreenImage == null){
            offScreenImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage,0,0,null);
    }

    @Override
    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+bulletList.size(),10,40);
        g.drawString("敌人的数量："+enemyTanks.size(),10,60);
        g.setColor(c);

        // 坦克自动行走
        myTank.paint(g);

        // 敌方坦克渲染
        for (int i = 0; i < enemyTanks.size(); i++) {
            enemyTanks.get(i).paint(g);
        }

        // 子弹自动行走
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }

        // 子弹与坦克碰撞
        for (int i = 0; i < bulletList.size(); i++) {
            for (int tk = 0; tk < enemyTanks.size(); tk++) {
                bulletList.get(i).collideWith(enemyTanks.get(tk));
            } 
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
                case KeyEvent.VK_SPACE:
                    // 按键抬起时
                    if(!flag){
                        myTank.fired();
                    }
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
            if(!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
            }else{
                if(bL) myTank.setDir(Dir.LEFT);
                if(bU) myTank.setDir(Dir.UP);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bD) myTank.setDir(Dir.DOWN);

                myTank.setMoving(true);
            }
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
