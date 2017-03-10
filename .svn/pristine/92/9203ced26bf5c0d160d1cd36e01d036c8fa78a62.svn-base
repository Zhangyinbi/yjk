package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AssistDetailsAdapter;
import com.xiaomizhuang.buildcaptain.entity.MaterialType;
import com.xiaomizhuang.buildcaptain.entity.MessageTotal;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015/11/19.辅材明细大类
 */

public class AssistDetailsActivity extends BaseActivity implements View.OnClickListener{

    private GridView adGridview = null;
    private List<MaterialType> mAssistDetailss = new ArrayList<MaterialType>();
    private AssistDetailsAdapter mAssistDetailsAdapter = null;
    private String uid= null,msg_id = null,bm_id;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("辅材明细");
        titleBar.setRightImg0Res(R.mipmap.news);
        titleBar.setRightText0AlertDotNumBg(R.mipmap.warn);
        titleBar.setRightImg0Listener(this);
        titleBar.setLeftImgDefaultBack(this);
        getMessageTotal();
        mAssistDetailsAdapter = new AssistDetailsAdapter(this,mAssistDetailss);
        adGridview = (GridView)findViewById(R.id.adGridview);
        adGridview.setAdapter(mAssistDetailsAdapter);
        //获取上个界面传递过来的值
        uid = getIntent().getStringExtra(AppFlag.UID);
        bm_id = getIntent().getStringExtra("bm_id");
        msg_id = getIntent().getStringExtra(AppFlag.MSGID);
        //获取辅材明细大类
        HashMap<String, String> mMap = new HashMap<String, String>();
        mMap.put(AppFlag.UID, uid);
        if(msg_id != null){
            mMap.put("msg_id",msg_id);
        }
        analyzeJson.requestData(HttpConstant.DaLeiUrl, mMap, REQUEST_SUCCESS);
        adGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //跳转到辅材明细小类
                Intent it = new Intent(AssistDetailsActivity.this, AssistTypeActivity.class);
                it.putExtra(AppFlag.BAOMINGID, mAssistDetailss.get(position).getBm_id());
                it.putExtra(AppFlag.FID, mAssistDetailss.get(position).getId());
                it.putExtra(AppFlag.TITLE, mAssistDetailss.get(position).getName());
                it.putExtra(AppFlag.MSGID, msg_id);
                it.putExtra("bm_id",bm_id);
                it.putExtra("msg_id",msg_id);
                startActivity(it);
            }
        });
    }

    @Override
    protected void initData() {

    }

    private void getMessageTotal() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        analyzeJson.requestData(HttpConstant.GetMessageTotalUrl, params, BuildSiteActivity.MESSAGE_TOTAL_FLAG);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_assist_details;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what){
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData)msg.obj;
                mAssistDetailss = gson.fromJson(data.data, new TypeToken<List<MaterialType>>() {}.getType());
                mAssistDetailsAdapter.notifyDataSetChanged(mAssistDetailss);
                break;
            case BuildSiteActivity.MESSAGE_TOTAL_FLAG:
                ResponseSucceedData data2 = (ResponseSucceedData) msg.obj;
                MessageTotal total = gson.fromJson(data2.data, MessageTotal.class);
                titleBar.setRightText0AlertDotNum(total.getTolotemsgcount() + "");
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.right_igame0_relative://标题栏右侧消息提醒
                Intent intent=new Intent(AssistDetailsActivity.this,MessageActivity.class);
                intent.putExtra("titleType","全部消息");
                startActivity(intent);
                break;
        }
    }
}
