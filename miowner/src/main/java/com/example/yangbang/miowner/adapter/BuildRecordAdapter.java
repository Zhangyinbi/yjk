package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.BuildRecord;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.view.MyGridView;

import java.util.List;

/**
 * Created by mwy on 2016/6/28.
 */
public class BuildRecordAdapter extends AbsBaseAdapter<BuildRecord> {
	private Context context;

	public BuildRecordAdapter(List<BuildRecord> dataList, Context context) {
		super(dataList);
		this.context = context;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		if (convertView == null) {
			convertView = View.inflate(context, R.layout.listitem_build_record, null);
			viewHolder = new ViewHolder();
			viewHolder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
			viewHolder.tv_switch = (TextView) convertView.findViewById(R.id.tv_switch);
			viewHolder.gv_imgs = (MyGridView) convertView.findViewById(R.id.gv_imgs);
			viewHolder.tv_add_time = (TextView) convertView.findViewById(R.id.tv_add_time);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}

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

		viewHolder.gv_imgs.setAdapter(new BuildRecordImageAdapter(dataList.get(position).getImgs(),context));
		viewHolder.tv_add_time.setText("上传时间 : "+dataList.get(position).getAddtime());
		return convertView;
	}

	private class ViewHolder {
		TextView tv_content;
		TextView tv_switch;
		TextView tv_add_time;
		MyGridView gv_imgs;
	}
}
