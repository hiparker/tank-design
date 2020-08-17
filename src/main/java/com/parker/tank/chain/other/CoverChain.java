package com.parker.tank.chain.other;

import com.parker.tank.StringG;
import com.parker.tank.factory.WallFactory;
import com.parker.tank.chain.ChainStack;
import com.parker.tank.chain.BaseGameChain;
import com.parker.tank.config.PropertiesMgr;
import com.parker.tank.config.ResourcesMgr;
import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.faced.CoverModel;

import java.util.concurrent.TimeUnit;


/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.chain
 * @Author: Parker
 * @CreateTime: 2020-08-14 00:06
 * @Description: 封面
 */
public class CoverChain extends BaseGameChain {

    @Override
    public boolean handler() {
        ChainStack.INSTANCE.put(this);
        super.remake();
        try {
            System.out.println("=========================");
            System.out.println("=  欢迎来到 坦克大战 ！！！ =");
            System.out.println("=  上下左右键移动,空格开火  =");
            System.out.println("=========================");
            System.out.println();
            System.out.println("----------------------------------------------------");
            System.out.println();
            System.out.println("作者：Parker");
            System.out.println("Github：https://github.com/hiparker/tank-design");
            System.out.println();
            System.out.println("----------------------------------------------------");
            System.out.println();

            BaseGameModel gameModel = new CoverModel();
            gameModel.builder();

            // 开始封面运作
            tankFrame.setBgm(gameModel);

            int gameWidth = 800, gameHeight = 600;
            if(PropertiesMgr.getByInteger("gameWidth") != null){
                gameWidth = PropertiesMgr.getByInteger("gameWidth");
            }
            if(PropertiesMgr.getByInteger("gameHeight") != null){
                gameHeight = PropertiesMgr.getByInteger("gameHeight");
            }

            // 背景图
            BaseGameModel bgm = tankFrame.getBgm();
            bgm.add(
                    WallFactory.INSTANCE.createWall(
                            (gameWidth-800)/2,(gameHeight-600)/2,800,600, ResourcesMgr.bg));
            // 文字
            bgm.add(new StringG(10,40," ESC关闭游戏 ，S 存档 , L 恢复存档 ，R 重玩"));
            // 文字
            bgm.add(new StringG(180,540,"欢迎来到 坦克大战 "));
            // 文字
            bgm.add(new StringG(180,560,"上下左右键移动,空格开火 "));
            // 文字
            bgm.add(new StringG(180,600,"作者：Parker "));
            // 文字
            bgm.add(new StringG(180,620,"Github：https://github.com/hiparker/tank-design "));

            // 3 秒后 停止
            int count = 6;
            new Thread(()->{
                try {
                    TimeUnit.SECONDS.sleep(count);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.successStop();
            }).start();

            // 卡死进程  -----------------
            while (super.state.get()){}
        }catch (Exception e){
            super.result.set(false);
        }
        return super.result.get();
    }
}
