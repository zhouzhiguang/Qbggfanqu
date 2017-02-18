package com.fanqu.dinner.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */

public class DateUtill {

    public static String[] formartString(String dateString, int future) {
        String startDay = dateString;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 EEEE");
        String[] result = new String[future];
        try {
            Date sd = sdf.parse(startDay);
            Calendar c = Calendar.getInstance();
            c.setTime(sd);
            result[0] = sdf.format(c.getTime());
            for (int i = 1; i < result.length; i++) {
                c.add(Calendar.DAY_OF_MONTH, 1);
                result[i] = sdf.format(c.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

    //获取当前详细的时分秒
    public static String getcurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 EEEE");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        return str;
    }

    public static String getcurrentDetailDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒 EEEE");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        return str;
    }
}
