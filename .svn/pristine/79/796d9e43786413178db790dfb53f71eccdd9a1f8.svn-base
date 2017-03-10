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
import com.xiaomizhuang.buildcaptain.adapter.AssistSelectUnitAdapter;
import com.xiaomizhuang.buildcaptain.entity.AssistSelectUnit;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2016/1/13.选择单位
 */
public class AssistSelectUnitActivity extends BaseActivity{
    private ListView construction_stage_listview = null;
    private AssistSelectUnitAdapter mAssistSelectUnitAdapter = null;
    private List<AssistSelectUnit> mAssistSelectUnits = new ArrayList<AssistSelectUnit>();
    private String fid = null,brand = null,dl_id = null,mtypeid = null,norms = null,bm_id;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("单位");
        titleBar.setLeftImgRes(R.drawable.back);
        titleBar.setLeftImgListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseActivity();
            }
        });
        construction_stage_listview = (ListView) findViewById(R.id.construction_stage_listview);

        mtypeid = getIntent().getStringExtra(AppFlag.MTYPEID);
        brand = getIntent().getStringExtra("brand");
        fid = getIntent().getStringExtra("fid");
        dl_id = getIntent().getStringExtra("dl_id");
        norms = getIntent().getStringExtra("norms");
        bm_id = MyApplication.getApp().getBuildSite().getUid();
        HashMap<String, String> mMap = new HashMap<String, String>();
        mMap.put(AppFlag.MTYPEID, mtypeid);
        mMap.put(AppFlag.FID, fid);
        mMap.put("brand", brand);
        mMap.put("dl_id", dl_id);
        mMap.put("norms", norms);
        mMap.put("bm_id", bm_id);
        analyzeJson.requestData(HttpConstant.SelectUnitUrl, mMap, REQUEST_SUCCESS);

        mAssistSelectUnitAdapter = new AssistSelectUnitAdapter(this, mAssistSelectUnits);
        construction_stage_listview.setAdapter(mAssistSelectUnitAdapter);
        construction_stage_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(AssistSelectUnitActivity.this, AssistOrderDetailsActivity.class);
                it.putExtra("selectunit", mAssistSelectUnits.get(position));
                setResult(AssistOrderDetailsActivity.SELECTUNIT, it);
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
                mAssistSelectUnits = gson.fromJson(data.data, new TypeToken<List<AssistSelectUnit>>() {
                }.getType());
                mAssistSelectUnitAdapter.notifyDataSetChanged(mAssistSelectUnits);
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
        Intent it = new Intent(AssistSelectUnitActivity.this, AssistOrderDetailsActivity.class);
        AssistSelectUnit obj = new AssistSelectUnit();
        it.putExtra("selectunit", obj);
        setResult(AssistOrderDetailsActivity.SELECTUNIT, it);
        finishActivity();
    }
}
