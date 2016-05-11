package com.mao.infocrawler.utils;

/**
 * Created by mao on 2016/5/9.
 */
public class StringUtil {

    /**
     * str是否为空
     * @param str
     * @return
     */
    public static boolean validateString(String str) {
        if ("".equals(str.trim()) || str == null)
            return false;
        return true;
    }
}
