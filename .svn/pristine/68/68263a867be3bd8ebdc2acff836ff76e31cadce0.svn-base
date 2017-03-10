package com.hbw.library.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * 描述: App工具
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-07-20 18:16
 */
public class AppUtils {

	/**
	 * 判断是否是DEBUG模式
	 * @param context
	 * @return
	 */
	public static boolean isApkDebugable(Context context) {
		try {
			ApplicationInfo info= context.getApplicationInfo();
			return (info.flags&ApplicationInfo.FLAG_DEBUGGABLE)!=0;
		} catch (Exception e) {

		}
		return false;
	}
}
