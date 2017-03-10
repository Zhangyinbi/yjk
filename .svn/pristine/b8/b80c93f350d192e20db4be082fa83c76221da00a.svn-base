package com.xiaomizhuang.buildcaptain.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import com.xiaomizhuang.buildcaptain.activity.MessageActivity;

import java.io.IOException;

import cn.jpush.android.api.JPushInterface;

/**
 * 自定义jpush广播接收器
 *
 * @FileName: com.xiaomizhuang.buildcaptain.util.MyJpushReceiver.java
 * @author: Yangbang
 * @date: 2015-12-30 10:11
 */
public class MyJpushReceiver extends BroadcastReceiver {
    String TAG = "MyJpushReceiver";
    private Context context;
    private MediaPlayer player;
    private Vibrator vibrator;

    @Override
    public void onReceive(Context context, Intent intent) {
        this.context = context;
        Bundle bundle = intent.getExtras();
        String action = intent.getAction();
        Log.d(TAG, "onReceive - " + action);
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(action)) {
            MyApplication.registrationID = JPushInterface.getRegistrationID(context);
            Log.i(TAG, "registration_id-->" + MyApplication.registrationID);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(action)) {
            vibrator();//震动
            ring();//提示音
            System.out.println("收到了自定义消息。消息内容是：" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
            // 自定义消息不会展示在通知栏，完全要开发者写代码去处理
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(action)) {
            vibrator();//震动
            ring();//提示音
            System.out.println("收到了通知");
            // 在这里可以做些统计，或者做些其他工作
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(action)) {
            System.out.println("用户点击打开了通知");
            // 在这里可以自己写代码去定义用户点击后的行为
            Intent i = new Intent(context, MessageActivity.class);  //自定义打开的界面
            i.putExtra("titleType", "全部消息");
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
            JPushInterface.clearAllNotifications(context);
        } else {
            Log.d(TAG, "Unhandled intent - " + intent.getAction());

        }
    }


    //提示音
    private MediaPlayer ring() {
        Uri alert = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if (player == null) {
            player = new MediaPlayer();
        }
        try {
            player.setDataSource(context, alert);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        if (audioManager.getStreamVolume(AudioManager.STREAM_NOTIFICATION) != 0) {
            player.setAudioStreamType(AudioManager.STREAM_NOTIFICATION);
//            player.setLooping(true);
            try {
                player.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.start();
        }
        return player;
    }

    //震动
    private void vibrator() {
        /*
         * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到
         * */
        if (vibrator == null) {
            vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        }
        long[] pattern = {0, 100, 200, 300};
//        vibrator.vibrate(pattern, 1);           //重复两次上面的pattern 如果只想震动一次，index设
        vibrator.vibrate(1000);
    }

}
