package com.parker.tank;

import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.event.SystemEvent;
import com.parker.tank.faced.BaseGameModel;

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

    private static  String TITLE = "TankWar - Game v2.0.0";

    /** 游戏画布宽高 */
    public static int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    /** 调停者 */
    private volatile BaseGameModel bgm;


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
        this.addKeyListener(new SystemEvent());
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
        if(bgm != null){
            // 交给调停者去处理
            bgm.paint(g);
        }
    }


    // -------------------------------------------------------------------


    public BaseGameModel getBgm() {
        return bgm;
    }

    public void setBgm(BaseGameModel bgm) {
        if(this.bgm != null){
            this.bgm.stopModel();
        }
        this.bgm = bgm;
    }
}
