package com.example.yangbang.miowner.entity;

import android.graphics.Bitmap;

import java.io.Serializable;

public class PatrolRecordImages implements Serializable {
	private String id;
	private String small;// 小图
	private String normal;// 大图
	private Bitmap bitmap;
	private String path;
	
	public PatrolRecordImages() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getNormal() {
		return normal;
	}

	public void setNormal(String normal) {
		this.normal = normal;
	}

	public Bitmap getBitmap() {
		return bitmap;
	}

	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	

}
