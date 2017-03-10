package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbw.library.ActivityManager;
import com.hbw.library.BaseActivity;
import com.hbw.library.utils.SPUtils;
import com.hbw.library.utils.ToastUtil;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.HashMap;

/**
 * Created by user on 2016/1/6.修改密码
 */
public class AssistChangePasswordActivity extends BaseActivity implements View.OnClickListener{

    private LinearLayout changepassword_img_LinearLayout;
    private TextView changepassword_old_alert;
    private TextView changepassword_new_alert;
    private EditText changepassword_phone_et;
    private EditText changepassword_passowrd_et;
    private EditText changepassword_redo_passowrd_et;
    private String password;
    private boolean isPasswordCorrect = false;
    private HashMap<String, String> mMap = new HashMap<String, String>();

    @Override
    protected void initWidget() {
        titleBar.setTitleText("修改密码");
        titleBar.setLeftImgDefaultBack(this);
        titleBar.setRightText0("确认");
        titleBar.setRightText0Listener(this);
        changepassword_img_LinearLayout = (LinearLayout) findViewById(R.id.changepassword_img_LinearLayout);
        changepassword_phone_et = (EditText) findViewById(R.id.changepassword_phone_et);
        changepassword_passowrd_et = (EditText) findViewById(R.id.changepassword_passowrd_et);
        changepassword_redo_passowrd_et = (EditText) findViewById(R.id.changepassword_redo_passowrd_et);
        changepassword_old_alert = (TextView) findViewById(R.id.changepassword_old_alert);
        changepassword_new_alert = (TextView) findViewById(R.id.changepassword_new_alert);
        password = (String) SPUtils.get(this, AppFlag.PWD, "");

        changepassword_phone_et.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(!password.equals(changepassword_phone_et.getText().toString().trim())){
                        changepassword_old_alert.setVisibility(TextView.VISIBLE);
                        changepassword_img_LinearLayout.setVisibility(LinearLayout.GONE);
                        isPasswordCorrect = false;
                    }else{
                        changepassword_old_alert.setVisibility(TextView.GONE);
                        changepassword_img_LinearLayout.setVisibility(LinearLayout.VISIBLE);
                        isPasswordCorrect = true;
                    }
                }
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_assist_change_password;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.right_text0:
                String passwordOne = changepassword_passowrd_et.getText().toString().trim();
                String passwordTwo = changepassword_redo_passowrd_et.getText().toString().trim();
                if(!isPasswordCorrect){
                    ToastUtil.show(this,"原始密码错误，请重新输入");
                    break;
                }
                if("".equals(passwordOne)){
                    ToastUtil.show(this,"新密码不能为空");
                    break;
                }
                if(!passwordOne.equals(passwordTwo)){
                    ToastUtil.show(this,"新密码输入不一致，请重新输入");
                    changepassword_new_alert.setVisibility(View.VISIBLE);
                    break;
                }else{
                    changepassword_new_alert.setVisibility(View.GONE);
                    mMap.put("ys_password", changepassword_phone_et.getText().toString().trim());
                    mMap.put("new_password", passwordOne);
                    mMap.put("qr_password", passwordTwo);
                    mMap.put("token", MyApplication.TOKEN);
                    analyzeJson.requestData(HttpConstant.EditPwdUrl, mMap, REQUEST_SUCCESS);
                }
                break;
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ToastUtil.show(this,"修改成功");
                //清除用户信息
                MyApplication.TOKEN = null;
                MyApplication.UID = null;
                SPUtils.remove(this, AppFlag.TOKEN);
                SPUtils.remove(this, AppFlag.PWD);
                SPUtils.remove(this, AppFlag.IS_LOGIN);
                //退出所有activity
                ActivityManager.getActivityManager().finish(null);
                startActivity(new Intent(AssistChangePasswordActivity.this, LoginActivity.class));
                break;
        }
        return super.handleMessage(msg);
    }
}
