package com.xiaomizhuang.buildcaptain.util;

import android.app.Activity;
import android.provider.Settings;
import android.view.WindowManager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/7.
 */

public class Utils {

    public static int screenBrightness;

    public static void setScreenalpha(final Activity activity){
        try
        {
            if(android.provider.Settings.System.getInt(activity.getContentResolver(),android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE) == android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
            {
                android.provider.Settings.System.putInt(activity.getContentResolver(),
                        android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE,
                        android.provider.Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }
        }
        catch (Settings.SettingNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        screenBrightness = (int)(android.provider.Settings.System.getInt(activity.getContentResolver(),
                android.provider.Settings.System.SCREEN_BRIGHTNESS, 0));
        //不让屏幕全暗
        if(screenBrightness<=1)
        {
            screenBrightness=1;
        }
        //设置当前activity的屏幕亮度

        //0到1,调整亮度暗到全亮
       /* final ArrayList arrayList=new ArrayList();
        for (int i=screenBrightness;i>screenBrightness/3;i--) {
            final WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
            lp.screenBrightness = Float.valueOf(i /255f);
            activity.getWindow().setAttributes(lp);
//            arrayList.add(i);
        }*/

            new Thread(new Runnable() {
            @Override
            public void run() {
                final WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
                for (int i=screenBrightness;i>screenBrightness/3;i--){
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final int finalI1 = i;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            lp.screenBrightness = Float.valueOf(finalI1 /255f);
                            activity.getWindow().setAttributes(lp);
                        }
                    });
                }
            }
        }).start();

    }
}
