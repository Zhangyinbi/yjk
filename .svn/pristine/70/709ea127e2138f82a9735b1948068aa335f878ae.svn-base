package com.xiaomizhuang.buildcaptain.util;

import com.hbw.library.InitApplication;
import com.hbw.library.utils.SPUtils;
import com.xiaomizhuang.buildcaptain.entity.BuildSite;

import cn.jpush.android.api.JPushInterface;

/**
 * #0001    mwy     2016/07/21   添加线下线上切换
 */
public class MyApplication extends InitApplication {
    public String SDCARDROOT;
    // 网络是否连接
    public static boolean netWorkFlag = false;
    public static final String DEFAULT_APPKEY = "6bec7445-8478-4ec3-8bf0-2334c6f818ad";
    public static String APPKEY = DEFAULT_APPKEY;
    public static String IP = null;
    public static int PORT = -1;

    public static boolean isStatusUpdateSucceed = false;
    public static boolean isAssessSucceed = false;
    public static boolean isRegistSucceed = false;
    public static String baoming_id;
    public static String TOKEN = "";
    public static String UID = "";
    public static String registrationID = "";

    private BuildSite BuildSite;


    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        getUserInfoFormSP();
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
        registrationID = JPushInterface.getRegistrationID(getApplicationContext());
	    //如果是debug模式  默认线下 只有在debug模式下 才能切换线上线下 <#0001
        if (InitApplication.getIsDebug()){
            host = HttpConstant.HOST_OFFLINE;
            picHost = HttpConstant.IMAGES_ROOT_URL;
	        //从sharedPreference中 看是否有isOnline
	        isOnline = (boolean) SPUtils.get(this, AppFlag.ISONLINE, false);
			if (isOnline == false){
				SPUtils.put(this,AppFlag.ISONLINE,false);
				isOnline = false;
			}else {
				isOnline = true;
			}
        }else{//如果是release 模式  默认线上
            host = HttpConstant.HOST_ONLINE;
            picHost = HttpConstant.IMAGES_ROOT_URL;
        } // #0001/>

    }

    private void getUserInfoFormSP() {
        TOKEN = (String) SPUtils.get(getApplicationContext(), AppFlag.TOKEN, "");
        UID = (String) SPUtils.get(getApplicationContext(), AppFlag.UID, "");
    }

    protected static MyApplication application;

    public static MyApplication getApp() {
        return application;
    }

    public com.xiaomizhuang.buildcaptain.entity.BuildSite getBuildSite() {
        return BuildSite;
    }

    public void setBuildSite(com.xiaomizhuang.buildcaptain.entity.BuildSite buildSite) {
        BuildSite = buildSite;
    }

}
