package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragmentActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.ScheduleAdapter;
import com.xiaomizhuang.buildcaptain.entity.Schedule;
import com.xiaomizhuang.buildcaptain.entity.ScheduleOfPostService;
import com.xiaomizhuang.buildcaptain.entity.ScheduleTime;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.HintDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * 设置施工排期
 */
public class SetScheduleActivity extends BaseFragmentActivity{

	private ListView lvSetSchedule;
	private Button btnSetSchedule;
	private List<Schedule> schedules;
	private ScheduleAdapter adapter;
	private Map<Integer,Boolean> checkeds;//选择集合
	private Map<Integer, ScheduleTime> scheduleTimes; //时间集合
	private List<ScheduleOfPostService> sopsList;//要提交的排期集合数据
	private HintDialog hintDialog;
	private String postJson;//提交的json
	private String baoming_id;
	private static final int SET_SCHEDUL = 0x2010;

	private void assignViews() {
		lvSetSchedule = (ListView) findViewById(R.id.lv_set_schedule);
		btnSetSchedule = (Button) findViewById(R.id.btn_set_schedule);
	}



	@Override
	protected void initWidget() {
		titleBar.setLeftImgDefaultBack(this);
		titleBar.setTitleText("设置工程排期");
		assignViews();
		schedules = new ArrayList<>();
		sopsList = new ArrayList<>();
		adapter = new ScheduleAdapter(this,schedules);
		lvSetSchedule.setAdapter(adapter);

		baoming_id = getIntent().getStringExtra("baoming_id");

		btnSetSchedule.setOnClickListener(new SubmitOnClickListener());

		hintDialog = new HintDialog(this).setDefultCancelListener().setConfirmListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				params.clear();
				params.put(AppFlag.TOKEN, MyApplication.TOKEN);
				params.put(AppFlag.BAOMINGID,baoming_id);
				params.put("data_str",postJson);
				analyzeJson.requestData(HttpConstant.SetPlan,params,SET_SCHEDUL);
				hintDialog.dismiss();
			}
		}).setTitleText("排期只能提交一次,您确定提交排期?");

		params.clear();
		params.put(AppFlag.TOKEN, MyApplication.TOKEN);
		analyzeJson.requestData(HttpConstant.GetProjects,params,REQUEST_SUCCESS);
	}

	@Override
	protected void initData() {

	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_set_schedule;
	}


	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				List<Schedule> dataList = gson.fromJson(data.data, new TypeToken<List<Schedule>>() {
				}.getType());
				schedules.clear();
				schedules.addAll(dataList);
				dataList.clear();
				dataList = null;
				adapter.notifyDataSetChanged(schedules);
				break;
			case SET_SCHEDUL://操作成功返回主界面
				ResponseSucceedData data2 = (ResponseSucceedData) msg.obj;
				ToastUtil.show(SetScheduleActivity.this,data2.message);
				startActivity(new Intent(SetScheduleActivity.this,BuildSiteActivity.class));
				break;
			default:
				break;
		}
		return super.handleMessage(msg);
	}

	class SubmitOnClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			scheduleTimes = adapter.getScheduleTimes();
			checkeds = adapter.getCheckeds();
			sopsList.clear();
			//获取所有勾选的数据
			Iterator<Map.Entry<Integer, Boolean>> iterator = checkeds.entrySet().iterator();
			int count = 0;
			long previousEndTime = -1024 ;
			while (iterator.hasNext()){
				Map.Entry entry = iterator.next();
				int position = (int) entry.getKey();
				//没有勾选
				if ((Boolean) entry.getValue() != true){
					continue;
				}else{
					count++;
					if (scheduleTimes.get(position) == null
							|| scheduleTimes.get(position).getStartTime()==0
							|| scheduleTimes.get(position).getEndTime() == 0){
						ToastUtil.show(SetScheduleActivity.this,"请选择"+schedules.get(position).getName()+"的时间");
						return;
					}
					if(scheduleTimes.get(position).getStartTime() >= scheduleTimes.get(position).getEndTime()){
						ToastUtil.show(SetScheduleActivity.this,schedules.get(position).getName()+"的起始时间必须小于结束时间");
						return;
					}
					//如果是选择的是第一个阶段 没有上一个阶段 如果是第一次循环到这
					if (previousEndTime == -1024) {
						previousEndTime = scheduleTimes.get(position).getEndTime();
					}else{//表示勾选的 有上一个阶段的排期
						if (previousEndTime > scheduleTimes.get(position).getStartTime()){
							ToastUtil.show(SetScheduleActivity.this,
									schedules.get(position).getName()+"的起始时间必须大于等于上个阶段的结束时间");
							return;
						}
					}
					ScheduleOfPostService sops = new ScheduleOfPostService();
					sops.setId(schedules.get(position).getStep());
					sops.setProject_id(schedules.get(position).getId());
					sops.setTitle(schedules.get(position).getName());
					sops.setStart(new SimpleDateFormat("yyyy-MM-dd").format(new Date(scheduleTimes.get(position).getStartTime())));
					sops.setEnd(new SimpleDateFormat("yyyy-MM-dd").format(new Date(scheduleTimes.get(position).getEndTime())));
					sopsList.add(sops);
				}
			}
			if(count == 0 || checkeds.size() == 0){
				ToastUtil.show(SetScheduleActivity.this,"您没有勾选任何排期");
				return;
			}
			postJson = gson.toJson(sopsList,
					new TypeToken<List<ScheduleOfPostService>>() {}.getType());

			hintDialog.show();
		}
	}
}
