package com.parker.tank.event;

import com.parker.tank.util.AudioUtil;
import com.parker.tank.dist.Dir;
import com.parker.tank.factory.TankFrameFactory;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.event
 * @Author: Parker
 * @CreateTime: 2020-08-14 01:21
 * @Description: 窗口键盘监听事件
 */
public class SystemEvent extends KeyAdapter {

    private boolean bL = false;
    private boolean bU = false;
    private boolean bR = false;
    private boolean bD = false;

    /**
     * 键盘按键事件处理
     * @param flag
     * @param e
     */
    public void KeyEventHandler(boolean flag, KeyEvent e){

        // 按下方向键
        if(KeyEvent.VK_LEFT == e.getKeyCode() || KeyEvent.VK_UP == e.getKeyCode() ||
                KeyEvent.VK_RIGHT == e.getKeyCode() || KeyEvent.VK_DOWN == e.getKeyCode() ){
            this.setMainTankDir(flag,e);
            return;
        }

        // 键盘按下时 操作
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                // 按键抬起时
                if(!flag){
                    if(TankFrameFactory.INSTANCE.getTankFrame().getBgm().getMainTank() == null ) break;
                    TankFrameFactory.INSTANCE.getTankFrame().getBgm().getMainTank().fired();
                    // 开火音效
                    new Thread(()->{
                        new AudioUtil("static/audio/tank_fire.wav").play();
                    }).start();
                }
                break;
            case KeyEvent.VK_ESCAPE:
                // 关闭程序
                System.exit(0);
                break;
            default:
                break;
        }
    }

    /**
     * 设置主战坦克方向
     */
    private void setMainTankDir(boolean flag, KeyEvent e) {

        if(TankFrameFactory.INSTANCE.getTankFrame().getBgm().getMainTank() == null){
            return;
        }

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

        if(!bL && !bU && !bR && !bD) {
            TankFrameFactory.INSTANCE.getTankFrame().getBgm().getMainTank().stop();
        }else{
            if(bL) TankFrameFactory.INSTANCE.getTankFrame().getBgm().getMainTank().setDir(Dir.LEFT);
            if(bU) TankFrameFactory.INSTANCE.getTankFrame().getBgm().getMainTank().setDir(Dir.UP);
            if(bR) TankFrameFactory.INSTANCE.getTankFrame().getBgm().getMainTank().setDir(Dir.RIGHT);
            if(bD) TankFrameFactory.INSTANCE.getTankFrame().getBgm().getMainTank().setDir(Dir.DOWN);

            // 移动音乐
            new Thread(()->{
                new AudioUtil("static/audio/tank_move.wav").play();
            }).start();

            TankFrameFactory.INSTANCE.getTankFrame().getBgm().getMainTank().start();
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
