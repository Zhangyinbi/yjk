package com.xiaomizhuang.buildcaptain.entity;

/**
 * 
 * @ClassName User
 * @description 用户相关实体类
 * @author Yangbang
 * @date 2015年5月14日
 */
public class User {
//	/** 登陆 */
//	public class LoginData {
//		public String status;
//		public String error_code;
//		public String message;
//		public UserInfo data;
//	}

	public class UserInfo {
		public String uid;
		public String idt;
		public String mobile;
		public String lastlogin;
		public String header;
		public String token;
	}

	/** 获取验证码 **/
	public class GetCodeIndex {
		public String status;
		public String error_code;
		public String message;
		public GetCodeData data;
	}

	public class GetCodeData {
		public String code;
		public String token;
		public String session_id;
	}

	public class RegistData {
		public String uid;
		public String mobile;
		public String token;
	}

	/** 重置密码 */
	public class ResetPwdInfo {
		public String status;
		public String error_code;
		public String message;
	}

	/** 设置密码 */
	public class RegisterSuccessIndex {
		public String status;
		public String error_code;
		public RegisterInfo data;
	}

	public class RegisterInfo {
		public String uid;
		public String mobile;
		public String token;
	}
}
