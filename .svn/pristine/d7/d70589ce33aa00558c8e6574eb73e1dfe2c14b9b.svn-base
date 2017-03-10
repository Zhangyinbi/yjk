package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.Report;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.pictureview.ImageGalleryActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 监理报告Adapter 节点验收
 *
 * @FileName: com.example.yangbang.miowner.adapter.SupervisionReportAdapter.java
 * @author: Yangbang
 * @date: 2015-12-21 11:04
 */
public class SupervisionReportAdapter extends AbsBaseAdapter<Report> {
    Context context;
	ArrayList<String> imageUrls = new ArrayList<String>();
    public SupervisionReportAdapter(List<Report> dataList, Context context) {
        super(dataList);
        this.context = context;
    }

    @Override
    public void notifyDataSetChanged(List<Report> dataList) {
        super.notifyDataSetChanged(dataList);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
	    ViewHodler viewHodler;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_supervision_report, null);
	        viewHodler = new ViewHodler();
	        viewHodler.tv_title = (TextView) convertView.findViewById(R.id.tv_title);
	        viewHodler.tv_super_upload_time = (TextView) convertView.findViewById(R.id.tv_super_upload_time);
	        viewHodler.imgv_super_item = (ImageView) convertView.findViewById(R.id.imgv_super_item);
	        viewHodler. ll_report_item = (LinearLayout) convertView.findViewById(R.id.ll_report_item);
	        viewHodler.tv_no_picture = (TextView) convertView.findViewById(R.id.tv_no_picture);
	        convertView.setTag(viewHodler);
        }else{
	        viewHodler = (ViewHodler) convertView.getTag();
        }
	    viewHodler.tv_title.setText(dataList.get(position).getTitle());
	    if (dataList.get(position).getAddtime()==null||"null".equals(dataList.get(position).getAddtime())){
		    viewHodler.tv_super_upload_time.setText("最新上传时间 : 0000-00-00 00:00:00");
	    }else{
		    viewHodler.tv_super_upload_time.setText("最新上传时间 : " + dataList.get(position).getAddtime());
	    }
	    //有图片时显示图片
	    if (dataList.get(position).getImgs().size()!=0
			    && dataList.get(position).getImgs().get(0).getSmall()!=null
			    && ! "".equals(dataList.get(position).getImgs().get(0).getSmall())) {

		    MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS+dataList.get(position).getImgs().get(0).getSmall(),
				    viewHodler.imgv_super_item);
	    }else{
		    viewHodler.imgv_super_item.setVisibility(View.GONE);
		    viewHodler.tv_no_picture.setVisibility(View.VISIBLE);
	    }

	    viewHodler.imgv_super_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ImageGalleryActivity.class);
	            imageUrls.clear();
	            imageUrls.add(HttpConstant.IMAGEADDRESS+dataList.get(position).getImgs().get(0).getNormal());
                intent.putStringArrayListExtra(ImageGalleryActivity.EXTRA_URLS, imageUrls);
                intent.putStringArrayListExtra(ImageGalleryActivity.EXTRA_FILE_PATHS, imageUrls);
                intent.putExtra(ImageGalleryActivity.EXTRA_INDEX, position);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

	private class ViewHodler{
		TextView tv_title;
		TextView tv_super_upload_time;
		ImageView imgv_super_item;
		LinearLayout ll_report_item;
		TextView tv_no_picture;
	}
}
