package com.example.yangbang.miowner.activity;

import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.SupervisionReportAdapter;
import com.example.yangbang.miowner.entity.Report;
import com.example.yangbang.miowner.entity.SupervisionReportPropmt;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.MyEmptyView;
import com.example.yangbang.miowner.view.PopTelphone;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.view.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 节点验收Activity
 *
 * @FileName: com.example.yangbang.miowner.activity.SupervisionReportActivity.java
 * @author: Yangbang
 * @date: 2015-12-18 17:53
 */
public class SupervisionReportActivity extends BaseActivity implements View.OnClickListener {
    public static final int SUPERVISION_REPORT_STATUS = 50;
    public static final int SUPERVISION_REPORT_LIST = 51;
    TabLayout tl_spv_re_tab_layout;
    ListView lv_supervision_report;
    List<Report> reports = new ArrayList<>();
    SupervisionReportAdapter supervisionReportAdapter;
    PopTelphone popTelphone;
    private MyEmptyView emptyView;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("节点验收");
        titleBar.setLeftImgDefaultBack(this);
        titleBar.setRightImg0Res(R.mipmap.phone);
        titleBar.setRightImg0Listener(this);
        tl_spv_re_tab_layout = (TabLayout) this.findViewById(R.id.tl_spv_re_tab_layout);
        lv_supervision_report = (ListView) this.findViewById(R.id.lv_supervision_report);
        supervisionReportAdapter = new SupervisionReportAdapter(reports, this);
        lv_supervision_report.setAdapter(supervisionReportAdapter);
        initTabLayout();
        //getSupervisionReportStatus();
        tl_spv_re_tab_layout.setCurrentPosition(0);
    }

    private void initTabLayout() {
        tl_spv_re_tab_layout.newTabItem("交底", 0);
        tl_spv_re_tab_layout.newTabItem("水电", 1);
        tl_spv_re_tab_layout.newTabItem("泥木", 2);
        tl_spv_re_tab_layout.newTabItem("油漆", 3);
        tl_spv_re_tab_layout.newTabItem("竣工", 4);
        tl_spv_re_tab_layout.newTabItem("增补", 5);
        /*tl_spv_re_tab_layout.setTabMsgRemind(4, 8);*/
        tl_spv_re_tab_layout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                getSupervisionReportList(position);
            }
        });
    }

    private void getSupervisionReportStatus() {
        params.clear();
        params.put("token", MyApplication.getApp().getOwnerUser().getToken());
        analyzeJson.requestData(HttpConstant.SUPERVISION_REPORT_PROMPT, params, SUPERVISION_REPORT_STATUS);
    }

    private void getSupervisionReportList(int type) {
        params.clear();
        params.put("token", MyApplication.getApp().getOwnerUser().getToken());
        params.put("type", type + "");
        analyzeJson.requestData(HttpConstant.SUPERVISIONREPORT_LIST, params, SUPERVISION_REPORT_LIST);
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_supervision_report;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case SUPERVISION_REPORT_STATUS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                List<SupervisionReportPropmt> supervisionReportPropmts = gson.fromJson(data.data, new TypeToken<List<SupervisionReportPropmt>>() {
                }.getType());
                setTabPropmtCount(supervisionReportPropmts);
                break;
            case SUPERVISION_REPORT_LIST:
                ResponseSucceedData data2 = (ResponseSucceedData) msg.obj;
                reports = gson.fromJson(data2.data, new TypeToken<List<Report>>() {
                }.getType());
                supervisionReportAdapter.notifyDataSetChanged(reports);
                break;
        }
        return super.handleMessage(msg);
    }

    /**
     * 设置消息Tab提醒数量
     *
     * @param supervisionReportPropmts
     */
    private void setTabPropmtCount(List<SupervisionReportPropmt> supervisionReportPropmts) {
        for (int i = 0; i < supervisionReportPropmts.size(); i++) {
            tl_spv_re_tab_layout.setTabMsgRemind(i, supervisionReportPropmts.get(i).getCount());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_igame0_relative:
                if (popTelphone == null) {
                    popTelphone = new PopTelphone(SupervisionReportActivity.this, MyApplication.getApp().getOwnerUser());
                }
                popTelphone.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
        }
    }

    @Override
    public void setEmptyView(Message msg) {
        emptyView = new MyEmptyView(this);
        emptyView.setEmptyViewImg(R.mipmap.noreport);
        emptyView.setEmptyViewContent("暂无数据");
        ((ViewGroup)lv_supervision_report.getParent()).addView(emptyView.getEmptyView());
        lv_supervision_report.setEmptyView(emptyView.getEmptyView());
    }
}
