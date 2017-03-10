package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.AssistMaterialType;
import com.xiaomizhuang.buildcaptain.entity.AssistType;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.List;

/**
 * Created by user on 2015/11/24.
 */
public class AssistMaterialTypeAdapter extends AbsBaseAdapter<AssistMaterialType>{
    private Context context = null;

    public AssistMaterialTypeAdapter(Context context,List<AssistMaterialType> dataList) {
        super(dataList);
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder = null;
        if (v == null) {
            holder = new ViewHolder();
            v = View.inflate(context, R.layout.adapter_assist_material_type, null);
            holder.textView = (TextView) v.findViewById(R.id.adapter_assist_material_type_text);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.textView.setText(getDataList().get(position).getName());
        return v;
    }

    class ViewHolder{
        public TextView textView;
    }
}
