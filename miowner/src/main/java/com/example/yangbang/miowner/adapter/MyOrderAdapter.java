package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.activity.MyOrderActivity;
import com.example.yangbang.miowner.activity.OrderDetailActivity;
import com.example.yangbang.miowner.entity.Order;
import com.example.yangbang.miowner.view.MaterialViewItem;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.ViewHolders;

import java.util.List;

/**
 * 我的订单适配器
 *
 * @FileName: com.example.yangbang.miowner.adapter.MyOrderAdapter.java
 * @author: Yangbang
 * @date: 2016-06-27 18:35
 *
 * 工程:
 *  mwy 暂时隐藏评价功能 #0001
 */
public class MyOrderAdapter extends AbsBaseAdapter<Order> {
    Context context;

    public MyOrderAdapter(List<Order> dataList, Context context) {
        super(dataList);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_my_order, null);
        }
        if (MyOrderActivity.currentTabIndex == 0) {
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_num)).setVisibility(View.GONE);
            (ViewHolders.get(convertView, R.id.view_listitem_order_divide)).setVisibility(View.GONE);
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_status)).setVisibility(View.VISIBLE);
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_status)).setText("待付款");
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_status)).setTextColor(context.getResources().getColor(R.color.text_f39700));
            ((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_my_order_wait_status)).setVisibility(View.VISIBLE);
            ((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_my_order_add_material)).setVisibility(View.GONE);
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_name)).setText(dataList.get(position).getMaterial_name());
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_brand)).setText(dataList.get(position).getBrand());
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_type)).setText(dataList.get(position).getType());
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_price)).setText(dataList.get(position).getAct_price() + "元");
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_unit)).setText(dataList.get( position).getUnit());
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_count)).setText("x" + dataList.get(position).getAct_count());
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_total)).setText("合计：" + dataList.get(position).getAct_total_price() + "元(含运费0元)");
        } else {
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_num)).setVisibility(View.VISIBLE);
            (ViewHolders.get(convertView, R.id.view_listitem_order_divide)).setVisibility(View.VISIBLE);
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_status)).setVisibility(View.INVISIBLE);
            ((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_my_order_wait_status)).setVisibility(View.GONE);
            ((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_my_order_add_material)).setVisibility(View.VISIBLE);
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_num)).setText("流水号：" + dataList.get(position).getOrderID());
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_total)).setText("共" + dataList.get(position).getProduct().size() + "种建材 合计：" + dataList.get(position).getTotalprice() + "元(含运费0元)");
            ((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_my_order_add_material)).removeAllViews();
            for (int i = 0; i < dataList.get(position).getProduct().size(); i++) {
                MaterialViewItem viewItem = new MaterialViewItem(context);
                viewItem.setMaterialName(dataList.get(position).getProduct().get(i).getMaterial_name());
                viewItem.setMaterialBrand(dataList.get(position).getProduct().get(i).getBrand());
                viewItem.setMaterialPrice(dataList.get(position).getProduct().get(i).getAct_price() + "元");
                ((LinearLayout) ViewHolders.get(convertView, R.id.ll_listitem_my_order_add_material)).addView(viewItem.getView());
            }
        }
        //#0001
        /*if (MyOrderActivity.currentTabIndex == 3) {
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_status)).setVisibility(View.VISIBLE);
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_status)).setText("待评价");
            ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_my_order_status)).setTextColor(context.getResources().getColor(R.color.red_e76170));
        }*/
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MyOrderActivity.currentTabIndex != 0) {
                    Intent intent = new Intent(context, OrderDetailActivity.class);
                    intent.putExtra("status", MyOrderActivity.status);
                    intent.putExtra("OrderID", dataList.get(position).getOrderID());
                    context.startActivity(intent);
                }
            }
        });
        return convertView;
    }
}
