package com.youjuke.miprojectmanager.activity;

import java.util.HashMap;

import android.content.Intent;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import com.hbw.library.BaseActivity;
import com.hbw.library.utils.SPUtils;
import com.hbw.library.utils.ToastUtil;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.util.HelpClass;
import com.youjuke.miprojectmanager.util.HttpConstant;

/**
 * @author Yangbang
 * @ClassName RetrievePasswordActivity
 * @description 设置新密码界面
 * @date 2015年6月18日
 */
public class RetrievePasswordActivity extends MiBaseActivity implements
        OnClickListener {
    String newPsw;
    String checkPsw;
    EditText mewPwdEdit, newPwdAgainEdit;
    String token, code, phone, session_id;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_password_btn:
                newPsw = mewPwdEdit.getText().toString().trim();
                checkPsw = newPwdAgainEdit.getText().toString().trim();
                if (newPsw.length() < 6) {
                    Toast.makeText(this, "密码至少为6位字母或数字", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!newPsw.equals(checkPsw)) {
                    Toast.makeText(this, "两次密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    chengePwdHttp();
                }
                break;
            default:
                break;
        }

    }

    private void chengePwdHttp() {
        HashMap<String, String> mMap = new HashMap<String, String>();
        mMap.put("token", token);
        mMap.put("session_id", session_id);
        mMap.put("password", newPsw);
        mMap.put("repassword", checkPsw);
        mMap.put("code", code + "");
        mMap.put("mobile", phone);
        // post请求
        analyzeJson.requestData(HttpConstant.FindPwdUrl, mMap, 1);

    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
//                Log.e("jsonstr", msg.obj.toString());
                // ResetPwdInfo mResetPwdInfo = gson.fromJson(msg.obj.toString(),
                // new TypeToken<ResetPwdInfo>() {
                // }.getType());
                saveUserInfo();
                ToastUtil.show(this, "密码修改成功，请尝试用新密码登陆");
                Intent intent = new Intent(this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                break;
        }
        return super.handleMessage(msg);
    }

    private void saveUserInfo() {
        SPUtils.put(this, HelpClass.SpAccount, phone);
        SPUtils.put(this, HelpClass.SpPwd, newPsw);
        Log.e("修改的密码", newPsw);
    }

    @Override
    protected void initWidget() {
        token = getIntent().getStringExtra("token");
        session_id = getIntent().getStringExtra("session_id");
        code = getIntent().getStringExtra("code");
        phone = getIntent().getStringExtra("phone");
        titleBar.setTitleText("设置新密码");
        titleBar.setLeftImgDefaultBack(this, R.drawable.back);
        mewPwdEdit = ((EditText) findViewById(R.id.set_new_pwd));
        newPwdAgainEdit = ((EditText) findViewById(R.id.set_new_pwd_again));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.login_set_password_layout;
    }

}
