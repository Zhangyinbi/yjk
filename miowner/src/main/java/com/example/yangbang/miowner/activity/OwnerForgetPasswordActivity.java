package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.OwnerUser;
import com.example.yangbang.miowner.util.HttpConstant;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.Tools;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by user on 2015/12/24.忘记密码 验证手机号码
 */
public class OwnerForgetPasswordActivity extends BaseActivity implements View.OnClickListener{

    private EditText forget_phone_et;
    private LinearLayout forget_img_LinearLayout;
    private HashMap<String, String> mMap = new HashMap<String, String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titleBar.setTitleText("忘记密码");
        titleBar.setLeftImgDefaultBack(this);
        titleBar.setRightText0("下一步");
        titleBar.setRightText0Listener(this);

        forget_phone_et = (EditText) findViewById(R.id.forget_phone_et);
        forget_img_LinearLayout = (LinearLayout) findViewById(R.id.forget_img_LinearLayout);
        forget_img_LinearLayout.setOnClickListener(this);

    }

    @Override
    protected void initWidget() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_owner_forget_password;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_text0:
                Tools.closeKeybord(forget_phone_et, this);
                String phone = forget_phone_et.getText().toString();
                if ("".equals(phone)) {
                    ToastUtil.showLong(this, "手机号码不能为空");
                    break;
                }
                mMap.put("mobile",phone);
                analyzeJson.requestData(HttpConstant.FORGET_PASSWORD_URL, mMap, REQUEST_SUCCESS);
                break;
            case R.id.forget_img_LinearLayout:
                forget_phone_et.setText("");
                break;
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                try {
                    JSONObject json = new JSONObject(data.data);
                    String mobile = json.getString("mobile");
                    Intent it = new Intent(OwnerForgetPasswordActivity.this,OwnerForgetPasswordValideActivity.class);
                    it.putExtra("mobile",mobile);
                    startActivity(it);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.handleMessage(msg);
    }
}
