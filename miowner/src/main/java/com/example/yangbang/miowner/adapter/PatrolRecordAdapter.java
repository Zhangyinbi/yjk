package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.PatrolRecord;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.MoneySimpleFormat;
import com.hbw.library.view.MyGridView;

import java.util.List;

/**
 * Created by mwy on 2016/6/24.
 */
public class PatrolRecordAdapter extends AbsBaseAdapter<PatrolRecord> {
	private Context context;

	public PatrolRecordAdapter(Context context, List<PatrolRecord> dataList) {
		super(dataList);
		this.context = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.listitem_patrol_record, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
			viewHolder.tv_switch = (TextView) convertView.findViewById(R.id.tv_switch);
			viewHolder.tv_amerce_money = (TextView) convertView.findViewById(R.id.tv_amerce_money);
			viewHolder.gv_imgs = (MyGridView) convertView.findViewById(R.id.gv_imgs);
			viewHolder.tv_add_time = (TextView) convertView.findViewById(R.id.tv_add_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

		String strAmerceMoney = dataList.get(position).getAmerce_money();
		if (strAmerceMoney==null||"".equals(strAmerceMoney.trim())){
			strAmerceMoney="0";
		}
		//格式化字符串
		String yuanAmerceMoney = MoneySimpleFormat.getMoneyType(strAmerceMoney);
		viewHolder.tv_amerce_money.setText("罚款金额 : " + yuanAmerceMoney+"元");
		SpannableStringBuilder builder = new SpannableStringBuilder(viewHolder.tv_amerce_money.getText().toString());
		ForegroundColorSpan yellowSpan = new ForegroundColorSpan(Color.parseColor("#f39700"));
		ForegroundColorSpan graySpan = new ForegroundColorSpan(Color.parseColor("#959595"));
		builder.setSpan(graySpan,0,7, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		builder.setSpan(yellowSpan,7,viewHolder.tv_amerce_money.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
		viewHolder.tv_amerce_money.setText(builder);
		//全文 & 收起
		viewHolder.tv_content.setText(dataList.get(position).getContent());
		//异步获取textview的行数
		viewHolder.tv_content.post(new Runnable() {
			@Override
			public void run() {
				//字符长度
				double textlengh = viewHolder.tv_content.getTextSize() * dataList.get(position).getContent().length();
				//字符间距
				double textSacleX = viewHolder.tv_content.getTextScaleX() * dataList.get(position).getContent().length();

				if (textlengh + textSacleX > viewHolder.tv_content.getWidth() * 2) {//超过2行显示
					viewHolder.tv_switch.setVisibility(View.VISIBLE);
					//默认不展开
					viewHolder.tv_switch.setTag(false);

					viewHolder.tv_switch.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							boolean flag = (boolean) v.getTag();
							if (!flag) {//为未展开状态 点击展开
								v.setTag(true);
								viewHolder.tv_content.setMaxLines(Integer.MAX_VALUE);
								viewHolder.tv_content.setEllipsize(null);
								viewHolder.tv_switch.setText("收起");
							} else {//展开状态 点击收起
								v.setTag(false);
								viewHolder.tv_content.setMaxLines(2);
								viewHolder.tv_content.setEllipsize(TextUtils.TruncateAt.END);
								viewHolder.tv_switch.setText("全文");
							}
						}
					});
				}
			}
		});

		viewHolder.gv_imgs.setAdapter(new PatrolRecordImageAdapter(context, dataList.get(position).getImgs()));
		viewHolder.tv_add_time.setText("上传时间 : "+dataList.get(position).getAddtime());
		return convertView;
	}

	private class ViewHolder {
		TextView tv_content;
		TextView tv_switch;
		TextView tv_amerce_money;
		TextView tv_add_time;
		MyGridView gv_imgs;
	}
}
