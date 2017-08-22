package com.ruiyi.carassistant.utils;

import java.math.RoundingMode;
import java.text.NumberFormat;

public class NumberUtil {
    /**
     * 使用NumberFormat,保留小数点后两位
     */
    public static String format3(double value, Integer num) {

        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(num);
        /*
         * setMinimumFractionDigits设置成2 如果不这么做，那么当value的值是100.00的时候返回100
         * 而不是100.00
         */
        nf.setMinimumFractionDigits(num);
        nf.setRoundingMode(RoundingMode.HALF_UP);
        /*
         * 如果想输出的格式用逗号隔开，可以设置成true
         */
        nf.setGroupingUsed(false);
        return nf.format(value);
    }
}
