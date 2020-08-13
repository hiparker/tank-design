package com.parker.tank;

import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.fire.TankFire;
import com.parker.tank.fire.TankFireDefault;
import com.parker.tank.util.TankImageUtil;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 15:46
 * @Description: 主战坦克
 */
public class Tank{

    /** 随机数 */
    private static final Random random = new Random();

    /** 宽高 将静态宽高 改为动态，但是引用比较多 暂时还是 大写的*/
    public int TANK_WIDTH = 50, TANK_HEIGHT = 50;

    /** 速度 */
    private int speed = 5;
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
    private TankGroup group;
    /** 开火策略模式 */
    private TankFire tf = null;
    /** 自动模式 */
    private boolean autoFlag = false;
    private Dir[] dirs = {Dir.LEFT,Dir.UP,Dir.RIGHT,Dir.DOWN};
    private Tank futureTank;




    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Tank(int x, int y, Dir dir, TankFrame tankFrame, TankGroup group) {

        // 初始化
        this.init();

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;

        // 设置碰撞检测位置
        rectangle = new Rectangle(this.x,this.y,TANK_WIDTH,TANK_HEIGHT);

        // 赋值坦克宽高
        this.TANK_WIDTH = TankImageUtil.getTankImage(this.group,Dir.UP).getWidth();
        this.TANK_HEIGHT = TankImageUtil.getTankImage(this.group,Dir.UP).getHeight();

        // 初始化开火的策略模式
        this.initFireStrategy();
    }

    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     */
    public Tank(int x, int y, Dir dir, TankFrame tankFrame, TankGroup group, boolean autoFlag) {

        // 初始化
        this.init();

        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        this.autoFlag = autoFlag;

        // 设置碰撞检测位置
        rectangle = new Rectangle(this.x,this.y,TANK_WIDTH,TANK_HEIGHT);

        // 赋值坦克宽高
        this.TANK_WIDTH = TankImageUtil.getTankImage(this.group,Dir.UP).getWidth();
        this.TANK_HEIGHT = TankImageUtil.getTankImage(this.group,Dir.UP).getHeight();

        // 初始化开火的策略模式
        this.initFireStrategy();
    }

    /**
     * 初始化
     */
    public void init(){
        // 速度
        if(PropertiesMgr.getByInteger("tankSpeed") != null){
            speed = PropertiesMgr.getByInteger("tankSpeed");
        }
    }

    /**
     * 初始化开火的策略模式
     */
    protected void initFireStrategy(){
        // 策略模式 不同的开火方案
        if(TankGroup.BLUE.equals(this.group)){
            String fireName = PropertiesMgr.getByString("blueFire");
            try {
                tf = (TankFire) Class.forName(fireName).getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | InstantiationException |
                    IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                //System.out.println("找不到 blueFire 开火策略");
            }
        }else if(TankGroup.RED.equals(this.group)){
            String fireName = PropertiesMgr.getByString("redFire");
            try {
                tf = (TankFire) Class.forName(fireName).getDeclaredConstructor().newInstance();
            } catch (ClassNotFoundException | InstantiationException |
                    IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                //System.out.println("找不到 redFire 开火策略");
            }
        }
        // 如果策略为空 则使用默认策略
        if(tf == null){
            tf = TankFireDefault.INSTANCE;
        }
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
                xT -= this.speed;
                break;
            case UP:
                yT -= this.speed;
                break;
            case RIGHT:
                xT += this.speed;
                break;
            case DOWN:
                yT += this.speed;
                break;
        }

        // 边缘处理
        if(xT < 0 || yT < TANK_HEIGHT/2|| xT > TankFrame.GAME_WIDTH-TANK_WIDTH || yT > TankFrame.GAME_HEIGHT-TANK_HEIGHT){
            return;
        }

        x = xT;
        y = yT;
    }

    /**
     * 描绘
     * @param g 画笔
     */
    public void paint(Graphics g) {
        // 坦克阵亡
        if(!liveFlag){
            tankFrame.removeBadTank(this);
            return;
        }

        // 获得坦克当前图片
        g.drawImage(TankImageUtil.getTankImage(this.group,this.dir),x,y,null);

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


    /**
     * 开火
     */
    public void fired() {
        if(tf != null){
            tf.fire(this);
        }
    }

    /**
     * 死亡
     */
    public void died() {
        this.liveFlag = false;
        // 坦克阵亡新建爆炸
        Explode explode = new Explode(this.x, this.y, tankFrame);
        tankFrame.addExplode(explode);
    }

    // -----------------------------------------------

    public TankGroup getGroup() {
        return group;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Dir getDir() {
        return dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank getFutureTank() {
        return futureTank;
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

    public TankFrame getTankFrame() {
        return tankFrame;
    }
}
