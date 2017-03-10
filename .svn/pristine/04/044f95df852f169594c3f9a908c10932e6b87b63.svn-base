package com.example.yangbang.miowner.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.yangbang.miowner.R;


/**
 * Created by user on 2015/11/25.提示框
 */
public class AlertDialog extends Dialog{

    private TextView dialog_alert_title;

    public AlertDialog(Context context,String title) {
        /* 设置样式 */
        super(context, R.style.MyDialogStyle);
        this.setContentView(R.layout.diaglog_alert);
        dialog_alert_title = (TextView)findViewById(R.id.dialog_alert_title);
        dialog_alert_title.setText(title);
    }

    public void setDialog_alert_title(TextView dialog_alert_title) {
        this.dialog_alert_title = dialog_alert_title;
    }
}
