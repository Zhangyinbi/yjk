package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AssistAsBuiltRecordsAdapter;
import com.xiaomizhuang.buildcaptain.entity.AssistAsBuiltRecords;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.MyEmptyView;
import com.xiaomizhuang.buildcaptain.view.PopSelectDate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2016/1/26.施工记录
 */
public class AssistAsBuiltRecordsActivity extends BaseActivity implements View.OnClickListener, PopSelectDate.OnAffirmDataListener,
		AssistAsBuiltRecordsAdapter.DeleteRecordListener {

	private ListView as_listview;
	private LinearLayout ll_fm_material_detail_header_assist;
	private AssistAsBuiltRecordsAdapter mAssistAsBuiltRecordsAdapter = null;
	private List<AssistAsBuiltRecords> mAssistAsBuiltRecordss = new ArrayList<AssistAsBuiltRecords>();
	private LinearLayout llFmMaterialDetailHeaderAssistTime;
	private ImageView imgvFmMaterialDetailHeaderAssistTime;
	private TextView tvFmMaterialDetailHeaderAssistTime;
	private PopSelectDate popSelectDate;
	private String filterDate = "";//筛选日期
	private MyEmptyView emptyView;

	private String deleteId = "";//被删除的id
	private int is_jgstatus; //是否竣工

	public static final int DELETE_RECORD_SUCCESS = 0x112;

	@Override
	protected void initWidget() {

		titleBar.setTitleText("施工记录");
		titleBar.setLeftImgDefaultBack(this);

		is_jgstatus = getIntent().getIntExtra("is_jgstatus", 1);
		if (is_jgstatus == 0) {
			titleBar.setRightText0("添加");
			titleBar.setRightText0Listener(this);
		}


		llFmMaterialDetailHeaderAssistTime = (LinearLayout) findViewById(R.id.ll_fm_material_detail_header_assist_time);
		imgvFmMaterialDetailHeaderAssistTime = (ImageView) findViewById(R.id.imgv_fm_material_detail_header_assist_time);
		tvFmMaterialDetailHeaderAssistTime = (TextView) findViewById(R.id.tv_fm_material_detail_header_assist_time);
		ll_fm_material_detail_header_assist = (LinearLayout) findViewById(R.id.ll_fm_material_detail_header_assist);
		as_listview = (ListView) findViewById(R.id.as_listview);


		mAssistAsBuiltRecordsAdapter = new AssistAsBuiltRecordsAdapter(this, mAssistAsBuiltRecordss, this,is_jgstatus);
		as_listview.setAdapter(mAssistAsBuiltRecordsAdapter);

		llFmMaterialDetailHeaderAssistTime.setOnClickListener(this);


		GetData();
	}

	private void GetData() {
		HashMap<String, String> mMap = new HashMap<String, String>();
		mMap.put("token", MyApplication.TOKEN);
		mMap.put("baoming_id", getIntent().getStringExtra("bm_id"));
		mMap.put("posttime", filterDate);
		analyzeJson.requestData(HttpConstant.BuildRecordListUrl, mMap, REQUEST_SUCCESS);
	}

	@Override
	protected void initData() {

	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_assist_as_built_records;
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				mAssistAsBuiltRecordss = gson.fromJson(data.data, new TypeToken<List<AssistAsBuiltRecords>>() {
				}.getType());
				if (mAssistAsBuiltRecordss != null && mAssistAsBuiltRecordss.size() > 0) {
					mAssistAsBuiltRecordsAdapter.notifyDataSetChanged(mAssistAsBuiltRecordss);
				} else {
					mAssistAsBuiltRecordsAdapter.notifyDataSetChanged(mAssistAsBuiltRecordss);
				}
				break;
			//删除施工记录
			case DELETE_RECORD_SUCCESS:
				//刷新数据
				GetData();
				ToastUtil.show(this, "删除成功");
		}
		return super.handleMessage(msg);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_fm_material_detail_header_assist_time://时间筛选
				if (popSelectDate == null) {
					popSelectDate = new PopSelectDate(this, imgvFmMaterialDetailHeaderAssistTime);
					popSelectDate.setOnAffirmDataListener(this);
				}
				popSelectDate.showAsDropDown(v);
				imgvFmMaterialDetailHeaderAssistTime.setImageResource(R.mipmap.ic_build_greyarrowup);
				break;
			case R.id.right_text0:
				Intent it = new Intent(this, AssistAsBuiltRecordsAddActivity.class);
				it.putExtra("baoming_id", getIntent().getStringExtra("bm_id"));
				startActivity(it);
				break;
		}
	}

	@Override
	public void onAffirmData(String data) {
		imgvFmMaterialDetailHeaderAssistTime.setImageResource(R.mipmap.ic_build_greyarrowdown);
		if (TextUtils.isEmpty(data)) {
			tvFmMaterialDetailHeaderAssistTime.setText("全部时间");
		} else {
			tvFmMaterialDetailHeaderAssistTime.setText(data);
		}
		filterDate = data;
		GetData();
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		initWidget();
	}

	/***
	 * 删除施工记录
	 *
	 * @param id
	 */
	@Override
	public void deleteRecod(String id) {
		deleteId = id;
		params.put("token", MyApplication.TOKEN);
		params.put("record_id", id);
		analyzeJson.requestData(HttpConstant.DelBuildRecord, params, DELETE_RECORD_SUCCESS);
	}

	@Override
	public void setEmptyView(Message msg) {
		emptyView = new MyEmptyView(this);
		emptyView.setEmptyViewImg(R.mipmap.ic_empty_orders);
		emptyView.setEmptyViewContent("暂无记录");
		((ViewGroup) ll_fm_material_detail_header_assist.getParent()).addView(emptyView.getEmptyView());
		as_listview.setEmptyView(emptyView.getEmptyView());
	}
}

