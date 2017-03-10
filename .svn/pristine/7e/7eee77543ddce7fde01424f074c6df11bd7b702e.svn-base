package com.youjuke.miprojectmanager.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.entity.ResponseSucceedData;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.AssistProgectAddMatterAdapter;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.entity.AssistProgectAddMatter;
import com.youjuke.miprojectmanager.util.HttpConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/11/19.工程增加项类别选择
 */

public class AssistProgectAddMatterActivity extends MiBaseActivity implements View.OnClickListener {

    private GridView adGridview = null;
    private List<AssistProgectAddMatter> mAssistProgectAddMatters = new ArrayList<AssistProgectAddMatter>();
    private AssistProgectAddMatterAdapter mAssistProgectAddMatterAdapter = null;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("增加工程项");
        titleBar.setLeftImgDefaultBack(this);
        titleBar.setRightText1("订单详情");
        titleBar.setRightText1Listener(this);

        mAssistProgectAddMatterAdapter = new AssistProgectAddMatterAdapter(this, mAssistProgectAddMatters);
        adGridview = (GridView) findViewById(R.id.adGridview);
        adGridview.setAdapter(mAssistProgectAddMatterAdapter);

        HashMap<String, String> mMap = new HashMap<String, String>();
        mMap.put("token", HttpConstant.token);
        analyzeJson.requestData(HttpConstant.ProjectTypeUrl, mMap, REQUEST_SUCCESS);

        adGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                finishActivity();
                Intent it = new Intent(AssistProgectAddMatterActivity.this, AssistProgectAddMatterOrderActivity.class);
                it.putExtra("id", mAssistProgectAddMatters.get(position).getId());
                it.putExtra("bm_id", getIntent().getStringExtra("bm_id"));
                startActivity(it);
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_assist_details;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                mAssistProgectAddMatters = gson.fromJson(data.data, new TypeToken<List<AssistProgectAddMatter>>() {
                }.getType());
                mAssistProgectAddMatterAdapter.notifyDataSetChanged(mAssistProgectAddMatters);
                break;
        }
        return super.handleMessage(msg);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.right_text1:
                //订单详情
                Intent quantitiesIntent = new Intent(this, QuantitiesActivity.class);
                quantitiesIntent.putExtra("bm_id", getIntent().getStringExtra("bm_id"));
                startActivity(quantitiesIntent);
                break;
            default:
                break;
        }
    }
}
