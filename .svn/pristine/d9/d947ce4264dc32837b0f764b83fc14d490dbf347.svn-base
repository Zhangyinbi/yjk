package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.OwnerConstrucProgressDetailsChild;
import com.hbw.library.AbsBaseAdapter;

import java.util.List;

/**
 * Created by user on 2015/12/29.
 */
public class OwnerConstrucProgressChildAdapter extends AbsBaseAdapter<OwnerConstrucProgressDetailsChild> {

	private Context context;

	public OwnerConstrucProgressChildAdapter(Context context, List<OwnerConstrucProgressDetailsChild> dataList) {
		super(dataList);
		this.context = context;
	}

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		ViewHolder holder;
		if (v == null) {
		    v = View.inflate(context, R.layout.listitem_owner_construc_progress_child_item_addview, null);
			holder = new ViewHolder();
			holder.child_text = (TextView) v.findViewById(R.id.child_text);
			holder.child_image_right = (ImageView) v.findViewById(R.id.child_image_right);
			holder.listview_device = v.findViewById(R.id.listview_device);
			if (position==dataList.size()-1){
				holder.listview_device.setVisibility(View.GONE);
			}else {
				holder.listview_device.setVisibility(View.VISIBLE);
			}
			v.setTag(holder);
		}else{
			holder = (ViewHolder) v.getTag();
		}
		holder.child_text.setText(dataList.get(position).getTitle());
		switch (dataList.get(position).getStatus()) {
			case "0":
				holder.child_image_right.setImageBitmap(null);
				break;
			case "1":
				holder.child_image_right.setImageBitmap(
						((BitmapDrawable) context.getResources().getDrawable(R.mipmap.gouxuanblue)).getBitmap());
				break;
			case "2":
				holder.child_image_right.setImageBitmap(null);
				break;
		}
		return v;
	}

	class ViewHolder {
		public TextView child_text;
		public ImageView child_image_right;
		public View listview_device;
	}
}
