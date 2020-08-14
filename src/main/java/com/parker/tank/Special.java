package com.parker.tank;

import com.parker.tank.chain.ChainStack;
import com.parker.tank.chain.GameChain;
import com.parker.tank.config.ResourcesMgr;
import com.parker.tank.dist.WallGroup;
import com.parker.tank.faced.BaseGameModel;

import java.awt.*;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 19:56
 * @Description: 墙
 */
public class Special extends GameObject{

    /** 存活状态 */
    private boolean liveFlag = true;
    /** 当前位置 */
    private Rectangle rectangle;
    /** 调停者 */
    private BaseGameModel gm;

    public Special(int x, int y, int width, int height, BaseGameModel gm) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gm = gm;
        // 设置碰撞检测位置
        this.rectangle = new Rectangle(x,y,width,height);
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

    @Override
    public void paint(Graphics g) {

        // 鸟巢死亡移除
        if(!this.liveFlag){
            gm.remove(this);
        }

        g.drawImage(ResourcesMgr.special,this.x,this.y,this.width,this.height,null);
    }

    /**
     * 鸟巢死亡
     */
    public void died() {
        this.liveFlag = false;

        this.gm.add(new StringG(100,100,"GAME OVER!"));

        new Thread(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 结束责任链
            GameChain gc = ChainStack.INSTANCE.peek();
            if(gc != null){
                gc.errorStop();
            }
        }).start();
    }

    public BaseGameModel getGm() {
        return gm;
    }
}
