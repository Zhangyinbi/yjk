package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AssistTypeAdapter;
import com.xiaomizhuang.buildcaptain.entity.AssistType;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by user on 2015/11/20.辅材明细小类
 */

public class AssistTypeActivity extends BaseActivity{

    private ListView atListView = null;
    private String fid;
    private String title;
    private List<AssistType> mAssistTypes = new ArrayList<AssistType>();
    private AssistTypeAdapter mAssistTypeAdapter = null;
    private String bm_id;
    private String msg_id;
    @Override
    protected void initWidget() {
        fid = getIntent().getStringExtra(AppFlag.FID);
        title = getIntent().getStringExtra(AppFlag.TITLE);
        msg_id = getIntent().getStringExtra(AppFlag.MSGID);
        bm_id = getIntent().getStringExtra("bm_id");
        titleBar.setTitleText(title);
        titleBar.setLeftImgDefaultBack(this);
        //获取辅材明细小类
        HashMap<String, String> mMap = new HashMap<String, String>();
        mMap.put(AppFlag.UID, getIntent().getStringExtra(AppFlag.BAOMINGID));
        mMap.put(AppFlag.FID, getIntent().getStringExtra(AppFlag.FID));
        analyzeJson.requestData(HttpConstant.XiaoLeiUrl, mMap, REQUEST_SUCCESS);
        atListView = (ListView)findViewById(R.id.atListView);
        mAssistTypeAdapter = new AssistTypeAdapter(this,mAssistTypes);
        atListView.setAdapter(mAssistTypeAdapter);
        atListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(AssistTypeActivity.this,AssistOrderDetailsActivity.class);
                it.putExtra(AppFlag.ID,mAssistTypes.get(position).getId());//小类ID
                it.putExtra(AppFlag.FID,fid);//大类ID
                it.putExtra(AppFlag.BAOMINGID,mAssistTypes.get(position).getBm_id());
                it.putExtra(AppFlag.MSGID, msg_id);
                it.putExtra("bm_id", bm_id);
                startActivity(it);
            }
        });

    }

    @Override
    protected void initData() {

    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData)msg.obj;
                mAssistTypes = gson.fromJson(data.data, new TypeToken<List<AssistType>>() {}.getType());
                mAssistTypeAdapter.notifyDataSetChanged(mAssistTypes);
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_assist_type;
    }

}
