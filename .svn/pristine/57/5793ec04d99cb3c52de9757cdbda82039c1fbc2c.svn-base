package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AllOrdersAdapter;
import com.xiaomizhuang.buildcaptain.adapter.AssistTypeOrderAdapter;
import com.xiaomizhuang.buildcaptain.entity.DateFilter;
import com.xiaomizhuang.buildcaptain.entity.MaterialType;
import com.xiaomizhuang.buildcaptain.entity.Orders;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.MarqueeTextView;
import com.xiaomizhuang.buildcaptain.view.PopOrdersFilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/11/20.
 */
public class AllOrdersActivity extends BaseActivity implements View.OnClickListener, AssistTypeOrderAdapter.OnTypeClicklistener, PopOrdersFilter.OnDateFilter {
    private static final int GET_MATERIRL_TYPE = 90;//材料类型
    private static final int GET_ORDERS_DETAIL = 91;//订单明细
    private static final int GET_ORDERS_TIME_QUERY = 93;//时间筛选
    private RelativeLayout leftIgameRelative;
    private ImageView leftImage;
    private LinearLayout linearTitleTv;
    private MarqueeTextView titleTv;
    private TextView rightText0;
    private GridView gv_assist_type;
    private ListView lvAllOrdersList;
    private List<MaterialType> types = new ArrayList<>();
    private List<Orders> ordersList = new ArrayList<>();
    private AssistTypeOrderAdapter assistTypeOrderAdapter;
    private AllOrdersAdapter ordersAdapter;
    View empty_view;
    String typeId;
    String bm_id;
    int month = 0;
    DateFilter dateFilter;
    PopOrdersFilter popOrdersFilter;


    @Override
    protected void initWidget() {
        bm_id = getIntent().getExtras().getString("bm_id");
        assignViews();
        GetOrdersTimeQuery();
        getMaterialType();
        getOrdersDetail("0", month);
//        getOrdersDetail("0", "4938", month);
    }

    private void assignViews() {
        leftIgameRelative = (RelativeLayout) findViewById(R.id.left_igame_back_relative);
        leftImage = (ImageView) findViewById(R.id.left_image);
        linearTitleTv = (LinearLayout) findViewById(R.id.linear_titleTv);
        titleTv = (MarqueeTextView) findViewById(R.id.tv_orders_title);
        rightText0 = (TextView) findViewById(R.id.right_text0_xiadan);
        gv_assist_type = (GridView) findViewById(R.id.gv_assist_type);
        lvAllOrdersList = (ListView) findViewById(R.id.lv_all_orders_list);
        empty_view = LayoutInflater.from(this).inflate(R.layout.empty_view, null);
        empty_view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ((ViewGroup) lvAllOrdersList.getParent()).addView(empty_view);
        lvAllOrdersList.setEmptyView(empty_view);
        leftIgameRelative.setOnClickListener(this);
//        linearTitleTv.setOnClickListener(this);//时间筛选
        rightText0.setOnClickListener(this);
        ordersAdapter = new AllOrdersAdapter(this, ordersList, analyzeJson);
        lvAllOrdersList.setAdapter(ordersAdapter);
        titleTv.setText(getIntent().getStringExtra("title"));
    }

    private void setTypeData() {
        MaterialType assistDetails = new MaterialType();
        assistDetails.setId("0");
        assistDetails.setBm_id("4938");
        assistDetails.setImg("");
        assistDetails.setName("全部");
        types.add(assistDetails);
        assistTypeOrderAdapter = new AssistTypeOrderAdapter(this, types);
        assistTypeOrderAdapter.setOnTypeClicklistener(this);
        gv_assist_type.setAdapter(assistTypeOrderAdapter);
    }

    private void getMaterialType() {
        HashMap<String, String> params = new HashMap<>();
        params.put("uid", MyApplication.UID);
        params.put("token", MyApplication.TOKEN);
        analyzeJson.requestData(HttpConstant.DaLeiUrl, params, GET_MATERIRL_TYPE);
    }

    private void getOrdersDetail(String typeId, int month) {
        this.typeId = typeId;
        HashMap<String, String> params = new HashMap<>();
        params.put("token", MyApplication.TOKEN);
        params.put("number", typeId);
        params.put("bm_id", bm_id);
        params.put("month", month + "");
        analyzeJson.requestData(HttpConstant.OrderPlanDetailUrl, params, GET_ORDERS_DETAIL);
    }

    private void GetOrdersTimeQuery() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", MyApplication.TOKEN);
        params.put("bm_id", bm_id);
        analyzeJson.requestData(HttpConstant.OrderTimeQueryUrl, params, GET_ORDERS_TIME_QUERY);
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case GET_MATERIRL_TYPE://辅材类型
                setTypeData();
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                List<MaterialType> lists = gson.fromJson(data.data, new TypeToken<List<MaterialType>>() {
                }.getType());
                types.addAll(lists);
                assistTypeOrderAdapter.notifyDataSetChanged(types);
                break;
            case GET_ORDERS_DETAIL://订单详情
                ResponseSucceedData data2 = (ResponseSucceedData) msg.obj;
                ordersList = gson.fromJson(data2.data, new TypeToken<List<Orders>>() {
                }.getType());
                ordersAdapter.notifyDataSetChanged(ordersList);
                break;
            case AllOrdersAdapter.CONFIRM_RECEIPT://确认下单
                getOrdersDetail(typeId, month);
                break;
            case GET_ORDERS_TIME_QUERY://时间查询
                ResponseSucceedData data3 = (ResponseSucceedData) msg.obj;
                dateFilter = gson.fromJson(data3.data, DateFilter.class);
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    protected void initData() {
        setIsShowTitleBar(false);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_all_orders;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_igame_back_relative://返回
                finishActivity();
                break;
            case R.id.linear_titleTv://标题
                if (popOrdersFilter == null) {
                    popOrdersFilter = new PopOrdersFilter(AllOrdersActivity.this, dateFilter, titleTv);
                    popOrdersFilter.setOnDateFilter(this);
                }
                int[] location = new int[2];
                v.getLocationOnScreen(location);
                int popupWidth = popOrdersFilter.getWidth();
                int popupHeight = popOrdersFilter.getHeight();
                popOrdersFilter.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] + v.getHeight());
                break;
            case R.id.right_text0_xiadan://下单
                Intent intent = new Intent(AllOrdersActivity.this, AssistDetailsActivity.class);
                intent.putExtra("uid", bm_id);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onClilck(MaterialType assistDetails) {
        getOrdersDetail(assistDetails.getId(), month);
    }

    @Override
    public void filterDate(int month) {
        this.month = month;
        getOrdersDetail(typeId, month);
    }

}
