package com.parker.tank;

import com.parker.tank.net.Client;
import com.parker.tank.net.msg.TankIdMsg;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.MsgType;
import com.parker.tank.net.msg.TankMoveMsg;
import com.parker.tank.util.TankUtil;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.*;
import java.util.List;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 01:14
 * @Description: 坦克 认识 Frame
 */
public final class TankFrame extends Frame{

    public static final TankFrame INSTANCE = new TankFrame();

    private static final String TITLE = "坦克大战 v1.0.0";

    private Random r = new Random();

    /** 游戏画布宽高 */
    public static final int GAME_WIDTH = 900,GAME_HEIGHT = 700;

    Map<UUID,Tank> tanks = new HashMap<>();

    /** 子弹集合 */
    Map<UUID,Bullet> bullets = new HashMap<>();

    /** 爆炸集合 */
    List<Explode> explodeList = new ArrayList<>();

    /** 我方主战坦克 */
    Tank myTank = TankFactory.createTank(
            r.nextInt(GAME_WIDTH-100)+50,r.nextInt(GAME_HEIGHT-100)+50,
            Dir.values()[r.nextInt(Dir.values().length)],
            this,
            TankGroup.RED, UUID.randomUUID());


    private TankFrame(){}

    /**
     * 初始化
     */
    public TankFrame init(){
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

        // 将自身加入 坦克队列
        this.addTank(myTank);
        return this;
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

    public Tank getMyTank() {
        return myTank;
    }

    public void setMyTank(Tank myTank) {
        this.myTank = myTank;
    }


    /** 坦克集合 **/
    public void addTank(Tank t){
        this.tanks.put(t.getId(),t);
    }

    public boolean hasTank(UUID id){
        if(this.tanks.get(id) != null){
            return true;
        }
        return false;
    }

    public Tank getTank(UUID id) {
        return tanks.get(id);
    }

    public void removeTank(UUID id) {
        tanks.remove(id);
    }

    /** 炮弹集合 **/
    public void addBullet(Bullet b){
        this.bullets.put(b.getId(),b);
    }

    public boolean hasBullet(UUID id){
        if(this.bullets.get(id) != null){
            return true;
        }
        return false;
    }

    public Bullet getBullet(UUID id) {
        return this.bullets.get(id);
    }

    public void removeBullet(UUID id) {
        this.bullets.remove(id);
    }




    @Override
    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量："+bullets.size(),10,40);
        g.drawString("敌人的数量："+(tanks.size()-1),10,60);
        g.drawString("爆炸的数量："+explodeList.size(),10,80);
        g.setColor(c);

        // 坦克渲染
        //tanks.values().stream().forEach((e) -> e.paint(g));
        Set<UUID> tankIds = tanks.keySet();
        for (UUID tankId : tankIds) {
            Tank tank = tanks.get(tankId);
            if(tank != null) tank.paint(g);
        }

        // 炮弹渲染
        //bullets.values().stream().forEach((e) -> e.paint(g));
        Set<UUID> bulletIds = bullets.keySet();
        for (UUID bulletId : bulletIds) {
            Bullet bullet = bullets.get(bulletId);
            if(bullet != null) bullet.paint(g);
        }

        // 坦克爆炸
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
        }

        // 子弹与坦克碰撞
        for (UUID bulletId : bulletIds) {
            for (UUID tankId : tankIds) {
                TankUtil.collideWith(tanks.get(tankId),bullets.get(bulletId));
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
                        if(myTank == null){
                            break;
                        }
                        // 发送开火通知到服务器
                        Client.INSTANCE.send(new TankIdMsg(myTank.getId()), MsgType.TANK_FIRE);
                        //myTank.fired();
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
            if(myTank == null){
                return;
            }
            boolean moving = false;
            Dir dir = null;
            if(bL || bU || bR || bD){
                if(bL) dir = Dir.LEFT;
                if(bU) dir = Dir.UP;
                if(bR) dir = Dir.RIGHT;
                if(bD) dir = Dir.DOWN;

                moving = true;
                //myTank.setMoving(true);
            }

            // 只有不同状态才发 moving事件 这样减少客户端与服务端数据传输
            if(moving != myTank.isMoving() || !myTank.getDir().equals(dir)){
                // 发送移动通知到服务器
                Client.INSTANCE.send(new TankMoveMsg(myTank.getId(),dir,moving),MsgType.TANK_MOVE);
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
