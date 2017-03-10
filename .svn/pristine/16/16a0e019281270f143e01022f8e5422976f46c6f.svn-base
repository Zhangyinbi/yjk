package com.xiaomizhuang.buildcaptain.adapter;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.ViewHolders;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.AssistAsBuiltRecordsActivity;
import com.xiaomizhuang.buildcaptain.activity.BuildDetailActivity;
import com.xiaomizhuang.buildcaptain.activity.BuildSiteActivity;
import com.xiaomizhuang.buildcaptain.activity.MessageActivity;
import com.xiaomizhuang.buildcaptain.entity.BuildSite;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.HintDialog;
import com.xiaomizhuang.buildcaptain.view.PopTelphone;

import java.util.List;

/**
 * Created by user on 2015/11/20.
 */
public class BuildSiteAdapter extends AbsBaseAdapter<BuildSite> {
	Activity activity;
	TextView totalRemidNumTv;

	public BuildSiteAdapter(Activity activity, List dataList, TextView totalRemidNumTv) {
		super(dataList);
		this.activity = activity;
		this.totalRemidNumTv = totalRemidNumTv;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(activity).inflate(R.layout.build_site_item, parent, false);
		}
		((TextView) ViewHolders.get(convertView, R.id.tv_owner_name)).setText(dataList.get(position).getName());
		((TextView) ViewHolders.get(convertView, R.id.tv_project_manager)).setText(dataList.get(position).getSuperior());
		((TextView) ViewHolders.get(convertView, R.id.tv_designer)).setText(dataList.get(position).getDesigner());
		((TextView) ViewHolders.get(convertView, R.id.tv_muser)).setText(dataList.get(position).getMuser());
		((TextView) ViewHolders.get(convertView, R.id.tv_start_time)).setText(dataList.get(position).getActual_start_time());
		((TextView) ViewHolders.get(convertView, R.id.tv_end_time)).setText(dataList.get(position).getHetong_complete_time());
		((TextView) ViewHolders.get(convertView, R.id.tv_build_address)).setText(dataList.get(position).getZx_address());
		((TextView) ViewHolders.get(convertView, R.id.tv_msg_remind_count)).setVisibility(dataList.get(position).getMsgcount() > 0 ? View.VISIBLE : View.INVISIBLE);
		((TextView) ViewHolders.get(convertView, R.id.tv_msg_remind_count)).setText(dataList.get(position).getMsgcount() + "");
		((ImageView) ViewHolders.get(convertView, R.id.img_builde_site_item_iscomplete)).setVisibility(dataList.get(position).getIs_jgstatus() == 1 ? View.VISIBLE : View.GONE);
	 /*   ((TextView) ViewHolders.get(convertView, R.id.tv_build_record)).setEnabled(dataList.get(position).getIs_jgstatus() == 1 ? false : true);*/
//        if (position == 0) {
//            totalRemidNumTv.setVisibility(dataList.get(position).getTolotemsgcount() > 0 ? View.VISIBLE : View.INVISIBLE);
//            totalRemidNumTv.setText(dataList.get(position).getTolotemsgcount() + "");
//        }
		//套餐类型
		if (dataList.get(position).getTaocan_type() == null || "".equals(dataList.get(position).getTaocan_type().trim())) {
			((LinearLayout) ViewHolders.get(convertView, R.id.ll_taocan_type)).setVisibility(View.GONE);
		} else {
			((LinearLayout) ViewHolders.get(convertView, R.id.ll_taocan_type)).setVisibility(View.VISIBLE);
			((TextView) ViewHolders.get(convertView, R.id.tv_taocan_type)).setText(dataList.get(position).getTaocan_type());
		}
		//是否显示拒单按钮  0 不显示 1 显示
		((TextView) ViewHolders.get(convertView, R.id.tv_refuse)).setVisibility(
				TextUtils.isEmpty(dataList.get(position).getRefuse_msg()) ? View.GONE : View.VISIBLE);
		//是否显示排期按钮 0 不显示 1 显示  当排期按钮显示的时候 施工记录 和消息提醒隐藏 反之则反
		if (dataList.get(position).getSet_project() == 0){
			((TextView) ViewHolders.get(convertView, R.id.tv_set_project)).setVisibility(View.GONE);
			((TextView) ViewHolders.get(convertView, R.id.tv_build_record)).setVisibility(View.VISIBLE);
			((FrameLayout) ViewHolders.get(convertView, R.id.fl_msg)).setVisibility(View.VISIBLE);
		}else{
			((TextView) ViewHolders.get(convertView, R.id.tv_set_project)).setVisibility(View.VISIBLE);
			((TextView) ViewHolders.get(convertView, R.id.tv_build_record)).setVisibility(View.GONE);
			((FrameLayout) ViewHolders.get(convertView, R.id.fl_msg)).setVisibility(View.GONE);
		}
		//TODO 设置排期 设置排期暂时不上线
		/*((TextView) ViewHolders.get(convertView, R.id.tv_set_project)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//只有上传完交底单才能 设置排期
				if (TextUtils.isEmpty(dataList.get(position).getJdimg_msg())){
					Intent intent = new Intent(activity, SetScheduleActivity.class);
					intent.putExtra("baoming_id",dataList.get(position).getUid());
					activity.startActivity(intent);
				}else{
					new HintDialog(activity).setCancelHint().setDefultConfirmListener()
							.setTitleText(dataList.get(position).getJdimg_msg()+"").show();
				}

			}
		});*/

		//是否是优居客监理
		((TextView) ViewHolders.get(convertView, R.id.tv_project_manager_invalid))
				.setText(dataList.get(position).getYjk_jianli() == 1 ?
						activity.getString(R.string.yjk_jianli) : activity.getString(R.string.str_project_manager));


		convertView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isStart(position)) {
					Intent intent = new Intent(activity, BuildDetailActivity.class);
					intent.putExtra("bm_id", dataList.get(position).getUid());
					intent.putExtra("title", dataList.get(position).getZx_address());
					intent.putExtra("is_jgstatus", dataList.get(position).getIs_jgstatus());
					intent.putExtra("confirm_num", dataList.get(position).getConfirm_num());
					intent.putExtra("ownerPhone",dataList.get(position).getYz_mobile());
					intent.putExtra("ownerName",dataList.get(position).getName());
					//把工地信息保存起来 减少传参
					MyApplication.getApp().setBuildSite(dataList.get(position));
					activity.startActivity(intent);
				}
			}
		});
		((TextView) ViewHolders.get(convertView, R.id.tv_build_record)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				if (isStart(position)) {
					Intent intent3 = new Intent(activity, AssistAsBuiltRecordsActivity.class);
					intent3.putExtra("bm_id", dataList.get(position).getUid());
					intent3.putExtra("is_jgstatus", dataList.get(position).getIs_jgstatus());
					activity.startActivity(intent3);
				}
			}
		});
		((TextView) ViewHolders.get(convertView, R.id.tv_msg_remind)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isStart(position)) {
					Intent intent2 = new Intent(activity, MessageActivity.class);
					intent2.putExtra("titleType", "消息提醒");
					intent2.putExtra("bm_id", dataList.get(position).getUid());
					//把工地信息保存起来 减少传参
					MyApplication.getApp().setBuildSite(dataList.get(position));
					activity.startActivity(intent2);
				}
			}
		});
		((ImageView) ViewHolders.get(convertView, R.id.img_telphone)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				PopTelphone popTelphone = new PopTelphone(activity, dataList.get(position));
				popTelphone.showAtLocation(v, Gravity.BOTTOM, 0, 0);
			}
		});

		((TextView) ViewHolders.get(convertView, R.id.tv_refuse)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((BuildSiteActivity)activity).refuse(dataList.get(position).getUid(),position);
			}
		});

		return convertView;
	}

	//工地是否开工
	private boolean isStart(int position) {
		if (!TextUtils.isEmpty(dataList.get(position).getStart_msg())) {
			HintDialog hintDialog = new HintDialog(activity);
			hintDialog.setTitleText(dataList.get(position).getStart_msg());
			hintDialog.setCancelHint().setDefultConfirmListener().show();
			return false;
		} else {
			return true;
		}
	}
}
