package com.hbw.library.utils;

import android.os.Environment;
import android.os.StatFs;

import com.hbw.library.InitApplication;

import java.io.File;

/**
 * SD 卡相关操作类
 * 
 * @author shien.sj
 * 
 */
public class SDCardHelper {

	private static SDCardHelper mSDCardHelper = new SDCardHelper();

	private SDCardHelper() {

	}

	public static SDCardHelper getInstance() {
		return mSDCardHelper;
	}

	/**
	 * 获得sd卡的可用空间
	 * 
	 * @return 可用字节数
	 */
	@SuppressWarnings("deprecation")
	public long getAvailableSdCardSize() {
		String path = Environment.getExternalStorageDirectory().getPath();
		StatFs statFs = new StatFs(path);
		long blockSize = statFs.getBlockSize();
		int available = statFs.getAvailableBlocks();
		return available * blockSize;
	}

	/**
	 * 检查是否安装了sd卡
	 * 
	 * @return false 未安装
	 */
	public boolean sdCardMounted() {
		final String state = Environment.getExternalStorageState();
		if (state.equals(Environment.MEDIA_MOUNTED)
				&& !state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
			return true;
		}
		return false;
	}

	/**
	 * 获取SD卡路径
	 * 
	 * @return
	 */
	public String getSDPath() {
		File sdDir = null;
		boolean sdCardExist = Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
		if (sdCardExist) {// 存在
			sdDir = Environment.getExternalStorageDirectory();// 获取根目录
		} else {
			return InitApplication.FileName;
		}
		return sdDir.toString() +File.separator+ InitApplication.FileName;
	}
}
