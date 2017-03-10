package com.youjuke.miprojectmanager.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youjuke.miprojectmanager.entity.BackMessage;
import com.youjuke.miprojectmanager.entity.BuildingSite;
import com.youjuke.miprojectmanager.entity.Contacts;
import com.youjuke.miprojectmanager.entity.Jdysimgs;
import com.youjuke.miprojectmanager.entity.PatrolRecord;
import com.youjuke.miprojectmanager.entity.PatrolRecordDetails;
import com.youjuke.miprojectmanager.entity.ResponseHead;

public class JsonTools {
	public static final String TAG = "JsonTools-->";

	/**
	 * 传入json字符串返回相应泛型对象
	 * 
	 * @param jsonString
	 * @return
	 */
	public static <T> ResponseHead<T> getResponseHead(String jsonString) {
		Gson gson = new Gson();
		ResponseHead<T> head = null;
		try {
			head = gson.fromJson(jsonString, new TypeToken<ResponseHead<T>>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return head;
	}

	/**
	 * 传入json字符串返回相应对象
	 * 
	 * @param jsonString
	 * @return 在建工地对象
	 */
	public static BuildingSite getBuildingSite(String jsonString) {
		Gson gson = new Gson();
		BuildingSite project = null;
		try {
			project = gson.fromJson(jsonString, new TypeToken<BuildingSite>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	/**
	 * 传入json字符串返回相应对象
	 * 
	 * @param jsonString
	 * @return 巡查记录对象
	 */
	public static PatrolRecord getPatrolRecord(String jsonString) {
		Gson gson = new Gson();
		PatrolRecord project = null;
		try {
			project = gson.fromJson(jsonString, new TypeToken<PatrolRecord>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	/**
	 * 传入json字符串返回相应对象
	 * 
	 * @param jsonString
	 * @return 巡查记录详情
	 */
	public static PatrolRecordDetails getPatrolRecordDetails(String jsonString) {
		Gson gson = new Gson();
		PatrolRecordDetails project = null;
		try {
			project = gson.fromJson(jsonString,
					new TypeToken<PatrolRecordDetails>() {
					}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	/**
	 * 传入json字符串返回相应对象
	 * 
	 * @param jsonString
	 * @return 提交完成之后反馈信息
	 */
	public static BackMessage getBackMessage(String jsonString) {
		Gson gson = new Gson();
		BackMessage project = null;
		try {
			project = gson.fromJson(jsonString, new TypeToken<BackMessage>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return project;
	}

	/**
	 * 传入json字符串返回相应对象
	 * 
	 * @param jsonString
	 * @return 通讯录列表
	 */
	public static Contacts getContacts(String jsonString) {
		Gson gson = new Gson();
		Contacts contacts = null;
		try {
			contacts = gson.fromJson(jsonString, new TypeToken<Contacts>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}
	
	/**
	 * 传入json字符串返回相应对象
	 * 
	 * @param jsonString
	 * @return 通讯录列表
	 */
	public static Jdysimgs getManagerProjectJdysimgs(String jsonString) {
		Gson gson = new Gson();
		Jdysimgs contacts = null;
		try {
			contacts = gson.fromJson(jsonString, new TypeToken<Jdysimgs>() {
			}.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return contacts;
	}

}
