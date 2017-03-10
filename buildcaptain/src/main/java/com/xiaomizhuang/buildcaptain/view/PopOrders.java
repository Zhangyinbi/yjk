package com.xiaomizhuang.buildcaptain.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.hbw.library.BasePopupWindow;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.view.DialogPrompt;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.BuildSite;

/**
 * Created by user on 2015/11/19.
 * 电话列表弹出框
 */
public class PopOrders extends BasePopupWindow implements View.OnClickListener {
    DialogPrompt dialogPrompt;
    private TextView tvOwnerPhone;//主材下单
    private TextView tvProjectManagerPhone;//辅材下单
    private TextView tvDesignerPhone;//增加工程量
    private TextView tvCancel;//取消

    public PopOrders(Activity activity) {
        super(activity);
        initView();
    }

    private void initView() {
        initPopView();
        setAnimationStyle(R.style.PopupAnimation);//设置popwin动画
    }

    private void initPopView() {
        tvOwnerPhone = (TextView) getContentView().findViewById(R.id.tv_owner_phone);
        tvProjectManagerPhone = (TextView) getContentView().findViewById(R.id.tv_project_manager_phone);
        tvDesignerPhone = (TextView) getContentView().findViewById(R.id.tv_designer_phone);
        tvCancel = (TextView) getContentView().findViewById(R.id.tv_cancel);
        tvOwnerPhone.setText("主材下单");
        tvProjectManagerPhone.setText("辅材下单");
        tvDesignerPhone.setText("增加工程量");
        tvOwnerPhone.setOnClickListener(this);
        tvProjectManagerPhone.setOnClickListener(this);
        tvDesignerPhone.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
    }

    @Override
    protected int inItContentViewId() {
        return R.layout.pop_telphone_view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_owner_phone://主材下单
                dismiss();
                break;
            case R.id.tv_project_manager_phone://辅材下单
                dismiss();
                break;
            case R.id.tv_designer_phone://增加工程量
                dismiss();
                break;
            case R.id.tv_cancel://取消
                dismiss();
                break;
        }
    }
}
