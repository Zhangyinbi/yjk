package com.youjuke.miprojectmanager.activity;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.entity.User.GetCodeIndex;
import com.youjuke.miprojectmanager.util.HttpConstant;
import com.youjuke.miprojectmanager.view.TimeCount;

/**
 * @author Yangbang
 * @ClassName ForgetPasswordActivity
 * @description 找回密码获取验证码界面
 * @date 2015年6月18日
 */
public class ForgetPasswordActivity extends MiBaseActivity implements
        OnClickListener {

    TimeCount timer;
    Button mBtn;
    EditText phoneEdit, codePhone;
    String token;
    String session_id;
    String phoneNum;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.retrieve_password_btn_code:
                phoneNum = phoneEdit.getText().toString().trim();
                if (isMobileNum(phoneNum)) {
                    getCodeFromHttp(phoneNum);

                } else {
                    Toast.makeText(this, "手机号码不合法", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.retrieve_password_btn_next:
                if (phoneEdit.getText().toString().length() > 0
                        && codePhone.getText().toString().length() > 0) {
                    Intent intent = new Intent(ForgetPasswordActivity.this,
                            RetrievePasswordActivity.class);
                    intent.putExtra("code", codePhone.getText().toString().trim());
                    intent.putExtra("token", token);
                    intent.putExtra("session_id", session_id);
                    intent.putExtra("phone", phoneNum);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "手机号码和验证码不允许有空", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                break;
        }

    }

    private void getCodeFromHttp(String phoneNum) {
        HashMap<String, String> mMap = new HashMap<String, String>();
        mMap.put("mobile", phoneNum);
        // post请求
        analyzeJson.requestData(HttpConstant.GetForgetCodeUrl, mMap, 1);
    }

    public boolean isMobileNum(String mobiles) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                timer.start();
                GetCodeIndex mGetRegisterIndex = gson.fromJson(msg.obj.toString(),
                        new TypeToken<GetCodeIndex>() {
                        }.getType());
                token = mGetRegisterIndex.data.token;
                session_id = mGetRegisterIndex.data.session_id;
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    protected void initWidget() {
        ((Button) findViewById(R.id.retrieve_password_btn_code))
                .setOnClickListener(this);
        mBtn = (Button) findViewById(R.id.retrieve_password_btn_code);
        phoneEdit = ((EditText) findViewById(R.id.retrieve_password_et_phone));
        codePhone = ((EditText) findViewById(R.id.retrieve_code));
        timer = new TimeCount(60000, 1000, mBtn, this);
        titleBar.setTitleText("找回密码");
        titleBar.setLeftImgDefaultBack(this, R.drawable.back);
    }

    OnClickListener titlelistener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            finishActivity();
        }
    };

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.login_retrieve_password_layout;
    }
}
