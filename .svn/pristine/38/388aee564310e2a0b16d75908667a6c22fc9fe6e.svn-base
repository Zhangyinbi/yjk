package com.xiaomizhuang.buildcaptain.activity;

import android.graphics.Color;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.DeductRecordAdapter;
import com.xiaomizhuang.buildcaptain.entity.DeductRecord;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.PopSelectDate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 全部工地扣款记录
 *
 * @FileName: com.xiaomizhuang.buildcaptain.activity.AllBuildDeductRecordActivity.java
 * @author: Yangbang
 * @date: 2016-01-21 14:45
 */
public class AllBuildDeductRecordActivity extends BaseActivity implements View.OnClickListener, PopSelectDate.OnAffirmDataListener {
    private LinearLayout llFmDeductRecordHeaderDateFilter;
    private TextView tvFmDeductRecordHeaderDate;
    private ImageView imgvFmDeductRecordHeader;
    private TextView tvFmDeductRecordTotalMoney;//扣款总额
    private ListView lvFmDeductRecord;
    List<DeductRecord> deductRecords = new ArrayList<>();
    DeductRecordAdapter deductRecordAdapter;
    private String dateFilter = "";
    PopSelectDate popSelectDate;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("全部扣款记录");
        titleBar.setLeftImgDefaultBack(this);
        assignViews();
        getAllRecord();
    }

    private void assignViews() {
        llFmDeductRecordHeaderDateFilter = (LinearLayout) findViewById(R.id.ll_fm_deduct_record_header_date_filter);
        tvFmDeductRecordHeaderDate = (TextView) findViewById(R.id.tv_fm_deduct_record_header_date);
        imgvFmDeductRecordHeader = (ImageView) findViewById(R.id.imgv_fm_deduct_record_header);
        tvFmDeductRecordTotalMoney = (TextView) findViewById(R.id.tv_fm_deduct_record_total_money);
        lvFmDeductRecord = (ListView) findViewById(R.id.lv_fm_deduct_record);
        imgvFmDeductRecordHeader.setColorFilter(Color.BLACK);
        llFmDeductRecordHeaderDateFilter.setVisibility(View.VISIBLE);
        llFmDeductRecordHeaderDateFilter.setOnClickListener(this);
        deductRecordAdapter = new DeductRecordAdapter(deductRecords, this, true);
        lvFmDeductRecord.setAdapter(deductRecordAdapter);
    }


    private void getAllRecord() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("posttime", dateFilter);
        analyzeJson.requestData(HttpConstant.AmerceRecordAllList, params, REQUEST_SUCCESS);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_deduct_record;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_fm_deduct_record_header_date_filter:
                if (popSelectDate == null) {
                    popSelectDate = new PopSelectDate(AllBuildDeductRecordActivity.this, imgvFmDeductRecordHeader);
                    popSelectDate.setOnAffirmDataListener(this);
                }
                imgvFmDeductRecordHeader.setImageResource(R.mipmap.ic_build_greyarrowup);
                popSelectDate.showAsDropDown(v);
                break;
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(data.data);
                    tvFmDeductRecordTotalMoney.setText("工地扣款总额：" + jsonObject.getString("totalprice") + "元");
                    deductRecords = gson.fromJson(jsonObject.getString("list"), new TypeToken<List<DeductRecord>>() {
                    }.getType());
                    deductRecordAdapter.notifyDataSetChanged(deductRecords);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public void onAffirmData(String data) {
        dateFilter = data;
        if (TextUtils.isEmpty(dateFilter)) {
            tvFmDeductRecordHeaderDate.setText("全部时间");
        } else {
            tvFmDeductRecordHeaderDate.setText(dateFilter);
        }
        getAllRecord();
    }
}
