package com.hbw.library.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 文件操作辅助类，类中集成的操作方法有：<br>
 * (1){@link getSdCardAvailableSize}() 获取SD 卡剩余空间<br>
 * <br>
 * (1){@link getSdCardAvailableSize}() 连接path 片断为完整的Path 路径 <br>
 * <br>
 * (1){@link deleteFile}() 删除文件或整个目录<br>
 * <br>
 * (1){@link clearDir}() 清除文件目录<br>
 * <br>
 * (1){@link saveStreamAsFile}() 将数据流保存为文件<br>
 * <br>
 * (1){@link getFileName}() 获取文件名称<br>
 * <br>
 * 
 * @author shine_sj
 * 
 */
public class FileUtil {

	/**
	 * 连接 path 片断为完整的Path 路径 例: <br/>
	 * joinPath("file:///","/sdcard","sample.txt") file:///sdcard/sample.txt
	 * joinPath("sdcard","yek") sdcard/yek
	 * 
	 * @param paths
	 *            path 片断
	 * @return 组合后的片断
	 */
	public static String joinPath(String... paths) {
		StringBuffer pathBuffer = new StringBuffer();
		boolean endWithSlash = false;
		for (String p : paths) {
			if ("".equals(p))
				continue;
			boolean startWithSlash = p.startsWith("/");
			if (endWithSlash && startWithSlash) {
				p = p.substring(1);
			} else if (!endWithSlash && !startWithSlash
					&& pathBuffer.length() > 0) {
				pathBuffer.append("/");
			}
			pathBuffer.append(p);
			endWithSlash = p.endsWith("/");
		}
		return pathBuffer.toString();
	}

	/**
	 * 删除文件或整个目录
	 * 
	 * @param file
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (File f : files) {
					deleteFile(f);
				}
				file.delete();
			}
		}
	}

	/**
	 * 清除文件目录
	 * 
	 * @param file
	 */
	public static void clearDir(File file) {
		if (file.exists() && file.isDirectory()) {
			File files[] = file.listFiles();
			for (File f : files) {
				deleteFile(f);
			}
		}
	}

	/**
	 * 将数据流保存为文件
	 * 
	 * @param input
	 *            要保存的数据流
	 * @param file
	 *            保存后的文件
	 * @return true 保存成功，false 保存失败
	 */
	public static boolean saveStreamAsFile(InputStream input, File file) {
		if (GetPhoneState.isSDCardAvailable()) {
			OutputStream os = null;
			byte buffer[] = null;
			try {
				buffer = new byte[input.available()];
				File work = file.getParentFile();
				if (!work.exists()) {
					work.mkdirs();
				}
				os = new BufferedOutputStream(new FileOutputStream(file));
				int length = 0;
				while ((length = input.read(buffer)) != -1) {
					os.write(buffer, 0, length);
				}
				os.flush();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			} finally {
				try {
					if (os != null) {
						os.close();
					}
					if (input != null) {
						input.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			return false;
		}
	}

	/**
	 * 获取文件名称
	 * 
	 * @param urlOrPath
	 *            url 或 文件路径
	 * @return 文件的名称
	 */
	public static String getFileName(String urlOrPath) {
		return urlOrPath.substring(urlOrPath.lastIndexOf("/") + 1);
	}
}
