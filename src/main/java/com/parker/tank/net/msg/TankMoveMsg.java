package com.parker.tank.net.msg;

import com.parker.tank.Dir;
import com.parker.tank.Tank;

import java.io.*;
import java.util.UUID;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 15:46
 * @Description: 主战坦克
 */
public class TankMoveMsg extends Msg {



    /** 坦克方向 */
    private Dir dir = Dir.DOWN;
    /** 是否是移动的状态 */
    private boolean moving = false;


    public TankMoveMsg() {
    }

    /**
     * 构造函数
     * @param dir
     * @param id
     */
    public TankMoveMsg(UUID id, Dir dir, boolean moving) {
        this.dir = dir;
        this.moving = moving;
        this.id = id;
    }

    /**
     * 构造函数
     * @param tank
     */
    public TankMoveMsg(Tank tank) {
        if(tank == null) return;
        this.id = tank.getId();
        this.dir = tank.getDir();
        this.moving = tank.isMoving();
    }

    /**
     * 构造函数
     * @param tank
     */
    public TankMoveMsg(Tank tank, boolean moving) {
        if(tank == null) return;
        this.id = tank.getId();
        this.dir = tank.getDir();
        this.moving = moving;
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

            this.dir = Dir.values()[dis.readInt()];
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
            int dirC = 0;
            if(this.dir != null){
                dirC = this.dir.ordinal();
            }
            dos.writeInt(dirC);
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
                ", dir=" + dir +
                ", moving=" + moving +
                ", id=" + id +
                '}';
    }

    // ------- -------


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

}
