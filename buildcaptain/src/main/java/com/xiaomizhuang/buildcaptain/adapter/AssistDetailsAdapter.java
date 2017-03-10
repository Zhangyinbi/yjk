package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.MaterialType;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.List;

/**
 * Created by user on 2015/11/19.
 */
public class AssistDetailsAdapter extends AbsBaseAdapter<MaterialType>{

    private Context context;

    public AssistDetailsAdapter(Context context,List<MaterialType> dataList) {
        super(dataList);
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder = null;
        if (v == null) {
            holder = new ViewHolder();
            v = View.inflate(context, R.layout.adapter_assist_details, null);
            holder.imageView = (ImageView) v.findViewById(R.id.adapter_assist_details_image);
            holder.textView = (TextView) v.findViewById(R.id.adapter_assist_details_title);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.textView.setText(getDataList().get(position).getName());
        String url = getDataList().get(position).getImg();
        MyApplication.imageLoader.displayImage(
                url.startsWith("http") ? getDataList().get(position).getImg()
                        : HttpConstant.IMAGEADDRESS+getDataList().get(position).getImg()
                , holder.imageView);
        return v;
    }

    class ViewHolder{
        public ImageView imageView;
        public TextView textView;
    }
}
