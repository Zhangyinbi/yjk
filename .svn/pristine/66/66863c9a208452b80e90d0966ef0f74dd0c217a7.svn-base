package com.xiaomizhuang.buildcaptain.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.hbw.library.ActivityManager;
import com.hbw.library.BaseActivity;
import com.hbw.library.utils.SPUtils;
import com.hbw.library.utils.ToastUtil;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.PopIsExit;

import java.util.HashMap;

/**
 * Created by user on 2016/1/12.
 */
public class AssistSettingActivity extends BaseActivity implements View.OnClickListener {

    private RelativeLayout set_changpassword;
    private RelativeLayout deductions_record;
    private RelativeLayout set_exit;
    private HashMap<String, String> mMap = new HashMap<String, String>();

    @Override
    protected void initWidget() {
        titleBar.setTitleText("我的");
        titleBar.setLeftImgDefaultBack(this);
        set_changpassword = (RelativeLayout) findViewById(R.id.set_changpassword);
        set_exit = (RelativeLayout) findViewById(R.id.set_exit);
        deductions_record = (RelativeLayout) findViewById(R.id.deductions_record);
        set_changpassword.setOnClickListener(this);
        set_exit.setOnClickListener(this);
        deductions_record.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_assist_setting;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_changpassword:
                startActivity(new Intent(AssistSettingActivity.this, AssistChangePasswordActivity.class));
                break;
            case R.id.set_exit://退出登录

               /* Utils.setScreenalpha(this);
                ToastPopuWindowIsExit();*/
                PopIsExit pop = new PopIsExit(AssistSettingActivity.this, analyzeJson);
                pop.showAtLocation(v, Gravity.BOTTOM, 0, 0);

                /*mMap.put("token", MyApplication.TOKEN);
                analyzeJson.requestData(HttpConstant.LogOutUrl, mMap, REQUEST_SUCCESS);*/
                break;
            case R.id.deductions_record://扣款记录
                startActivity(new Intent(AssistSettingActivity.this, AllBuildDeductRecordActivity.class));
                break;
        }
    }



   /* *//**
     * 是否退出用户信息
     *//*
    @TargetApi(Build.VERSION_CODES.M)
    private void ToastPopuWindowIsExit() {

        LayoutInflater inflater = LayoutInflater.from(this);
        View contentView=inflater.inflate(R.layout.isexitpopuwindow,null);

        final PopupWindow popupWindow= new PopupWindow(contentView, RadioGroup.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.WRAP_CONTENT,true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setAnimationStyle(R.style.PopupAnimation);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowManager.LayoutParams attributes = AssistSettingActivity.this.getWindow().getAttributes();
                attributes.screenBrightness = Float.valueOf(Utils.screenBrightness/255f);
                AssistSettingActivity.this.getWindow().setAttributes(attributes);
            }
        });
        contentView.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
        contentView.findViewById(R.id.btn_exit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                mMap.put("token", MyApplication.TOKEN);
                analyzeJson.requestData(HttpConstant.LogOutUrl, mMap, REQUEST_SUCCESS);
            }
        });

        // 设置好参数之后再show
        popupWindow.showAtLocation(getWindow().getDecorView(),Gravity.BOTTOM,0,0);
    }*/

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ToastUtil.show(this, "退出成功");
                MyApplication.TOKEN = null;
                MyApplication.UID = null;
                SPUtils.remove(this, AppFlag.UID);
                SPUtils.remove(this, AppFlag.TOKEN);
                SPUtils.remove(this, AppFlag.PWD);
                SPUtils.remove(this, AppFlag.IS_LOGIN);
                //退出所有activity
                ActivityManager.getActivityManager().finish(null);
                startActivity(new Intent(AssistSettingActivity.this, LoginActivity.class));
                break;
        }
        return super.handleMessage(msg);
    }
}
