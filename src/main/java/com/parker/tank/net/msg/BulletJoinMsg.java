package com.parker.tank.net.msg;

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
public class BulletJoinMsg implements BaseMsg{


    /** ID */
    private UUID id;
    /** 坦克ID */
    private UUID tankId;
    /** 消息类型 */
    private BulletType type;

    public BulletJoinMsg() {
    }

    /**
     * 构造函数
     * @param id
     * @param tankId
     * @param type
     */
    public BulletJoinMsg(UUID id,UUID tankId,BulletType type) {
        this.id = id;
        this.tankId = tankId;
        this.type = type;
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
            // 设置表示 1为 炮弹
            dos.writeInt(2000);
            // UUID 高低64位 一共 128位
            dos.writeLong(this.id.getMostSignificantBits());
            dos.writeLong(this.id.getLeastSignificantBits());
            dos.writeLong(this.tankId.getMostSignificantBits());
            dos.writeLong(this.tankId.getLeastSignificantBits());
            dos.writeInt(this.type.ordinal());
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
                ", type=" + type +
                '}';
    }

    // ------- -------


    public UUID getId() {
        return id;
    }

    public BulletJoinMsg setId(UUID id) {
        this.id = id;
        return this;
    }

    public UUID getTankId() {
        return tankId;
    }

    public BulletJoinMsg setTankId(UUID tankId) {
        this.tankId = tankId;
        return this;
    }

    public BulletType getType() {
        return type;
    }

    public BulletJoinMsg setType(BulletType bulletType) {
        this.type = bulletType;
        return this;
    }
}
