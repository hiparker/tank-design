package com.parker.tank.net.msg;

import java.util.UUID;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.net.msg
 * @Author: Parker
 * @CreateTime: 2020-08-21 14:33
 * @Description: 基础信息类
 */
public abstract class Msg {

    /** 消息类型 */
    protected MsgType type;
    /** ID */
    protected UUID id;

    /**
     * 转成字节
     * @return
     */
    public abstract byte[] toBytes();

    /**
     * 解析数据
     * @param bytes
     */
    public abstract void parse(byte[] bytes);


    // -----------


    public MsgType getType() {
        return type;
    }

    public void setType(MsgType type) {
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
