package com.parker.tank.net.msg;

import com.parker.tank.*;

import java.io.*;
import java.util.UUID;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 15:46
 * @Description: 主战坦克
 */
public class TankJoinMsg extends Msg {


    /** XY坐标 */
    private int x , y;
    /** 坦克方向 */
    private Dir dir = Dir.DOWN;
    /** 是否是移动的状态 */
    private boolean moving = false;
    /** 坦克分组 */
    private TankGroup group;


    public TankJoinMsg() {
    }

    /**
     * 构造函数
     * @param x
     * @param y
     * @param dir
     * @param group
     * @param id
     */
    public TankJoinMsg(int x, int y, Dir dir, TankGroup group, boolean moving, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.moving = moving;
        this.id = id;
    }

    /**
     * 构造函数
     * @param tank
     */
    public TankJoinMsg(Tank tank) {
        if(tank == null) return;
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.group = tank.getGroup();
        this.moving = tank.isMoving();
        this.id = tank.getId();
    }

    /**
     * 构造函数
     * @param tank
     */
    public TankJoinMsg(Tank tank,boolean moving) {
        if(tank == null) return;
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.group = tank.getGroup();
        this.moving = moving;
        this.id = tank.getId();
    }

    /**
     * 构造函数
     * @param id
     */
    public TankJoinMsg(UUID id, MsgType msgType) {
        this.x = 0;
        this.y = 0;
        this.dir = Dir.DOWN;
        this.group = TankGroup.BLUE;
        this.moving = false;
        this.id = id;
    }

    /**
     * 解析数据
     * @param bytes
     */
    @Override
    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            //TODO:先读TYPE信息，根据TYPE信息处理不同的消息
            //略过消息类型
            //dis.readInt();

            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.group = TankGroup.values()[dis.readInt()];
            this.moving = dis.readBoolean();
            this.id = new UUID(dis.readLong(), dis.readLong());

            //this.name = dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 转换成二进制字节数组
     * @return
     */
    public byte[] toBytes(){
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {

            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeInt(this.x);
            dos.writeInt(this.y);
            dos.writeInt(this.dir.ordinal());
            dos.writeInt(this.group.ordinal());
            dos.writeBoolean(this.moving);
            // UUID 高低64位 一共 128位
            dos.writeLong(this.id.getMostSignificantBits());
            dos.writeLong(this.id.getLeastSignificantBits());
            dos.flush();

            bytes = baos.toByteArray();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(dos != null){
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    @Override
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", moving=" + moving +
                ", group=" + group +
                ", id=" + id +
                '}';
    }

    // ------- -------

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public TankGroup getGroup() {
        return group;
    }

    public void setGroup(TankGroup group) {
        this.group = group;
    }


}
