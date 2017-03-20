package com.serbad.androidexample.common.utils;

import android.util.Log;

/**
 * Created by zhoutingjie on 2016/11/21.
 * <p>
 * 打印日志类
 */

public final class LogUtil {

    private static boolean isDebug = true;

    private static String tag = "xxxx";

    //如果要打印的信息超过250个字符就自动换行
    private static int bigChars = 250;

    public static void v(String tag, String msg, Throwable tr) {
        if (isDebug) {
            for (int i = 0; i <= msg.length() / bigChars; i++) {
                Log.v(tag, msg.substring(0 + bigChars * i, msg.length() > (bigChars + bigChars * i) ? (bigChars + bigChars * i) : msg.length()), tr);
            }
        }
    }

    public static void v(String tag, String msg) {
        if (isDebug) {
            for (int i = 0; i <= msg.length() / bigChars; i++) {
                Log.v(tag, msg.substring(0 + bigChars * i, msg.length() > (bigChars + bigChars * i) ? (bigChars + bigChars * i) : msg.length()));
            }
        }
    }

    public static void v(String msg) {
        if (isDebug) {
            for (int i = 0; i <= msg.length() / bigChars; i++) {
                Log.v(tag, msg.substring(0 + bigChars * i, msg.length() > (bigChars + bigChars * i) ? (bigChars + bigChars * i) : msg.length()));
            }
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (isDebug) {
            for (int i = 0; i <= msg.length() / bigChars; i++) {
                Log.i(tag, msg.substring(0 + bigChars * i, msg.length() > (bigChars + bigChars * i) ? (bigChars + bigChars * i) : msg.length()), tr);
            }
        }
    }

    public static void i(String tag, String msg) {
        if (isDebug) {
            for (int i = 0; i <= msg.length() / bigChars; i++) {
                Log.i(tag, msg.substring(0 + bigChars * i, msg.length() > (bigChars + bigChars * i) ? (bigChars + bigChars * i) : msg.length()));
            }
        }
    }

    public static void i(String msg) {
        if (isDebug) {
            for (int i = 0; i <= msg.length() / bigChars; i++) {
                Log.i(tag, msg.substring(0 + bigChars * i, msg.length() > (bigChars + bigChars * i) ? (bigChars + bigChars * i) : msg.length()));
            }
        }
    }
}
