package com.parker.tank.faced;

import com.parker.tank.Audio;
import com.parker.tank.GameObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-13 15:35
 * @Description: GameModel
 *
 * Model 和 View 分离
 *
 * 门面 Faced 设计模式
 *
 */
public class TitleModel extends BaseGameModel{

    /** 物体集合 */
    private final List<GameObject> gameObjects = new ArrayList<>();


    public TitleModel() {

    }

    /**
     * 描绘
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {
        // 物体渲染
        for (int i = 0; i < this.gameObjects.size(); i++) {
            this.gameObjects.get(i).paint(g);
        }
    }

    // ---------------------------------------------------------

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

}
