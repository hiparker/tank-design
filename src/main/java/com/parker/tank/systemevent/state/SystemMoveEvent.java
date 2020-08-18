package com.parker.tank.systemevent.state;

import com.parker.tank.factory.TankFrameFactory;
import com.parker.tank.observer.factory.TankObserverFactory;

import java.awt.event.KeyEvent;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.systemevent.state
 * @Author: Parker
 * @CreateTime: 2020-08-18 15:16
 * @Description: 键盘移动事件
 */
public class SystemMoveEvent implements SystemEventState {

    private int myState = KeyEvent.VK_UP;

    private boolean bL = false;
    private boolean bU = false;
    private boolean bR = false;
    private boolean bD = false;

    @Override
    public void handler(boolean flag, KeyEvent e) {
        this.setMainTankDir(flag,e);
    }

    /**
     * 设置主战坦克方向
     */
    private void setMainTankDir(boolean flag, KeyEvent e) {
        // 键盘按下时 操作
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                bL = flag;
                break;
            case KeyEvent.VK_UP:
                bU = flag;
                break;
            case KeyEvent.VK_RIGHT:
                bR = flag;
                break;
            case KeyEvent.VK_DOWN:
                bD = flag;
                break;
        }

        TankObserverFactory.INSTANCE.mainTankMoveHandler(
                TankFrameFactory.INSTANCE.getTankFrame().getBgm().getMainTank(),
                bL,bU,bR,bD);
    }

    @Override
    public int getState() {
        return this.myState;
    }
}
