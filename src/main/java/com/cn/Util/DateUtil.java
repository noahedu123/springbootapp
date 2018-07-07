package com.cn.Util;
import java.util.Date;
public class DateUtil {

    /**
     * miniutes分钟之前
     */
    public static Date hourMinutes(int minutes) {
        Date now = new Date();
        now.setMinutes(now.getMinutes() - minutes);
        return now;
    }


}
