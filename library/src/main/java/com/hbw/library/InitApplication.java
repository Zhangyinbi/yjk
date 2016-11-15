package com.hbw.library;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hbw.library.utils.AppUtils;
import com.hbw.library.utils.CrashHandler;
import com.hbw.library.utils.FileCache;
import com.hbw.library.utils.L;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * 初始化应用程序相关配置信息，在程序第一次启动时初始化类中的所有参数方法。 例如：图片缓存路径、crash默认跳转Activity、是否是调试模式等。
 *
 */
public abstract class InitApplication extends Application {

    /**
     * 是否是测试模式,打包上线APK的时候一定要更改
     */
    private static boolean isDebug = true;

    /**
     * 初始化默认跳转Activity
     */
    private static Class<?> errorProcessActivity;

    /**
     * 首页按返回键的次数
     */
    public static int BackKeyCount = 0;

    /**
     * 是否使用自动堆栈管理
     */
    public static boolean isUseActivityManager = true;

    /**
     * 单例模式application
     */
    private static InitApplication application = null;

    /**
     * 请求工具volley
     */
    public static RequestQueue mRequestQueue = null;

    /**
     * ImageLoader图片存放地址
     */
    public static String FileName = "小米装项目经理";

    /**
     * 图片加载imageloader
     */
    public static ImageLoader imageLoader = null;
    public static DisplayImageOptions options = null;

    public static FileCache fileCache;

	/**
	 * //#0001
	 * 接口主地址 例如 http://api.xiaomizhuang.com
	 */
	public static String host;

	/**
	 * 图片主地址  //#0001
	 */
	public static String picHost;

	/**
	 * 默认为线下模式  0线下  1线上
	 */
	public static boolean isOnline = false;

    @Override
    public void onCreate() {
        super.onCreate();        //获取是DEBUG模式 还是release 模式
	    boolean isdebug = AppUtils.isApkDebugable(this);
	    isDebug = isdebug;
        fileCache = new FileCache(getApplicationContext());
        //初始化Volley请求队列
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        //开启Volley
        mRequestQueue.start();
        // 设置工程是否打印LOG
        L.isDebug = isDebug;
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
        //初始化图片加载工具
        initImageLoader(getApplicationContext());
    }

    // 单例模式中获取唯一的MyApplication实例
    //暂时先屏蔽
/*    public static InitApplication getInstance() {
        if (null == application) {
            application = new InitApplication();
        }
        return application;
    }*/

    /**
     * 设置是否是测试模式
     */
    @SuppressWarnings("static-access")
    public void setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
    }

    /**
     * 获取当前是否是测试模式。true为测试环境，false为正式环境
     *
     * @return
     */
    public static boolean getIsDebug() {
        return isDebug;
    }

    /**
     * 设置如果程序奔溃跳转页
     *
     * @param errorActivity
     */
    public static void setErrorProcessActivity(Class<?> errorActivity) {
        errorProcessActivity = errorActivity;
    }

    /**
     * 获取如果程序奔溃跳转页
     *
     * @return
     */
    public static Class<?> getErrorProcessActivity() {
        return errorProcessActivity;
    }

    /**
     * 初始化图片加载工具
     */
    private void initImageLoader(Context context) {
        File cacheDir = StorageUtils.getOwnCacheDirectory(context, FileName + "/image/");
        // 默认配置对象
        DisplayImageOptions.Builder oBuilder = new DisplayImageOptions.Builder()
                .imageScaleType(ImageScaleType.EXACTLY)// 图片的缩放类型
                .cacheInMemory(true)// 缓存到内存
                .cacheOnDisc(true)// 缓存到SD卡
                .showImageOnLoading(R.color.default_img_color)// 加载开始前的默认图片
                .showImageForEmptyUri(R.color.default_img_color) // URL为空会显示该图片
                .showImageOnFail(R.color.default_img_color); // 加载图片出现问题，会显示该图片
        // .displayer(new RoundedBitmapDisplayer(5));// 图片圆角显示，值为整数
        options = oBuilder.build();
        ImageLoaderConfiguration.Builder cBuilder = new ImageLoaderConfiguration.Builder(
                context).defaultDisplayImageOptions(options);
        ImageLoaderConfiguration config = cBuilder
                // 线程池大小
                .threadPoolSize(5)
                        // 线程优先级
                .threadPriority(Thread.NORM_PRIORITY - 2)
                        // 缓存大小
                .memoryCacheSize(10 * 1024 * 1024)
                .denyCacheImageMultipleSizesInMemory()
                .discCache(new UnlimitedDiscCache(cacheDir))
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
        imageLoader = ImageLoader.getInstance();
        //.enableLogging()
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mRequestQueue != null) {
            mRequestQueue.stop();
        }
    }
}
