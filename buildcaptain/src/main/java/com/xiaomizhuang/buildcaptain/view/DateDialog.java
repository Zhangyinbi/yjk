package com.xiaomizhuang.buildcaptain.view;

import android.app.Dialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.xiaomizhuang.buildcaptain.R;

/**
 * Created by user on 2015/11/23.
 */
public class DateDialog extends Dialog {

    private DatePicker orderDetailsDatePicker;
    private TimePicker orderDetailsTimePicker;
    private TextView orderDetailsCancel;
    private TextView orderDetailsConfirm;
    private TextView orderDetailsTitle;//标题

    public DateDialog(Context context) {
        /* 设置样式 */
        super(context, R.style.MyDialogStyle);
        /* 设置布局 */
        this.setContentView(R.layout.dialog_date);
        initView();
    }

    private void initView() {
        orderDetailsDatePicker = (DatePicker) findViewById(R.id.order_details_DatePicker);
        orderDetailsTimePicker = (TimePicker) findViewById(R.id.order_details_TimePicker);
        orderDetailsCancel = (TextView) findViewById(R.id.order_details_cancel);
        orderDetailsConfirm = (TextView) findViewById(R.id.order_details_confirm);
        orderDetailsTitle = (TextView) findViewById(R.id.order_details_title);
        orderDetailsTimePicker.setIs24HourView(true);
    }

    /**
     * 设置dialog标题
     *
     * @param title
     */
    public void setDialogTitle(String title) {
        if (title != null)
            orderDetailsTitle.setText(title);
    }

    /**
     * 设置隐藏或者显示时间选择器
     *
     * @param visible
     */
    public void setOrderDetailsTimePickerVisible(int visible) {
        orderDetailsTimePicker.setVisibility(visible);
    }

    public DateDialog setCancelListener(android.view.View.OnClickListener listener) {
        if (listener != null) {
            orderDetailsCancel.setOnClickListener(listener);
        }
        return this;
    }

    public DateDialog setConfirmListener(android.view.View.OnClickListener listener) {
        if (listener != null) {
            orderDetailsConfirm.setOnClickListener(listener);
        }
        return this;
    }

    /**
     * 取得日期选择器
     *
     * @return
     */
    public DatePicker getOrderDetailsDatePicker() {
        return orderDetailsDatePicker;
    }

    public DatePicker setDatePickerChangedListener(int year, int monthOfYear, int dayOfMonth, DatePicker.OnDateChangedListener listener) {
        orderDetailsDatePicker.init(year, monthOfYear, dayOfMonth, listener);
        return orderDetailsDatePicker;
    }

    public TimePicker setTimePickerChangedListener(TimePicker.OnTimeChangedListener listener) {
        if (listener != null) {
            orderDetailsTimePicker.setOnTimeChangedListener(listener);
        }
        return orderDetailsTimePicker;
    }

}
