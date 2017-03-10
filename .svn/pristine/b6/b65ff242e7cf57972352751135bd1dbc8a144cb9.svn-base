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
public class PopTelphone extends BasePopupWindow implements View.OnClickListener {
    BuildSite buildSite;
    DialogPrompt dialogPrompt;
    private TextView tvOwnerPhone;//业主电话
    private TextView tvProjectManagerPhone;//项目经理电话
    private TextView tvDesignerPhone;//设计师电话
    private TextView tv_muser_mobile;//下单员电话
    //    private TextView tvBuilder;//施工队长电话
    private TextView tvCancel;//取消

    public PopTelphone(Activity activity, BuildSite buildSite) {
        super(activity);
        this.buildSite = buildSite;
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
        tv_muser_mobile = (TextView) getContentView().findViewById(R.id.tv_muser_mobile);
//        tvBuilder = (TextView) view.findViewById(R.id.tvBuilder);
        tvCancel = (TextView) getContentView().findViewById(R.id.tv_cancel);
        tvOwnerPhone.setTag(buildSite.getYz_mobile());

        tvProjectManagerPhone.setTag(buildSite.getSuperior_mobile());
        tvDesignerPhone.setTag(buildSite.getDesigner_mobile());
        tv_muser_mobile.setTag(buildSite.getMuser_mobile());
        tvOwnerPhone.setOnClickListener(this);
        tvProjectManagerPhone.setOnClickListener(this);
        tvDesignerPhone.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        tv_muser_mobile.setOnClickListener(this);

        if (TextUtils.isEmpty(buildSite.getSuperior())) {
            tvProjectManagerPhone.setText(buildSite.getYjk_jianli() == 1 ?
                    "联系监理（暂无）" : "联系项目经理（暂无）");
            tvProjectManagerPhone.setClickable(false);
        } else {
            tvProjectManagerPhone.setText(buildSite.getYjk_jianli() == 1 ?
                    "联系监理（" + buildSite.getSuperior() + "）" : "联系项目经理（" + buildSite.getSuperior() + "）");
        }

        if (TextUtils.isEmpty(buildSite.getName())) {
            tvOwnerPhone.setText("联系业主（暂无）");
            tvOwnerPhone.setClickable(false);
        } else {
            tvOwnerPhone.setText("联系业主（" + buildSite.getName() + "）");
        }

        if (TextUtils.isEmpty(buildSite.getDesigner())) {
            tvDesignerPhone.setText("联系设计师（暂无）");
            tvDesignerPhone.setClickable(false);
        } else {
            tvDesignerPhone.setText("联系设计师（" + buildSite.getDesigner() + "）");
        }

        if (TextUtils.isEmpty(buildSite.getMuser())) {
            tv_muser_mobile.setText("联系下单员（暂无）");
            tv_muser_mobile.setClickable(false);
        } else {
            tv_muser_mobile.setText("联系下单员（" + buildSite.getMuser() + "）");
        }


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
                    intent.setAction(Intent.ACTION_DIAL);
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
    protected int inItContentViewId() {
        return R.layout.pop_telphone_view;
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
            case R.id.tv_muser_mobile://下单员
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
