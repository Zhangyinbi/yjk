package com.xiaomizhuang.buildcaptain.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaomizhuang.buildcaptain.R;

/**
 * 添加延期备注对话框
 *
 * @FileName: com.xiaomizhuang.buildcaptain.view.DialogAddRemark.java
 * @author: Yangbang
 * @date: 2016-01-22 13:54
 */
public class DialogAddRemark extends Dialog implements View.OnClickListener {
    View view;
    ImageView imgv_dialog_close;//关闭对话框
    EditText et_dialog_remark;//填写的备注内容
    TextView tv_dialog_add_remark_ok;//确认

    public DialogAddRemark(Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        initDialogView();
    }

    private void initDialogView() {
        view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_remark, null);
        setContentView(view);
        imgv_dialog_close = (ImageView) view.findViewById(R.id.imgv_dialog_close);
        et_dialog_remark = (EditText) view.findViewById(R.id.et_dialog_remark);
        tv_dialog_add_remark_ok = (TextView) view.findViewById(R.id.tv_dialog_add_remark_ok);
        imgv_dialog_close.setOnClickListener(this);
    }

    /**
     * 设置确认按钮事件监听
     *
     * @param listener
     */
    public void setDialogOkListener(View.OnClickListener listener) {
        if (listener != null) {
            tv_dialog_add_remark_ok.setOnClickListener(listener);
        }
    }

    /**
     * 获取备注内容
     *
     * @return
     */
    public String getRemarkContent() {
        return et_dialog_remark.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imgv_dialog_close:
                dismiss();
//                et_dialog_remark.setText("");//清空备注
                break;
        }
    }
}
