package com.xiaomizhuang.buildcaptain.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.jzxiang.pickerview.TimePickerDialog;
import com.jzxiang.pickerview.data.Type;
import com.jzxiang.pickerview.listener.OnDateSetListener;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.SetScheduleActivity;
import com.xiaomizhuang.buildcaptain.entity.Schedule;
import com.xiaomizhuang.buildcaptain.entity.ScheduleTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 描述: 设置排期适配器
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-09-08 13:58
 */
public class ScheduleAdapter extends AbsBaseAdapter<Schedule> {

	private SetScheduleActivity activity;
	private Map<Integer, Boolean> checkeds;
	private TimePickerDialog timePicker;
	private long tenYear = 10L * 365L * 24L * 60L * 60L * 1000L;
	private Map<Integer, ScheduleTime> scheduleTimes; //时间集合
	public static final int START = -1;//起始时间flag
	public static final int END = -2;//结束时间flag

	public ScheduleAdapter(SetScheduleActivity activity, List<Schedule> dataList) {
		super(dataList);
		this.activity = activity;
		checkeds = new TreeMap<>();
		scheduleTimes = new TreeMap<>();
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ScheduleAdapter.ViewHodler holder = null;
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(R.layout.list_item_set_schedule, null);
			holder = new ViewHodler();
			holder.ivCheck = (ImageView) convertView.findViewById(R.id.iv_check);
			holder.rlCheck = (RelativeLayout) convertView.findViewById(R.id.rl_check);
			holder.tvEndTime = (TextView) convertView.findViewById(R.id.ed_end_time);
			holder.tvStartTime = (TextView) convertView.findViewById(R.id.ed_start_time);
			holder.tv_phase_name = (TextView) convertView.findViewById(R.id.tv_phase_name);

			convertView.setTag(holder);
		} else {
			holder = (ViewHodler) convertView.getTag();
		}

		holder.tv_phase_name.setText(dataList.get(position).getName());
		holder.ivCheck.setOnClickListener(new MyOnClickListener(position));
		holder.tvStartTime.setOnClickListener(new OnSetDateLinstener(holder, position, START));
		if (scheduleTimes.get(position) != null && scheduleTimes.get(position).getStartTime() != 0){
			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(scheduleTimes.get(position).getStartTime()));
			holder.tvStartTime.setText(date);
		}
		holder.tvEndTime.setOnClickListener(new OnSetDateLinstener(holder, position, END));
		if (scheduleTimes.get(position) != null && scheduleTimes.get(position).getEndTime() != 0){
			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date(scheduleTimes.get(position).getEndTime()));
			holder.tvEndTime.setText(date);
		}
		return convertView;
	}

	/**
	 * 勾选的点击事件
	 */
	class MyOnClickListener implements View.OnClickListener {
		private int position;

		public MyOnClickListener(int positon) {
			this.position = positon;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.iv_check:
					if (checkeds.get(position) != null) {
						if (checkeds.get(position) == false) {
							checkeds.put(position, true);
							((ImageView) v).setImageResource(R.mipmap.checked);
						} else {
							checkeds.put(position, false);
							((ImageView) v).setImageResource(R.mipmap.uncheck);
						}
					} else {
						checkeds.put(position, true);
						((ImageView) v).setImageResource(R.mipmap.checked);
					}
					break;
				default:
					break;
			}
		}
	}

	class ViewHodler {
		RelativeLayout rlCheck;
		ImageView ivCheck;
		TextView tvStartTime;
		TextView tvEndTime;
		TextView tv_phase_name;
	}

	public Map<Integer, Boolean> getCheckeds() {
		return checkeds;
	}

	public void setCheckeds(Map<Integer, Boolean> checkeds) {
		this.checkeds = checkeds;
	}

	/**
	 * 选择事件的监听
	 */
	class OnSetDateLinstener implements View.OnClickListener {

		private ViewHodler viewHodler;
		private int position;
		private int type;

		public OnSetDateLinstener(ViewHodler viewHodler, int position, int type) {
			this.viewHodler = viewHodler;
			this.position = position;
			this.type = type;
		}

		@Override
		public void onClick(View v) {
			timePicker = new TimePickerDialog.Builder()
					.setCallBack(new OnDateSetListener() {
						@Override
						public void onDateSet(TimePickerDialog timePickerView, long millseconds) {
							if (type == START) {
								viewHodler.tvStartTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(millseconds)));
								if (scheduleTimes.get(position) == null){
									scheduleTimes.put(position,new ScheduleTime(position,millseconds,0));
								}else{
									ScheduleTime scheduleTime = scheduleTimes.get(position);
									scheduleTime.setStartTime(millseconds);
									scheduleTimes.put(position,scheduleTime);
								}
							} else {
								viewHodler.tvEndTime.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date(millseconds)));
								if (scheduleTimes.get(position) == null){
									scheduleTimes.put(position,new ScheduleTime(position,0,millseconds));
								}else{
									ScheduleTime scheduleTime = scheduleTimes.get(position);
									scheduleTime.setEndTime(millseconds);
									scheduleTimes.put(position,scheduleTime);
								}
							}
						}
					})
					.setTitleStringId("")
					.setCancelStringId("取消")
					.setSureStringId("确定")
					.setMinMillseconds(System.currentTimeMillis())
					.setMaxMillseconds(System.currentTimeMillis() + tenYear)
					.setType(Type.YEAR_MONTH_DAY)
					.build();
			timePicker.show(activity.getSupportFragmentManager(), "year_month_day");
		}

	}

	public Map<Integer, ScheduleTime> getScheduleTimes() {
		return scheduleTimes;
	}

	public void setScheduleTimes(Map<Integer, ScheduleTime> scheduleTimes) {
		this.scheduleTimes = scheduleTimes;
	}
}
