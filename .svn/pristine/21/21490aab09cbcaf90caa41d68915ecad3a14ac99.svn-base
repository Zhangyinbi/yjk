package com.xiaomizhuang.buildcaptain.fragment;

import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.BuildDetailActivity;
import com.xiaomizhuang.buildcaptain.adapter.GatheringAdapter;
import com.xiaomizhuang.buildcaptain.entity.Payment;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 工期收款 fragment
 * --------------------------------------------
 * 工程:
 * #0000     mwy     创建日期: 2016-10-20 10:19
 */

public class GatheringFragment  extends BaseFragment{

    private RecyclerView mRvGatheringDetail;
    private GatheringAdapter adapter;
    private List<Payment> dateList;

    private void assignViews() {
        mRvGatheringDetail = (RecyclerView) getView().findViewById(R.id.rv_gathering_detail);

    }

    @Override
    public void onResume() {
        super.onResume();
        ((BuildDetailActivity)getActivity()).llBuildDetailTabLeft.setVisibility(View.GONE);
    }
    //一下两个生命周期可以控制底部Tab的隐藏状态
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden)
            ((BuildDetailActivity)getActivity()).llBuildDetailTabLeft.setVisibility(View.GONE);
    }

    @Override
    protected void initWidget() {
        assignViews();

        dateList = new ArrayList<>();
        mRvGatheringDetail.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new GatheringAdapter(getContext(),dateList);
        mRvGatheringDetail.setAdapter(adapter);
        
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("baoming_id", ((BuildDetailActivity) getActivity()).getBm_id());
//        params.put("baoming_id", "17453");
        analyzeJson.requestData(HttpConstant.Payment, params, REQUEST_SUCCESS);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_gathering;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData)msg.obj;
                List<Payment> tempList = gson.fromJson(data.data, new TypeToken<List<Payment>>() {}.getType());
                dateList.clear();
                dateList.addAll(tempList);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.handleMessage(msg);
    }
}
