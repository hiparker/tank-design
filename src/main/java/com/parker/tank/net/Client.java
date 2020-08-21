package com.parker.tank.net;

import com.parker.tank.TankFrame;
import com.parker.tank.net.coder.TankJoinMsgDecoder;
import com.parker.tank.net.coder.TankJoinMsgEncoder;
import com.parker.tank.net.msg.BaseMsg;
import com.parker.tank.net.msg.TankJoinMsg;
import com.parker.tank.net.msg.TankType;
import com.parker.tank.net.thread.ClientMainThread;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @BelongsProject: learn-netty-gradle
 * @BelongsPackage: com.parker.netty.study01
 * @Author: Parker
 * @CreateTime: 2020-08-18 21:01
 * @Description: 客户端
 */
public enum Client {

    INSTANCE;

    public Channel  channel = null;

    public void connect(){
        EventLoopGroup group = new NioEventLoopGroup(1);

        Bootstrap b = new Bootstrap();
        try{
            ChannelFuture channelFuture = b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("初始化");
                            ch.pipeline()
                                    .addLast(new TankJoinMsgEncoder())
                                    .addLast(new TankJoinMsgDecoder())
                                    .addLast(new ClientChanHandler());
                        }
                    })
                    .connect("192.168.0.103",12345)
                    .sync();

            if(channelFuture.isSuccess()){
                channel = channelFuture.channel();
                System.out.println("客户端启动！");
            }
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e) {
            String errorStr = "连接服务器失败";
            System.out.println(errorStr);
        } finally {
            // 关闭线程组
            group.shutdownGracefully();
        }
    }

    public Channel getChannel() {
        return this.channel;
    }


    /**
     * 发送消息
     * @param msg
     */
    public void send(BaseMsg msg){
        if(channel == null) return;
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(msg.toBytes());
        this.channel.writeAndFlush(buf);
    }
}

class ClientChanHandler extends SimpleChannelInboundHandler<BaseMsg> {


    public ClientChanHandler(){

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = Unpooled.buffer();
        byte[] bytes = new TankJoinMsg(
                TankFrame.INSTANCE.getMyTank(), TankType.CREATE).toBytes();

        buf.writeBytes(
                bytes);

        ctx.channel().writeAndFlush(buf);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, BaseMsg msg) throws Exception {
        if(msg == null){
            return;
        }

        ClientMainThread.INSTANCE.process(ctx,msg);
    }

}
