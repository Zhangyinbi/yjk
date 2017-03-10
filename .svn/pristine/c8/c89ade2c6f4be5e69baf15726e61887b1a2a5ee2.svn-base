package com.hbw.library;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.hbw.library.minterface.UserLogOut;
import com.hbw.library.net.AnalyzeJson;
import com.hbw.library.utils.L;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.view.TitleBar;

import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * BaseFragmentActivity
 *
 * @FileName: com.hbw.library.BaseFragmentActivity.java
 * @author: Yangbang
 * @date: 2015-12-17 14:32
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements Handler.Callback {

    private static final String TAG = "tag_activity";
    /**
     * 单条请求数据成功标示
     */
    public static final int REQUEST_SUCCESS = 8888;// 请求成功

    /**
     * 网络超时
     */
    public static final int TIMEOUT = -1;//网络超时

    /**
     * 非200状态吗，请求失败或者错误
     */
    public static final int ERROR = -2;//400请求失败

    /**
     * 网络不可用
     */
    public static final int NONET = -3;//网络不可用

    /**
     * 数据异常、或者解析异常
     */
    public static final int DATAERROR = -4;//数据异常，数据解析错误等

    /**
     * handler
     */
    protected Handler handler;

    /**
     * 网络请求工具
     */
    protected AnalyzeJson analyzeJson;

    /**
     * 是否显示自定义的头部
     */
    private boolean isShowTitleBar = true;

    /**
     * 是否为首页
     */
    private boolean isHomeActivity = false;

    /**
     * 是否全屏
     */
    private boolean isFullScreen = false;

    /**
     * 屏幕参数
     */
    public DisplayMetrics mDisplayMetrics = null;

    /**
     * 退出方式 0：弹出框，1：两次退出
     */
    private static int exitType = 1;

    /**
     * 是否在前台
     */
    public static boolean isActive = true;

    protected Gson gson = null;

    protected HashMap<String, String> params = null;

    /**
     * BaseActivity的布局文件
     */
    protected LinearLayout contentContainer = null;

    /**
     * 自定义头部
     */
    protected TitleBar titleBar = null;

    private UserLogOut userLogOut = null;

    private boolean isShowAnimation = true;//默认加载动画

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.AppTheme);
        L.i(TAG, this.getClass().getSimpleName() + "[被创建]--->onCreate");
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        handler = new Handler(this);
        analyzeJson = new AnalyzeJson(this, handler);
        gson = new Gson();
        params = new HashMap<>();
        //初始化值，比如是否显示自定义头部，是否全屏等等。
        initData();
        // 设置layout布局
        initBaseView();
        // 判断是否全屏 true为全屏 false为不全屏
        if (getIsFullScreen()) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        /** 初始化设备屏幕参数 */
        mDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
        // 将每一个新开的acitivity放在activity管理集合中
        ActivityManager.getActivityManager().add(this);
        if (isHomeActivity) {
            ActivityManager.homeActivity = getClass();
        }
        //初始化各种组件
        initWidget();

    }

    private void initBaseView() {
        View view = LayoutInflater.from(this).inflate(R.layout.base_activity, null);
        contentContainer = (LinearLayout) view.findViewById(R.id.base_activity_content);
        titleBar = (TitleBar) view.findViewById(R.id.base_activity_title_bar);
        //是否隐藏自定义头部
        if (!isShowTitleBar()) {
            titleBar.setVisibility(View.GONE);
        }
        if (initPageLayoutID() != 0) {
            View contentView = LayoutInflater.from(this).inflate(initPageLayoutID(), null);
            contentView.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
            contentContainer.addView(contentView);
            setContentView(view);
        }
    }

    /**
     * oncreate方法中调用，初始化界面各部分控件
     */
    protected abstract void initWidget();

    /**
     * initData 初始化数据
     */
    protected abstract void initData();

    /**
     * 生成主文件布局ID
     */
    protected abstract int initPageLayoutID();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!isAppOnForeground()) {
            // 全局变量isActive = false 记录当前已经进入后台
            isActive = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 将当前acitivity移除activity管理集合中
        ActivityManager.getActivityManager().removeActivity(this);
        L.i(TAG, this.getClass().getSimpleName() + "--->onDestroy");
    }

    /**
     * 底部导航栏点击返回跳到首页操作，如果是首页则执行退出操作
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        ActivityManager.getActivityManager();
        // 先判断是否是返回键
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            // 判断是不是首页
            if (this.getClass() != ActivityManager.homeActivity) {
                // 如果不是首页但是是底部导航则执行跳转到首页操作
                if (ActivityManager.getActivityManager().isBottomActivity(this)) {
                    ActivityManager.getActivityManager().backIndex(this);
                } else {
                    return super.onKeyDown(keyCode, event);
                }
            } else {
                // 首页按返回键提示是否退出
                showExit();
            }
        }
        return false;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        if (isShowAnimation)
            overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);
        if (isShowAnimation)
            overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    /**
     * 结束当前activity
     */
    protected void finishActivity() {
        ActivityManager.getActivityManager().popActivity(this);
        if (isShowAnimation)
            overridePendingTransition(0, R.anim.base_slide_right_out);
    }

    @Override
    public void onBackPressed() {
        finishActivity();
        if (isShowAnimation)
            overridePendingTransition(0, R.anim.base_slide_right_out);
    }

    /**
     * 退出的方式
     */
    private void showExit() {
        switch (exitType) {
            case 0:
                new AlertDialog.Builder(this)
                        .setTitle("温馨提示")
                        .setMessage("确定要退出吗？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                ActivityManager.getActivityManager().exit();
                                finishActivity();
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                break;
            case 1:
                InitApplication.BackKeyCount++;
                if (InitApplication.BackKeyCount >= 2) {
                    if (userLogOut != null) {
                        userLogOut.logout();
                    }
                    ActivityManager.getActivityManager().exit();
                    finishActivity();
                } else {
                    ToastUtil.show(this, "再按一次退出程序");
                    //10秒之后BackKeyCount设置为0
                    new Thread(new Runnable() {
                        public void run() {
                            Timer timer = new Timer();
                            timer.schedule(new MyTask(), 10000);
                        }
                    }).start();
                }
                break;
        }
    }

    /**
     * 指定时间后执行task任务
     */
    class MyTask extends TimerTask {
        @Override
        public void run() {
            InitApplication.BackKeyCount = 0;
        }
    }

    /**
     * 设置退出方式
     */
    public void setExitType(int exitType) {
        this.exitType = exitType;
    }

    /**
     * 获取退出方式
     */
    public int getExitType() {
        return exitType;
    }

    /**
     * 设置是否全屏
     */
    public void setIsFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    /**
     * 获取是否全屏
     */
    public boolean getIsFullScreen() {
        return isFullScreen;
    }

    /**
     * 设置是否是首页
     */
    public void setisHomeActivity(boolean isHome) {
        isHomeActivity = isHome;
    }

    /**
     * 获取是否是首页
     */
    public boolean getIsHomeActivity() {
        return isHomeActivity;
    }

    public boolean isShowTitleBar() {
        return isShowTitleBar;
    }

    public void setIsShowTitleBar(boolean isShowTitleBar) {
        this.isShowTitleBar = isShowTitleBar;
    }

    /**
     * APP是否在后台
     */
    public boolean isAppOnForeground() {
        // Returns a list of application processes that are running on the device
        android.app.ActivityManager activityManager = (android.app.ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();
        List<android.app.ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;
        for (android.app.ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName) && appProcess.importance == android.app.ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }
        return false;
    }

    public UserLogOut getUserLogOut() {
        return userLogOut;
    }

    public void setUserLogOut(UserLogOut userLogOut) {
        this.userLogOut = userLogOut;
    }

    public boolean isShowAnimation() {
        return isShowAnimation;
    }

    public void setIsShowAnimation(boolean isShowAnimation) {
        this.isShowAnimation = isShowAnimation;
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

}
