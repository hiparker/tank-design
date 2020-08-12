package com.parker.tank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 15:46
 * @Description: 主战坦克
 */
public class Tank {

    /** 速度 */
    private final static int SPEED = 5;
    /** 宽高 将静态宽高 改为动态，但是引用比较多 暂时还是 大写的*/
    public int TANK_WIDTH = 50, TANK_HEIGHT = 50;

    /** XY坐标 */
    private int x , y;
    /** 坦克方向 */
    private Dir dir = Dir.DOWN;
    /** 是否是移动的状态 */
    private boolean moving = false;
    /** 画布 */
    private TankFrame tankFrame;
    /** 存活状态 */
    private boolean liveFlag = true;
    /** 当前位置 */
    private Rectangle rectangle;
    /** 坦克分组 */
    public TankGroup group;
    /** 自动模式 */
    private boolean autoFlag = false;
    private Dir[] dirs = {Dir.LEFT,Dir.UP,Dir.RIGHT,Dir.DOWN};
    private Tank futureTank;

    private Random random = new Random();

    private Audio moveAudio = new Audio("static/audio/tank_move.wav");

    /** 获得坦克图片 */
    private Map<String,BufferedImage> tankImagesMap = new HashMap<>();

    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Tank(int x, int y, Dir dir,TankFrame tankFrame,TankGroup group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;

        // 设置碰撞检测位置
        rectangle = new Rectangle(this.x,this.y,TANK_WIDTH,TANK_HEIGHT);
        // 获得坦克图片
        this.getTankImage(this.dir,this);
        // 赋值坦克宽高
        this.TANK_WIDTH = tankImagesMap.get("tankU").getWidth();
        this.TANK_HEIGHT = tankImagesMap.get("tankU").getHeight();
    }

    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Tank(int x, int y, Dir dir,TankFrame tankFrame,TankGroup group,boolean autoFlag) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.autoFlag = autoFlag;

        // 设置碰撞检测位置
        rectangle = new Rectangle(this.x,this.y,TANK_WIDTH,TANK_HEIGHT);
        // 获得坦克图片
        this.getTankImage(this.dir,this);

        // 赋值坦克宽高
        this.TANK_WIDTH = tankImagesMap.get("tankU").getWidth();
        this.TANK_HEIGHT = tankImagesMap.get("tankU").getHeight();
    }

    /**
     * 获得当前位置（用于碰撞检测）
     * @return
     */
    public Rectangle getPosition(){
        rectangle.x = this.x;
        rectangle.y = this.y;
        return rectangle;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void setFutureTank(Tank futureTank) {
        this.futureTank = futureTank;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * 坦克方向处理
     */
    public void moveHandler(){
        if(!moving){
            return;
        }

        int xT = x;
        int yT = y;

        switch (dir) {
            case LEFT:
                xT -= SPEED;
                break;
            case UP:
                yT -= SPEED;
                break;
            case RIGHT:
                xT += SPEED;
                break;
            case DOWN:
                yT += SPEED;
                break;
        }

        // 边缘处理
        if(xT < 0 || yT < TANK_HEIGHT/2|| xT > TankFrame.GAME_WIDTH-TANK_WIDTH || yT > TankFrame.GAME_HEIGHT-TANK_HEIGHT){
            return;
        }

        x = xT;
        y = yT;
    }

    public void paint(Graphics g) {
        // 坦克阵亡
        if(!liveFlag){
            tankFrame.enemyTanks.remove(this);
            return;
        }

        switch (dir) {
            case LEFT:
                g.drawImage(tankImagesMap.get("tankL"),x,y,null);
                break;
            case UP:
                g.drawImage(tankImagesMap.get("tankU"),x,y,null);
                break;
            case RIGHT:
                g.drawImage(tankImagesMap.get("tankR"),x,y,null);
                break;
            case DOWN:
                g.drawImage(tankImagesMap.get("tankD"),x,y,null);
                break;
        }

        // 坦克自动行走
        this.moveHandler();

        // 设置坦克随机开炮 与 行走
        if(this.autoFlag){

            this.setMoving(true);

            // 随机开炮 几率暂定 5%
            int randomBullet = random.nextInt(100);
            if(randomBullet > 95){
                this.fired();
            }

            // 随机坦克方向 几率暂定 4%
            int randomTankDire = random.nextInt(100);
            if(randomTankDire > 96){
                int po = random.nextInt(4);
                this.setDir(dirs[po]);
            }

        }
    }

    private void getTankImage(Dir dirFlag,Tank tank){
        if(TankGroup.BLUE.equals(tank.group)){
            tankImagesMap.put("tankL",ResourcesMgr.badTankL);
            tankImagesMap.put("tankU",ResourcesMgr.badTankU);
            tankImagesMap.put("tankR",ResourcesMgr.badTankR);
            tankImagesMap.put("tankD",ResourcesMgr.badTankD);
        }else if(TankGroup.RED.equals(tank.group)){
            tankImagesMap.put("tankL",ResourcesMgr.goodTankL);
            tankImagesMap.put("tankU",ResourcesMgr.goodTankU);
            tankImagesMap.put("tankR",ResourcesMgr.goodTankR);
            tankImagesMap.put("tankD",ResourcesMgr.goodTankD);
        }else{
            tankImagesMap.put("tankL",ResourcesMgr.defaultTankL);
            tankImagesMap.put("tankU",ResourcesMgr.defaultTankU);
            tankImagesMap.put("tankR",ResourcesMgr.defaultTankR);
            tankImagesMap.put("tankD",ResourcesMgr.defaultTankD);
        }
    }

    /**
     * 开火
     */
    public void fired() {
        tankFrame.bulletList.add(new Bullet(this.x,this.y,this.dir,this.tankFrame,this));

        // 开火音效
        new Thread(()->{
            new Audio("static/audio/tank_fire.wav").play();
        }).start();
    }

    public void died() {
        this.liveFlag = false;
        // 坦克阵亡新建爆炸
        tankFrame.explodeList.add(new Explode(this.x,this.y,tankFrame));
    }
}
