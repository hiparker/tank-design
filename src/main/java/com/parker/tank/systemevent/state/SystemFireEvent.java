package com.parker.tank.systemevent.state;

import com.parker.tank.factory.TankFrameFactory;
import com.parker.tank.observer.factory.TankObserverFactory;

import java.awt.event.KeyEvent;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.systemevent.state
 * @Author: Parker
 * @CreateTime: 2020-08-18 15:16
 * @Description: 键盘开火事件
 */
public class SystemFireEvent implements SystemEventState {

    private int myState = KeyEvent.VK_SPACE;

    @Override
    public void handler(boolean flag, KeyEvent e) {
        // 按键抬起时
        if(!flag){
            TankObserverFactory.INSTANCE.mainTankFireHandler(
                    TankFrameFactory.INSTANCE.getTankFrame().getBgm().getMainTank());
        }

    }
    @Override
    public int getState() {
        return this.myState;
    }

}
