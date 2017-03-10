package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.net.AnalyzeJson;
import com.hbw.library.utils.L;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.ViewHolders;
import com.hbw.library.view.DialogPrompt;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.AssistDetailsActivity;
import com.xiaomizhuang.buildcaptain.activity.MessageActivity;
import com.xiaomizhuang.buildcaptain.entity.Message;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.DateDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/11/23.
 */
public class MessageAdapter extends AbsBaseAdapter<Message> {
    public static final int ORDER_OR_CHECK = 87;//接单或验收
    Context context;
    SimpleDateFormat dateFormat;
    Calendar cal;
    String date;
    String todayDate;
    String yesterdayDate;
    DialogPrompt dialogPrompt;
    DateDialog dateDialog;
    AnalyzeJson analyzeJson;

    public MessageAdapter(Context context, List<Message> dataList, AnalyzeJson analyzeJson) {
        super(dataList);
        this.context = context;
        this.analyzeJson = analyzeJson;
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        cal = Calendar.getInstance();
        todayDate = dateFormat.format(cal.getTime());
        cal.add(Calendar.DATE, -1);
        yesterdayDate = dateFormat.format(cal.getTime());
        L.i("YANGBANG", "todayDate-->" + todayDate);
        L.i("YANGBANG", "yesterdayDate-->" + yesterdayDate);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.message_item, parent, false);
        }
        if (position > 0 && dataList.get(position).getCreate_time().equals(dataList.get(position - 1).getCreate_time())) {
            ((LinearLayout) ViewHolders.get(convertView, R.id.linear_msg_title_time)).setVisibility(View.GONE);
        } else {
            ((LinearLayout) ViewHolders.get(convertView, R.id.linear_msg_title_time)).setVisibility(View.VISIBLE);
        }
        if (dataList.get(position).getCreate_time().equals(todayDate)) {
            ((TextView) ViewHolders.get(convertView, R.id.tv_msg_title_time)).setText("今天");
        } else if (dataList.get(position).getCreate_time().equals(yesterdayDate)) {
            ((TextView) ViewHolders.get(convertView, R.id.tv_msg_title_time)).setText("昨天");
        } else {
            ((TextView) ViewHolders.get(convertView, R.id.tv_msg_title_time)).setText(dataList.get(position).getCreate_time());
        }
        ((TextView) ViewHolders.get(convertView, R.id.tv_msg_address)).setText(dataList.get(position).getZx_address());
        ((TextView) ViewHolders.get(convertView, R.id.tv_msg_time)).setText(dataList.get(position).getRemind_day());
        ((TextView) ViewHolders.get(convertView, R.id.tv_msg_type)).setText(dataList.get(position).getMsg());
        ((TextView) ViewHolders.get(convertView, R.id.tv_msg_delay_days)).setText(dataList.get(position).getDelay());
        if (dataList.get(position).getType().equals("1")) {
            ((TextView) ViewHolders.get(convertView, R.id.tv_msg_xiadan)).setText("去下单");
        } else if (dataList.get(position).getType().equals("2")) {
            ((TextView) ViewHolders.get(convertView, R.id.tv_msg_xiadan)).setText("申请验收");
        } else if (dataList.get(position).getType().equals("7")) {
            ((TextView) ViewHolders.get(convertView, R.id.tv_msg_xiadan)).setText("知道了");
        }else if (dataList.get(position).getType().equals("8")){
            ((TextView) ViewHolders.get(convertView, R.id.tv_msg_xiadan)).setText("知道了");
        }
            ((TextView) ViewHolders.get(convertView, R.id.tv_msg_xiadan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dataList.get(position).getType().equals("1")) {
                    Intent intent = new Intent(context, AssistDetailsActivity.class);
                    intent.putExtra("uid", dataList.get(position).getBm_id());
                    intent.putExtra("msg_id", dataList.get(position).getId());
                    context.startActivity(intent);
                } else if (dataList.get(position).getType().equals("2")) {
                    if (dateDialog == null) {
                        dateDialog = new DateDialog(context);
                    }
                    dateDialog.setDialogTitle("设置期望验收时间");
                    dateDialog.setOrderDetailsTimePickerVisible(View.GONE);
                    dateDialog.setCancelListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dateDialog.dismiss();
                        }
                    });
                    dateDialog.setConfirmListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            cal = Calendar.getInstance();
                            long systemData = cal.getTimeInMillis();
                            cal.set(dateDialog.getOrderDetailsDatePicker().getYear(), dateDialog.getOrderDetailsDatePicker().getMonth(), dateDialog.getOrderDetailsDatePicker().getDayOfMonth(), 23, 59, 59);
                            long expectData = cal.getTimeInMillis();
                            String remind_day = dateDialog.getOrderDetailsDatePicker().getYear() + "-" + (dateDialog.getOrderDetailsDatePicker().getMonth() + 1) + "-" + dateDialog.getOrderDetailsDatePicker().getDayOfMonth();
                            Log.i("YANGBANG", "期望验收时间为-->" + expectData + ",日期-->" + remind_day);
                            Log.i("YANGBANG", "系统时间为-->" + systemData);
                            if (expectData - systemData >= 0) {
                                HashMap<String, String> params = new HashMap<String, String>();
                                params.put("token", MyApplication.TOKEN);
                                params.put("id", dataList.get(position).getId());
                                params.put("remind_day", remind_day);
                                params.put("type", dataList.get(position).getType());
                                analyzeJson.requestData(HttpConstant.ApplyCheckUrl, params, MessageActivity.CONFIRM_RECEIVE);
                                dateDialog.dismiss();
                            } else {
                                ToastUtil.show(context, "不能小于当前日期");
                            }
                        }
                    });
                    dateDialog.show();

                    /*if (dialogPrompt == null) {
                        dialogPrompt = new DialogPrompt(context);
                    }
                    dialogPrompt.setPromptTitle("提示");
                    dialogPrompt.setPromptContent("提交后1-3日通过验证审核");
                    dialogPrompt.setLeftBtnContent("取消");
                    dialogPrompt.setRightBtnContent("申请验收");
                    dialogPrompt.setLeftDialogPrompeListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialogPrompt.dismiss();
                        }
                    });
                    dialogPrompt.setRightDialogPrompeListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            HashMap<String, String> params = new HashMap<String, String>();
                            params.put("token", MyApplication.TOKEN);
                            params.put("id", dataList.get(position).getId());
                            analyzeJson.requestData(HttpConstant.ApplyCheckUrl, params, MessageActivity.CONFIRM_RECEIVE);
                            dialogPrompt.dismiss();
                        }
                    });
                    dialogPrompt.show();*/
                } else if (dataList.get(position).getType().equals("7") || dataList.get(position).getType().equals("8")) {
                    HashMap<String, String> params = new HashMap<>();
                    params.put("token", MyApplication.TOKEN);
                    params.put("id", dataList.get(position).getId());
                    params.put("type", dataList.get(position).getType());
                    analyzeJson.requestData(HttpConstant.ApplyCheckUrl, params, MessageActivity.CONFIRM_RECEIVE);
//                    if (dialogPrompt == null) {
//                        dialogPrompt = new DialogPrompt(context);
//                    }
//                    dialogPrompt.setPromptTitle("确认接单");
//                    dialogPrompt.setLeftBtnContent("取消");
//                    dialogPrompt.setRightBtnContent("确认");
//                    dialogPrompt.setLeftDialogPrompeListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            dialogPrompt.dismiss();
//                        }
//                    });
//                    dialogPrompt.setRightDialogPrompeListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            HashMap<String, String> params = new HashMap<String, String>();
//                            params.put("token", MyApplication.TOKEN);
//                            params.put("id", dataList.get(position).getId());
//                            params.put("type", dataList.get(position).getType());
//                            analyzeJson.requestData(HttpConstant.ApplyCheckUrl, params, MessageActivity.CONFIRM_RECEIVE);
//                            dialogPrompt.dismiss();
//                        }
//                    });
//                    dialogPrompt.show();
                }
            }
        });
        return convertView;
    }
}
