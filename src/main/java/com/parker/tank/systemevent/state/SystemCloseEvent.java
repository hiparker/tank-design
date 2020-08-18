package com.parker.tank.systemevent.state;


import java.awt.event.KeyEvent;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.systemevent.state
 * @Author: Parker
 * @CreateTime: 2020-08-18 15:16
 * @Description: 键盘关闭程序事件
 */
public class SystemCloseEvent implements SystemEventState {

    private int myState = KeyEvent.VK_ESCAPE;

    @Override
    public void handler(boolean flag, KeyEvent e) {
        // 按键抬起时
        if(!flag){
            // 关闭程序
            System.exit(0);
        }
    }

    @Override
    public int getState() {
        return this.myState;
    }

}
