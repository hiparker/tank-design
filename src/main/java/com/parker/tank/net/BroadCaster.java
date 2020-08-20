package com.parker.tank.net;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @BelongsProject: learn-netty-gradle
 * @BelongsPackage: com.parker.netty.study01
 * @Author: Parker
 * @CreateTime: 2020-08-20 01:44
 * @Description: 广播工具
 */
public enum BroadCaster {

    INSTANCE;

    private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 添加信道
     * @param channel
     */
    public void  add(Channel channel){
        if(channel != null){
            CHANNEL_GROUP.add(channel);
        }
    }

    /**
     * 删除信道
     * @param channel
     */
    public void  remove(Channel channel){
        if(channel != null){
            CHANNEL_GROUP.remove(channel);
        }
    }

    /**
     * 广播信息
     * @param msg
     */
    public void  cast(Object msg){
        if(msg != null){
            CHANNEL_GROUP.writeAndFlush(msg);
        }
    }

}
