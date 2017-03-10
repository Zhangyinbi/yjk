package com.youjuke.miprojectmanager.entity;

/**
 * 接口反馈信息
 * @author hebiwen
 *
 */
public class BackMessage {
	
	private int code;
	private String message;
	
	@Override
	public String toString() {
		return "BackMessage [code=" + code + ", message=" + message + "]";
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
