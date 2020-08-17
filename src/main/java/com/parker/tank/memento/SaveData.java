package com.parker.tank.memento;

import com.parker.tank.*;
import com.parker.tank.chain.ChainStack;
import com.parker.tank.chain.GameChain;
import com.parker.tank.chain.GateGameChain;
import com.parker.tank.chain.MainGameChain;
import com.parker.tank.chain.gate.Gate;
import com.parker.tank.faced.BaseGameModel;
import com.parker.tank.faced.GameModel;
import com.parker.tank.factory.TankFrameFactory;
import com.parker.tank.util.DateUtil;

import java.io.*;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.memento
 * @Author: Parker
 * @CreateTime: 2020-08-17 17:12
 * @Description: 存盘
 *
 * Memento 备忘录 设计模式
 *
 */
public enum SaveData {

    INSTANCE;

    /**
     * 存档
     *
     * 1.存盘时间
     * 2.责任链自身编号
     * 3.责任链父类编号
     * 4.责任链敌方坦克数量
     * 5.责任链失败次数
     * 6.敌方坦克数量
     * 7.主战坦克血量
     * 8.主战坦克
     * 9.模型对象
     */
    public void save(){
        long time = System.currentTimeMillis();
        ObjectOutputStream oos = null;
        try {
            URL resource = Main.class.getResource("/");
            File tankData = new File(resource.getPath()+"/memento/tank.data");
            oos = new ObjectOutputStream(new FileOutputStream(tankData));


            // ------------ 存放对象 ------------
            GameChain gameChain = ChainStack.INSTANCE.peek();
            if(gameChain instanceof Gate){
                Gate gate = (Gate) gameChain;

                // 1.设置存盘时间
                oos.writeObject(time);
                // 2.责任链自身编号
                oos.writeObject(gate.getSelfNum());
                // 3.责任链父类编号
                oos.writeObject(gate.getParentNum());
                // 4.责任链敌方坦克数量
                oos.writeObject(gate.getBadTankCount());
                // 5.责任链失败次数
                oos.writeObject(gate.getPauseCount());

                // 游戏模型
                GameModel gameModel = (GameModel) gate.getGameModel();

                // 6.敌方坦克数量
                int badTankCount = gameModel.getBadTankCount();
                oos.writeObject(badTankCount);

                // 7.主战坦克血量
                int mainHp = gameModel.getMainHp();
                oos.writeObject(mainHp);

                // 8.主战坦克
                Tank mainTank = gameModel.getMainTank();
                oos.writeObject(mainTank);

                // 9.模型对象
                GameObject[] gameObjects = new GameObject[gameModel.getGameObjects().size()];
                gameModel.getGameObjects().toArray(gameObjects);
                oos.writeObject(gameObjects);

                // 如果是 关卡的话 进行存档

                System.out.println("存盘 - Time："+ DateUtil.formatDate(DateUtil.long2Date(time)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos != null){
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    /**
     * 回档
     *
     * 1.存盘时间
     * 2.责任链自身编号
     * 3.责任链父类编号
     * 4.责任链敌方坦克数量
     * 5.责任链失败次数
     * 6.敌方坦克数量
     * 7.主战坦克血量
     * 8.主战坦克
     * 9.模型对象
     */
    public void load(){
        Long time = null;
        ObjectInputStream ois = null;
        try {
            URL resource = Main.class.getResource("/");
            File tankData = new File(resource.getPath()+"/memento/tank.data");
            ois = new ObjectInputStream(new FileInputStream(tankData));

            GameChain gameChain = ChainStack.INSTANCE.peek();
            if(gameChain != null){

                // 停止当前栈 关卡 责任链
                gameChain.forceEnd();
                // 停止主责任链
                Main.GAMECHAIN.forceEnd();

                // ------------ 处理对象 ------------
                // 1.获得存盘时间
                time = (Long) ois.readObject();
                if(time == null){
                    return;
                }
                // 2.责任链自身编号
                int selfNum = (int) ois.readObject();
                // 3.责任链父类编号
                int parentNum = (int) ois.readObject();
                // 4.责任链敌方坦克数量
                int badTankCountC = (int) ois.readObject();
                // 5.责任链失败次数
                int pauseCount = (int) ois.readObject();
                // 6.敌方坦克数量
                int badTankCount = (int) ois.readObject();
                // 7.主战坦克血量
                int mainHp = (int) ois.readObject();
                // 8.主战坦克
                Tank mainTank = (Tank) ois.readObject();
                // 9.模型对象
                GameObject[] gameObjects =(GameObject[]) ois.readObject();

                Long finalTime = time;

                // 加载回档进程
                new Thread(()->Main.GAMECHAIN.handlerLoad(selfNum)).start();

                TimeUnit.MILLISECONDS.sleep(500);

                new Thread(()->{

                    // 如果开始加载关卡了 则设置参数
                    while (SaveData.INSTANCE.isGateChain()){
                        // 等待
                    }

                    // 设置延迟 代码多线程执行
                    try {
                        TimeUnit.MILLISECONDS.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    GameChain gateChain = ChainStack.INSTANCE.peek();

                    Gate gate = (Gate) gateChain;
                    gate.setSelfNum(selfNum);
                    gate.setParentNum(parentNum);
                    gate.setBadTankCount(badTankCountC);
                    gate.setPauseCount(pauseCount);

                    // 游戏模型
                    // 监控游戏模型 如果是gamemodel 直接开始设置参数
                    while (SaveData.INSTANCE.isGameModel()){
                        // 等待
                    }
                    GameModel gameModel = (GameModel) TankFrameFactory.INSTANCE.getTankFrame().getBgm();
                    gameModel.setBadTankCount(badTankCount);
                    gameModel.setMainHp(mainHp);
                    gameModel.setMainTank(mainTank);

                    // 处理对象
                    for (int i = 0; i < gameObjects.length; i++) {
                        if(gameObjects[i] instanceof Tank){
                            Tank t = (Tank)gameObjects[i];
                            t.initFireStrategy();
                        }
                    }

                    // 初始化回档对象
                    gameModel.clearGameObjects();
                    for (int i = 0; i < gameObjects.length; i++) {
                        gameModel.add(gameObjects[i]);
                    }

                    System.out.println("读盘 - Time："+ DateUtil.formatDate(DateUtil.long2Date(finalTime)));

                }).start();
            }
        } catch (IOException | ClassNotFoundException  e ) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(ois != null){
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 重玩
     */
    public void reboot() {
        // 调用完整责任链
        new Thread(Main.GAMECHAIN::handler).start();
    }

    private boolean isGateChain(){
        if(ChainStack.INSTANCE.peek() == null){
            return true;
        }
        if(ChainStack.INSTANCE.peek() instanceof Gate){
            return false;
        }
        return true;
    }


    private boolean isGameModel(){
        if(TankFrameFactory.INSTANCE.getTankFrame().getBgm() == null){
            return true;
        }
        if(TankFrameFactory.INSTANCE.getTankFrame().getBgm() instanceof GameModel){
            return false;
        }
        return true;
    }


}
