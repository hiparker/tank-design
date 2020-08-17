package com.parker.tank.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @BelongsProject: tank-design
 * @BelongsPackage: com.parker.tank.util
 * @Author: Parker
 * @CreateTime: 2020-08-17 18:21
 * @Description: TODO
 */
public final class DateUtil {

    private static final String PATTERN = "yyyy-dd-MM HH:mm:ss";

    private DateUtil(){
    }

    public static Date long2Date(long time){
        return new Date(time);
    }

    public static String formatDate(Date date){
        SimpleDateFormat sdf= new SimpleDateFormat(PATTERN);
        return sdf.format(date);
    }

    public static String formatDate(Date date,String pattern){
        if(date == null){
            return null;
        }
        if(pattern == null || "".equals(pattern)){
            pattern = PATTERN;
        }

        SimpleDateFormat sdf= new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

}
