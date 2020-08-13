package com.parker.tank;

import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.dist.Dir;
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

    private static  String TITLE = "坦克大战 v2.0.0";

    /** 游戏画布宽高 */
    public static int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    /** 调停者 */
    private GameModel gm = new GameModel();



    public TankFrame(){

        this.init();

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

        // 背景音乐
        new Thread(()->{
            new Audio("static/audio/war1.wav").loop();
        }).start();

    }

    /**
     * 初始化
     */
    private void init(){
        TITLE = PropertiesMgr.getByString("title")+" - "+PropertiesMgr.getByString("version");
        if(PropertiesMgr.getByInteger("gameHeight") != null){
            GAME_HEIGHT = PropertiesMgr.getByInteger("gameHeight");
        }
        if(PropertiesMgr.getByInteger("gameWidth") != null){
            GAME_WIDTH = PropertiesMgr.getByInteger("gameWidth");
        }
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

        // 交给调停者去处理
        gm.paint(g);
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
                    // 处理坦克方向
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    bU = flag;
                    // 处理坦克方向
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = flag;
                    // 处理坦克方向
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    bD = flag;
                    // 处理坦克方向
                    setMainTankDir();
                    break;
                case KeyEvent.VK_SPACE:
                    // 按键抬起时
                    if(!flag){
                        gm.getMainTank().fired();
                    }
                    break;
                case KeyEvent.VK_ESCAPE:
                    // 关闭程序
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }

        /**
         * 设置主战坦克方向
         */
        private void setMainTankDir() {
            if(!bL && !bU && !bR && !bD) {
                gm.getMainTank().setMoving(false);
            }else{
                if(bL) gm.getMainTank().setDir(Dir.LEFT);
                if(bU) gm.getMainTank().setDir(Dir.UP);
                if(bR) gm.getMainTank().setDir(Dir.RIGHT);
                if(bD) gm.getMainTank().setDir(Dir.DOWN);

                // 移动音乐
                new Thread(()->{
                    new Audio("static/audio/tank_move.wav").play();
                }).start();

                gm.getMainTank().setMoving(true);
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

    // -------------------------------------------------------------------

    public GameModel getGm() {
        return gm;
    }


}
