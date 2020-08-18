package com.parker.tank.systemevent.state;


import com.parker.tank.memento.SaveData;

import java.awt.event.KeyEvent;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.systemevent.state
 * @Author: Parker
 * @CreateTime: 2020-08-18 15:16
 * @Description: 键盘回档序事件
 */
public class SystemLoadEvent implements SystemEventState {

    private int myState = KeyEvent.VK_L;

    @Override
    public void handler(boolean flag, KeyEvent e) {
        // 按键抬起时
        if(!flag){
            // 回档
            SaveData.INSTANCE.load();
        }
    }

    @Override
    public int getState() {
        return this.myState;
    }

}
