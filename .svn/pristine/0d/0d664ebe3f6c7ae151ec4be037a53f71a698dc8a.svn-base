package com.hbw.library.utils;

import android.text.TextUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 操作字符串工具类
 *
 * @author hebiwen
 */
public class StringUtils {

    private final static String TAG = "StringUtils";

    /**
     * 得到一个不为NULL的字符串
     *
     * @param str
     * @return
     */
    public static String getNonNullString(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    /**
     * 得到一个不为NULL的字符串,为null 返回 0
     *
     * @param str
     * @return
     */
    public static String retrunNonNullString(String str) {
        return TextUtils.isEmpty(str) ? "0" : str;
    }

    /**
     * 得到一个不为NULL的字符串
     *
     * @param n
     * @return
     */
    public static String getNonNullString(Long n) {
        return n == null ? "" : n.toString();
    }

    /**
     * 得到一个不为NULL的字符串
     *
     * @param n
     * @return
     */
    public static String getNonNullString(Integer n) {
        return n == null ? "" : n.toString();
    }

    /**
     * 得到一个不为NULL的字符串
     *
     * @param f
     * @return
     */
    public static String getNonNullString(Float f) {
        return f == null ? "" : f.toString();
    }

    /**
     * 得到一个不为NULL的字符串
     *
     * @param d
     * @return
     */
    public static String getNonNullString(Double d) {
        return d == null ? "" : d.toString();
    }

    /**
     * 数字转换为字母（0,1,2... --> A,B,C....AA,AB,AC...AAA,AAB,AAC...）
     *
     * @param num
     * @return
     */
    public static String int2MoreChar(int num) {
        String str = "";
        if (num < 0) {
            return str;
        }
        if (num > 25) {
            for (int j = 0; j < num / 26; j++) {
                str += "A";
            }
            num = num % 26;
        }
        char c = (char) (num + 65);
        str += c;
        return str;
    }

    /**
     * String[] 转换 为 String
     *
     * @param array
     * @return
     */
    public static String strArray2Str(String[] array) {
        StringBuffer sb = new StringBuffer();
        if (array == null) {
            return sb.toString();
        }
        for (String str : array) {
            if (TextUtils.isEmpty(str)) {
                continue;
            }
            sb.append(str);
        }
        return sb.toString();
    }

    /**
     * 保留指定的小数点位数
     *
     * @param decimal
     * @param f
     * @return 返回字符串
     */
    public static String formatFloat2Str(int decimal, float f) {
        String str = "";
        DecimalFormat format;
        switch (decimal) {
            case 1: {
                format = new DecimalFormat("######.#");// 设置输出数值的格式为XX.X
                break;
            }
            case 2: {
                format = new DecimalFormat("######.## ");// 设置输出数值的格式为XX.XX
                break;
            }
            default:
                format = new DecimalFormat("######");// 设置输出数值的格式为XX
                break;
        }
        str = format.format(f);
        return str;
    }

    /**
     * 保留指定的小数点位数
     *
     * @param decimal
     * @param f
     * @return 返回double型数据
     */
    public static double formatFloat2Double(int decimal, float f) {
        double pow = Math.pow(10, decimal);

        long value = Math.round(f * pow);

        double result = value / pow;

        return result;
    }

    /**
     * 判断字符是否 是数字或字母
     *
     * @param str
     * @return
     */
    public static boolean isNumOrLetter(String str) {
        boolean flag = false;
        if (TextUtils.isEmpty(str)) {
            return flag;
        }
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            flag = (current >= 48 && current <= 57)
                    || (current >= 65 && current <= 90)
                    || (current >= 97 && current <= 122);
            if (!flag) {
                break;
            }
        }
        return flag;
    }

    /**
     * 获取一个字符串最后几个字符
     *
     * @param str
     * @param num
     * @return
     */
    public static String getLastNumStr(String str, int num) {
        if (str == null) {
            return "";
        }

        if (str.length() < num) {
            return str;
        }
        str = str.substring(str.length() - num);
        return str;
    }

    /**
     * 字符串 转换 为 int，转换异常 返回 Integer.MIN_VALUE
     *
     * @param str
     * @return
     */
    public static int strToInt(String str) {
        int n = Integer.MIN_VALUE;

        if (TextUtils.isEmpty(str)) {
            return n;
        }
        if (!TextUtils.isDigitsOnly(str)) {
            return n;
        }
        try {
            n = Integer.valueOf(str);
        } catch (Exception e) {
            L.e(TAG, "strToInt(String " + str + "):%s" + e.getMessage());
        }
        return n;
    }

    /**
     * 比较时间的方法
     */
    public static int compare_date(String DATE1) {

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse("2015-01-01  00:00");
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * 功能：判断字符串是否为数字
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern
                .compile("^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    // 使用java判断字符串是否日日期类型 (正则表达式)
    public static boolean checkStrFormat(String str) {
        Pattern pattern = Pattern
                .compile("^([0-9]{4})((0([1-9]{1}))|(1[0-2]))(([0-2]([0-9]{1}))|(3[0|1]))(([0-1]([0-9]{1}))|(2[0-4]))([0-5]([0-9]{1}))([0-5]([0-9]{1}))");

        // //Pattern pattern = Pattern.compile("[0-9]{2}:[0-9]{2}");
        Matcher matcher = pattern.matcher(str);
        boolean bool = matcher.matches();
        return bool;
    }

    /**
     * 字符串转换unicode
     */
    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * unicode 转字符串
     */
    public static String unicode2String(String unicode) {

        StringBuffer string = new StringBuffer();

        String[] hex = unicode.split("\\\\u");

        for (int i = 1; i < hex.length; i++) {

            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);

            // 追加成string
            string.append((char) data);
        }

        return string.toString();
    }

}
