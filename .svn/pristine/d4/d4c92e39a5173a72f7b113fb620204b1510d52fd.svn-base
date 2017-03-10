package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.MoneyMatters;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.DateUtils;
import com.hbw.library.utils.MoneySimpleFormat;

import java.util.List;

/**
 * Created by mwy on 2016/6/30.
 */
public class MoneyMattersAdapter extends AbsBaseAdapter<MoneyMatters> {
	private Context context;

	public MoneyMattersAdapter(Context context,List<MoneyMatters> dataList) {
		super(dataList);
		this.context = context;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		ViewHolder holder = null;
		if (v == null) {
			holder = new ViewHolder();
			v = View.inflate(context, R.layout.listitem_money_matters_of_event_remind, null);
			holder.tv_event_remind_title = (TextView) v.findViewById(R.id.tv_event_remind_title);
			holder.tv_event_remind_create_time = (TextView) v.findViewById(R.id.tv_event_remind_create_time);
			holder.tv_event_remind_state = (TextView)v.findViewById(R.id.tv_event_remind_state);
			holder.tv_event_remind_time = (TextView)v.findViewById(R.id.tv_event_remind_time);
			holder.tv_event_remind_money = (TextView)v.findViewById(R.id.tv_event_remind_money);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		holder.tv_event_remind_time.setText(dataList.get(position).getMsg());
		String createTime = dataList.get(position).getCreate_time();
		holder.tv_event_remind_create_time.setText(DateUtils.simpleFormat("yyyy-MM-dd","MM月dd日",createTime));
		if ("1".equals(dataList.get(position).getStatus().trim())){//1.打款，0未打款
			holder.tv_event_remind_state.setText("已打款");
			holder.tv_event_remind_state.setTextColor(Color.parseColor("#959595"));
		}else{
			holder.tv_event_remind_state.setText("未打款");
			holder.tv_event_remind_state.setTextColor(Color.parseColor("#ff0000"));
		}
		String time = dataList.get(position).getTime_of_payment();
		String week = DateUtils.getWeek("yyy-MM-dd",time);
		String newDate = DateUtils.simpleFormat("yyyy-MM-dd","yyyy.MM.dd",time);
		holder.tv_event_remind_time.setText("建议缴纳时间 : "+newDate+" "+week);
		holder.tv_event_remind_money.setText("应缴纳金额 : "
				+ MoneySimpleFormat.getYuanType(dataList.get(position).getPayable_amount()));
		return v;
	}
	private class ViewHolder{
		public TextView tv_event_remind_create_time;
		public TextView tv_event_remind_title;
		public TextView tv_event_remind_state;
		public TextView tv_event_remind_money;
		public TextView tv_event_remind_time;
	}
}
