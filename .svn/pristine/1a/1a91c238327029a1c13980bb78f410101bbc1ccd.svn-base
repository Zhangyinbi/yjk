package com.youjuke.miprojectmanager.activity;

import android.os.Message;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.view.TraditionListView;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.QuantitiesAdapter;
import com.youjuke.miprojectmanager.entity.Quantities;
import com.youjuke.miprojectmanager.util.HttpConstant;

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

    @Override
    protected void initWidget() {
        bm_id = getIntent().getStringExtra("bm_id");
        titleBar.setTitleText("工程增加项");
        titleBar.setLeftImgDefaultBack(this);
        lv_quantities = (TraditionListView) findViewById(R.id.lv_quantities);
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
        params.put("token", HttpConstant.token);
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
}
