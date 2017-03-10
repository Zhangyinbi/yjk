package com.youjuke.miprojectmanager.util;

import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @Description: view工具类
 * @author machuang
 * * @version 创建时间：2015年3月20日10:27:50
 * 
 */
public class ViewUtil {

	private static LayoutInflater inflater;

	public static View buildView(int layout) {
		return buildView(layout, null);
	}

	public static View buildView(int resource, ViewGroup root) {
		return buildView(resource, root, root != null);
	}

	/**
	 * @Description: 创建视图
	 */
	public static View buildView(int resource, ViewGroup root,
			boolean attachToRoot) {
		return getInflater().inflate(resource, root, attachToRoot);
	}

	private static LayoutInflater getInflater() {
		if (null == inflater) {
			inflater = LayoutInflater.from(getApp());
		}
		return inflater;
	}

	public static Application getApp() {
		return MyApplication.getApp();
	}
}