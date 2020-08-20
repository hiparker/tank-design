package com.parker.tank.net;

import com.parker.tank.net.coder.TankJoinMsgDecoder;
import com.parker.tank.net.coder.TankJoinMsgEncoder;
import com.parker.tank.net.msg.TankJoinMsg;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @BelongsProject: learn-netty-gradle
 * @BelongsPackage: com.parker.netty.study01
 * @Author: Parker
 * @CreateTime: 2020-08-18 21:10
 * @Description: 服务端
 */
public enum Server {

    INSTANCE;

    public void connect(){

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();

        b.group(bossGroup,workGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                System.out.println("服务端初始化");
                ch.pipeline()
                        .addLast(new TankJoinMsgEncoder())
                        .addLast(new TankJoinMsgDecoder())
                        .addLast(new ServerChannelHandler());
            }
        });

        try{
            ChannelFuture f = b.bind(12345).sync();
            if(f.isSuccess()){
                ServerFrame.INSTANCE.updateServerMsg("服务端启动！！！");
            }

            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}

class ServerChannelHandler extends SimpleChannelInboundHandler<TankJoinMsg>{

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TankJoinMsg msg) throws Exception {
        if(msg == null){
            return;
        }

        // 服务器
        BroadCaster.INSTANCE.cast(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        BroadCaster.INSTANCE.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端关闭");
        BroadCaster.INSTANCE.remove(ctx.channel());
        ctx.close();
    }
}
