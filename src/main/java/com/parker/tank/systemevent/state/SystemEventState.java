package com.parker.tank.systemevent.state;

import java.awt.event.KeyEvent;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.systemevent.state
 * @Author: Parker
 * @CreateTime: 2020-08-18 15:14
 * @Description: 根据状态操作具体执行
 *
 * 伪 State模式
 *
 */
public interface SystemEventState {

    /**
     * 执行
     */
    void handler(boolean flag, KeyEvent e);

    int getState();

}
