package com.youjuke.miprojectmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;

import com.hbw.library.ActivityManager;
import com.hbw.library.BaseActivity;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.util.HelpClass;
import com.youjuke.miprojectmanager.util.MyApplication;

/**
 * 设置界面
 *
 * @author Youjk
 */
public class SetActivity extends MiBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void clickButton(View v) {
        switch (v.getId()) {
            case R.id.set_exit:
                load();
                break;
            case R.id.set_changpassword:
                Intent intent2 = new Intent(this, ChangePasswordActivity.class);
                startActivity(intent2);
                break;
            case R.id.set_changself_data:
                Intent intent5 = new Intent(this, ChangSelfDataActivity.class);
                startActivity(intent5);
                break;
        }
    }

    // 退出
    private void load() {
        HelpClass.logout(getApplicationContext(), true);
        ActivityManager.getActivityManager().exit();
        // showProgressDialog("登出中");
        // new AnalyzeJson(handler, HttpConstant.EXIT,
        // HttpConstant.getMapToken(),
        // REQUEST_SUCCESS);
    }

    @Override
    protected void initWidget() {
        titleBar.setTitleText("设置");
        titleBar.setLeftImgDefaultBack(this, R.drawable.back);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_set;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                HelpClass.logout(getApplicationContext(), true);
                ActivityManager.getActivityManager().exit();
                break;

            default:
                break;
        }
        return super.handleMessage(msg);
    }

}
