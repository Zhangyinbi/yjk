package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.BuildRecord;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.pictureview.ImageGalleryActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwy on 2016/6/28.
 */
public class BuildRecordImageAdapter extends AbsBaseAdapter<BuildRecord.ImgsBean> {
	private Context context;
	private ArrayList<String> imageUrls = new ArrayList<String>();

	public BuildRecordImageAdapter(List<BuildRecord.ImgsBean> dataList,Context context) {
		super(dataList);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView==null){
			convertView = View.inflate(context, R.layout.gridview_item_image,null);
			viewHolder = new ViewHolder();
			viewHolder.gv_item_img = (ImageView) convertView.findViewById(R.id.gv_item_img);
			convertView.setTag(viewHolder);
		}else{
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.gv_item_img.setOnClickListener(new MyonClickListener(position));
		MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS+dataList.get(position).getSmall(),viewHolder.gv_item_img);


		return convertView;
	}


	private class ViewHolder{
		ImageView gv_item_img;
	}

	private class MyonClickListener implements View.OnClickListener{
		private int position;
		public MyonClickListener(int position){
			this.position = position;
		}

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
				case R.id.gv_item_img:
					Intent intent = new Intent(context, ImageGalleryActivity.class);
					imageUrls.clear();
					for (int i = 0; i < dataList.size(); i++) {
						imageUrls.add(HttpConstant.IMAGEADDRESS+dataList.get(i).getNormal());
					}
					intent.putStringArrayListExtra(ImageGalleryActivity.EXTRA_URLS, imageUrls);
					intent.putStringArrayListExtra(ImageGalleryActivity.EXTRA_FILE_PATHS, imageUrls);
					intent.putExtra(ImageGalleryActivity.EXTRA_INDEX, position);
					context.startActivity(intent);
					break;

				default:
					break;
			}
		}
	}
}
