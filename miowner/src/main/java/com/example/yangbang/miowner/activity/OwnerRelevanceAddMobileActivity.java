package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.HintDialog;
import com.hbw.library.BaseActivity;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.Tools;

import java.util.HashMap;//添加关联手机号

/**
 * Created by user on 2016/1/7.
 */
public class OwnerRelevanceAddMobileActivity extends BaseActivity implements View.OnClickListener{

    private EditText add_mobile;
    private HintDialog mHintDialog = null;
    @Override
    protected void initWidget() {
        titleBar.setTitleText("关联手机号");
        titleBar.setLeftImgDefaultBack(this);
        titleBar.setRightText0("确认");
        titleBar.setRightText0Listener(this);
        add_mobile = (EditText)findViewById(R.id.add_mobile);

        //初始化提示框
        if (mHintDialog == null) {
            mHintDialog = new HintDialog(this);
        }

        mHintDialog.setTitleText("添加手机号后无法修改").setConfirmListener(this).setCancelListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_owner_relevance_add_mobile;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.right_text0:
                Tools.closeKeybord(add_mobile, this);
                if ("".equals(add_mobile.getText().toString().trim())) {
                    ToastUtil.showLong(this, "关联手机号码不能为空");
                    break;
                }
                mHintDialog.show();
                break;
            case R.id.hintdialog_cancel://关闭表单提醒框
                mHintDialog.dismiss();
                break;
            case R.id.hintdialog_confirm:
                mHintDialog.dismiss();
                HashMap<String, String> mMap = new HashMap<String, String>();
                mMap.put("token", MyApplication.getApp().getOwnerUser().getToken());
                mMap.put("mobile_2", add_mobile.getText().toString().trim());
                analyzeJson.requestData(HttpConstant.ADD_ASSOCIATED_UMBER_URL, mMap, REQUEST_SUCCESS);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mHintDialog != null){
            mHintDialog.dismiss();
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                startActivity(new Intent(OwnerRelevanceAddMobileActivity.this,OwnerRelevanceMobileActivity.class));
                break;
        }
        return super.handleMessage(msg);
    }
}
