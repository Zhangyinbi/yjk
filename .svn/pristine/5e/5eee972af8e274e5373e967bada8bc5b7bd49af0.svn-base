package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.UserPurchaseAdapter;
import com.xiaomizhuang.buildcaptain.entity.BuySelfOrder;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.MyEmptyView;

import java.util.ArrayList;
import java.util.List;


/**
 * 自购才材料Activity
 * create by mwy 2016/10/19
 */
public class UserPurchaseActivity extends BaseActivity {

    private RecyclerView mRvUserPurchase;
    private Button mBtnContactOwner;
    private String bm_id;
    private String ownerPhone;//业主电话
    private String ownerName;//业主姓名
    private List<BuySelfOrder> orderList;
    private UserPurchaseAdapter adapter;
    private TextView tvHint;

    private void assignViews() {
        mRvUserPurchase = (RecyclerView) findViewById(R.id.rv_user_purchase);
        mBtnContactOwner = (Button) findViewById(R.id.btn_contact_owner);
        tvHint = (TextView) findViewById(R.id.tv_hint);

    }


    @Override
    protected void initWidget() {

        titleBar.setTitleText("自购材料");
        titleBar.setLeftImgDefaultBack(this);
        bm_id = getIntent().getStringExtra("bm_id");
        ownerPhone = getIntent().getStringExtra("ownerPhone");
        ownerName = getIntent().getStringExtra("ownerName");
        assignViews();
        orderList = new ArrayList<>();
        mRvUserPurchase.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserPurchaseAdapter(orderList,this);
        mRvUserPurchase.setAdapter(adapter);

        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("baoming_id", bm_id);
        analyzeJson.requestData(HttpConstant.BuySelfOrderList, params, REQUEST_SUCCESS);

        mBtnContactOwner.setText("联系业主("+ownerName+")");
        mBtnContactOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_DIAL);
                call.setData(Uri.parse("tel:" +ownerPhone));
                startActivity(call);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_user_purchase;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData)msg.obj;
                List<BuySelfOrder> tempList = gson.fromJson(data.data, new TypeToken<List<BuySelfOrder>>() {}.getType());
                if (tempList.size()!=0){
                    mBtnContactOwner.setVisibility(View.VISIBLE);
                    tvHint.setVisibility(View.GONE);
                }
                orderList.clear();
                orderList.addAll(tempList);
                adapter.notifyDataSetChanged();
                break;
        }
        return super.handleMessage(msg);
    }


}
