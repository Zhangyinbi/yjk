package com.hbw.library.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

/**
 * 拍照、相册，选择相片后剪切
 * 
 * @author shine.sj
 * @E-mail shine.sj@hotmail.com
 * @time 2013-12-24 上午11:31:07 Class Description
 */
public class ImageCut {
	/** 拍照 */
	public static final int imgRequestCode = 23;
	/** 剪切 */
	public static final int imgCaptureCrop = 24;
	/** 相册 */
	public static final int pickedData = 26;
	/** 拍照图片名字 */
	private static String imgName = "/loft.jpg";
	/** 图片地址 */
	private static String imageDate = "";

	public ImageCut() {

	}

	/**
	 * 获取建材好的图片数据
	 * 
	 * @return
	 */
	public String getImageDate() {
		return imageDate;
	}

	/**
	 * 获取图片命名
	 * 
	 * @return
	 */
	public static String getPhotoFileName() {
		return imgName;
	}

	/**
	 * 给图片命名
	 * 
	 * @return
	 */
	public static void setPhotoFileName(String name) {
		imgName = name;
	}

	/**
	 * 拍照
	 */
	public void getPicFromCapture(Activity con) {
		/**
		 * 下面这句还是老样子，调用快速拍照功能，至于为什么叫快速拍照，大家可以参考如下官方
		 * 文档，you_sdk_path/docs/guide/topics/media/camera.html
		 * 我刚看的时候因为太长就认真看，其实是错的，这个里面有用的太多了，所以大家不要认为
		 * 官方文档太长了就不看了，其实是错的，这个地方小马也错了，必须改正
		 */
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// 下面这句指定调用相机拍照后的照片存储的路径
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
				SDCardHelper.getInstance().getSDPath(), getPhotoFileName())));
		con.startActivityForResult(intent, imgRequestCode);
	}

	/**
	 * 从相册
	 */
	public void getPicFromPicked(Activity con) {
		/**
		 * 刚开始，我自己也不知道ACTION_PICK是干嘛的，后来直接看Intent源码，
		 * 可以发现里面很多东西，Intent是个很强大的东西，大家一定仔细阅读下
		 */
		Intent intent = new Intent(Intent.ACTION_PICK, null);

		/**
		 * 下面这句话，与其它方式写是一样的效果，如果：
		 * intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		 * intent.setType(""image/*");设置数据类型
		 * 如果朋友们要限制上传到服务器的图片类型时可以直接写如："image/jpeg 、 image/png等的类型"
		 * 这个地方小马有个疑问，希望高手解答下：就是这个数据URI与类型为什么要分两种形式来写呀？有什么区别？
		 */
		intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
				"image/*");
		con.startActivityForResult(intent, pickedData);
	}

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Activity con, Uri uri, int w, int h) {
		/*
		 * 至于下面这个Intent的ACTION是怎么知道的，大家可以看下自己路径下的如下网页
		 * yourself_sdk_path/docs/reference/android/content/Intent.html
		 * 直接在里面Ctrl+F搜：CROP ，之前小马没仔细看过，其实安卓系统早已经有自带图片裁剪功能, 是直接调本地库的，小马不懂C C++
		 * 这个不做详细了解去了，有轮子就用轮子，不再研究轮子是怎么 制做的了...吼吼
		 */
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", w);
		intent.putExtra("outputY", h);
		intent.putExtra("return-data", true);
		con.startActivityForResult(intent, imgCaptureCrop);
	}

	/**
	 * 裁剪图片方法实现
	 * 
	 * @param uri
	 */
	public void startPhotoZoomSj(Activity con, Uri uri, int w, int h) {
		/*
		 * 至于下面这个Intent的ACTION是怎么知道的，大家可以看下自己路径下的如下网页
		 * yourself_sdk_path/docs/reference/android/content/Intent.html
		 * 直接在里面Ctrl+F搜：CROP ，之前小马没仔细看过，其实安卓系统早已经有自带图片裁剪功能, 是直接调本地库的，小马不懂C C++
		 * 这个不做详细了解去了，有轮子就用轮子，不再研究轮子是怎么 制做的了...吼吼
		 */
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 下面这个crop=true是设置在开启的Intent中设置显示的VIEW可裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		// intent.putExtra("aspectX", 1);
		// intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		// intent.putExtra("outputX", w);
		// intent.putExtra("outputY", h);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("return-data", true);
		con.startActivityForResult(intent, imgCaptureCrop);
	}

	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param picdata
	 */
	@SuppressWarnings("deprecation")
	public void setPicToView(Intent picdata, View img) {
		Bundle extras = picdata.getExtras();
		if (extras != null) {
			Bitmap photo = extras.getParcelable("data");

			Drawable drawable = new BitmapDrawable(photo);

			/**
			 * 下面注释的方法是将裁剪之后的图片以Base64Coder的字符方式上 传到服务器，QQ头像上传采用的方法跟这个类似
			 */
			/**
			 * 下面注释的方法是将裁剪之后的图片以Base64Coder的字符方式上 传到服务器，QQ头像上传采用的方法跟这个类似
			 */
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			photo.compress(Bitmap.CompressFormat.JPEG, 30, stream);
			byte[] b = stream.toByteArray(); // 将图片流以字符串形式存储下来
			imageDate = new String(Base64.encodeToString(b, Base64.DEFAULT))
					.replaceAll("\n", "");

			// 这个地方大家可以写下给服务器上传图片的实现，直接把tp直接上传就可以了， 服务器处理的方法是服务器那边的事了，吼吼
			// 如果下载到的服务器的数据还是以Base64Coder的形式的话，可以用以下方式转换 为我们可以用的图片类型就OK啦...吼吼
			// Bitmap dBitmap = BitmapFactory.decodeFile(tp);
			// Drawable drawable = new BitmapDrawable(dBitmap);
			((ImageView) img).setImageDrawable(drawable);
			// img.setBackgroundDrawable(drawable);
			File temp = new File(SDCardHelper.getInstance().getSDPath()
					+ getPhotoFileName());
			temp.deleteOnExit();

		}
	}

	/**
	 * 保存裁剪之后的图片数据
	 * 
	 * @param bm
	 * @param img
	 */
	public void setPicToViewSj(Bitmap bm, View img) {
		if (bm != null) {
			/**
			 * 下面注释的方法是将裁剪之后的图片以Base64Coder的字符方式上 传到服务器，QQ头像上传采用的方法跟这个类似
			 */
			/**
			 * 下面注释的方法是将裁剪之后的图片以Base64Coder的字符方式上 传到服务器，QQ头像上传采用的方法跟这个类似
			 */
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			bm.compress(Bitmap.CompressFormat.JPEG, 60, stream);
			byte[] b = stream.toByteArray(); // 将图片流以字符串形式存储下来
			imageDate = new String(Base64.encodeToString(b, Base64.DEFAULT))
					.replaceAll("\n", "");

			// 这个地方大家可以写下给服务器上传图片的实现，直接把tp直接上传就可以了， 服务器处理的方法是服务器那边的事了，吼吼
			// 如果下载到的服务器的数据还是以Base64Coder的形式的话，可以用以下方式转换 为我们可以用的图片类型就OK啦...吼吼
			// Bitmap dBitmap = BitmapFactory.decodeFile(tp);
			// Drawable drawable = new BitmapDrawable(dBitmap);
			// ((ImageView) img).setImageDrawable(drawable);
			// img.setBackgroundDrawable(drawable);
			File temp = new File(SDCardHelper.getInstance().getSDPath()
					+ getPhotoFileName());
			temp.deleteOnExit();

		}
	}

}
