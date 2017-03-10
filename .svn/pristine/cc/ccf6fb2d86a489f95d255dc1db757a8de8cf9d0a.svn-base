package com.xiaomizhuang.buildcaptain.activity;


import android.os.Message;
import android.widget.ExpandableListView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AssistNodeNoteAdapter;
import com.xiaomizhuang.buildcaptain.entity.AssistNodeNote;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/2/23.
 */
public class AssistNodeNoteActivity extends BaseActivity {

    private String id;
    private String plan_type;
    private String baoming_id;
    private ArrayList<AssistNodeNote> mAssistNodeNotes = new ArrayList<AssistNodeNote>();
    private ExpandableListView node_note_expandable_listview;

    private com.xiaomizhuang.buildcaptain.adapter.AssistNodeNoteAdapter mAssistNodeNoteAdapter = null;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("节点备注");
        titleBar.setLeftImgDefaultBack(this);

        id = getIntent().getStringExtra("id");
        plan_type = getIntent().getStringExtra("plan_type");
        baoming_id = getIntent().getStringExtra("baoming_id");

        node_note_expandable_listview = (ExpandableListView) findViewById(R.id.node_note_expandable_listview);
        //设置属性去掉默认向下的箭头
        node_note_expandable_listview.setGroupIndicator(null);
        mAssistNodeNoteAdapter = new AssistNodeNoteAdapter(this, mAssistNodeNotes);
        node_note_expandable_listview.setAdapter(mAssistNodeNoteAdapter);

        HashMap<String, String> mMap = new HashMap<String, String>();
        mMap.put("token", MyApplication.TOKEN);
        mMap.put("baoming_id", baoming_id);
        mMap.put("plan_type", plan_type);
        mMap.put("step", id);
        analyzeJson.requestData(HttpConstant.Get_logsUrl, mMap, REQUEST_SUCCESS);

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_assist_node_note;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                mAssistNodeNotes = gson.fromJson(data.data, new TypeToken<List<AssistNodeNote>>() {
                }.getType());
                mAssistNodeNoteAdapter.setNotifyDataSetChangedList(mAssistNodeNotes);
                for (int i = 0; i < mAssistNodeNoteAdapter.getGroupCount(); i++) {
                    node_note_expandable_listview.expandGroup(i);
                }
                break;
        }
        return super.handleMessage(msg);
    }
}
