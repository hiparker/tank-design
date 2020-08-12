package com.parker.tank.factory.base;

import com.parker.tank.TankFrame;
import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.fire.TankFire;
import com.parker.tank.fire.TankFireDefault;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

/**
 * @BelongsProject: tank-02
 * @BelongsPackage: com.parker.tank.factory.base
 * @Author: Parker
 * @CreateTime: 2020-08-12 23:16
 * @Description: BaseTank 类
 *
 * 钩子函数 - 》 模版方法 （设计模式）
 *
 */
public abstract class BaseTank {

    /** 速度 */
    protected int speed = 5;
    /** 宽高 将静态宽高 改为动态，但是引用比较多 暂时还是 大写的*/
    public int TANK_WIDTH = 50, TANK_HEIGHT = 50;
    /** XY坐标 */
    protected int x , y;
    /** 坦克方向 */
    protected Dir dir = Dir.DOWN;
    /** 是否是移动的状态 */
    protected boolean moving = false;
    /** 画布 */
    protected TankFrame tankFrame;
    /** 存活状态 */
    protected boolean liveFlag = true;
    /** 当前位置 */
    protected Rectangle rectangle;
    /** 坦克分组 */
    protected TankGroup group;
    /** 开火策略模式 */
    protected TankFire tf = null;
    /** 自动模式 */
    protected boolean autoFlag = false;
    protected Dir[] dirs = {Dir.LEFT,Dir.UP,Dir.RIGHT,Dir.DOWN};
    protected BaseTank futureTank;

    /**
     * 坦克开火
     */
    public void fired(){
        throw new RuntimeException("坦克无开火处理");
    }

    /**
     * 坦克死亡
     */
    public void died(){
        throw new RuntimeException("坦克无死亡处理");
    }

    /**
     * 坦克移动处理
     */
    public void moveHandler(){
        throw new RuntimeException("坦克移动无实例化");
    }

    /**
     * 描绘
     * @param g 画笔
     */
    public void paint(Graphics g){
        throw new RuntimeException("坦克无描绘处理");
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

    // --------
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

    public void setFutureTank(BaseTank futureTank) {
        this.futureTank = futureTank;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BaseTank getFutureTank() {
        return futureTank;
    }

    public TankFrame getTankFrame() {
        return tankFrame;
    }
}
