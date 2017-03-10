package com.youjuke.miprojectmanager.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RelativeLayout;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.view.TraditionListView;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.PatrolRecordAdapter;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.entity.PatrolRecord;
import com.youjuke.miprojectmanager.util.HttpConstant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 当前项目巡查记录数据列表页
 *
 * @author Youjk
 */
public class PatrolRecordActivity extends MiBaseActivity {
    private TraditionListView listView_landclearing;
    private RelativeLayout layout_popwindow;
    private PatrolRecordAdapter adapter;
    private List<PatrolRecord> list = new ArrayList<PatrolRecord>();
    private View view_empty = null;
    String baoming_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        load(false);
    }

    private void load(boolean isShowProgressDialog) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("token", HttpConstant.token);
        map.put("baoming_id", baoming_id);
        analyzeJson.setShowDialog(isShowProgressDialog);
        analyzeJson.requestData(HttpConstant.PatrolRecordListUrl, map,
                REQUEST_SUCCESS);
    }

    protected String getStringFromSP(String spName, String parameter) {
        return getSharePre(spName).getString(parameter, "");
    }

    private SharedPreferences getSharePre(String spName) {
        return getSharedPreferences(spName, MODE_PRIVATE);
    }

    OnClickListener titleListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.right_igame0_relative: // 跳转到新增界面
                    Intent intent = new Intent(PatrolRecordActivity.this,
                            PatrolRecordAddActivity.class);
                    intent.putExtra("baoming_id", baoming_id);
                    startActivity(intent);
                    break;
                case R.id.right_igame1_relative:
                    Intent intent2 = new Intent(PatrolRecordActivity.this,
                            ContactsActivity.class);
                    intent2.putExtra("baoming_id", baoming_id);
                    startActivity(intent2);
                    break;
                case R.id.right_igame2_relative:// 跳转到验收单界面
                    Intent checkBillIntent = new Intent(PatrolRecordActivity.this, CheckBillActivity.class);
                    checkBillIntent.putExtra("baoming_id", baoming_id);
                    startActivity(checkBillIntent);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void initWidget() {
        baoming_id = getIntent().getStringExtra("baoming_id");
        titleBar.setTitleText("巡查记录");
        titleBar.setLeftImgDefaultBack(this);
        titleBar.setRightImg0Res(R.drawable.add);
        titleBar.setRightImg0Listener(titleListener);
        titleBar.setRightImg1Res(R.drawable.ic_contacts);
        titleBar.setRightImg1Listener(titleListener);
        titleBar.setRightImg2Res(R.drawable.ic_bill);
        titleBar.setRightImg2Listener(titleListener);
        listView_landclearing = (TraditionListView) findViewById(R.id.listView_landclearing);
        layout_popwindow = (RelativeLayout) findViewById(R.id.layout_project_pop);
        adapter = new PatrolRecordAdapter(this, list);
        listView_landclearing.setAdapter(adapter);
        listView_landclearing.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getApplicationContext(),
                        PatrolRecordDetailsActivity.class);
                intent.putExtra("pro_post_id", list.get(position - 1).id + "");
                startActivity(intent);
            }
        });

        listView_landclearing.setOnRefreshListener(new TraditionListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        load(false);
                    }
                }, 1000);
            }
        });

        load(true);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_patrol_record;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                List<PatrolRecord> patrolRecords = gson.fromJson(data.data, new TypeToken<List<PatrolRecord>>() {
                }.getType());
                if (patrolRecords != null
                        && patrolRecords.size() > 0) {
                    list.clear();
                    list.addAll(patrolRecords);
                    adapter.notifyDataSetChanged(list);
                } else if (listView_landclearing.getEmptyView() == null) {
                    view_empty = getLayoutInflater().inflate(R.layout.land_empty,
                            null);
                    view_empty.setLayoutParams(new LayoutParams(
                            LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
                    ((ViewGroup) listView_landclearing.getParent())
                            .addView(view_empty);
                    listView_landclearing.setEmptyView(view_empty);
                }
                listView_landclearing.onRefreshComplete();
                break;

            default:
                break;
        }
        return super.handleMessage(msg);
    }

}
