package com.youjuke.miprojectmanager.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.AppUtils;
import com.hbw.library.utils.L;
import com.hbw.library.utils.SPUtils;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.view.SwitchButton;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.entity.User.UserInfo;
import com.youjuke.miprojectmanager.util.HelpClass;
import com.youjuke.miprojectmanager.util.HttpConstant;
import com.youjuke.miprojectmanager.util.MyApplication;

import java.util.HashMap;

/**
 * 登陆activity
 * #0001    mwy    2016/07/21   添加线下线上切换
 */
public class LoginActivity extends MiBaseActivity implements OnClickListener {

    public static final String CONFIG = "login_config";
    private String mAccount, mPassword, loginType = "admin_user", token;
    private Button login_btn;
    private EditText accountEdit, pwdEdit;
    private HashMap<String, String> mMap = new HashMap<String, String>();
	private LinearLayout login_layout;

    @Override
    protected void initWidget() {
        ((TextView) findViewById(R.id.tv_forget_password))
                .setOnClickListener(this);
        accountEdit = (EditText) findViewById(R.id.login_account);
        pwdEdit = (EditText) findViewById(R.id.login_password);
        login_btn = (Button) findViewById(R.id.login_btn);
	    login_layout = (LinearLayout) findViewById(R.id.login_layout);
        login_btn.setOnClickListener(this);
        initLoginInfo();
	    addChangeLineButton();//#0001

	    L.i("Intent.hashcode :  "+getIntent().hashCode()+"");
    }

    @Override
    protected void initData() {
        setIsShowTitleBar(false);
        setIsShowAnimation(false);
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
                mMap.put("password", mPassword);
                mMap.put("idt", loginType);
                analyzeJson.requestData(HttpConstant.LoginUrl, mMap,
                        REQUEST_SUCCESS);
                break;
            case R.id.tv_forget_password:// 找回密码
                Intent forgetIntent = new Intent(LoginActivity.this,
                        ForgetPasswordActivity.class);
                startActivity(forgetIntent);
                break;
        }
    }

	@Override
	protected void onDestroy() {
		super.onDestroy();
		L.i("LoginActivity[被销毁]--->onDestroy");
	}

	@Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                UserInfo userInfo = gson.fromJson(data.data, UserInfo.class);
                saveUserInfo(userInfo);
                token = userInfo.token;
                HttpConstant.token = token;
                HttpConstant.pwd = mPassword;
                startActivity(new Intent(LoginActivity.this,
                        BuildingSiteActivity.class));
                break;
        }
        return super.handleMessage(msg);
    }

    private void saveUserInfo(UserInfo data) {
        SPUtils.put(this, HelpClass.SpAccount, mAccount);
        SPUtils.put(this, HelpClass.SpUid, data.uid);
        SPUtils.put(this, HelpClass.SpToken, data.token);
//        SPUtils.put(this, HelpClass.SpLastlogin, data.lastlogin);
        SPUtils.put(this, HelpClass.SpPwd, mPassword);
        SPUtils.put(this, HelpClass.SpIsLogin, true);
    }

    private void initLoginInfo() {
        mAccount = (String) SPUtils.get(this, HelpClass.SpAccount,
                "");
        mPassword = (String) SPUtils
                .get(this, HelpClass.SpPwd, "");
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
			if ((Boolean) SPUtils.get(getApplicationContext(), HelpClass.ISONLINE, false) == false) {//如果是线下
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
						SPUtils.put(getApplicationContext(), HelpClass.ISONLINE, true);
						ToastUtil.show(getApplicationContext(), "已切换至线上模式");
					} else {//切换到线下
						MyApplication.host = HttpConstant.HOST_OFFLINE;
						MyApplication.isOnline = false;
						SPUtils.put(getApplicationContext(), HelpClass.ISONLINE, false);
						ToastUtil.show(getApplicationContext(), "已切换至线下模式");
					}
				}
			});
		}
	}
}
