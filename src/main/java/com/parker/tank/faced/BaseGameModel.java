package com.parker.tank.faced;


import com.parker.tank.GameObject;
import com.parker.tank.Tank;
import com.parker.tank.util.AudioUtil;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain
 * @Author: Parker
 * @CreateTime: 2020-08-14 01:04
 * @Description: Base
 */
public abstract class BaseGameModel implements Serializable {

    /** 物体集合 */
    protected volatile List<GameObject> gameObjects = new ArrayList<>();

    /** 我方主战坦克 */
    private volatile Tank mainTank;

    /**
     * 声音
     */
    protected volatile AudioUtil audioUtil;
    /**
     * 声音线程
     */
    protected volatile Thread musicThread;

    protected volatile int tempNum;

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
        this.add(this.mainTank);
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

    public List<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    /**
     * 清空对象集合
     */
    public void clearGameObjects(){
        this.gameObjects.clear();
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