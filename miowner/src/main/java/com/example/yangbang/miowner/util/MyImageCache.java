package com.example.yangbang.miowner.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Imageloder 的图片缓存类
 * @author Youjk
 *
 */
public class MyImageCache implements ImageCache {
	private LruCache<String, Bitmap> lruCache = null;
	private Map<String, SoftReference<Bitmap>> map = null;
	private static MyImageCache myImageCache = null;
	private static Context context;

	private MyImageCache() {
		int memorySize = (int) Runtime.getRuntime().maxMemory();
		int lrucacheSize = memorySize / 8;
		lruCache = new LruCache<String, Bitmap>(lrucacheSize) {
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}
		};
		map = new HashMap<String, SoftReference<Bitmap>>();
	}

	public static MyImageCache getInstance(Context context1) {
		if (myImageCache == null) {
			myImageCache = new MyImageCache();
		}
		context = context1;
		return myImageCache;
	}

	@Override
	public Bitmap getBitmap(String url) {
		// Bitmap bitmap = null;
		// if (lruCache != null) {
		// bitmap = lruCache.get(url);
		// }
		// if (bitmap != null) {
		//
		// }
		return lruCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
//		SDHelper.getInstance().saveBitmapToSDCardPrivateCacheDir(bitmap, url,
//				context);
		lruCache.put(url, bitmap);
	}

	static class SDHelper {
		public static SDHelper helper1;

		public static SDHelper getInstance() {
			if (helper1 == null) {
				helper1 = new SDHelper();
			}
			return helper1;
		}

		// 判断sd是否挂载
		public boolean isSDCardMounted() {
			return Environment.getExternalStorageState().equals(
					Environment.MEDIA_MOUNTED);
		}

		// 保存bitmap图片到SDCard的私有Cache目录
		public boolean saveBitmapToSDCardPrivateCacheDir(Bitmap bitmap,
				String fileName, Context context) {

			if (isSDCardMounted()) {
				// 获取私有的Cache缓存目录
				File file = context.getExternalCacheDir();
				File file1 = new File(file, fileName);
				if (!file1.exists()) {
					file1.mkdirs();
					FileOutputStream fos = null;
					try {
						fos = new FileOutputStream(file1);
//						Log.d("11111111", "11111111111111");
					} catch (FileNotFoundException e) {
//						Toast.makeText(context, "11111111111211", 0).show();
						e.printStackTrace();
					}
					if (fileName != null
							&& (fileName.contains(".png") || fileName
									.contains(".PNG"))) {
						bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
					} else {
//						Toast.makeText(context, "11111111111211", 0).show();
						bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
					}
				}
				return true;
			} else {
				return false;
			}
		}
	}
}