package com.parker.tank.faced;


import com.parker.tank.GameObject;
import com.parker.tank.Tank;
import com.parker.tank.util.AudioUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain
 * @Author: Parker
 * @CreateTime: 2020-08-14 01:04
 * @Description: Base
 */
public abstract class BaseGameModel {

    /** 物体集合 */
    protected final List<GameObject> gameObjects = new ArrayList<>();

    /** 我方主战坦克 */
    private Tank mainTank;

    /**
     * 声音
     */
    protected AudioUtil audioUtil;
    /**
     * 声音线程
     */
    protected Thread musicThread;

    protected int tempNum;

    /**
     * 构建
     */
    public abstract BaseGameModel builder();

    /**
     * 描绘
     * @param g 画笔
     */
    public abstract void paint(Graphics g);

    /**
     * 设置主战坦克
     * @return
     */
    public void setMainTank(Tank mainTank){
        this.mainTank = mainTank;
    }
    /**
     * 获得主战坦克
     * @return
     */
    public Tank getMainTank(){
        return mainTank;
    }

    // ---------------------------------------------------------


    public int getTempNum() {
        return tempNum;
    }

    public void setTempNum(int tempNum) {
        this.tempNum = tempNum;
    }

    /**
     * 添加物体
     * @param go
     */
    public void add(GameObject go) {
        this.gameObjects.add(go);
    }

    /**
     * 删除物体
     * @param go
     */
    public void remove(GameObject go) {
        this.gameObjects.remove(go);
    }

    /**
     * 关闭模型
     */
    public void stopModel(){
        this.stopMusic();
    }


    /**
     * 关闭声音
     */
    public void stopMusic(){
        if(this.musicThread != null && this.audioUtil != null){
            this.audioUtil.close();
            this.musicThread.stop();
        }
    }

    /**
     * 开启声音
     */
    public void startMusic(){
        if(this.musicThread != null && this.audioUtil != null){
            this.musicThread.start();
        }
    }

}
