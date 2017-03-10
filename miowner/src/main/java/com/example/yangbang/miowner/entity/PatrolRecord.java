package com.example.yangbang.miowner.entity;

import java.util.List;

/**
 * Created by mwy on 2016/6/24.
 * 项目经理巡查记录 实体类
 */
public class PatrolRecord {

	private String id;
	private String baoming_id;
	private String content;
	private String addtime;
	private String amerce_money;
	private String amerce;
	private String projectphase;

	private List<ImgsBean> imgs;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBaoming_id() {
		return baoming_id;
	}

	public void setBaoming_id(String baoming_id) {
		this.baoming_id = baoming_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getAmerce_money() {
		return amerce_money;
	}

	public void setAmerce_money(String amerce_money) {
		this.amerce_money = amerce_money;
	}

	public String getAmerce() {
		return amerce;
	}

	public void setAmerce(String amerce) {
		this.amerce = amerce;
	}

	public String getProjectphase() {
		return projectphase;
	}

	public void setProjectphase(String projectphase) {
		this.projectphase = projectphase;
	}

	public List<ImgsBean> getImgs() {
		return imgs;
	}

	public void setImgs(List<ImgsBean> imgs) {
		this.imgs = imgs;
	}

	public static class ImgsBean {
		private String id;
		private String small;
		private String normal;

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
	}
}
