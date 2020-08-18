package com.parker.tank.systemevent;

import com.parker.tank.systemevent.state.SystemEventState;
import com.parker.tank.util.PackageUtil;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.event
 * @Author: Parker
 * @CreateTime: 2020-08-14 01:21
 * @Description: 窗口键盘监听事件
 */
public class SystemEvent extends KeyAdapter {

    private static final Map<Integer, SystemEventState> STATE_MAP = new HashMap<>();

    static {
        // 拿到state包下 实现了 SystemEventState 接口的,所有子类
        Set<Class<?>> clazzSet = PackageUtil.listSubClazz(SystemEventState.class.getPackage().getName(),
                true,
                SystemEventState.class
        );

        for (Class<?> aClass : clazzSet) {
            // 位运算 去除抽象类
            if((aClass.getModifiers() & Modifier.ABSTRACT) != 0){
                continue;
            }

            // 通过反射 加载所有的 键盘处理类
            try {
                SystemEventState handler = (SystemEventState) aClass.newInstance();
                int state = handler.getState();
                STATE_MAP.put(state,handler);

            } catch (Exception e) {
                System.out.println("反射失败");
            }
            
        }
    }

    /**
     * 键盘按键事件处理
     * @param flag
     * @param e
     */
    public void KeyEventHandler(boolean flag, KeyEvent e){

        // 按下方向键
        if(KeyEvent.VK_LEFT == e.getKeyCode() || KeyEvent.VK_UP == e.getKeyCode() ||
                KeyEvent.VK_RIGHT == e.getKeyCode() || KeyEvent.VK_DOWN == e.getKeyCode() ){
            STATE_MAP.get(KeyEvent.VK_UP).handler(flag,e);
            return;
        }

        // 其他操作
        SystemEventState systemEventState = STATE_MAP.get(e.getKeyCode());
        if(systemEventState != null){
            STATE_MAP.get(e.getKeyCode()).handler(flag,e);
        }
    }



    @Override
    public void keyPressed(KeyEvent e) {
        // 键盘按下时 操作
        this.KeyEventHandler(true,e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 键盘按下时 操作
        this.KeyEventHandler(false,e);
    }
}
