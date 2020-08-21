package com.parker.tank.net.msg;


import java.io.*;
import java.util.UUID;

/**
 * @BelongsProject: tank-01
 * @BelongsPackage: com.parker.tank
 * @Author: Parker
 * @CreateTime: 2020-08-10 15:46
 * @Description: 主战坦克
 */
public class BulletJoinMsg extends Msg {



    /** 坦克ID */
    private UUID tankId;


    public BulletJoinMsg() {
    }

    /**
     * 构造函数
     * @param id
     * @param tankId
     */
    public BulletJoinMsg(UUID id,UUID tankId) {
        this.id = id;
        this.tankId = tankId;
        this.type = type;
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

            // UUID 高低64位 一共 128位
            this.id = new UUID(dis.readLong(), dis.readLong());
            this.tankId = new UUID(dis.readLong(), dis.readLong());

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
    @Override
    public byte[] toBytes(){
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {

            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            // UUID 高低64位 一共 128位
            dos.writeLong(this.id.getMostSignificantBits());
            dos.writeLong(this.id.getLeastSignificantBits());
            dos.writeLong(this.tankId.getMostSignificantBits());
            dos.writeLong(this.tankId.getLeastSignificantBits());
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
        return "BulletJoinMsg{" +
                "id=" + id +
                ", tankId=" + tankId +
                '}';
    }

    // ------- -------

    public UUID getTankId() {
        return tankId;
    }

    public void setTankId(UUID tankId) {
        this.tankId = tankId;
    }

}
