package com.parker.tank.factory;

import com.parker.tank.TankFrame;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.factory
 * @Author: Parker
 * @CreateTime: 2020-08-14 00:34
 * @Description: Frame工厂
 */
public enum TankFrameFactory {

    INSTANCE;

    private TankFrame tankFrame = new TankFrame();

    public TankFrame getTankFrame(){
        return tankFrame;
    }

}
