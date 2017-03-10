package com.example.yangbang.miowner.activity;

import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.BuildRecordAdapter;
import com.example.yangbang.miowner.entity.BuildRecord;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.MyEmptyView;
import com.example.yangbang.miowner.view.PopSelectDate;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;

import java.util.List;

/**
 * 施工队长日志
 * @author mwy
 * @date 2016-6-28 11:49
 */
public class BuildRecordActivity extends BaseActivity implements View.OnClickListener, PopSelectDate.OnAffirmDataListener {
	private LinearLayout llBuildRecordHeaderAssistTime;
	private TextView tvBuildRecordHeaderAssistTime;
	private ImageView imgBuildRecordHeaderAssistTime;
	private ListView lvBuildRecord;

	private PopSelectDate popSelectDate;
	private String filterDate = "";//筛选日期
	private List<BuildRecord> dataList;
	private BuildRecordAdapter buildRecordAdapter;
	private MyEmptyView emptyView;

	@Override
	protected void initWidget() {
		titleBar.setTitleText("施工队长日志");
		titleBar.setLeftImgDefaultBack(this);
		assignViews();
		llBuildRecordHeaderAssistTime.setOnClickListener(this);
		buildRecordAdapter = new BuildRecordAdapter(dataList,BuildRecordActivity.this);
		lvBuildRecord.setAdapter(buildRecordAdapter);
	}

	@Override
	protected void initData() {
		params.put("token",MyApplication.getApp().getOwnerUser().getToken());
		analyzeJson.requestData(HttpConstant.RecordLog,params,REQUEST_SUCCESS);
	}

	private void assignViews() {
		llBuildRecordHeaderAssistTime = (LinearLayout) findViewById(R.id.ll_build_record_header_assist_time);
		tvBuildRecordHeaderAssistTime = (TextView) findViewById(R.id.tv_build_record_header_assist_time);
		imgBuildRecordHeaderAssistTime = (ImageView) findViewById(R.id.img_build_record_header_assist_time);
		lvBuildRecord = (ListView) findViewById(R.id.lv_build_record);
	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_build_record;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_build_record_header_assist_time: //时间帅选
				if (popSelectDate == null) {
					popSelectDate = new PopSelectDate(this, imgBuildRecordHeaderAssistTime);
					popSelectDate.setOnAffirmDataListener(this);
				}
				popSelectDate.showAsDropDown(v);
				imgBuildRecordHeaderAssistTime.setImageResource(R.mipmap.ic_build_greyarrowup);
				break;

			default:
				break;
		}
	}

	@Override
	public void onAffirmData(String data) {
		imgBuildRecordHeaderAssistTime.setImageResource(R.mipmap.ic_build_greyarrowdown);
		if (TextUtils.isEmpty(data)) {
			tvBuildRecordHeaderAssistTime.setText("全部时间");
		} else {
			tvBuildRecordHeaderAssistTime.setText(data);
		}
		filterDate = data;

		params.put("posttime",filterDate);
		params.put("token", MyApplication.getApp().getOwnerUser().getToken());
		analyzeJson.requestData(HttpConstant.RecordLog,params,REQUEST_SUCCESS);
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				dataList = gson.fromJson(data.data, new TypeToken<List<BuildRecord>>(){}.getType());
				buildRecordAdapter.notifyDataSetChanged(dataList);

				break;

			default:
				break;
		}
		return super.handleMessage(msg);
	}

	@Override
	public void setEmptyView(Message msg) {
		emptyView = new MyEmptyView(this);
		emptyView.setEmptyViewImg(R.mipmap.ic_empty_orders);
		emptyView.setEmptyViewContent("暂无数据");
		((ViewGroup)lvBuildRecord.getParent()).addView(emptyView.getEmptyView());
		lvBuildRecord.setEmptyView(emptyView.getEmptyView());
	}
}
