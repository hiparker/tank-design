package com.parker.tank.systemevent.state;


import com.parker.tank.memento.SaveData;

import java.awt.event.KeyEvent;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.systemevent.state
 * @Author: Parker
 * @CreateTime: 2020-08-18 15:16
 * @Description: 键盘存盘事件
 */
public class SystemSaveEvent implements SystemEventState {

    private int myState = KeyEvent.VK_S;

    @Override
    public void handler(boolean flag, KeyEvent e) {
        // 按键抬起时
        if(!flag){
            // 存盘
            SaveData.INSTANCE.save();
        }
    }

    @Override
    public int getState() {
        return this.myState;
    }

}
