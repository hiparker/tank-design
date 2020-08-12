package com.parker.tank;

import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.factory.GameFactory;
import com.parker.tank.factory.base.BaseBullet;
import com.parker.tank.factory.base.BaseExplode;
import com.parker.tank.factory.base.BaseTank;
import com.parker.tank.factory.child.DefaultFactory;
import com.parker.tank.util.TankUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;
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

    private static  String TITLE = "坦克大战 v2.0.0";

    /** 游戏工厂 */
    private static GameFactory gf = null;

    /** 游戏画布宽高 */
    public static int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    /** 子弹集合 */
    private final List<BaseBullet> bulletList = new ArrayList<>();

    /** 爆炸集合 */
    private final List<BaseExplode> explodeList = new ArrayList<>();

    /** 我方主战坦克 */
    private final BaseTank myTank = gf.createTank(200,400, Dir.DOWN,this, TankGroup.RED);

    /** 敌方坦克 */
    private final List<BaseTank> badTanks = new ArrayList<>();

    /** 初始化 游戏工厂 */
    static{
        try {
            gf = (GameFactory) Class.forName(PropertiesMgr.getByString("tankFactory")).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | InstantiationException |
                    IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            //System.out.println("找不到 游戏工厂策略");
        }
        if(gf == null){
            gf = new DefaultFactory();
        }
    }


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

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+bulletList.size(),10,40);
        g.drawString("敌人的数量："+badTanks.size(),10,60);
        g.drawString("爆炸的数量："+explodeList.size(),10,80);
        g.setColor(c);

        // 坦克自动行走
        myTank.paint(g);

        // 敌方坦克渲染
        for (int i = 0; i < badTanks.size(); i++) {
            badTanks.get(i).paint(g);
        }

        // 子弹自动行走
        for (int i = 0; i < bulletList.size(); i++) {
            bulletList.get(i).paint(g);
        }
        
        // 坦克爆炸
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
        }

        // 子弹与坦克碰撞
        for (int i = 0; i < bulletList.size(); i++) {
            for (int tk = 0; tk < badTanks.size(); tk++) {
                TankUtil.collideWith(badTanks.get(tk),bulletList.get(i));
            }
            TankUtil.collideWith(myTank,bulletList.get(i));
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
                        myTank.fired();
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
                myTank.setMoving(false);
            }else{
                if(bL) myTank.setDir(Dir.LEFT);
                if(bU) myTank.setDir(Dir.UP);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bD) myTank.setDir(Dir.DOWN);

                // 移动音乐
                new Thread(()->{
                    new Audio("static/audio/tank_move.wav").play();
                }).start();

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

    // -------------------------------------------------------------------

    /**
     * 获得游戏工厂
     * @return
     */
    public GameFactory getGf() {
        return gf;
    }

    /**
     * 添加爆炸
     * @param be
     */
    public void addExplode(BaseExplode be){
        this.explodeList.add(be);
    }
    /**
     * 删除爆炸
     * @param be
     */
    public void removeExplode(BaseExplode be){
        this.explodeList.remove(be);
    }
    /**
     * 添加炮弹
     * @param bb
     */
    public void addBullet(BaseBullet bb){
        this.bulletList.add(bb);
    }
    /**
     * 删除炮弹
     * @param be
     */
    public void removeBullet(BaseBullet be){
        this.bulletList.remove(be);
    }
    /**
     * 添加坦克
     * @param bt
     */
    public void addBadTank(BaseTank bt){
        this.badTanks.add(bt);
    }
    /**
     * 删除坦克
     * @param bt
     */
    public void removeBadTank(BaseTank bt){
        this.badTanks.remove(bt);
    }
}
