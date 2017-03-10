package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.PatrolRecordAdapter;
import com.example.yangbang.miowner.entity.PatrolRecord;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.MyEmptyView;
import com.example.yangbang.miowner.view.PopSelectDate;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;

import java.util.ArrayList;
import java.util.List;

/**
 * 工地管理 施工队长巡查记录
 * @author mwy
 */
public class PatrolRecordActivity extends BaseActivity implements View.OnClickListener,PopSelectDate.OnAffirmDataListener, AdapterView.OnItemClickListener {
	private LinearLayout llPatrolRecordHeaderAssistTime;
	private TextView tvPatrolRecordHeaderAssistTime;
	private ImageView imgPatrolRecordHeaderAssistTime;
	private ListView lv_patrol_record;
	private PopSelectDate popSelectDate;
	private String filterDate = "";//筛选日期
	private PatrolRecordAdapter patrolRecordAdapter;
	private List<PatrolRecord> dataList;
	private MyEmptyView emptyView;

	private void assignViews() {
		llPatrolRecordHeaderAssistTime = (LinearLayout) findViewById(R.id.ll_patrol_record_header_assist_time);
		tvPatrolRecordHeaderAssistTime = (TextView) findViewById(R.id.tv_patrol_record_header_assist_time);
		imgPatrolRecordHeaderAssistTime = (ImageView) findViewById(R.id.img_patrol_record_header_assist_time);
		lv_patrol_record = (ListView) findViewById(R.id.lv_patrol_record);
	}


	@Override
	protected void initWidget() {
		titleBar.setTitleText("巡查记录");
		titleBar.setLeftImgDefaultBack(this);
		assignViews();
		llPatrolRecordHeaderAssistTime.setOnClickListener(this);

		dataList = new ArrayList<PatrolRecord>();
		patrolRecordAdapter = new PatrolRecordAdapter(PatrolRecordActivity.this,dataList);
		lv_patrol_record.setAdapter(patrolRecordAdapter);
		lv_patrol_record.setOnItemClickListener(this);

	}

	@Override
	protected void initData() {
		params.put("token",MyApplication.getApp().getOwnerUser().getToken());
		analyzeJson.requestData(HttpConstant.PatrolRecord,params,REQUEST_SUCCESS);
	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_patrol_record;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_patrol_record_header_assist_time: //时间帅选
				if (popSelectDate == null) {
					popSelectDate = new PopSelectDate(this, imgPatrolRecordHeaderAssistTime);
					popSelectDate.setOnAffirmDataListener(this);
				}
				popSelectDate.showAsDropDown(v);
				imgPatrolRecordHeaderAssistTime.setImageResource(R.mipmap.ic_build_greyarrowup);
				break;

			default:
				break;
		}
	}

	@Override
	public void onAffirmData(String data) {
		imgPatrolRecordHeaderAssistTime.setImageResource(R.mipmap.ic_build_greyarrowdown);
		if (TextUtils.isEmpty(data)) {
			tvPatrolRecordHeaderAssistTime.setText("全部时间");
		} else {
			tvPatrolRecordHeaderAssistTime.setText(data);
		}
		filterDate = data;

		params.put("posttime",filterDate);
		params.put("token",MyApplication.getApp().getOwnerUser().getToken());
		analyzeJson.requestData(HttpConstant.PatrolRecord,params,REQUEST_SUCCESS);
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				dataList = gson.fromJson(data.data, new TypeToken<List<PatrolRecord>>(){}.getType());
				patrolRecordAdapter.notifyDataSetChanged(dataList);

				break;

			default:
				break;
		}
		return super.handleMessage(msg);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = new Intent(PatrolRecordActivity.this, PatrolRecordDetailsActivity.class);
		intent.putExtra("id",dataList.get(position).getId());
		startActivity(intent);
	}

	@Override
	public void setEmptyView(Message msg) {
		emptyView = new MyEmptyView(this);
		emptyView.setEmptyViewImg(R.mipmap.ic_empty_orders);
		emptyView.setEmptyViewContent("暂无数据");
		((ViewGroup)lv_patrol_record.getParent()).addView(emptyView.getEmptyView());
		lv_patrol_record.setEmptyView(emptyView.getEmptyView());
	}
}
