package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.MessageAdapter;
import com.xiaomizhuang.buildcaptain.entity.Message;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.MyEmptyView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/11/20.
 */
public class MessageActivity extends BaseActivity {
    public static final int CONFIRM_RECEIVE = 90;
    ListView lv_message_list;
    String titleType;
    String bm_id;
    MessageAdapter messageAdapter;
    List<Message> messageList = new ArrayList<>();
    MyEmptyView emptyView;

    @Override
    protected void initWidget() {
        titleType = getIntent().getStringExtra("titleType");
        bm_id = getIntent().getStringExtra("bm_id");
        titleBar.setTitleText(titleType);
        titleBar.setLeftImgDefaultBack(this);
        lv_message_list = (ListView) this.findViewById(R.id.lv_message_list);
        emptyView = new MyEmptyView(this);
        emptyView.setEmptyViewImg(R.mipmap.ic_empty_msg);
        emptyView.setEmptyViewContent("暂无消息");
        ((ViewGroup) lv_message_list.getParent()).addView(emptyView.getEmptyView());
        lv_message_list.setEmptyView(emptyView.getEmptyView());
        messageAdapter = new MessageAdapter(this, messageList, analyzeJson);
        lv_message_list.setAdapter(messageAdapter);
        getMessageList();
    }

    private void getMessageList() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", MyApplication.TOKEN);
        if (bm_id != null) {
            params.put("bm_id", bm_id);
        }
        analyzeJson.requestData(HttpConstant.QueryMessageUrl, params, REQUEST_SUCCESS);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_message;
    }

    @Override
    public boolean handleMessage(android.os.Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                messageList = gson.fromJson(data.data, new TypeToken<List<Message>>() {
                }.getType());
                messageAdapter.notifyDataSetChanged(messageList);
                break;
            case CONFIRM_RECEIVE:
                ToastUtil.show(this, "操作成功");
                getMessageList();
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public void onBackPressed() {
        if (BuildSiteActivity.isActive == false) {
            startActivity(new Intent(this, BuildSiteActivity.class));
        }
        super.onBackPressed();
    }
}
