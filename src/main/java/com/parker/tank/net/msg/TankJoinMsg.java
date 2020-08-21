package com.parker.tank.net.msg;

import com.parker.tank.*;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 15:46
 * @Description: 主战坦克
 */
public class TankJoinMsg implements BaseMsg{


    /** XY坐标 */
    private int x , y;
    /** 坦克方向 */
    private Dir dir = Dir.DOWN;
    /** 是否是移动的状态 */
    private boolean moving = false;
    /** 坦克分组 */
    private TankGroup group;
    /** ID */
    private UUID id;
    /** 消息类型 */
    private TankType tankType;

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
    public TankJoinMsg(int x, int y, Dir dir, TankGroup group, boolean moving, UUID id, TankType tankType) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.moving = moving;
        this.id = id;
        this.tankType = tankType;
    }

    /**
     * 构造函数
     * @param tank
     */
    public TankJoinMsg(Tank tank, TankType tankType) {
        if(tank == null) return;
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.group = tank.getGroup();
        this.moving = tank.isMoving();
        this.id = tank.getId();
        this.tankType = tankType;
    }

    /**
     * 构造函数
     * @param tank
     */
    public TankJoinMsg(Tank tank,boolean moving, TankType tankType) {
        if(tank == null) return;
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.group = tank.getGroup();
        this.moving = moving;
        this.id = tank.getId();
        this.tankType = tankType;
    }

    /**
     * 构造函数
     * @param id
     */
    public TankJoinMsg(UUID id, TankType tankType) {
        this.x = 0;
        this.y = 0;
        this.dir = Dir.DOWN;
        this.group = TankGroup.BLUE;
        this.moving = false;
        this.id = id;
        this.tankType = tankType;
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
            dos.writeInt(this.tankType.ordinal());
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

    public TankJoinMsg setX(int x) {
        this.x = x;
        return this;
    }

    public int getY() {
        return y;
    }

    public TankJoinMsg setY(int y) {
        this.y = y;
        return this;
    }

    public Dir getDir() {
        return dir;
    }

    public TankJoinMsg setDir(Dir dir) {
        this.dir = dir;
        return this;
    }

    public boolean isMoving() {
        return moving;
    }

    public TankJoinMsg setMoving(boolean moving) {
        this.moving = moving;
        return this;
    }

    public TankGroup getGroup() {
        return group;
    }

    public TankJoinMsg setGroup(TankGroup group) {
        this.group = group;
        return this;
    }

    public UUID getId() {
        return id;
    }

    public TankJoinMsg setId(UUID id) {
        this.id = id;
        return this;
    }

    public TankType getType() {
        return tankType;
    }

    public TankJoinMsg setType(TankType tankType) {
        this.tankType = tankType;
        return this;
    }
}
