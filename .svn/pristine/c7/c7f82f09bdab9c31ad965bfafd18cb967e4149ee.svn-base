package com.youjuke.miprojectmanager.activity;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.hbw.library.BaseActivity;
import com.hbw.library.utils.ToastUtil;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.util.HttpConstant;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * 修改密码
 *
 * @author Youjk
 */
public class ChangePasswordActivity extends MiBaseActivity {
    private EditText edittext_changpassword_old, edittext_changpassword_new,
            edittext_changpassword_newonece;
    private String newpwd, renewpwd, password;
    private RequestQueue queue;

    public void clickButton(View v) {
        switch (v.getId()) {
            case R.id.button_changpassword_OK:
                // 检查密码正确性 如果正确则跳转到登陆界面
                if (checkPassWord()) {
                    load();
                }
                break;
        }
    }

    public boolean checkPassWord() {
        String regexp = "^[0-9a-zA-Z]{6}$";
        Pattern pattern = Pattern.compile(regexp);
        String password_old = edittext_changpassword_old.getText().toString();
        newpwd = edittext_changpassword_new.getText().toString();
        renewpwd = edittext_changpassword_newonece.getText().toString();
        boolean newMatch = pattern.matcher(password).matches();
        if (TextUtils.isEmpty(password_old)) {
            ToastUtil.show(this, "请输入旧密码");
            return false;
        } else if (TextUtils.isEmpty(newpwd) && !TextUtils.isEmpty(password)) {
            ToastUtil.show(this, "请输入新密码");
            return false;
        } else if (TextUtils.isEmpty(renewpwd) && !TextUtils.isEmpty(newpwd)
                && !TextUtils.isEmpty(password)) {
            ToastUtil.show(this, "请再次输入新密码");
            return false;
        } else if (!password_old.equals(password)) {
            ToastUtil.show(this, "旧密码错误");
            edittext_changpassword_old.setText("");
            return false;
        } else if (!newpwd.equals(renewpwd) && password_old.equals(password)) {
            ToastUtil.show(this, "两次密码不一致");
            return false;
        } else if (!newMatch) {
            ToastUtil.show(this, "请正确输入密码");
            return false;
        } else if (newpwd.equals(password_old)) {
            ToastUtil.show(this, "号码未做改变");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        if (msg.what == 7) {
            ToastUtil.show(this, "密码修改成功，请尝试用新密码登陆");
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        return super.handleMessage(msg);
    }

    // 提交密码参数
    private void load() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("token", HttpConstant.token);
        map.put("newpwd", newpwd);
        map.put("renewpwd", renewpwd);
        map.put("password", password);
        analyzeJson.requestData(HttpConstant.ModifyPwdUrl, map, 7);
        // queue.add(request) ;
    }

    @Override
    protected void initWidget() {
        edittext_changpassword_new = (EditText) findViewById(R.id.edittext_changpassword_new);
        edittext_changpassword_old = (EditText) findViewById(R.id.edittext_changpassword_old);
        edittext_changpassword_newonece = (EditText) findViewById(R.id.edittext_changpassword_newonece);
        titleBar.setTitleText("修改密码");
        titleBar.setLeftImgDefaultBack(this);
    }

    @Override
    protected void initData() {
        password = HttpConstant.pwd;
        queue = Volley.newRequestQueue(this);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.set_changpassword;
    }
}
