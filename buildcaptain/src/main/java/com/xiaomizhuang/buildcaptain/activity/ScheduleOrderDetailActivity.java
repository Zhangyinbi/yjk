package com.xiaomizhuang.buildcaptain.activity;

import android.os.Message;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.ScheduleDetailAdapter;
import com.xiaomizhuang.buildcaptain.entity.ScheduleDetail;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 排期详情材料订单Activity
 *
 * @FileName: com.xiaomizhuang.buildcaptain.activity.ScheduleOrderDetailActivity.java
 * @author: Yangbang
 * @date: 2016-01-22 10:44
 */
public class ScheduleOrderDetailActivity extends BaseActivity {
    public static final int AFFIRM_RECEIVING = 10;
    public static final int ADD_REMARK = 11;
    ListView lv_schedule_order_detail;
    String id;
    String plan_type;
    String event_type;
    String baoming_id;
    ScheduleDetailAdapter detailAdapter;
    List<ScheduleDetail> detailLis = new ArrayList<>();

    @Override
    protected void initWidget() {
        id = getIntent().getStringExtra("id");
        plan_type = getIntent().getStringExtra("plan_type");
        event_type = getIntent().getStringExtra("event_type");
        baoming_id = getIntent().getStringExtra("baoming_id");
        titleBar.setTitleText(getIntent().getStringExtra("title"));
        titleBar.setLeftImgDefaultBack(this);
        lv_schedule_order_detail = (ListView) this.findViewById(R.id.lv_schedule_order_detail);
        detailAdapter = new ScheduleDetailAdapter(detailLis, this, analyzeJson);
        lv_schedule_order_detail.setAdapter(detailAdapter);
        getDetailList();
    }

    private void getDetailList() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("id", id);
        params.put("plan_type", plan_type);
        params.put("event_type", event_type);
        params.put("baoming_id", baoming_id);
        analyzeJson.requestData(HttpConstant.ScheduleDetailList, params, REQUEST_SUCCESS);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_schedule_order_detail;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                detailLis = gson.fromJson(data.data, new TypeToken<List<ScheduleDetail>>() {
                }.getType());
                detailAdapter.notifyDataSetChanged(detailLis);
                break;
            case AFFIRM_RECEIVING://确认收货
                ResponseSucceedData data2 = (ResponseSucceedData) msg.obj;
                ToastUtil.show(this, data2.message);
                getDetailList();
                break;
            case ADD_REMARK://添加备注
                break;
        }
        return super.handleMessage(msg);
    }
}
