package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.hbw.library.AbsBaseAdapter;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.PatrolRecordImages;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.List;

public class PatrolRecordUpdateAdapter extends AbsBaseAdapter<PatrolRecordImages> {

    private Context context;
    private int flag;

    public PatrolRecordUpdateAdapter(Context context, List<PatrolRecordImages> dataList, int flag) {
        super(dataList);
        this.context = context;
        this.dataList = dataList;
        this.flag = flag;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {

        ViewHolder holder = null;
        if (v == null) {
            holder = new ViewHolder();
            v = View.inflate(context, R.layout.griditem_updatepic, null);
            holder.imageView = (ImageView) v
                    .findViewById(R.id.imageView_record);
            holder.imageView_delete = (ImageView) v
                    .findViewById(R.id.imageView_delete);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        if (getDataList().get(position).getId().equals("0")) {
            holder.imageView.setImageBitmap(getDataList().get(position)
                    .getBitmap());
        } else {
            MyApplication.imageLoader.displayImage(HttpConstant.IMAGEADDRESS
                    + getDataList().get(position).getSmall(), holder.imageView);
        }
        if (position == getDataList().size() - 1) {
            holder.imageView_delete.setVisibility(ImageView.GONE);
        } else {
            holder.imageView_delete.setVisibility(ImageView.VISIBLE);
        }

        if (flag == 10 && position == 9) {
            v.setVisibility(View.GONE);
            return v;
        }
        return v;

    }

    class ViewHolder {
        private ImageView imageView;
        private ImageView imageView_delete;
    }

}
