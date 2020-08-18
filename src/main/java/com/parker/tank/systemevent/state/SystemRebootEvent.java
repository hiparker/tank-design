package com.parker.tank.systemevent.state;


import com.parker.tank.memento.SaveData;

import java.awt.event.KeyEvent;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.systemevent.state
 * @Author: Parker
 * @CreateTime: 2020-08-18 15:16
 * @Description: 键盘重玩事件
 */
public class SystemRebootEvent implements SystemEventState {

    private int myState = KeyEvent.VK_R;

    @Override
    public void handler(boolean flag, KeyEvent e) {
        // 按键抬起时
        if(!flag){
            // 重玩
            SaveData.INSTANCE.reboot();
        }
    }

    @Override
    public int getState() {
        return this.myState;
    }

}
