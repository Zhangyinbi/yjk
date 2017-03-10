package com.example.yangbang.miowner.entity;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.DateUtils;

import java.util.List;

/**
 * Created by mwy on 2016/7/1.
 */
public class AllActasPurchasingAgencyAdapter extends AbsBaseAdapter<AllActasPurchasingAgency> {
	private Context context;

	public AllActasPurchasingAgencyAdapter(Context context,List<AllActasPurchasingAgency> dataList) {
		super(dataList);
		this.context = context;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		ViewHolder holder = null;
		if (v == null) {
			holder = new ViewHolder();
			v = View.inflate(context, R.layout.listitem_all_actas_purchasing_agency_of_event_remind, null);
			holder.tv_event_remind_title = (TextView) v.findViewById(R.id.tv_event_remind_title);
			holder.tv_event_remind_create_time = (TextView) v.findViewById(R.id.tv_event_remind_create_time);
			holder.tv_event_remind_state = (TextView)v.findViewById(R.id.tv_event_remind_state);
			holder.tv_event_remind_time = (TextView)v.findViewById(R.id.tv_event_remind_time);
			holder.tv_event_remind_name = (TextView)v.findViewById(R.id.tv_event_remind_name);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		String createTime = dataList.get(position).getCreate_time();
		holder.tv_event_remind_create_time.setText(DateUtils.simpleFormat("yyyy-MM-dd HH:mm:ss","MM月dd日",createTime));
		if ("1".equals(dataList.get(position).getStatus().trim())){//1已安装 0未安装
			holder.tv_event_remind_state.setText("已安装");
			holder.tv_event_remind_state.setTextColor(Color.parseColor("#959595"));
		}else{
			holder.tv_event_remind_state.setText("未安装");
			holder.tv_event_remind_state.setTextColor(Color.parseColor("#ff0000"));
		}
		String time = dataList.get(position).getRemind_day();
		String week = DateUtils.getWeek("yyy-MM-dd",time);
		String newDate = DateUtils.simpleFormat("yyyy-MM-dd","yyyy.MM.dd",time);
		holder.tv_event_remind_time.setText("预计安装时间 : "+newDate+" "+week);
		holder.tv_event_remind_title.setText(dataList.get(position).getTitle());
		holder.tv_event_remind_name.setText("主材名称 : "+dataList.get(position).getMaterial_name());
		return v;
	}

	private class ViewHolder{
		public TextView tv_event_remind_create_time;
		public TextView tv_event_remind_title;
		public TextView tv_event_remind_state;
		public TextView tv_event_remind_name;
		public TextView tv_event_remind_time;
	}
}
