package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.AssistBrandManufacturers;
import com.xiaomizhuang.buildcaptain.entity.AssistConstructionStage;

import java.util.List;

/**
 * Created by user on 2015/12/14.
 */
public class AssistBrandManufacturersAdapter extends AbsBaseAdapter<AssistBrandManufacturers> {

    private Context context;

    public AssistBrandManufacturersAdapter(Context context,List<AssistBrandManufacturers> dataList) {
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
        holder.textView.setText(getDataList().get(position).getBrand_name());
        return v;
    }

    class ViewHolder{
        public TextView textView;
    }
}
