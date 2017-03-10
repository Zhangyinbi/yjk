package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AssistBrandManufacturersAdapter;
import com.xiaomizhuang.buildcaptain.entity.AssistBrandManufacturers;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/12/14.品牌厂家
 */
public class AssistBrandManufacturersActivity extends BaseActivity {

    private ListView construction_stage_listview = null;
    private AssistBrandManufacturersAdapter mAssistBrandManufacturersAdapter = null;
    private List<AssistBrandManufacturers> mAssistBrandManufacturers = new ArrayList<AssistBrandManufacturers>();
    private String bm_id;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("品牌厂家");
        titleBar.setLeftImgRes(R.drawable.back);
        titleBar.setLeftImgListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseActivity();
            }
        });
        construction_stage_listview = (ListView) findViewById(R.id.construction_stage_listview);

	    bm_id = MyApplication.getApp().getBuildSite().getUid();

        //查询品牌厂家
        HashMap<String, String> mMap = new HashMap<String, String>();
        mMap.put(AppFlag.MTID, getIntent().getStringExtra(AppFlag.MTID));//铺材小类ID
        mMap.put("dl_id", getIntent().getStringExtra(AppFlag.FID));//铺材小类ID
        mMap.put("bm_id",bm_id);
        analyzeJson.requestData(HttpConstant.GetBandInfoUrl, mMap, REQUEST_SUCCESS);

        mAssistBrandManufacturersAdapter = new AssistBrandManufacturersAdapter(this, mAssistBrandManufacturers);
        construction_stage_listview.setAdapter(mAssistBrandManufacturersAdapter);
        construction_stage_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(AssistBrandManufacturersActivity.this, AssistOrderDetailsActivity.class);
                it.putExtra(AppFlag.BRANDMANUFACTURERS, mAssistBrandManufacturers.get(position));
                setResult(AssistOrderDetailsActivity.BRANDMANUFACTURERS, it);
                finishActivity();
            }
        });
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_construction_stage;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                mAssistBrandManufacturers = gson.fromJson(data.data, new TypeToken<List<AssistBrandManufacturers>>() {
                }.getType());
                mAssistBrandManufacturersAdapter.notifyDataSetChanged(mAssistBrandManufacturers);
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        CloseActivity();
        return super.onKeyDown(keyCode, event);
    }

    private void CloseActivity() {
        Intent it = new Intent(AssistBrandManufacturersActivity.this, AssistOrderDetailsActivity.class);
        AssistBrandManufacturers obj = new AssistBrandManufacturers();
        it.putExtra(AppFlag.BRANDMANUFACTURERS, obj);
        setResult(AssistOrderDetailsActivity.BRANDMANUFACTURERS, it);
        finishActivity();
    }
}
