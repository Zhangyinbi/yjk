package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.OwnerRegister;
import com.example.yangbang.miowner.util.AppFlag;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.view.TimeButton;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.Tools;

import java.util.HashMap;

/**
 * Created by user on 2015/12/17.注册
 */
public class OwnerRegisterActivity extends BaseActivity implements View.OnClickListener {

	private EditText registerPhoneEt;
	private LinearLayout login_img_LinearLayout;
	private ImageView registerImg;
	private EditText registerVerificationCodeEt;
	private TimeButton registerGainCodeBtn;
	private String code = "123456";//验证码
	private HashMap<String, String> mMap = new HashMap<String, String>();
	private OwnerRegister mOwnerUser;//验证码

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		titleBar.setTitleText("注册");
		titleBar.setLeftImgDefaultBack(this);
		titleBar.setRightText0("下一步");
		titleBar.setRightText0Listener(this);

		registerPhoneEt = (EditText) findViewById(R.id.register_phone_et);
		login_img_LinearLayout = (LinearLayout) findViewById(R.id.login_img_LinearLayout);
		registerImg = (ImageView) findViewById(R.id.register_img);
		registerVerificationCodeEt = (EditText) findViewById(R.id.register_verification_code_et);
		login_img_LinearLayout.setOnClickListener(this);

		//自定义倒计时button
		registerGainCodeBtn = (TimeButton) findViewById(R.id.register_gain_code_btn);
		registerGainCodeBtn.onCreate(savedInstanceState);
		registerGainCodeBtn.setTextAfter("s后重新获取")//设置变化时的文字
				.setTextBefore("获取验证码")//设置默认文字
				.setLenght(60 * 1000)//设置总时长
				.setEditText(registerPhoneEt)//设置需要判断的手机号码
				.setTimeButtonListener(new TimeButton.TimeButtonListener() {//设置号码正确之后的回调监听
					@Override
					public void Back() {
						mMap.put("mobile", registerPhoneEt.getText().toString());
						analyzeJson.requestData(HttpConstant.REGEDIT_VALID_URL, mMap, REQUEST_SUCCESS);
					}
				});
	}

	@Override
	protected void initWidget() {

	}

	@Override
	protected void initData() {

	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_owner_register;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.right_text0:
				Tools.closeKeybord(registerVerificationCodeEt, this);
				String phone = registerPhoneEt.getText().toString();
				String mCode = registerVerificationCodeEt.getText().toString();
				if ("".equals(phone)) {
					ToastUtil.showLong(this, "手机号码不能为空");
					break;
				}
				if ("".equals(mCode)) {
					ToastUtil.showLong(this, "验证码不能为空");
					break;
				}
				if (mCode.equals(code)) {
					finishActivity();
					Intent intent = new Intent(OwnerRegisterActivity.this, OwnerRegisterSetPasswordActivity.class);
					intent.putExtra(AppFlag.OWNER_MOBILE, phone);
					intent.putExtra(AppFlag.OWNER_CODE, mCode);
					startActivity(intent);
				} else {
					ToastUtil.showLong(this, "您输入的验证码有误，请重新输入");
				}
				break;
			case R.id.login_img_LinearLayout:
				registerPhoneEt.setText("");
				break;
		}
	}

	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				mOwnerUser = gson.fromJson(data.data, OwnerRegister.class);
				//TODO 暂时把验证码定死
				code = "123456";
//                code = mOwnerUser.getValide();
				break;
		}
		return super.handleMessage(msg);
	}
}
