package com.youjuke.miprojectmanager.entity;

/**
 * 
 * @ClassName ResponseHead
 * @description 相应头部实体类
 * @author Yangbang
 * @date 2015年5月14日
 * @param <T>
 */
public class ResponseHead<T> {
	public int status;
	public int error_code;
	public T data;
}
