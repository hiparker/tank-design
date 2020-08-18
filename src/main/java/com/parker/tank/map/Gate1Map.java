package com.parker.tank.map;

import com.parker.tank.GameObject;
import com.parker.tank.Mine;
import com.parker.tank.Special;
import com.parker.tank.TankFrame;
import com.parker.tank.dist.Dir;
import com.parker.tank.dist.TankGroup;
import com.parker.tank.faced.GameModel;
import com.parker.tank.factory.TankFactory;
import com.parker.tank.factory.WallFactory;
import com.parker.tank.dist.WallGroup;
import com.parker.tank.factory.TankFrameFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.map
 * @Author: Parker
 * @CreateTime: 2020-08-14 04:08
 * @Description: 地图
 */
public enum Gate1Map implements GateMap{

    INSTANCE;

    /** 敌方坦克数量 */
    private int badCount = 8;

    /** 墙宽高 */
    private int width = 58, height = 22;

    /** 构建对象集合 */
    private List<GameObject> goList = new ArrayList<>();

    private TankFrame tankFrame = TankFrameFactory.INSTANCE.getTankFrame();

    @Override
    public GateMap builderWall() {

        // 创建墙

        // 上
        this.setWall(0,120,2,2,WallGroup.BRICK);

        this.setWall(80,120,1,8,WallGroup.BRICK);

        this.setWall(280,120,1,8,WallGroup.BRICK);

        this.setWall(440,180,2,4,WallGroup.STEEl);

        this.setWall(660,120,1,8,WallGroup.BRICK);

        this.setWall(860,120,1,8,WallGroup.BRICK);

        this.setWall(900,120,2,2,WallGroup.BRICK);

        // 中
        this.setWall(0,380,1,2,WallGroup.STEEl);

        this.setWall(200,380,3,4,WallGroup.BRICK);

        this.setWall(470,380,1,2,WallGroup.BRICK);

        this.setWall(625,380,3,4,WallGroup.BRICK);

        this.setWall(942,380,1,2,WallGroup.STEEl);

        // 下
        this.setWall(80,580,1,7,WallGroup.BRICK);

        this.setWall(238,580,9,2,WallGroup.BRICK);

        this.setWall(860,580,1,7,WallGroup.BRICK);

        // 老巢 墙
        this.setWall(374,650,1,7,WallGroup.STEEl);

        this.setWall(432,650,3,2,WallGroup.STEEl);

        this.setWall(567,650,1,7,WallGroup.STEEl);

        return this;
    }

    @Override
    public GateMap builderSpecial() {

        // 创建鸟巢
        this.goList.add(
                new Special(432, 695, 135, 118)
        );

        return this;
    }

    @Override
    public GateMap builderMine() {

        // 创建地雷
        this.goList.add(
                new Mine(942, 180, 50, 50)
        );
        this.goList.add(
                new Mine(0,424, 58, 58)
        );
        this.goList.add(
                new Mine(942, 280, 50, 50)
        );

        return this;
    }

    @Override
    public GateMap builder(int badCount) {

        GameModel bgm =(GameModel) tankFrame.getBgm();

        // 真正创建墙\碉堡\地雷
        for (int i = 0; i < this.goList.size(); i++) {
            bgm.add(this.goList.get(i));
        }

        TankFactory.autoCount = 0;
        TankFactory.usualCount = 0;


        // 设置主战坦克
        bgm.setMainTank(
                TankFactory.createTank(300,710, Dir.UP, TankGroup.RED)
        );

        // 设置敌方坦克
        bgm.createBadTank(badCount);

        System.out.println("普通坦克数量["+ TankFactory.usualCount+"]  自动坦克数量["+TankFactory.autoCount+"]");

        return this;
    }

    /**
     * 设置墙
     * @param x x轴
     * @param y y轴
     * @param xn x轴个数
     * @param yn y轴个数
     */
    private void setWall(int x,int y,int xn,int yn,WallGroup group){
        for (int i = 0; i < xn; i++) {
            for (int j = 0; j < yn; j++) {
                int a = 1;
                if(WallGroup.STEEl.equals(group)){
                    a = 5;
                }else if(WallGroup.BRICK.equals(group)){
                    a = 2;
                }else if(WallGroup.TREE.equals(group)){
                    a = 1000;
                }

                this.goList.add(
                        WallFactory.INSTANCE.createWallByHp(
                                x + width * i, y + height * j, width, height,a, group)
                );
            }
        }
    }


}
