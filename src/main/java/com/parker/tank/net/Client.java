package com.parker.tank.net;

import com.parker.tank.Tank;
import com.parker.tank.TankFactory;
import com.parker.tank.TankFrame;
import com.parker.tank.net.coder.TankJoinMsgDecoder;
import com.parker.tank.net.coder.TankJoinMsgEncoder;
import com.parker.tank.net.msg.TankJoinMsg;
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
                    .connect("localhost",12345)
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
     * @param tankJoinMsg
     */
    public void send(TankJoinMsg tankJoinMsg){
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(tankJoinMsg.toBytes());
        this.channel.writeAndFlush(buf);
    }
}

class ClientChanHandler extends SimpleChannelInboundHandler<TankJoinMsg> {


    public ClientChanHandler(){

    }


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf buf = Unpooled.buffer();
        byte[] bytes = new TankJoinMsg(
                TankFrame.INSTANCE.getMyTank()).toBytes();

        buf.writeBytes(
                bytes);
        ctx.channel().writeAndFlush(buf);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TankJoinMsg msg) throws Exception {
        if(msg == null){
            return;
        }

        System.out.println(TankFrame.INSTANCE.getMyTank().getId().equals(msg.getId()));
        System.out.println(TankFrame.INSTANCE.hasTank(msg.getId()));

        if(TankFrame.INSTANCE.getMyTank().getId().equals(msg.getId()) ||
             TankFrame.INSTANCE.hasTank(msg.getId())
            ){
            return;
        }

        Tank tank = TankFactory.createTank(msg.getX(), msg.getY(), msg.getDir(),TankFrame.INSTANCE,msg.getGroup(),msg.getId());

        TankFrame.INSTANCE.addTank(tank);

        // 将自己再写回新坦克面板里
        ByteBuf buf = Unpooled.buffer();
        TankJoinMsg myTankMsg = TankFrame.INSTANCE.getMyTankMsg();
        byte[] bytes = new TankJoinMsg(TankFrame.INSTANCE.getMyTank()).toBytes();

        buf.writeBytes(
                bytes);
        ctx.channel().writeAndFlush(buf);

        System.out.println("敌人："+msg.toString());
        System.out.println("自己："+myTankMsg.toString());

    }


}
