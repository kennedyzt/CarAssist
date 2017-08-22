package com.ruiyi.carassistant.utils;

public class StringUtil {

    public static Boolean isEmpty(String str) {
        if (null == str || str.isEmpty()) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

}
