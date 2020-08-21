package com.parker.tank.net.thread;

import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.handler.ClientHandler;
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
     * @param msg
     */
    public void process(ChannelHandlerContext ctx, TankJoinMsg msg){
        EXECUTOR_SERVICE.submit(()->{
           try {
                // 调用执行器
               BaseHandler handler = ClientHandler.getInstance().getHandler(msg.getType());
               if(handler != null){
                   handler.execute(msg);
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
