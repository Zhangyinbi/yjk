package com.xiaomizhuang.buildcaptain.activity;

import android.os.Message;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.view.MyGridView;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.ImgGridViewAdapter;
import com.xiaomizhuang.buildcaptain.entity.DeductRecordDetail;
import com.xiaomizhuang.buildcaptain.entity.Image;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 扣款记录详情Activity
 *
 * @FileName: com.xiaomizhuang.buildcaptain.activity.DeductRecordDetailActivity.java
 * @author: Yangbang
 * @date: 2016-01-21 10:37
 */
public class DeductRecordDetailActivity extends BaseActivity {
	private TextView tvDeductDetailOwnerName;
	private TextView tvDeductDetailBuildAddress;
	private TextView tvDeductDetailQuestionDesc;
	private TextView tvDeductDetailMaterialDesc;
	private TextView tvDeductDetailStage;
	private TextView tvDeductDetailReason;
	private TextView tvDeductDetailMoney;
	private MyGridView gvDeductDetailScenePhoto;
	private MyGridView gvDeductDetailOwnerPhoto;
	private String id = "";
	private DeductRecordDetail detail;
	private ImgGridViewAdapter imgsAdapter;//工地图片适配器
	private ImgGridViewAdapter userImgsAdapter;//业主沟通图片适配器
	private List<Image> images = new ArrayList<>();//工地图片集合
	private List<Image> userImages = new ArrayList<>();//业主沟通图片集合

	@Override
	protected void initWidget() {
		id = getIntent().getStringExtra("id");
		titleBar.setTitleText("扣款详情");
		titleBar.setLeftImgDefaultBack(this);
		assignViews();
		getDeductRecordDetail();
	}

	private void assignViews() {
		tvDeductDetailOwnerName = (TextView) findViewById(R.id.tv_deduct_detail_owner_name);
		tvDeductDetailBuildAddress = (TextView) findViewById(R.id.tv_deduct_detail_build_address);
		tvDeductDetailQuestionDesc = (TextView) findViewById(R.id.tv_deduct_detail_question_desc);
		tvDeductDetailMaterialDesc = (TextView) findViewById(R.id.tv_deduct_detail_material_desc);
		tvDeductDetailStage = (TextView) findViewById(R.id.tv_deduct_detail_stage);
		tvDeductDetailReason = (TextView) findViewById(R.id.tv_deduct_detail_reason);
		tvDeductDetailMoney = (TextView) findViewById(R.id.tv_deduct_detail_money);
		gvDeductDetailScenePhoto = (MyGridView) findViewById(R.id.gv_deduct_detail_scene_photo);
		gvDeductDetailOwnerPhoto = (MyGridView) findViewById(R.id.gv_deduct_detail_owner_photo);
		imgsAdapter = new ImgGridViewAdapter(images, this);
		userImgsAdapter = new ImgGridViewAdapter(userImages, this);
		gvDeductDetailScenePhoto.setAdapter(imgsAdapter);
		gvDeductDetailOwnerPhoto.setAdapter(userImgsAdapter);
	}

	private void getDeductRecordDetail() {
		params.clear();
		params.put("id", id);
		params.put("token", MyApplication.TOKEN);
		analyzeJson.requestData(HttpConstant.AmerceRecordDetail, params, REQUEST_SUCCESS);
	}

	private void setDeductRecordDetail() {
		if (detail != null) {
			tvDeductDetailOwnerName.setText(detail.getUsername());
			tvDeductDetailBuildAddress.setText(detail.getAddress());
			tvDeductDetailQuestionDesc.setText(detail.getContent());
			tvDeductDetailMaterialDesc.setText(detail.getMaterial_info());
			tvDeductDetailStage.setText(detail.getProjectphase());
			tvDeductDetailReason.setText(detail.getAmerceItem());
			tvDeductDetailMoney.setText(detail.getAmerce_money() + "元");
			String imgsString = gson.toJson(detail.getImgs());
			String userImgsString = gson.toJson(detail.getUserimgs());
			images = gson.fromJson(imgsString, new TypeToken<List<Image>>() {
			}.getType());
			userImages = gson.fromJson(userImgsString, new TypeToken<List<Image>>() {
			}.getType());
			imgsAdapter.notifyDataSetChanged(images);
			userImgsAdapter.notifyDataSetChanged(userImages);
		}
	}

	@Override
	protected void initData() {

	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_deduct_record_detail;
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				detail = gson.fromJson(data.data, DeductRecordDetail.class);
				setDeductRecordDetail();
				break;
		}
		return super.handleMessage(msg);
	}
}
