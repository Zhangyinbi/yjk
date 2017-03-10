package com.xiaomizhuang.buildcaptain.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.AppUtils;
import com.hbw.library.utils.SPUtils;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.view.SwitchButton;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.LoginData;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.HashMap;

import kr.co.namee.permissiongen.PermissionGen;

/**
 * 登陆activity
 * #0001    mwy    2016/07/21   添加线下线上切换
 * #0002    mwy    2016/08/04   添加在第一次启动时对6.0权限请求
 */
public class LoginActivity extends BaseActivity implements OnClickListener {

	public static final String CONFIG = "login_config";
	private String mAccount, mPassword, loginType = "admin_user", token;
	private Button login_btn;
	private EditText accountEdit, pwdEdit;
	private LinearLayout login_layout;
	private HashMap<String, String> mMap = new HashMap<String, String>();

	@Override
	protected void initWidget() {
//        ((TextView) findViewById(R.id.tv_forget_password))
//                .setOnClickListener(this);
		accountEdit = (EditText) findViewById(R.id.login_account);
		pwdEdit = (EditText) findViewById(R.id.login_password);
		login_btn = (Button) findViewById(R.id.login_btn);
		login_layout = (LinearLayout) findViewById(R.id.login_layout);
		login_btn.setOnClickListener(this);
		initLoginInfo();
		setIsShowAnimation(false);
		addChangeLineButton();//#0001
		onFristRequestPermission();//#0002
	}

	@Override
	protected void initData() {
		setIsShowTitleBar(false);
//        initLoginInfo();
		// new AnalyzeJson(handler,
		// "http://corner.lafina.cn/api/v1/advertisements?page=1&pre_page=5",
		// null, 111);
	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.login_layout;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.login_btn:
				mAccount = accountEdit.getText().toString().trim();
				mPassword = pwdEdit.getText().toString().trim();
				if (mAccount.length() < 11) {
					ToastUtil.showShort(this, "手机号输入有误");
					return;
				}
				if (mPassword.length() < 6) {
					ToastUtil.showShort(this, "密码输入有误");
					return;
				}
				mMap.put("mobile", mAccount);
				mMap.put("passwd", mPassword);
				mMap.put("registration_id", MyApplication.registrationID);
//                mMap.put("idt", loginType);
				analyzeJson.requestData(HttpConstant.LoginUrl, mMap,
						REQUEST_SUCCESS);
				break;
		}
	}

	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				LoginData loginData = gson.fromJson(data.data, LoginData.class);
				saveUserInfo(loginData);
				startActivity(new Intent(LoginActivity.this,
						BuildSiteActivity.class));
				finishActivity();
				break;
		}
		return super.handleMessage(msg);
	}


	private void saveUserInfo(LoginData data) {
		token = data.getToken();
		MyApplication.TOKEN = token;
		MyApplication.UID = data.getUid();
		SPUtils.put(this, AppFlag.ACCOUNT, data.getMobile());
		SPUtils.put(this, AppFlag.UID, data.getUid());
		SPUtils.put(this, AppFlag.TOKEN, data.getToken());
//        SPUtils.put(this, AppFlag.LAST_LOGIN, data.getLastlogin());
		SPUtils.put(this, AppFlag.PWD, mPassword);
		SPUtils.put(this, AppFlag.IS_LOGIN, true);
	}

	private void initLoginInfo() {
		mAccount = (String) SPUtils.get(this, AppFlag.ACCOUNT,
				"");
		mPassword = (String) SPUtils
				.get(this, AppFlag.PWD, "");
		accountEdit.setText(mAccount + "");
		pwdEdit.setText(mPassword + "");
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (progressDialog != null && progressDialog.isShowing()) {
//                progressDialog.dismiss();
//                return false;
//            }
//            exit();
//            return false;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

	public void exit() {
		onBackPressed();
		// if ((System.currentTimeMillis() - exitTime) > 2000) {
		// Toast.makeText(getApplicationContext(), "再按一次退出程序",
		// Toast.LENGTH_SHORT).show();
		// exitTime = System.currentTimeMillis();
		// } else {
		// MyApplication.getApp().popAllActivity();
		// // finishAffinity();
		// // System.exit(0);
		// }
	}

	/**
	 * 添加切换线上线下模式按钮  #0001
	 */
	private void addChangeLineButton() {
		//如果是DEBUG 模式  添加切换线路按钮
		if (AppUtils.isApkDebugable(this)) {
			View view = RelativeLayout.inflate(this, R.layout.switch_button_change_line, null);
			login_layout.addView(view);

			SwitchButton switchButton = (SwitchButton) view.findViewById(R.id.sb_change_line);
			//检测上一次的是线上还是线下
			if ((Boolean) SPUtils.get(getApplicationContext(), AppFlag.ISONLINE, false) == false) {//如果是线下
				MyApplication.host = HttpConstant.HOST_OFFLINE;
				switchButton.setToggleOff();
			} else {
				MyApplication.host = HttpConstant.HOST_ONLINE;
				switchButton.setToggleOn();
			}
			//设置switchButton的开关事件
			switchButton.setOnToggleChanged(new SwitchButton.OnToggleChanged() {
				@Override
				public void onToggle(boolean on) {
					if (on) {//切换到线上
						MyApplication.host = HttpConstant.HOST_ONLINE;
						MyApplication.isOnline = true;
						SPUtils.put(getApplicationContext(), AppFlag.ISONLINE, true);
						ToastUtil.show(getApplicationContext(), "已切换至线上模式");
					} else {//切换到线下
						MyApplication.host = HttpConstant.HOST_OFFLINE;
						MyApplication.isOnline = false;
						SPUtils.put(getApplicationContext(), AppFlag.ISONLINE, false);
						ToastUtil.show(getApplicationContext(), "已切换至线下模式");
					}
				}
			});
		}
	}

	//在安装完后第一次启动时请求相关权限
	private void onFristRequestPermission(){
		//是否请求过权限
		boolean isRequestedPermission = (boolean) SPUtils.get(getApplicationContext(), "IsRequestedPermission", false);
		if (!isRequestedPermission){//如果第一次
			//请求权限
			PermissionGen.with(LoginActivity.this)
					.addRequestCode(100)
					.permissions(
							Manifest.permission.CAMERA,
							Manifest.permission.WRITE_EXTERNAL_STORAGE,
							Manifest.permission.READ_EXTERNAL_STORAGE)
					.request();
			//设置已经请求完权限
			SPUtils.put(getApplicationContext(),"IsRequestedPermission",true);
		}
	}

}
