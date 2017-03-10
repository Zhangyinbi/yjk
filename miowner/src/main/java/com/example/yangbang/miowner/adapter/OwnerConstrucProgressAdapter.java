package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.OwnerConstrucProgress;
import com.example.yangbang.miowner.entity.OwnerConstrucProgressDetails;
import com.hbw.library.view.MeasureListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015/12/28.
 */
public class OwnerConstrucProgressAdapter extends BaseExpandableListAdapter {

	private Context context;
	private ArrayList<OwnerConstrucProgress> mOwnerConstrucProgress = new ArrayList<OwnerConstrucProgress>();

	public OwnerConstrucProgressAdapter(Context context, ArrayList<OwnerConstrucProgress> mOwnerConstrucProgress) {
		this.mOwnerConstrucProgress = mOwnerConstrucProgress;
		this.context = context;
	}

	@Override
	public int getGroupCount() {
		return mOwnerConstrucProgress.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return mOwnerConstrucProgress.get(groupPosition).getCreate_msg_time().size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return mOwnerConstrucProgress.get(groupPosition);
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return mOwnerConstrucProgress.get(groupPosition).getCreate_msg_time().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View v, ViewGroup parent) {
		ViewHolder holder = null;
		if (v == null) {
			holder = new ViewHolder();
			v = View.inflate(context, R.layout.listitem_owner_construc_progress_group, null);
			holder.ImageLeft = (ImageView) v.findViewById(R.id.listitem_owner_construc_progress_group_image_left);
			holder.ImageRight = (ImageView) v.findViewById(R.id.listitem_owner_construc_progress_group_image_right);
			holder.TextTop = (TextView) v.findViewById(R.id.listitem_owner_construc_progress_group_text_top);
			holder.TextBottom = (TextView) v.findViewById(R.id.listitem_owner_construc_progress_group_text_bottom);
			holder.TextType = (TextView) v.findViewById(R.id.listitem_owner_construc_progress_group_text_type);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		//判断isExpanded就可以控制是按下还是关闭，同时更换图片
		if (isExpanded) {
			holder.ImageRight.setBackgroundResource(R.mipmap.gouxuanspread);
		} else {
			holder.ImageRight.setBackgroundResource(R.mipmap.gouxuan);
		}
		holder.TextTop.setText(mOwnerConstrucProgress.get(groupPosition).getStep_name());
		holder.TextBottom.setText(mOwnerConstrucProgress.get(groupPosition).getStart_time() + "-" + mOwnerConstrucProgress.get(groupPosition).getEnd_time());
		//0,进行中，1.完成，2未开始
		switch (mOwnerConstrucProgress.get(groupPosition).getStatus()) {
			case "0":
				holder.TextType.setTextColor(context.getResources().getColor(R.color.Owner_yellow_f39700));
				holder.TextType.setText("正在进行");
				holder.ImageLeft.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.underway)).getBitmap());
				break;
			case "1":
				holder.TextType.setTextColor(context.getResources().getColor(R.color.Owner_blue_22ac38));
				holder.TextType.setText("已完成");
				holder.ImageLeft.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.start)).getBitmap());
				break;
			case "2":
				holder.TextType.setTextColor(context.getResources().getColor(R.color.Owner_white_ffffff));
				holder.TextType.setText("");
				holder.ImageLeft.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.nostart)).getBitmap());
				break;
		}
		return v;
	}

	class ViewHolder {
		public ImageView ImageLeft;
		public ImageView ImageRight;
		public TextView TextTop;
		public TextView TextBottom;
		public TextView TextType;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View v, ViewGroup parent) {

		List<OwnerConstrucProgressDetails> dataList = mOwnerConstrucProgress.get(groupPosition).getCreate_msg_time();

		ViewHolderChild holder = null;
		if (v == null) {
			holder = new ViewHolderChild();
			v = View.inflate(context, R.layout.listitem_owner_construc_progress_child_item, null);
			holder.date = (TextView) v.findViewById(R.id.child_date);
			holder.imageView = (ImageView) v.findViewById(R.id.child_image_left);
			holder.child_LinearLayout = (LinearLayout) v.findViewById(R.id.child_LinearLayout);
			holder.child_listview = (MeasureListView) v.findViewById(R.id.child_listview);
			v.setTag(holder);
		} else {
			holder = (ViewHolderChild) v.getTag();
		}
		holder.date.setText(dataList.get(childPosition).getCreate_time() + "  " + dataList.get(childPosition).getGet_day());
		//0,进行中，1.完成，2未开始
		switch (dataList.get(childPosition).getChecked()) {
			case "0":
				holder.imageView.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.maketimegrey)).getBitmap());
				break;
			case "1":
				holder.imageView.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.gouxuangrey)).getBitmap());
				break;
			case "2":
				holder.imageView.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.maketimeblue)).getBitmap());
				break;
		}
		switch (dataList.get(childPosition).getStatus_color()) {
			case "0":
				holder.child_LinearLayout.setBackgroundResource(R.drawable.projectschedulinglistnostart);
				break;
			case "1":
				holder.child_LinearLayout.setBackgroundResource(R.drawable.projectschedulinglistnostart);
				break;
			case "2":
				holder.child_LinearLayout.setBackgroundResource(R.drawable.projectschedulinglistcompleted);
				break;
		}

		OwnerConstrucProgressChildAdapter childAdapter = new OwnerConstrucProgressChildAdapter(context, dataList.get(childPosition).getMsg_list());

		holder.child_listview.setAdapter(childAdapter);

		return v;
	}

	private class ViewHolderChild {
		public TextView date;
		public ImageView imageView;
		public LinearLayout child_LinearLayout;
		public MeasureListView child_listview;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}

	public void setNotifyDataSetChangedList(ArrayList<OwnerConstrucProgress> mOwnerConstrucProgress) {
		this.mOwnerConstrucProgress = mOwnerConstrucProgress;
		this.notifyDataSetChanged();
	}

}
