package com.ruiyi.carassistant.config;

/**
 * Created by Lenovo on 2016/12/16.
 */
public abstract class GeocodeConfig {

    private static String url = "http://restapi.amap.com/v3/geocode/regeo";
    private static String key = "29a600ef852fe015f67f99141d425606";

    public static String getUrl() {
        return url;
    }

    public static String getKey() {
        return key;
    }
}
