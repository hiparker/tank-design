package com.parker.tank;

import com.parker.tank.config.ResourcesMgr;
import com.parker.tank.factory.GateFactory;
import com.parker.tank.factory.TankFrameFactory;

import java.awt.*;

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

    public Special(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
            if(TankFrameFactory.INSTANCE.getTankFrame().getBgm() != null){
                TankFrameFactory.INSTANCE.getTankFrame().getBgm().remove(this);
            }
        }

        g.drawImage(ResourcesMgr.special,this.x,this.y,this.width,this.height,null);
    }

    /**
     * 鸟巢死亡
     */
    public void died() {
        if(!this.liveFlag){
            return;
        }
        this.liveFlag = false;
        // 关卡错误结束
        GateFactory.INSTANCE.chainErrorGameOver();
    }

}
