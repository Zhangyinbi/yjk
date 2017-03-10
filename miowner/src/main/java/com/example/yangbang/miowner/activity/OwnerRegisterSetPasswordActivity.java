package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.util.AppFlag;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.view.AlertDialog;
import com.hbw.library.BaseActivity;
import com.hbw.library.utils.ToastUtil;

import java.util.HashMap;

/**
 * Created by user on 2015/12/17.设置密码
 */
public class OwnerRegisterSetPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText registerPassowrdEt;
    private EditText registerRedoPassowrdEt;
    private AlertDialog alertDialog = null;
    private HashMap<String, String> mMap = new HashMap<String, String>();
    private String phone;
    private String mCode;

    @Override
    protected void initWidget() {
        mCode = getIntent().getStringExtra(AppFlag.OWNER_CODE);
        phone = getIntent().getStringExtra(AppFlag.OWNER_MOBILE);

        titleBar.setTitleText("设置密码");
        alertDialog = new AlertDialog(this, "注册成功");
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
                mMap.put("mobile", phone);
                mMap.put("code", mCode);
                mMap.put("password", password);
                analyzeJson.requestData(HttpConstant.REGEDIT_URL, mMap, REQUEST_SUCCESS);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        alertDialog.dismiss();
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                alertDialog.show();
                finishActivity();
                startActivity(new Intent(OwnerRegisterSetPasswordActivity.this, OwnerLoginActivity.class));
                break;
            case ERROR:
	            finishActivity();
	            startActivity(new Intent(OwnerRegisterSetPasswordActivity.this, OwnerRegisterActivity.class));
                break;
        }
        return super.handleMessage(msg);
    }
}
