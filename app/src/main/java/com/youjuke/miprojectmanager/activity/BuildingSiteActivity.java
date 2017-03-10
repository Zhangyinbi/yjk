package com.youjuke.miprojectmanager.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.ActivityManager;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.Tools;
import com.hbw.library.view.DialogPrompt;
import com.hbw.library.view.TraditionListView;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.BuildingSiteAdapter;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.entity.BuildingSite;
import com.youjuke.miprojectmanager.entity.Version;
import com.youjuke.miprojectmanager.util.HttpConstant;

import java.util.ArrayList;
import java.util.List;

public class BuildingSiteActivity extends MiBaseActivity implements OnClickListener, TextWatcher {
    private static final int VERSIONUPDATE = 11;
    private EditText etBuildSiteSearch;
    private LinearLayout llBuildSiteCancel;
    private LinearLayout llBuildSiteSearchTop;
    private TraditionListView listView_builder;
    private List<BuildingSite> totalList = new ArrayList<BuildingSite>();
    private BuildingSiteAdapter adapter;
    private View v;
    String searchStr = "";
    OnClickListener titleListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.right_igame1_relative:
                    Intent intent = new Intent(BuildingSiteActivity.this,
                            SetActivity.class);
                    intent.putExtra("who", 3);
                    startActivity(intent);
                    break;
                case R.id.right_igame0_relative:
                    Intent intent1 = new Intent(BuildingSiteActivity.this, MessageActivity.class);
                    intent1.putExtra("titleType", "全部消息");
                    startActivity(intent1);
                    break;
                default:
                    break;
            }
        }
    };

    private void load() {
        params.clear();
        params.put("token", HttpConstant.token);
        params.put("key", searchStr);
        analyzeJson.setShowDialog(false);
        analyzeJson.requestData(HttpConstant.ProjectListUrl, params, REQUEST_SUCCESS);
    }

    //检查版本更新
    private void versionUpDate() {
        analyzeJson.setShowDialog(false);
        analyzeJson.requestData(HttpConstant.VersionUpDate, null, VERSIONUPDATE);
    }

    private void upDateVersion(final Version version) {
        int newVersion = Integer.parseInt(version.getVersion_code());
        Log.i("YANGBANG", "当前版本-->" + "version_code=" + Tools.getVersionCode(this) + ",version_name=" + Tools.getVersionName(this));
        Log.i("YANGBANG", "服务器版本-->" + "version_code=" + version.getVersion_code() + ",version_name=" + version.getVersion_name());
        if (newVersion > Tools.getVersionCode(this)) {
            final DialogPrompt dialogPrompt = new DialogPrompt(this);
            dialogPrompt.setPromptTitle("检测到新版本");
            dialogPrompt.setPromptContent(version.getVersion_msg());
            dialogPrompt.setLeftBtnContent("以后再说");
            dialogPrompt.setRightBtnContent("去更新");
            dialogPrompt.setLeftDialogPrompeListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialogPrompt.dismiss();
                }
            });
            dialogPrompt.setRightDialogPrompeListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(version.getApk_source());
                    intent.setData(content_url);
                    startActivity(intent);
                    dialogPrompt.dismiss();
                }
            });
            dialogPrompt.show();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if ((Intent.FLAG_ACTIVITY_CLEAR_TOP & intent.getFlags()) != 0) {
            finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        load();
    }

    @Override
    protected void initWidget() {
        titleBar.setTitleText("在建工地");
        titleBar.setRightImg0Res(R.drawable.news);
        titleBar.setRightText0AlertDotNumBg(R.drawable.warn);
        titleBar.setRightImg0Listener(titleListener);
        titleBar.setRightImg1Listener(titleListener);
        titleBar.setRightImg1Res(R.drawable.set);
        listView_builder = (TraditionListView) findViewById(R.id.listView_builder);
        etBuildSiteSearch = (EditText) findViewById(R.id.et_build_site_search);
        llBuildSiteCancel = (LinearLayout) findViewById(R.id.ll_build_site_cancel);
        llBuildSiteSearchTop = (LinearLayout) findViewById(R.id.ll_build_site_search_top);
        etBuildSiteSearch.setOnClickListener(this);
        etBuildSiteSearch.addTextChangedListener(this);
        llBuildSiteCancel.setOnClickListener(this);
        adapter = new BuildingSiteAdapter(this, totalList);
        // 设置listview为空的时候显示
        v = getLayoutInflater().inflate(R.layout.empty_view, null);
        v.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
//		v.setVisibility(View.GONE);
        ((ViewGroup) listView_builder.getParent()).addView(v);
        listView_builder.setEmptyView(v);
        listView_builder.setAdapter(adapter);
        listView_builder.setDividerHeight(0);
        listView_builder.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Intent intent = new Intent(BuildingSiteActivity.this,
                        PatrolRecordActivity.class);
                intent.putExtra("baoming_id", totalList.get(arg2 - 1).id + "");
                startActivity(intent);
            }
        });

        listView_builder.setOnRefreshListener(new TraditionListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        load();
                    }
                }, 1000);
            }
        });

        versionUpDate();

    }

    @Override
    protected void initData() {
        setisHomeActivity(true);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_buildingsite;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                List<BuildingSite> projects = gson.fromJson(data.data, new TypeToken<List<BuildingSite>>() {
                }.getType());
                if (projects != null
                        && projects.size() > 0) {
                    totalList = projects;
                    adapter.notifyDataSetChanged(totalList);
                    titleBar.setRightText0AlertDotNum(totalList.get(0).totle_msg_count + "");
                } else {
                    if (projects != null) {
                        adapter.notifyDataSetChanged(projects);
                    }
                    ((ViewGroup) listView_builder.getParent()).removeView(v);
                    v.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
                            LayoutParams.MATCH_PARENT));
                    ((TextView) v.findViewById(R.id.textView_dialog))
                            .setText("暂无记录");
                    ((TextView) v.findViewById(R.id.textView_dialog))
                            .setTextSize(25);
                    v.findViewById(R.id.ProgressBar_dialog)
                            .setVisibility(View.GONE);
                    ((ViewGroup) listView_builder.getParent()).addView(v);
                    listView_builder.setEmptyView(v);
                }
                listView_builder.onRefreshComplete();
                break;
            case VERSIONUPDATE:
                ResponseSucceedData data3 = (ResponseSucceedData) msg.obj;
                upDateVersion(gson.fromJson(data3.data, Version.class));
                break;

            default:
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public void onBackPressed() {
        ActivityManager.getActivityManager().exit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.et_build_site_search://点击搜索框
                etBuildSiteSearch.setHint("请输入搜索地址");
                etBuildSiteSearch.setCompoundDrawables(getResources().getDrawable(R.drawable.search), null, null, null);
                llBuildSiteCancel.setVisibility(View.VISIBLE);
                llBuildSiteSearchTop.setVisibility(View.GONE);
                break;
            case R.id.ll_build_site_cancel://取消搜索
                etBuildSiteSearch.setHint(null);
                etBuildSiteSearch.setCompoundDrawables(null, null, null, null);
                etBuildSiteSearch.setText("");
                llBuildSiteCancel.setVisibility(View.GONE);
                llBuildSiteSearchTop.setVisibility(View.VISIBLE);
                Tools.HideKeyBoard(this);
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchStr = s.toString();
        load();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
