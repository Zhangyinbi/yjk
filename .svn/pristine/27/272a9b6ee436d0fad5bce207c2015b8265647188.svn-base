package com.example.yangbang.miowner.activity;

import android.os.Message;
import android.util.TypedValue;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.MyOrderAdapter;
import com.example.yangbang.miowner.entity.Order;
import com.example.yangbang.miowner.entity.OrderStatus;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.view.TabLayout;
import com.hbw.library.view.TraditionListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的订单Activity
 *
 * @FileName: com.example.yangbang.miowner.activity.MyOrderActivity.java
 * @author: Yangbang
 * @date: 2016-06-27 18:32
 */
public class MyOrderActivity extends BaseActivity implements TabLayout.OnTabSelectedListener {
    private static final int ORDER_LIST = 10;
    private TabLayout tlMyOrderTabLayout;
    private TraditionListView lvMyOrder;

    private List<OrderStatus> orderStatuses = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();
    private MyOrderAdapter orderAdapter;
    public static int currentTabIndex = 0;
    public static String status = "";

    @Override
    protected void initWidget() {
        titleBar.setTitleText("我的订单");
        titleBar.setLeftImgDefaultBack(this);
        assignViews();
        getOrderStatus();
    }

    private void assignViews() {
        tlMyOrderTabLayout = (TabLayout) findViewById(R.id.tl_my_order_tab_layout);
        lvMyOrder = (TraditionListView) findViewById(R.id.lv_my_order);
    }

    private void setOrderTabStatus() {
        for (int i = 0; i < orderStatuses.size(); i++) {
            tlMyOrderTabLayout.newTabItem(orderStatuses.get(i).getStatus_name(), i);
        }
        tlMyOrderTabLayout.setTabTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.textsize_24px));
        tlMyOrderTabLayout.setOnTabSelectedListener(this);
        tlMyOrderTabLayout.setCurrentPosition(0);
//        tlMyOrderTabLayout.setTabItemBackgroundColor(getResources().getColor(R.color.gray_d2d2d2));
        for (int i = 0; i < orderStatuses.size(); i++) {
            tlMyOrderTabLayout.setTabMsgRemind(i, orderStatuses.get(i).getStatus_count());
        }
    }

    private void getOrderStatus() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        analyzeJson.requestData(HttpConstant.OrderStatus, params, REQUEST_SUCCESS);
    }

    private void getOrderList(int status_id) {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("status", status_id + "");
        analyzeJson.requestData(HttpConstant.OrderList, params, ORDER_LIST);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_my_order;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                orderStatuses = gson.fromJson(data.data, new TypeToken<List<OrderStatus>>() {
                }.getType());
                setOrderTabStatus();
                break;
            case ORDER_LIST:
                ResponseSucceedData data1 = (ResponseSucceedData) msg.obj;
                orderList = gson.fromJson(data1.data, new TypeToken<List<Order>>() {
                }.getType());
                if (orderAdapter == null) {
                    orderAdapter = new MyOrderAdapter(orderList, this);
                    lvMyOrder.setAdapter(orderAdapter);
                } else {
                    orderAdapter.notifyDataSetChanged(orderList);
                }
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public void onTabSelected(int position) {
        currentTabIndex = position;
        status = orderStatuses.get(position).getStatus_id() + "";
        getOrderList(orderStatuses.get(position).getStatus_id());
    }
}
