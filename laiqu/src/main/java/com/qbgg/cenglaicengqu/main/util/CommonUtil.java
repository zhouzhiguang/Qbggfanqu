package com.qbgg.cenglaicengqu.main.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.qbgg.cenglaicengqu.LaiquApplication;
import com.qbgg.cenglaicengqu.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Wayne
 */
public class CommonUtil {

    public static final int BUFFER_SIZE = 4096;
    public static final int MAX_STRING_LENGTH = 1024000;
    private static final double EARTH_RADIUS = 6378137;

    /**
     * @param str
     * @return Md 加密数据
     */
    public static String md5(String str) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(str.getBytes());
            byte b[] = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : b) {
                String hex = Integer.toHexString(0xFF & aByte);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            return sb.toString().toLowerCase();
        } catch (NoSuchAlgorithmException ignored) {

        }
        return "";
    }

    public static File getCacheDir() {
        return LaiquApplication.getInstance().getExternalCacheDir();
    }

    public static boolean isNetworkReachable() {
        ConnectivityManager cm = (ConnectivityManager) LaiquApplication.getInstance()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo current = cm.getActiveNetworkInfo();
        return current != null && (current.isAvailable());
    }

    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

    public static String readFileToString(File file, String charset) {
        if (file == null)
            return null;
        String data = null;
        byte[] buffer = new byte[BUFFER_SIZE];
        ByteArrayOutputStream out = new ByteArrayOutputStream(BUFFER_SIZE);
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            for (int len = -1; (len = in.read(buffer)) != -1; ) {
                out.write(buffer, 0, len);
                if (out.size() > MAX_STRING_LENGTH) {
                    Log.e("IoUtils", "File too large, maybe not a string. " + file.getAbsolutePath());
                    return null;
                }
            }
            data = out.toString(charset);
        } catch (IOException e) {
            e.printStackTrace();
            data = null;
        } finally {
            if (in != null)
                try {
                    in.close();
                } catch (IOException e) {
                }
            try {
                out.close();
            } catch (IOException e) {
            }
            in = null;
            out = null;
            buffer = null;
        }
        return data;
    }

    public static boolean saveStringToFile(String data, File file, String charset) {
        if (file == null || data == null)
            return false;
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            out.write(data.getBytes(charset));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                try {
                    out.close();
                } catch (IOException e) {
                }
        }
        return false;
    }


    public static ColorDrawable getCachePlaceHolder() {
        return new ColorDrawable(0xffdcdcdc);
    }

    public static String getInterval(String createTime) {
        String interval = null;

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date d1 = sd.parse(createTime, pos);

        long time = new Date().getTime() - d1.getTime();
        if (time / 1000 < 10) {
            interval = "刚刚";
        } else if (time / 3600000 < 24 && time / 3600000 > 0) {
            int h = (int) (time / 3600000);
            interval = h + "小时前";
        } else if (time / 60000 < 60 && time / 60000 > 0) {
            int m = (int) ((time % 3600000) / 60000);
            interval = m + "分钟前";
        } else if (time / 1000 < 60 && time / 1000 > 0) {
            int se = (int) ((time % 60000) / 1000);
            interval = se + "秒前";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            ParsePosition pos2 = new ParsePosition(0);
            Date d2 = sdf.parse(createTime, pos2);

            interval = sdf.format(d2);
        }
        return interval;
    }

    public static String getNextDayTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) + 1);
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return getTimeString(calendar);
    }

    public static String getTimeString(Calendar calendar) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(calendar.getTime());
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static String getDistance(double lng1, double lat1, double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        if (s > 1000) {
            return String.format("%skm", Math.round(s / 1000 * 10) / 10.f);
        }
        return String.format("%sm", s);
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return context.getString(R.string.version_name) + version;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

