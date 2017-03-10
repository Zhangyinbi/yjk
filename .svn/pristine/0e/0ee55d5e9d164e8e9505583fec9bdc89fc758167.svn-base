package com.hbw.library;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;

import com.hbw.library.utils.L;

/**
*管理Activity
*@author hebiwen
*created at 2015/9/2 10:10
*/
public class ActivityManager {

    private List<Activity> activities = new LinkedList<Activity>();

    private static ActivityManager manager;

    /**
     * activity 存在标志
     */
    private boolean isExist = false;

    /**
     * 首页所在的activity所对应的类名，必须在打开首页设置此项
     */
    public static Class<?> homeActivity;

    /**
     * 底部导航类集合
     */
    public static List<Class<?>> bottomActivities = new LinkedList<Class<?>>();

    /**
     * 获得 activity管理对象
     *
     * @return
     */
    public static ActivityManager getActivityManager() {
        if (null == manager) {
            manager = new ActivityManager();
        }
        return manager;
    }

    /**
     * 添加新的activity
     *
     * @param activity
     * @return
     */
    public boolean add(Activity activity) {

        int position = 0;
        // 判断是否自动清除非子activity
        if (InitApplication.isUseActivityManager) {
            // 导航栏activity进栈，删除非导航栏activity
            if (isBottomActivity(activity)) {
                for (int i = 0; i < activities.size() - 1; i++) {

                    if (!isBottomActivity(activities.get(i))) {
                        popActivity(activities.get(i));
                        i--;
                    }
                    if (i > 0) {
                        // 获得重复activity位置
                        if (activities.get(i).getClass().equals(activity.getClass())) {
                            isExist = true;
                            position = i;
                        }
                    }
                }
            }
        }

        if (!activities.add(activity)) {
            return false;
        }
        // 删除重复activity
        if (isExist) {
            isExist = false;
            activities.remove(position);
        }

        return true;
    }

    /**
     * 关闭除参数activity外的所有activity
     *
     * @param activity
     */
    public void finish(Activity activity) {
        for (Activity iterable : activities) {
            if (activity != iterable) {
                iterable.finish();
            }
        }
    }

    /**
     * 关闭所有的activity
     */
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        L.i("退出系统！！！");
        System.exit(0);
    }

    /**
     * 删除指定activity
     *
     * @param activity
     */
    public void popActivity(Activity activity) {

        if (activity != null) {
            activity.finish();
            activities.remove(activity);
        }
    }

    /**
     * 获得当前activity
     *
     * @return
     */
    public Activity currentActivity() {
        Activity activity = activities.get(activities.size() - 1);
        return activity;
    }

    /**
     * activity是否为底部导航
     *
     * @return
     */
    public boolean isBottomActivity(Activity activity) {

        for (int i = 0; i < bottomActivities.size(); i++) {
            if (activity.getClass() == bottomActivities.get(i)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 如需返回homeActivity则返回homeActivity
     *
     * @param context
     */
    public void backIndex(Context context) {
        if (activities.size() <= 0) {
            return;
        }
        if (isBottomActivity(activities.get(activities.size() - 1))) {
            Intent intent = new Intent();
            intent.setClass(context, homeActivity);
            context.startActivity(intent);
        }
    }

    /**
     * 删除已经finish的activity
     *
     * @param activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activities.remove(activity);
        }
    }

    /**
     * 初始化，存储底部导航类
     *
     * @param activityClass
     */
    public void setBottomActivities(Class<?> activityClass) {
        if (activityClass != null) {
            bottomActivities.add(activityClass);
        }
    }


    /***
     * 拿到上一个activity
     * @return
     */
    public Activity getPrevActivity(){
        return activities.size()>2 ? activities.get(activities.size()-2):null;
    }


    /***
     * 拿到第一个activity
     * @return
     */
    public Activity getFirstActivity(){
        return  activities.size()>0 ? activities.get(0):null;
    }

}
