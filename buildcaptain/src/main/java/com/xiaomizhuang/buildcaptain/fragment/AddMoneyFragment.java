package com.xiaomizhuang.buildcaptain.fragment;

import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.BuildDetailActivity;
import com.xiaomizhuang.buildcaptain.adapter.AddMoneyAdapter;
import com.xiaomizhuang.buildcaptain.entity.AddPayment;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述: 材料追加款 fragment
 * --------------------------------------------
 * 工程:
 * #0000     zyb     创建日期: 2016-11-02 11:19
 */

public class AddMoneyFragment extends BaseFragment{

    private RecyclerView mRvAddMoneyDetail;
    private AddMoneyAdapter adapter;
    private List<AddPayment> dateList;

    private void assignViews() {
        mRvAddMoneyDetail = (RecyclerView) getView().findViewById(R.id.rv_gathering_detail);
    }
    //以下两个生命周期可以控制底部Tab的隐藏状态
    @Override
    public void onResume() {
        super.onResume();
        ((BuildDetailActivity)getActivity()).llBuildDetailTabLeft.setVisibility(View.GONE);
    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden){
            ((BuildDetailActivity)getActivity()).llBuildDetailTabLeft.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initWidget() {
        assignViews();
        dateList = new ArrayList<>();
        mRvAddMoneyDetail.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new AddMoneyAdapter(getContext(),dateList);
        mRvAddMoneyDetail.setAdapter(adapter);
        
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("baoming_id", ((BuildDetailActivity) getActivity()).getBm_id());
//        params.put("baoming_id", "17453");
        analyzeJson.requestData(HttpConstant.AddMoney, params, REQUEST_SUCCESS);
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
                List<AddPayment> tempList = gson.fromJson(data.data, new TypeToken<List<AddPayment>>() {}.getType());
                dateList.clear();
                dateList.addAll(tempList);
                adapter.notifyDataSetChanged();

                break;
        }
        return super.handleMessage(msg);
    }
}
