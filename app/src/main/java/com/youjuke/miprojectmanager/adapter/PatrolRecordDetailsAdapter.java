package com.youjuke.miprojectmanager.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hbw.library.AbsBaseAdapter;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.entity.PatrolRecordImages;
import com.youjuke.miprojectmanager.util.HttpConstant;
import com.youjuke.miprojectmanager.util.MyApplication;

public class PatrolRecordDetailsAdapter extends AbsBaseAdapter<PatrolRecordImages> {


    private Context context;

    public PatrolRecordDetailsAdapter(Context context, List<PatrolRecordImages> dataList) {
        super(dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder = null;
        if (v == null) {
            holder = new ViewHolder();
            v = View.inflate(context, R.layout.griditem_addpic, null);
            holder.imageView = (ImageView) v.findViewById(R.id.imageView_record);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (getDataList().get(position).getId().equals("0")) {
            holder.imageView.setImageBitmap(getDataList().get(position).getBitmap());
        } else {
            MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS + getDataList().get(position).getSmall(), holder.imageView);
        }
        return v;
    }

    class ViewHolder {
        private ImageView imageView;
    }


}
