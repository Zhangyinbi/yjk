package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.ViewHolders;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.Quantities;

import java.util.List;

/**
 * 描述
 *
 * @FileName: com.xiaomizhuang.buildcaptain.adapter.QuantitiesAdapter.java
 * @author: Yangbang
 * @date: 2016-01-20 16:58
 */
public class QuantitiesAdapter extends AbsBaseAdapter<Quantities> {
    Context context;

    public QuantitiesAdapter(List<Quantities> dataList, Context context) {
        super(dataList);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_quantities, null);
        }
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_quantities_title)).setText(dataList.get(position).getZjx_name());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_quantities_brand)).setText(dataList.get(position).getBrand_name());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_quantities_type)).setText(dataList.get(position).getNorms());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_quantities_count)).setText("x" + dataList.get(position).getAct_count());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_quantities_remark)).setText(dataList.get(position).getBeizhu());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_quantities_remark)).setVisibility(TextUtils.isEmpty(dataList.get(position).getBeizhu()) ? View.GONE : View.VISIBLE);
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_quantities_money)).setText(dataList.get(position).getAct_total_price() + "元");
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_quantities_order_time)).setText("下单时间：" + dataList.get(position).getPosttime());
        return convertView;
    }
}
