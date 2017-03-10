package com.xiaomizhuang.buildcaptain.fragment;

import android.os.Message;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.view.TraditionListView;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.BuildDetailActivity;
import com.xiaomizhuang.buildcaptain.adapter.DeductRecordAdapter;
import com.xiaomizhuang.buildcaptain.entity.DeductRecord;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 扣款记录Fragment
 *
 * @FileName: com.xiaomizhuang.buildcaptain.fragment.FragmentDeductRecord.java
 * @author: Yangbang
 * @date: 2016-01-15 11:57
 */
public class FragmentDeductRecord extends BaseFragment {
    TraditionListView lv_fm_deduct_record;
    TextView tv_fm_deduct_record_total_money;//扣款总额
    List<DeductRecord> deductRecords = new ArrayList<>();
    DeductRecordAdapter deductRecordAdapter;

    @Override
    protected void initWidget() {
        lv_fm_deduct_record = (TraditionListView) getView().findViewById(R.id.lv_fm_deduct_record);
        tv_fm_deduct_record_total_money = (TextView) getView().findViewById(R.id.tv_fm_deduct_record_total_money);
        deductRecordAdapter = new DeductRecordAdapter(deductRecords, getActivity(), false);
        lv_fm_deduct_record.setAdapter(deductRecordAdapter);
        lv_fm_deduct_record.setOnRefreshListener(new TraditionListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDeductRecordList();
            }
        });
        getDeductRecordList();
    }

    private void getDeductRecordList() {
        params.clear();
        params.put("baoming_id", ((BuildDetailActivity) getActivity()).getBm_id());
        params.put("token", MyApplication.TOKEN);
        analyzeJson.requestData(HttpConstant.AmerceRecordList, params, REQUEST_SUCCESS);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_deduct_record;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                try {
                    JSONObject jsonObject = new JSONObject(data.data);
                    tv_fm_deduct_record_total_money.setText("工地扣款总额：" + jsonObject.getString("totalprice") + "元");
                    deductRecords = gson.fromJson(jsonObject.getString("list"), new TypeToken<List<DeductRecord>>() {
                    }.getType());
                    deductRecordAdapter.notifyDataSetChanged(deductRecords);
                    if (lv_fm_deduct_record.isCanRefresh()){
                        lv_fm_deduct_record.onRefreshComplete();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
        return super.handleMessage(msg);
    }
}
