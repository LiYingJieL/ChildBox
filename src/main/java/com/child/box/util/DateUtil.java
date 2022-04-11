package com.child.box.util;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final String YYYYMMDDHHMMSS_FMT = "yyyyMMddHHmmss";

    private static final String YYYYMMDD_FMT = "yyyyMMdd";

    private static final String TIME_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    private static final SimpleDateFormat ALL_TIME_FMT = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String getWholeTime(DateTime dateTime) {
        return dateTime.toString(YYYYMMDDHHMMSS_FMT);
    }

    public static String getYMD(DateTime dateTime) {
        return dateTime.toString(YYYYMMDD_FMT);
    }

    public static String getSimpleTime(DateTime dateTime) {
        return dateTime.toString(TIME_FORMATTER);
    }

    public static String getSimpleTime(Date date) {
        DateTime dateTime = new  DateTime(date);
        return getSimpleTime(dateTime);
    }

    public static Date addHourOfDate(Date date,int i){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.HOUR_OF_DAY,i);
        return c.getTime();
    }

    public static String getAllTime(Date date) {
        synchronized(ALL_TIME_FMT){
            return ALL_TIME_FMT.format(date);
        }
    }


    public static void main(String[] args) {
        System.out.println("args = [" + DateUtil.getWholeTime(new DateTime()) + "]");
    }
}
