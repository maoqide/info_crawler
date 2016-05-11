package com.mao.infocrawler.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mao on 2016/3/12.
 */
public class DateUtil {

    private static final String YDMHFormat = "yyyy-MM-dd-HH";

    private static final String YMDHMSSFormat = "yyyyMMddHHmmssSSS";

    /**
     * 得到时间 精确到H
     * @return
     */
    public static String getYMDHTime() {

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(YDMHFormat);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 得到时间 yyyyMMddHHmmssSSS,精确到毫秒
     * @return
     */
    public static String getYMDHMSSTime() {
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat(YMDHMSSFormat);
        return simpleDateFormat.format(new Date());
    }

    /**
     * 时间戳，毫秒数，String类型
     */
    public static String getTimeStamp() {

        return Long.toString(new Date().getTime());
    }

    public static void main(String[] args) {
        System.out.println(getYMDHTime());
    }
}
