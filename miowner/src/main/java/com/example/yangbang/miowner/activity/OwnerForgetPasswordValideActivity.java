package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.OwnerRegister;
import com.example.yangbang.miowner.util.AppFlag;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.view.TimeButton;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by user on 2015/12/24.忘记密码 获取验证码
 */
public class OwnerForgetPasswordValideActivity extends BaseActivity implements View.OnClickListener{

    private EditText registerVerificationCodeEt;
    private TimeButton registerGainCodeBtn;
    private String code;//验证码
    private HashMap<String, String> mMap = new HashMap<String, String>();
    private OwnerRegister mOwnerUser;//验证码
    private String mobile;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mobile = getIntent().getStringExtra("mobile");

        titleBar.setTitleText("忘记密码");
        titleBar.setLeftImgDefaultBack(this);
        titleBar.setRightText0("下一步");
        titleBar.setRightText0Listener(this);

        registerVerificationCodeEt = (EditText) findViewById(R.id.forget_password_valide_verification_code_et);

        //自定义倒计时button
        registerGainCodeBtn = (TimeButton) findViewById(R.id.forget_password_valide_gain_code_btn);
        registerGainCodeBtn.onCreate(savedInstanceState);
        registerGainCodeBtn.setTextAfter("s后重新获取")//设置变化时的文字
                .setTextBefore("获取验证码")//设置默认文字
                .setLenght(60 * 1000)//设置总时长
                .setEditText(null)//设置需要判断的手机号码
                .setTimeButtonListener(new TimeButton.TimeButtonListener() {//设置号码正确之后的回调监听
                    @Override
                    public void Back() {
                        mMap.put("mobile", mobile);
                        analyzeJson.requestData(HttpConstant.FORGET_PASSWORD_VALID_URL, mMap, REQUEST_SUCCESS);
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
        return R.layout.activity_owner_forget_password_valide;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_text0:
                Tools.closeKeybord(registerVerificationCodeEt, this);
                String mCode = registerVerificationCodeEt.getText().toString();
                if ("".equals(mCode)) {
                    ToastUtil.showLong(this, "验证码不能为空");
                    break;
                }
                if (mCode.equals(code)) {
                    finishActivity();
                    Intent intent = new Intent(OwnerForgetPasswordValideActivity.this, OwnerForgetPasswordSetPasswordActivity.class);
                    intent.putExtra(AppFlag.OWNER_MOBILE, mobile);
                    intent.putExtra(AppFlag.OWNER_CODE, mCode);
                    intent.putExtra("token", token);
                    startActivity(intent);
                } else {
                    ToastUtil.showLong(this, "您输入的验证码有误，请重新输入");
                }
                break;
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                mOwnerUser = gson.fromJson(data.data, OwnerRegister.class);
                //TODO 验证码暂时写死
                code = "123456";
                JSONObject json = null;
                try {
                    json = new JSONObject(data.data);
                    token = json.getString("token");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
//                code = mOwnerUser.getValide();
                break;
        }
        return super.handleMessage(msg);
    }
}
