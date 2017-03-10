package com.example.yangbang.miowner.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.OwnerUser;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.view.DialogPrompt;

/**
 * Created by user on 2015/11/19.
 * 电话列表弹出框
 */
public class PopTelphone extends PopupWindow implements View.OnClickListener {
    Activity activity;
    OwnerUser user;
    DialogPrompt dialogPrompt;
    private TextView tvOwnerPhone;//业主电话
    private TextView tvProjectManagerPhone;//项目经理电话
    private TextView tvDesignerPhone;//设计师电话
    //    private TextView tvBuilder;//施工队长电话
    private TextView tvCancel;//取消

    public PopTelphone(Activity activity, OwnerUser user) {
        this.activity = activity;
        this.user = user;
        initView();
    }

    private void initView() {
        initPopView();
        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(android.R.color.transparent)));
        setAnimationStyle(R.style.PopupAnimation);
    }

    private void initPopView() {
        View view = LayoutInflater.from(activity).inflate(R.layout.pop_telphone_view, null);
        tvOwnerPhone = (TextView) view.findViewById(R.id.tv_owner_phone);
        tvProjectManagerPhone = (TextView) view.findViewById(R.id.tv_project_manager_phone);
        tvDesignerPhone = (TextView) view.findViewById(R.id.tv_designer_phone);
//        tvBuilder = (TextView) view.findViewById(R.id.tvBuilder);
        tvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        tvOwnerPhone.setText("联系施工队长（" + user.getBuilder() + "）");
        tvOwnerPhone.setTag(user.getBuilder_mobile());
        tvProjectManagerPhone.setText("联系项目经理（" + user.getSuperior() + "）");
        tvProjectManagerPhone.setTag(user.getSuperior_mobile());
        tvDesignerPhone.setText("联系设计师（" + user.getDesigner() + "）");
        tvDesignerPhone.setTag(user.getDesigner_mobile());
        tvOwnerPhone.setOnClickListener(this);
        tvProjectManagerPhone.setOnClickListener(this);
        tvDesignerPhone.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        setContentView(view);
        if (dialogPrompt == null) {
            dialogPrompt = new DialogPrompt(activity);
            dialogPrompt.setLeftBtnContent("取消");
            dialogPrompt.setRightBtnContent("呼叫");
            dialogPrompt.setLeftDialogPrompeListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogPrompt.dismiss();
                }
            });
            dialogPrompt.setRightDialogPrompeListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogPrompt.dismiss();
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    if (TextUtils.isEmpty(dialogPrompt.getPromptContent())) {
                        ToastUtil.show(activity, "暂无电话");
                    } else {
                        intent.setData(Uri.parse("tel:" + dialogPrompt.getPromptContent()));
                        activity.startActivity(intent);
                    }
                }
            });
        }
    }

    @Override
    public void dismiss() {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 1.0f;
        activity.getWindow().setAttributes(lp);
        super.dismiss();
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.5f;
        activity.getWindow().setAttributes(lp);
        super.showAsDropDown(anchor, xoff, yoff, gravity);
//        popWindow.showAtLocation(findViewById(R.id.login_layout),
//                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.5f;
        activity.getWindow().setAttributes(lp);
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_owner_phone://业主
                dismiss();
                dialogPrompt.setPromptContent((String) v.getTag());
                dialogPrompt.show();
                break;
            case R.id.tv_project_manager_phone://项目经理
                dismiss();
                dialogPrompt.setPromptContent((String) v.getTag());
                dialogPrompt.show();
                break;
            case R.id.tv_designer_phone://设计师
                dismiss();
                dialogPrompt.setPromptContent((String) v.getTag());
                dialogPrompt.show();
                break;
            case R.id.tv_cancel://取消
                dismiss();
                break;
        }
    }
}
