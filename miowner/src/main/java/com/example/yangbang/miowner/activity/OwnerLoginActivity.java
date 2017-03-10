package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.os.Message;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.OwnerUser;
import com.example.yangbang.miowner.util.AppFlag;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.SPUtils;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.Tools;

import java.util.HashMap;

/**
 * Created by user on 2015/12/16.登录
 */
public class OwnerLoginActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout login_isShowPassword;
    private ImageView login_img;
    private EditText login_phone_et;
    private EditText login_pwd_et;
    private Button loginBtn;
    private TextView register_tv;
    private TextView loginForgetPwdTv;

    private boolean isShowPassword = false;//是否显示密码
    private String moblie;
    private String pwd;

    @Override
    protected void initWidget() {
        setIsShowAnimation(false);
        login_phone_et = (EditText) findViewById(R.id.login_phone_et);
        login_pwd_et = (EditText) findViewById(R.id.login_pwd_et);
        loginBtn = (Button) findViewById(R.id.login_btn);
        loginForgetPwdTv = (TextView) findViewById(R.id.login_forget_pwd_tv);
        register_tv = (TextView) findViewById(R.id.register_tv);
        login_isShowPassword = (LinearLayout) findViewById(R.id.login_isShowPassword);
        login_img = (ImageView) findViewById(R.id.login_img);

        loginBtn.setOnClickListener(this);
        loginForgetPwdTv.setOnClickListener(this);
        register_tv.setOnClickListener(this);
        login_isShowPassword.setOnClickListener(this);

        login_phone_et.setText((String) SPUtils.get(this, AppFlag.OWNER_USERNAME, ""));
        login_pwd_et.setText((String) SPUtils.get(this, AppFlag.OWNER_PASSWORD, ""));
    }

    @Override
    protected void initData() {
        setIsShowTitleBar(false);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_owner_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn:
                moblie = login_phone_et.getText().toString();
                pwd = login_pwd_et.getText().toString();
                if (!Tools.isCellPhoneNumber(moblie)) {
                    ToastUtil.showShort(this, "请您输入正确的手机号码");
                    return;
                }
                if (pwd.length() < 6) {
                    ToastUtil.showShort(this, "密码长度不应小于6位");
                    return;
                }
                HashMap<String, String> mMap = new HashMap<String, String>();
                mMap.put("mobile", moblie);
                mMap.put("password", pwd);
                mMap.put("idt", "user");
                analyzeJson.requestData(HttpConstant.LoginUrl, mMap, REQUEST_SUCCESS);
                break;
            case R.id.login_forget_pwd_tv:
                Intent login_forget_pwd_tv = new Intent(OwnerLoginActivity.this, OwnerForgetPasswordActivity.class);
                startActivity(login_forget_pwd_tv);
                break;
            case R.id.register_tv:
                Intent register_tv = new Intent(OwnerLoginActivity.this, OwnerRegisterActivity.class);
                startActivity(register_tv);
                break;
            case R.id.login_isShowPassword:
                if (!isShowPassword) {
                    login_img.setBackground(getResources().getDrawable(R.mipmap.showpassword));
                    login_pwd_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_FLAG_SIGNED);
                } else {
                    login_img.setBackground(getResources().getDrawable(R.mipmap.noshowpassword));
                    login_pwd_et.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                //设置光标位置
                CharSequence text = login_pwd_et.getText();
                if (text instanceof Spannable) {
                    Spannable spanText = (Spannable) text;
                    Selection.setSelection(spanText, text.length());
                }
                isShowPassword = !isShowPassword;
                break;
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                OwnerUser mOwnerUser = gson.fromJson(data.data, OwnerUser.class);
                //将请求获取到的用户数据存入MyApplication中
                MyApplication.getApp().setOwnerUser(mOwnerUser);
                MyApplication.TOKEN = mOwnerUser.getToken();
                //将用户名密码缓存到本地
                SPUtils.put(OwnerLoginActivity.this, AppFlag.OWNER_USERNAME, moblie);
                SPUtils.put(OwnerLoginActivity.this, AppFlag.OWNER_PASSWORD, pwd);
                SPUtils.put(OwnerLoginActivity.this, "superior", mOwnerUser.getSuperior());
                SPUtils.put(OwnerLoginActivity.this, "baoming_id", mOwnerUser.getBaoming_id());
                SPUtils.put(OwnerLoginActivity.this, "superior_mobile", mOwnerUser.getSuperior_mobile());
                SPUtils.put(OwnerLoginActivity.this, "designer", mOwnerUser.getDesigner());
                SPUtils.put(OwnerLoginActivity.this, "designer_mobile", mOwnerUser.getDesigner_mobile());
                SPUtils.put(OwnerLoginActivity.this, "builder", mOwnerUser.getBuilder());
                SPUtils.put(OwnerLoginActivity.this, "builder_mobile", mOwnerUser.getBuilder_mobile());
                SPUtils.put(OwnerLoginActivity.this, "token", mOwnerUser.getToken());
                // 跳转到主页
                startActivity(new Intent(OwnerLoginActivity.this, OwnerProjectSchedulActivity.class));
                finishActivity();
                break;
        }
        return super.handleMessage(msg);
    }
}
