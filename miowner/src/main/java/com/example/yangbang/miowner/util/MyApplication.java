package com.example.yangbang.miowner.util;

import com.example.yangbang.miowner.entity.OwnerUser;
import com.hbw.library.InitApplication;

import java.util.Map;

/**
 * MyApplication
 *
 * @FileName: com.example.yangbang.miowner.util.MyApplication.java
 * @author: Yangbang
 * @date: 2015-12-15 11:14
 */
public class MyApplication extends InitApplication {
    protected static MyApplication application;
    private OwnerUser ownerUser = null;
    public static String TOKEN = "";
    public static boolean isPublishSuccess = false;//是否发布成功

    // 用于存放倒计时时间
    public static Map<String, Long> TimeButtonMap;

    @Override
    public void onCreate() {
        super.FileName = "小米装业主APP";
        super.onCreate();
        application = this;
    }

    public static MyApplication getApp() {
        return application;
    }

    public OwnerUser getOwnerUser() {
        return ownerUser;
    }

    public void setOwnerUser(OwnerUser ownerUser) {
        this.ownerUser = ownerUser;
    }
}
