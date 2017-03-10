package com.youjuke.miprojectmanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.entity.PatrolRecordCause;
import com.youjuke.miprojectmanager.entity.SpinnerProgjectCost;

import java.util.List;

/**
 * Created by user on 2015/12/10.
 */
public class SpinnerProgjectFineCostAdapter extends AbsBaseAdapter<PatrolRecordCause> {

    private Context context;

    public SpinnerProgjectFineCostAdapter(Context context, List<PatrolRecordCause> dataList) {
        super(dataList);
        this.context = context;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        ViewHolder holder = null;
        if (v == null) {
            holder = new ViewHolder();
            v = View.inflate(context, R.layout.item_spinner_progject_cost, null);
            holder.textView = (TextView) v.findViewById(R.id.item_spinner_progject_cost);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }
        holder.textView.setText(getDataList().get(position).getTitle());
        return v;
    }

    class ViewHolder {
        private TextView textView;
    }
}
