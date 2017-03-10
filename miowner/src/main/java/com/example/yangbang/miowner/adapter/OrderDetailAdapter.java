package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.activity.OrderCommentActivity;
import com.example.yangbang.miowner.entity.OrderDetail;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.ViewHolders;

import java.util.List;

/**
 * 订单详情适配器
 *
 * @FileName: com.xiaomizhuang.mimaterialsbusiness.adapter.OrderDetailAdapter.java
 * @author: Yangbang
 * @date: 2016-05-12 09:52
 * 工程:
 *  mwy 暂时隐藏评价功能 #0001  隐藏相应布局 ll_order_detail_item_comment
 */
public class OrderDetailAdapter extends AbsBaseAdapter<OrderDetail.ProductBean> {
    Context context;

    public OrderDetailAdapter(List<OrderDetail.ProductBean> dataList, Context context) {
        super(dataList);
        this.context = context;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_order_detail, null);
        }
        //#0001
        /*if (MyOrderActivity.currentTabIndex == 3) {
            ((LinearLayout) ViewHolders.get(convertView, R.id.ll_order_detail_item_comment)).setVisibility(View.VISIBLE);
            ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_comment)).setText(dataList.get(position).getButton_name());
        } else {
            ((LinearLayout) ViewHolders.get(convertView, R.id.ll_order_detail_item_comment)).setVisibility(View.GONE);
        }*/
        ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_remark)).setVisibility(TextUtils.isEmpty(dataList.get(position).getBeizhu()) ? View.GONE : View.VISIBLE);
        ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_remark)).setText("备注：" + dataList.get(position).getBeizhu());
        ((TextView) ViewHolders.get(convertView, R.id.tv_material_name)).setText(dataList.get(position).getMaterial_name());
        ((TextView) ViewHolders.get(convertView, R.id.tv_material_order_num)).setText("订单编号：" + dataList.get(position).getOrder_id());
        ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_brand)).setText(dataList.get(position).getBrand());
        ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_norms)).setText(dataList.get(position).getNorms());
        ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_unit_price)).setText("￥" + dataList.get(position).getAct_price());
        ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_unit)).setText(dataList.get(position).getUnit());
        ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_count)).setText("x" + dataList.get(position).getAct_count());

        ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_comment)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, OrderCommentActivity.class);
                intent.putExtra("material_id", dataList.get(position).getMaterial_id());
                intent.putExtra("material_name", dataList.get(position).getMaterial_name());
                context.startActivity(intent);
            }
        });

//        ((TextView) ViewHolders.get(convertView, R.id.tv_material_subtotal)).setText("￥" + dataList.get(position).getAct_total_price());

        if (TextUtils.isEmpty(dataList.get(position).getShl())) {
            ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_loss_percentage)).setVisibility(View.GONE);
        } else {
            ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_loss_percentage)).setVisibility(View.VISIBLE);
            ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_loss_percentage)).setText(dataList.get(position).getShl());
        }
        if (TextUtils.isEmpty(dataList.get(position).getShl_count())) {
            ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_loss_count)).setVisibility(View.GONE);
        } else {
            ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_loss_count)).setVisibility(View.VISIBLE);
            ((TextView) ViewHolders.get(convertView, R.id.tv_order_detail_item_loss_count)).setText(dataList.get(position).getShl_count());
        }
        return convertView;
    }

}
