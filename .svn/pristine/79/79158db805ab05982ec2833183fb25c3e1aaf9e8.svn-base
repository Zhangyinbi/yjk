package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.MaterialType;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/11/25.
 */
public class AssistTypeOrderAdapter extends AbsBaseAdapter<MaterialType> {
    Context context;
    HashMap<Integer, Boolean> isSelect = new HashMap<>();
    OnTypeClicklistener onTypeClicklistener;

    //    TextView tv_item;
    public AssistTypeOrderAdapter(Context context, List<MaterialType> dataList) {
        super(dataList);
        this.context = context;
        isSelect.put(0, true);
        for (int i = 1; i < dataList.size(); i++) {
            isSelect.put(i, false);
        }
    }

    @Override
    public void notifyDataSetChanged(List<MaterialType> dataList) {
        isSelect.put(0, true);
        for (int i = 1; i < dataList.size(); i++) {
            isSelect.put(i, false);
        }
        super.notifyDataSetChanged(dataList);
    }

    public void setOnTypeClicklistener(OnTypeClicklistener onTypeClicklistener) {
        this.onTypeClicklistener = onTypeClicklistener;
    }

    public interface OnTypeClicklistener {
        void onClilck(MaterialType assistDetails);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.assist_type_item, parent, false);
        }
//        tv_item=((TextView)ViewHolders.get(convertView,R.id.tv_assist_type_item));
        final TextView tv_item = (TextView) convertView.findViewById(R.id.tv_assist_type_item);
        tv_item.setText(dataList.get(position).getName());
        if (isSelect.get(position)) {
            tv_item.setTextColor(context.getResources().getColor(R.color.white));
            tv_item.setBackgroundResource(R.drawable.shape_red_solid_stroke);
        } else {
            tv_item.setTextColor(context.getResources().getColor(R.color.gray_7d7d7d));
//            tv_item.setTextColor(context.getResources().getColor(R.drawable.selector_white_text_bg));
            tv_item.setBackgroundResource(R.drawable.selector_red_bg);
        }
        tv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < isSelect.size(); i++) {
                    isSelect.put(i, false);
                }
                isSelect.put(position, true);
                if (onTypeClicklistener != null) {
                    onTypeClicklistener.onClilck(dataList.get(position));
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }
}
