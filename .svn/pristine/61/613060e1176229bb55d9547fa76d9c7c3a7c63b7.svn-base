package com.xiaomizhuang.buildcaptain.view;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.DateFilter;

/**
 * Created by user on 2015/11/23.
 */
public class PopOrdersFilter extends PopupWindow implements View.OnClickListener {
    Activity activity;
    private LinearLayout linearAllOrders;
    private TextView tvAllOrdersCount;//全部订单数量
    private LinearLayout linearOneMonthOrders;
    private TextView tvAllOneMonthCount;//一个月内订单数量
    private LinearLayout linearThreeOrders;
    private TextView tvThreeOrdersCount;//三个月内订单数量
    OnDateFilter onDateFilter;
    DateFilter dateFilter;
    TextView titleView;

    public PopOrdersFilter(Activity activity, DateFilter dateFilter, TextView titleView) {
        this.activity = activity;
        this.dateFilter = dateFilter;
        this.titleView = titleView;
        initPop();
    }

    private void initPop() {
        initView();
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(android.R.color.transparent)));
        setWidth((int) activity.getResources().getDimension(R.dimen.x600));
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
    }

    private void initView() {
        View view = LayoutInflater.from(activity).inflate(R.layout.pop_orders_filter_view, null);
        linearAllOrders = (LinearLayout) view.findViewById(R.id.linear_all_orders);
        tvAllOrdersCount = (TextView) view.findViewById(R.id.tv_all_orders_count);
        linearOneMonthOrders = (LinearLayout) view.findViewById(R.id.linear_one_month_orders);
        tvAllOneMonthCount = (TextView) view.findViewById(R.id.tv_all_one_month_count);
        linearThreeOrders = (LinearLayout) view.findViewById(R.id.linear_three_orders);
        tvThreeOrdersCount = (TextView) view.findViewById(R.id.tv_three_orders_count);
        tvAllOrdersCount.setText("（" + dateFilter.getGetallcount() + "）");
        tvAllOneMonthCount.setText("（" + dateFilter.getGetonecount() + "）");
        tvThreeOrdersCount.setText("（" + dateFilter.getGetthreecount() + "）");
        linearAllOrders.setOnClickListener(this);
        linearOneMonthOrders.setOnClickListener(this);
        linearThreeOrders.setOnClickListener(this);
        setContentView(view);

    }

    @Override
    public void dismiss() {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 1.0f;
        activity.getWindow().setAttributes(lp);
        super.dismiss();
    }

    @Override
    public void showAsDropDown(View anchor) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.5f;
        activity.getWindow().setAttributes(lp);
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.5f;
        activity.getWindow().setAttributes(lp);
        super.showAtLocation(parent, gravity, x, y);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.5f;
        activity.getWindow().setAttributes(lp);
        super.showAsDropDown(anchor, xoff, yoff, gravity);
//        popWindow.showAtLocation(findViewById(R.id.login_layout),
//                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }

    public void setOnDateFilter(OnDateFilter onDateFilter) {
        this.onDateFilter = onDateFilter;
    }

    public interface OnDateFilter {
        void filterDate(int month);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_all_orders://全部订单
                onDateFilter.filterDate(dateFilter.getAllmonth());
                titleView.setText("全部订单");
                dismiss();
                break;
            case R.id.linear_one_month_orders://一个月内订单
                onDateFilter.filterDate(dateFilter.getOnemonth());
                titleView.setText("一个月内订单");
                dismiss();
                break;
            case R.id.linear_three_orders://三个月内订单
                onDateFilter.filterDate(dateFilter.getThreemonth());
                titleView.setText("三个月内订单");
                dismiss();
                break;
        }
    }
}
