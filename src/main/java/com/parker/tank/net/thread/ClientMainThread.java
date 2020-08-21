package com.parker.tank.net.thread;

import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.handler.KindHandler;
import com.parker.tank.net.handler.BulletHandler;
import com.parker.tank.net.handler.TankHandler;
import com.parker.tank.net.msg.BaseMsg;
import com.parker.tank.net.msg.BulletJoinMsg;
import com.parker.tank.net.msg.Msg;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.thread.factory.NameableThreadFactory;
import io.netty.channel.ChannelHandlerContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.thread
 * @Author: Parker
 * @CreateTime: 2020-08-21 14:22
 * @Description: 游戏处理 多线程改单线程处理
 */
public enum ClientMainThread {

    INSTANCE;

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor(new NameableThreadFactory("客户端 - 游戏主线程"));

    /**
     * 执行
     * @param ctx
     * @param baseMsg
     */
    public void process(ChannelHandlerContext ctx, BaseMsg baseMsg){
        EXECUTOR_SERVICE.submit(()->{
           try {
               // 调用执行器
               KindHandler kindHandler = Msg.INSTANCE.getHandler(baseMsg.getClass());
               BaseHandler handler = null;
               // 坦克执行
               if(kindHandler instanceof TankHandler){
                   TankJoinMsg msg = (TankJoinMsg) baseMsg;
                   handler = ((TankHandler) kindHandler).getHandler(msg.getType());
                   if(handler != null){
                       handler.execute(msg);
                   }
               }
               // 子弹执行
               else if(kindHandler instanceof BulletHandler){
                   BulletJoinMsg msg = (BulletJoinMsg) baseMsg;
                   handler = ((BulletHandler) kindHandler).getHandler(msg.getType());
                   if(handler != null){
                       handler.execute(msg);
                   }
               }
           }catch (Exception e){
               System.out.println(e.getMessage());
           }
        });
    }

    /**
     * 执行
     * @param r
     */
    public void process(Runnable r){
        if(r == null){
            return;
        }
        EXECUTOR_SERVICE.submit(r);
    }

}
