package com.example.yangbang.miowner.entity;

import java.io.Serializable;

/**
 * 描述: 事项提醒 实体类
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-07-19 14:07
 */
public class RemindMessage implements Serializable{
	private String status_id;
	private int status_count;
	private String status_name;

	public String getStatus_id() {
		return status_id;
	}

	public void setStatus_id(String status_id) {
		this.status_id = status_id;
	}

	public int getStatus_count() {
		return status_count;
	}

	public void setStatus_count(int status_count) {
		this.status_count = status_count;
	}

	public String getStatus_name() {
		return status_name;
	}

	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
}
