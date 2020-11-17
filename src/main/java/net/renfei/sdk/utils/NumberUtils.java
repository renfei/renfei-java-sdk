package net.renfei.sdk.utils;

import java.math.BigDecimal;

/**
 * <p>Title: IntergerUtils</p>
 * <p>Description: 数字工具</p>
 *
 * @author RenFei
 * @date : 2020-08-24 21:07
 */
public class NumberUtils {
    /**
     * 将字符串转成整型数字，如果失败返回默认值
     *
     * @param str          待转字符串
     * @param defaultValue 默认值
     * @return 转换后的值
     */
    public static int parseInt(String str, int defaultValue) {
        if (str == null || str.length() == 0) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转成浮点数字，如果失败返回默认值
     *
     * @param str          待转字符串
     * @param defaultValue 默认值
     * @return 转换后的值
     */
    public static float parseFloat(String str, float defaultValue) {
        if (str == null || str.length() == 0) {
            return defaultValue;
        }
        try {
            return Float.parseFloat(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }

    /**
     * 将字符串转成浮点数字，如果失败返回默认值
     *
     * @param str          待转字符串
     * @param defaultValue 默认值
     * @return 转换后的值
     */
    public static double parseDouble(String str, double defaultValue) {
        if (str == null || str.length() == 0) {
            return defaultValue;
        }
        try {
            return Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return defaultValue;
        }
    }
}
