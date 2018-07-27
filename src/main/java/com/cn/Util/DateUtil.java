package com.cn.Util;
import java.util.Calendar;
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

    /**
     * 得到几天前的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d,int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }
    /**
     * 得到几年后的时间
     * @param year
     * @return
     */
    public static Date getDateAfterYear(int year) {
        Calendar curr = Calendar.getInstance();
        curr.set(Calendar.YEAR,curr.get(Calendar.YEAR)+year);
        return  curr.getTime();
    }
    }
