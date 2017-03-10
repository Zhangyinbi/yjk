package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.ViewHolders;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.DeductRecordDetailActivity;
import com.xiaomizhuang.buildcaptain.entity.DeductRecord;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.List;

/**
 * 扣款记录adapter
 *
 * @FileName: com.xiaomizhuang.buildcaptain.adapter.DeductRecordAdapter.java
 * @author: Yangbang
 * @date: 2016-01-20 19:19
 */
public class DeductRecordAdapter extends AbsBaseAdapter<DeductRecord> {
	Context context;
	boolean isAllBuildDeduct = false;//false代表单个工地扣款，true代表所有工地扣款

	public DeductRecordAdapter(List<DeductRecord> dataList, Context context, boolean isAllBuildDeduct) {
		super(dataList);
		this.context = context;
		this.isAllBuildDeduct = isAllBuildDeduct;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.listitem_deduct_record, null);
		}
		if (isAllBuildDeduct) {
			((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_deduct_record_owner_name)).setVisibility(View.VISIBLE);
			((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_deduct_record_build_address)).setVisibility(View.VISIBLE);
			((TextView) ViewHolders.get(convertView, R.id.tv_listitem_deduct_record_owner_name)).setText(dataList.get(position).getUsername());
			((TextView) ViewHolders.get(convertView, R.id.tv_listitem_deduct_record_build_address)).setText(dataList.get(position).getAddress());
		}
		((TextView) ViewHolders.get(convertView, R.id.tv_listitem_deduct_record_data)).setText(dataList.get(position).getAddtime());
		((TextView) ViewHolders.get(convertView, R.id.tv_listitem_deduct_record_section)).setText(dataList.get(position).getProjectphase());
		((TextView) ViewHolders.get(convertView, R.id.tv_listitem_deduct_record_desc)).setText(dataList.get(position).getContent());
		((TextView) ViewHolders.get(convertView, R.id.tv_listitem_deduct_record_money)).setText(dataList.get(position).getAmerce_money() + "元");
		((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_deduct_record_imgs)).removeAllViews();
		for (int i = 0; i < dataList.get(position).getImgs().size(); i++) {
			if (i <= 2) {
				ImageView imageView =  (ImageView) LayoutInflater.from(context).inflate(R.layout.listitem_img_2, null);

				MyApplication.imageLoader.displayImage(HttpConstant.IMAGES_ROOT_URL + dataList.get(position).getImgs().get(i).getSmall(), imageView);
				((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_deduct_record_imgs)).addView(imageView);
			}
		}
		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, DeductRecordDetailActivity.class);
				intent.putExtra("id", dataList.get(position).getId());
				context.startActivity(intent);
			}
		});
		return convertView;
	}
}
