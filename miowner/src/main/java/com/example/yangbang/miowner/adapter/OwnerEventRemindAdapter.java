package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.OwnerIntermediateAcceptance;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.DateUtils;

import java.util.List;

/**
 * Created by user on 2016/1/12.
 */
public class OwnerEventRemindAdapter<T> extends AbsBaseAdapter<T> {

    private Context context;

    public OwnerEventRemindAdapter(Context context,List<T> dataList) {
        super(dataList);
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder = null;
        if (v == null) {
            holder = new ViewHolder();
            v = View.inflate(context, R.layout.listitem_intermediate_acceptance_of_event_remind, null);
            holder.tv_event_remind_title = (TextView) v.findViewById(R.id.tv_event_remind_title);
            holder.tv_event_remind_create_time = (TextView) v.findViewById(R.id.tv_event_remind_create_time);
            holder.tv_event_remind_state = (TextView)v.findViewById(R.id.tv_event_remind_state);
            holder.tv_event_remind_time = (TextView)v.findViewById(R.id.tv_event_remind_time);
            holder.tv_event_remind_description = (TextView)v.findViewById(R.id.tv_event_remind_description);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        String createTime = ((OwnerIntermediateAcceptance)dataList.get(position)).getCreate_time();
        holder.tv_event_remind_create_time.setText(DateUtils.simpleFormat("yyyy-MM-dd HH:mm:ss","MM月dd日",createTime));
        holder.tv_event_remind_title.setText(((OwnerIntermediateAcceptance)dataList.get(position)).getMsg());
        holder.tv_event_remind_description.setText(((OwnerIntermediateAcceptance)dataList.get(position)).getMessang());
        if (((OwnerIntermediateAcceptance)dataList.get(position)).getStatus().trim()=="1"){//1.完成，0未完成
            holder.tv_event_remind_state.setText("完成");
            holder.tv_event_remind_state.setTextColor(Color.parseColor("#959595"));
        }else{
            holder.tv_event_remind_state.setText("未完成");
            holder.tv_event_remind_state.setTextColor(Color.parseColor("#ff0000"));
        }
        String remindDay = ((OwnerIntermediateAcceptance) dataList.get(position)).getRemind_day();
        String week = DateUtils.getWeek("yyy-MM-dd",remindDay);
        String newDate = DateUtils.simpleFormat("yyyy-MM-dd","yyyy.MM.dd",remindDay);
        holder.tv_event_remind_time.setText("预计验收时间 : "+newDate+" "+week);
	    holder.tv_event_remind_description.setText(((OwnerIntermediateAcceptance) dataList.get(position)).getMessang());
        return v;
    }

    class ViewHolder {
        public TextView tv_event_remind_create_time;
        public TextView tv_event_remind_title;
        public TextView tv_event_remind_state;
        public TextView tv_event_remind_time;
        public TextView tv_event_remind_description;
    }
}
