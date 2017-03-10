package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.util.AppFlag;
import com.example.yangbang.miowner.util.HttpConstant;
import com.hbw.library.BaseActivity;
import com.hbw.library.utils.ToastUtil;

import java.util.HashMap;

/**
 * Created by user on 2015/12/24.
 */
public class OwnerForgetPasswordSetPasswordActivity extends BaseActivity implements View.OnClickListener{
    private EditText registerPassowrdEt;
    private EditText registerRedoPassowrdEt;
    private HashMap<String, String> mMap = new HashMap<String, String>();
    private String mCode;
    private String token;
    private String mobile;
    @Override
    protected void initWidget() {
        mCode = getIntent().getStringExtra(AppFlag.OWNER_CODE);
        token = getIntent().getStringExtra("token");
        mobile = getIntent().getStringExtra("mobile");
        titleBar.setTitleText("设定新密码");
        titleBar.setLeftImgDefaultBack(this);
        titleBar.setRightText0("确认");
        titleBar.setRightText0Listener(this);

        registerPassowrdEt = (EditText) findViewById(R.id.register_passowrd_et);
        registerRedoPassowrdEt = (EditText) findViewById(R.id.register_redo_passowrd_et);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_owner_register_set_password;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_text0:
                String password = registerPassowrdEt.getText().toString();
                String redo_password = registerRedoPassowrdEt.getText().toString();
                if ("".equals(password)) {
                    ToastUtil.show(this, "密码不能为空");
                    break;
                }
                if (!password.equals(redo_password)) {
                    ToastUtil.show(this, "两次密码输入不一致，请重新输入");
                    break;
                }
                mMap.put("token", token);
                mMap.put("valide", mCode);
                mMap.put("password", password);
                mMap.put("repassword",redo_password);
                mMap.put("mobile",mobile);
                analyzeJson.requestData(HttpConstant.SET_PASSWORD_URL, mMap, REQUEST_SUCCESS);
                break;
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ToastUtil.show(this,"设置成功");
                finishActivity();
                startActivity(new Intent(OwnerForgetPasswordSetPasswordActivity.this, OwnerLoginActivity.class));
                break;
        }
        return super.handleMessage(msg);
    }
}
