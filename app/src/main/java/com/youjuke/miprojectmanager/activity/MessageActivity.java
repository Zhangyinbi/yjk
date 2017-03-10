package com.youjuke.miprojectmanager.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.MessageAdapter;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.entity.Message;
import com.youjuke.miprojectmanager.util.HttpConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/11/20.
 */
public class MessageActivity extends MiBaseActivity {
    public static final int CONFIRM_RECEIVE = 90;
    ListView lv_message_list;
    String titleType;
    String bm_id;
    MessageAdapter messageAdapter;
    List<Message> messageList = new ArrayList<>();
    View emptyView;

    @Override
    protected void initWidget() {
        titleType = getIntent().getStringExtra("titleType");
        bm_id = getIntent().getStringExtra("bm_id");
        titleBar.setTitleText(titleType);
        titleBar.setLeftImgDefaultBack(this);
        lv_message_list = (ListView) this.findViewById(R.id.lv_message_list);
        emptyView = LayoutInflater.from(this).inflate(R.layout.empty_msg_view, null);
        ((ImageView) ((LinearLayout) emptyView).getChildAt(0)).setImageResource(R.drawable.ic_empty_msg);
        ((TextView) ((LinearLayout) emptyView).getChildAt(1)).setText("暂无消息");
        emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        ((ViewGroup) lv_message_list.getParent()).addView(emptyView);
        lv_message_list.setEmptyView(emptyView);
        messageAdapter = new MessageAdapter(this, messageList);
        lv_message_list.setAdapter(messageAdapter);
        getMessageList();
    }

    private void getMessageList() {
        HashMap<String, String> params = new HashMap<>();
        params.put("token", HttpConstant.token);
        if (bm_id != null) {
            params.put("bm_id", bm_id);
        }
        analyzeJson.requestData(HttpConstant.MessageRemind, params, REQUEST_SUCCESS);
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
                ToastUtil.show(this, "提交验收成功");
                getMessageList();
                break;
        }
        return super.handleMessage(msg);
    }
}
