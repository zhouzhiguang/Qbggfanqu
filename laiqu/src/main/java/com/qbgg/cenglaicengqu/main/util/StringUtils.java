package com.qbgg.cenglaicengqu.main.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {

    /**
     * 字符串处理,如果字符串为null,则返回一个空字符串,否则返回原字符串
     *
     * @param str
     * @return
     */
    public static String getLegalString(String str) {

        return str == null ? "" : str;
    }

    /**
     * 字符串是否为空或者空字符串
     *
     * @param str
     * @return 是则返回true
     */
    public static boolean isNullOrEmpty(String str) {

        return (str == null || str.isEmpty()) ? true : false;
    }

    /**
     * 过滤emoji表情 或者 其他非文字类型的字符
     *
     * @param source
     * @return
     */
    public static boolean isIncludeExpression(String source) {

        for (int i = 0; i < source.length(); i++) {
            char codePoint = source.charAt(i);

            if (isEmojiCharacter(codePoint)) {
                return true;// 如果不包含，直接返回
            }
        }
        return false;
    }

    private static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }

    /**
     * 将泛型为String类型的集合转化为逗号间隔字符串形式
     *
     */
    public static String formatStringList(List<String> stringList) {
        if (stringList == null || stringList.size() <= 0) {
            return "";
        }
        return stringList.toString().replaceAll("^\\[| |\\]$", "");
    }

    /**
     * 数字位补齐
     *
     * @param number
     *            数字
     * @param count
     *            位数
     * @return 补齐后的数字字符串
     */
    public static String numberToDigit(int number, int count) {
        String format = "";
        for (int i = 0; i < count; i++) {
            format += "0";
        }
        DecimalFormat df = new DecimalFormat(format);
        String numberString = df.format(number);
        return numberString;

    }

    /**
     * 判断手机格式是否正确
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNumber(String mobiles) {
        Pattern p = Pattern.compile("1\\d{10}$");
        Matcher m = p.matcher(mobiles);

        return m.matches();
    }

    /**
     * 判断email格式是否正确
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(email);

        return m.matches();
    }



    /**
     * 将将泛型为逗号间隔字符串形式转化为String类型的集合
     *
     * @param result
     * @return
     */
    public static ArrayList<String> formatListString(String result) {
        if (result == null || result.length() == 0) {
            return null;
        } else {
            String results[] = result.split(",");
            ArrayList<String> arrayList = new ArrayList<String>();
            for (int i = 0; i < results.length; i++) {
                arrayList.add(results[i]);
            }
            return arrayList;
        }
    }

    /**
     * 将一个int类型小于10且大于等于0的数字填充为说需要的字符串
     *
     * @param input
     * @param prefix
     * @return
     */
    public static String numberToOneDigit(int input, String prefix) {
        StringBuffer result = new StringBuffer();
        if (input >= 0 && input <= 9) {
            result.append(prefix + input);
        } else {
            result.append(input);
        }
        return result.toString();
    }

    public static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher matcher = pattern.matcher(str);

        return matcher.matches();
    }


}
