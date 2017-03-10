package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.PatrolRecordData;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.AbsBaseAdapter;

import java.util.List;

public class PatrolRecordDetailsAdapter extends AbsBaseAdapter<PatrolRecordData.ImgsBean> {


    private Context context;

    public PatrolRecordDetailsAdapter(Context context, List<PatrolRecordData.ImgsBean> dataList) {
        super(dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder = null;
        if (v == null) {
            holder = new ViewHolder();
            v = View.inflate(context, R.layout.gridview_item_image, null);
            holder.imageView = (ImageView) v.findViewById(R.id.gv_item_img);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + getDataList().get(position).getSmall(), holder.imageView);
        return v;
    }

    class ViewHolder {
        private ImageView imageView;
    }


}
