package com.xiaomizhuang.buildcaptain.entity;

/**
 * 描述: 用来提交设置排期到服务器 要转换成json的bean
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-09-09 11:02
 */
public class ScheduleOfPostService {

	/**
	 * id : 1
	 * start : 2016-05-05
	 * end : 2016-05-12
	 * title : 交底/拆旧
	 *  project_id  : 1
	 */

	private String id;
	private String start;
	private String end;
	private String title;
	private String project_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getProject_id() {
		return project_id;
	}

	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}
}
