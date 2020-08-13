package com.parker.tank.map;

import com.parker.tank.Mine;
import com.parker.tank.Special;
import com.parker.tank.TankFrame;
import com.parker.tank.WallFactory;
import com.parker.tank.dist.WallGroup;
import com.parker.tank.factory.TankFrameFactory;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.map
 * @Author: Parker
 * @CreateTime: 2020-08-14 04:08
 * @Description: 地图
 */
public enum Gate1Map {

    INSTANCE;

    private int width = 58, height = 22;

    private TankFrame tankFrame = TankFrameFactory.INSTANCE.getTankFrame();


    public void getMap(){

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

        // 老巢
        this.setWall(374,650,1,7,WallGroup.STEEl);

        this.setWall(432,650,3,2,WallGroup.STEEl);

        this.setWall(567,650,1,7,WallGroup.STEEl);

        // 创建鸟巢
        tankFrame.getBgm().add(new Special(432, 695, 135, 118, tankFrame.getBgm()));

        // 地雷
        tankFrame.getBgm().add(new Mine(942, 180, 50, 50, tankFrame.getBgm()));
        tankFrame.getBgm().add(new Mine(0,424, 58, 58, tankFrame.getBgm()));
        tankFrame.getBgm().add(new Mine(942, 180, 50, 50, tankFrame.getBgm()));
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
                tankFrame.getBgm().add(
                        WallFactory.INSTANCE.createWallByHp(
                                x + width * i, y + height * j, width, height,a, group,tankFrame.getBgm()));
            }
        }
    }

}
