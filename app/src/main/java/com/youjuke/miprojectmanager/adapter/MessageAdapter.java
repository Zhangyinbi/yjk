package com.youjuke.miprojectmanager.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.net.AnalyzeJson;
import com.hbw.library.utils.L;
import com.hbw.library.utils.ViewHolders;
import com.hbw.library.view.DialogPrompt;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.activity.CheckBillActivity;
import com.youjuke.miprojectmanager.entity.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by user on 2015/11/23.
 */
public class MessageAdapter extends AbsBaseAdapter<Message> {
    Context context;
    SimpleDateFormat dateFormat;
    Calendar cal;
    String date;
    String todayDate;
    String yesterdayDate;
    DialogPrompt dialogPrompt;
    AnalyzeJson analyzeJson;

    public MessageAdapter(Context context, List<Message> dataList) {
        super(dataList);
        this.context = context;
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
        if (position > 0 && dataList.get(position).getRemind_day().equals(dataList.get(position - 1).getRemind_day())) {
            ((LinearLayout) ViewHolders.get(convertView, R.id.linear_msg_title_time)).setVisibility(View.GONE);
        } else {
            ((LinearLayout) ViewHolders.get(convertView, R.id.linear_msg_title_time)).setVisibility(View.VISIBLE);
        }
        if (dataList.get(position).getRemind_day().equals(todayDate)) {
            ((TextView) ViewHolders.get(convertView, R.id.tv_msg_title_time)).setText("今天");
        } else if (dataList.get(position).getRemind_day().equals(yesterdayDate)) {
            ((TextView) ViewHolders.get(convertView, R.id.tv_msg_title_time)).setText("昨天");
        } else {
            ((TextView) ViewHolders.get(convertView, R.id.tv_msg_title_time)).setText(dataList.get(position).getRemind_day());
        }
        ((TextView) ViewHolders.get(convertView, R.id.tv_msg_address)).setText(dataList.get(position).getZx_address());
        ((TextView) ViewHolders.get(convertView, R.id.tv_msg_check_type)).setText(dataList.get(position).getProjectphase());
        ((TextView) ViewHolders.get(convertView, R.id.tv_msg_check_time)).setText("期望验收时间：" + dataList.get(position).getRemind_day());
        ((TextView) ViewHolders.get(convertView, R.id.tv_msg_xiadan)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CheckBillActivity.class);
                intent.putExtra("baoming_id", dataList.get(position).getBm_id() + "");
                intent.putExtra("msg_id", dataList.get(position).getMsg_id() + "");
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
