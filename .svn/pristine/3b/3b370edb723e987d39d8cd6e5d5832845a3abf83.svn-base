package com.hbw.library.utils;


import android.content.Context;
import android.widget.Toast;

public class ToastUtil {
private static Toast toast=null;
    private ToastUtil() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static boolean isShow = true;// 是否需要Toast，可以在application的onCreate函数里面初始化

    public static void show(Context context, String content) {
        if (isShow) {
            if (toast == null) {
                toast = Toast.makeText(context, content, Toast.LENGTH_SHORT);
            } else {
                toast.setText(content);
            }
            toast.show();

        }

        /*if (isShow)
            Toast.makeText(context, content, Toast.LENGTH_SHORT).show();*/
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, CharSequence message) {
        if (isShow) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();}
        /*if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();*/
    }

    /**
     * 短时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showShort(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, CharSequence message) {
        if (isShow) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();}
       /* if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();*/
    }

    /**
     * 长时间显示Toast
     *
     * @param context
     * @param message
     */
    public static void showLong(Context context, int message) {
        if (isShow)
            Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param duration
     * @param message
     */
    public static void show(Context context,CharSequence message,int duration) {
       /* if (isShow)
            Toast.makeText(context, message, duration).show();*/
        if (isShow) {
        if (toast == null) {
            toast = Toast.makeText(context, message, duration);
        } else {
            toast.setText(message);
        }
        toast.show();}
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param message
     * @param duration
     */
    public static void show(Context context, int message, int duration) {
       /* if (isShow)
            Toast.makeText(context, message, duration).show();*/
        if (isShow) {
        if (toast == null) {
            toast = Toast.makeText(context, message, duration);
        } else {
            toast.setText(message);
        }
        toast.show();}
    }
}
