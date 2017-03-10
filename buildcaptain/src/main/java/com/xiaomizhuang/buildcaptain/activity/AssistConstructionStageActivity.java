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
import com.xiaomizhuang.buildcaptain.adapter.AssistConstructionStageAdapter;
import com.xiaomizhuang.buildcaptain.entity.AssistConstructionStage;
import com.xiaomizhuang.buildcaptain.entity.AssistSpecificationsModels;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015/11/26.施工阶段
 */
public class AssistConstructionStageActivity extends BaseActivity {

    private ListView construction_stage_listview = null;
    private AssistConstructionStageAdapter massistConstructionStageAdapter = null;
    private List<AssistConstructionStage> massistConstructionStage = new ArrayList<AssistConstructionStage>();

    @Override
    protected void initWidget() {
        titleBar.setTitleText("施工阶段");
        titleBar.setLeftImgRes(R.drawable.back);
        titleBar.setLeftImgListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CloseActivity();
            }
        });
        construction_stage_listview = (ListView)findViewById(R.id.construction_stage_listview);

        //查询规格型号
        HashMap<String, String> mMap = new HashMap<String, String>();
//        mMap.put(AppFlag.MSGID, getIntent().getStringExtra(AppFlag.MSGID));//施工阶段ID
        analyzeJson.requestData(HttpConstant.GetProjectUrl, mMap, REQUEST_SUCCESS);
        massistConstructionStageAdapter = new AssistConstructionStageAdapter(this,massistConstructionStage);
        construction_stage_listview.setAdapter(massistConstructionStageAdapter);
        construction_stage_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(AssistConstructionStageActivity.this, AssistOrderDetailsActivity.class);
                it.putExtra(AppFlag.CONSTRUCTIONSTAGE, massistConstructionStage.get(position));
                setResult(AssistOrderDetailsActivity.CONSTRUCTIONSTAGE, it);
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
        switch (msg.what){
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData)msg.obj;
                massistConstructionStage = gson.fromJson(data.data, new TypeToken<List<AssistConstructionStage>>() {}.getType());
                massistConstructionStageAdapter.notifyDataSetChanged(massistConstructionStage);
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        CloseActivity();
        return super.onKeyDown(keyCode, event);
    }

    private void CloseActivity(){
        Intent it = new Intent(AssistConstructionStageActivity.this,AssistOrderDetailsActivity.class);
        AssistConstructionStage obj = new AssistConstructionStage();
        it.putExtra(AppFlag.CONSTRUCTIONSTAGE,obj);
        setResult(AssistOrderDetailsActivity.CONSTRUCTIONSTAGE,it);
        finishActivity();
    }
}
