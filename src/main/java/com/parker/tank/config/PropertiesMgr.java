package com.parker.tank.config;

import java.io.IOException;
import java.util.Properties;

/**
 * @BelongsProject: tank-02
 * @BelongsPackage: com.parker.tank.config
 * @Author: Parker
 * @CreateTime: 2020-08-12 14:29
 * @Description: 配置文件
 *
 * 单例模式
 *
 */
public final class PropertiesMgr {

    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_FILE_NAME = "application.properties";

    static{
        try {
            PROPERTIES.load(PropertiesMgr.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PropertiesMgr(){}

    public static String getByString(String key){
        if(PROPERTIES == null){
            return "";
        }
        return PROPERTIES.getProperty(key,"");
    }

    public static Integer getByInteger(String key){
        if(PROPERTIES == null){
            return null;
        }
        return Integer.parseInt(PROPERTIES.getProperty(key,""));
    }

    public static Boolean getByBoolean(String key){
        if(PROPERTIES == null){
            return null;
        }
        return Boolean.parseBoolean(PROPERTIES.getProperty(key,""));
    }


    public static void main(String[] args) {
        System.out.println(PropertiesMgr.getByString("title"));
    }

}
