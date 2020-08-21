package com.parker.tank.net.thread;

import com.parker.tank.net.BroadCaster;
import com.parker.tank.net.handler.BaseHandler;
import com.parker.tank.net.handler.ClientHandler;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;
import com.parker.tank.net.thread.factory.NameableThreadFactory;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.thread
 * @Author: Parker
 * @CreateTime: 2020-08-21 14:22
 * @Description: 游戏处理 多线程改单线程处理
 */
public enum ServerMainThread {

    INSTANCE;

    private static final ExecutorService EXECUTOR_SERVICE = Executors.newSingleThreadExecutor(new NameableThreadFactory("服务器 - 游戏主线程"));

    /**
     * 执行
     * @param ctx
     * @param msg
     */
    public void process(ChannelHandlerContext ctx, TankJoinMsg msg){
        EXECUTOR_SERVICE.submit(()->{
           try {
               // 如果是新建 将ID 绑定在信道上
               if(TankType.CREATE.equals(msg.getType())){
                   ctx.channel().attr(AttributeKey.valueOf("userId")).set(msg.getId());
               }
               // 服务器
               BroadCaster.INSTANCE.cast(msg);
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
