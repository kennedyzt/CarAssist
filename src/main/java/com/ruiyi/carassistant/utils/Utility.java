package com.ruiyi.carassistant.utils;

/**
 * 工具类，放一些简单的方法。
 * 
 * @author Yipd
 * @date 2016-10-19 下午5:48:35
 */
public class Utility {
    public Utility() {
    }

    public static void sleep(int nSecond) {
        try {
            Thread.sleep(nSecond);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void log(String sMsg) {
        System.err.println(sMsg);
    }

    public static void log(int sMsg) {
        System.err.println(sMsg);
    }
}