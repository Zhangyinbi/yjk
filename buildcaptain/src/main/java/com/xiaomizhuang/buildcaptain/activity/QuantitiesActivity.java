package com.xiaomizhuang.buildcaptain.activity;

import android.os.Message;
import android.widget.LinearLayout;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.view.TraditionListView;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.QuantitiesAdapter;
import com.xiaomizhuang.buildcaptain.entity.Quantities;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.MyEmptyView;

import java.util.ArrayList;
import java.util.List;

/**
 * 工程量列表Activity
 *
 * @FileName: com.xiaomizhuang.buildcaptain.activity.QuantitiesActivity.java
 * @author: Yangbang
 * @date: 2016-01-20 17:01
 */
public class QuantitiesActivity extends BaseActivity {
    TraditionListView lv_quantities;
    QuantitiesAdapter adapter;
    List<Quantities> quantitiesList = new ArrayList<>();
    String bm_id;
    private MyEmptyView emptyView;
	private LinearLayout ll_quantities;

    @Override
    protected void initWidget() {
        bm_id = getIntent().getStringExtra("bm_id");
        titleBar.setTitleText("工程增加项");
        titleBar.setLeftImgDefaultBack(this);
        lv_quantities = (TraditionListView) findViewById(R.id.lv_quantities);
	    ll_quantities = (LinearLayout) findViewById(R.id.ll_quantities);

        adapter = new QuantitiesAdapter(quantitiesList, this);
        lv_quantities.setAdapter(adapter);
        lv_quantities.setOnRefreshListener(new TraditionListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getQuantitiesList();
            }
        });
        getQuantitiesList();
    }

    private void getQuantitiesList() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("baoming_id", bm_id);
        analyzeJson.requestData(HttpConstant.AddQuantitiesList, params, REQUEST_SUCCESS);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_quantities;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                quantitiesList = gson.fromJson(data.data, new TypeToken<List<Quantities>>() {
                }.getType());
                adapter.notifyDataSetChanged(quantitiesList);
                if (lv_quantities.isCanRefresh()) {
                    lv_quantities.onRefreshComplete();
                }
                analyzeJson.setShowDialog(false);
                break;
        }

        return super.handleMessage(msg);
    }

	@Override
	public void setEmptyView(Message msg) {
		emptyView = new MyEmptyView(this);
		emptyView.setEmptyViewImg(R.mipmap.ic_empty_orders);
		emptyView.setEmptyViewContent("暂无明细");
		lv_quantities.setEmptyView(emptyView.getEmptyView());
		ll_quantities.addView(emptyView.getEmptyView());
	}
}
