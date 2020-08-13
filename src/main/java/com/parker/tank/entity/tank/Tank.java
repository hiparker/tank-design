package com.parker.tank.entity.tank;

import com.parker.tank.TankFrame;
import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.factory.base.BaseExplode;
import com.parker.tank.factory.base.BaseTank;
import com.parker.tank.util.TankImageUtil;

import java.awt.*;
import java.util.Random;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 15:46
 * @Description: 主战坦克
 */
public class Tank extends BaseTank{

    /**
     * 随机数
     */
    private static final Random random = new Random();


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
     * 坦克方向处理
     */
    @Override
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

    @Override
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
    @Override
    public void fired() {
        if(tf != null){
            tf.fire(this);
        }
    }

    /**
     * 死亡
     */
    @Override
    public void died() {
        this.liveFlag = false;
        // 坦克阵亡新建爆炸
        BaseExplode explode = tankFrame.getGf().createExplode(this.x, this.y, tankFrame);
        tankFrame.addExplode(explode);
    }
}
