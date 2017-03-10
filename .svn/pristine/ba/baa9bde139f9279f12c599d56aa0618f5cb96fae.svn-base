package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.minterface.UserLogOut;
import com.hbw.library.utils.Tools;
import com.hbw.library.view.DialogPrompt;
import com.hbw.library.view.TraditionListView;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.BuildSiteAdapter;
import com.xiaomizhuang.buildcaptain.entity.BuildSite;
import com.xiaomizhuang.buildcaptain.entity.MessageTotal;
import com.xiaomizhuang.buildcaptain.entity.Version;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.HintDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2015/11/19.
 */
public class BuildSiteActivity extends BaseActivity implements View.OnClickListener, TextWatcher {
    public static boolean isActive = false;
    public static final int MESSAGE_TOTAL_FLAG = 10;//消息提醒总数
    public static final int GET_VERSION = 9;//版本更新
    public static final int REFUSE_RESULT = -3;//拒单结果
    BuildSiteAdapter buildSiteAdapter;
    private LinearLayout llBuildSiteSearch;
    private EditText etBuildSiteSearch;
    private LinearLayout llBuildSiteCancel;
    private LinearLayout llBuildSiteSearchTop;
    private TraditionListView lvBuildSiteList;
    private HintDialog hintDialog;
    List<BuildSite> buildSiteList;
    int page = 1;
    int perpage = 5;
    String searchStr = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isActive = true;
    }

    @Override
    protected void initWidget() {
        titleBar.setTitleText("工地详情");
        titleBar.setRightImg0Res(R.mipmap.news);
        titleBar.setRightText0AlertDotNumBg(R.mipmap.warn);
        titleBar.setRightImg0Listener(this);
        titleBar.setLeftImgRes(R.mipmap.ic_mine);
        titleBar.setLeftImgListener(this);
        assignViews();
        if (buildSiteList == null) {
            buildSiteList = new ArrayList<>();
        }
        buildSiteAdapter = new BuildSiteAdapter(this, buildSiteList, titleBar.getRightText0AlertDot());
        lvBuildSiteList.setAdapter(buildSiteAdapter);
        getMessageTotal();
        getBuildSiteList();
        detectionVersion();
    }

    private void assignViews() {
        llBuildSiteSearch = (LinearLayout) findViewById(R.id.ll_build_site_search);
        etBuildSiteSearch = (EditText) findViewById(R.id.et_build_site_search);
        llBuildSiteCancel = (LinearLayout) findViewById(R.id.ll_build_site_cancel);
        llBuildSiteSearchTop = (LinearLayout) findViewById(R.id.ll_build_site_search_top);
        lvBuildSiteList = (TraditionListView) findViewById(R.id.lv_build_site_list);
        etBuildSiteSearch.setOnClickListener(this);
        etBuildSiteSearch.addTextChangedListener(this);
        llBuildSiteCancel.setOnClickListener(this);
        lvBuildSiteList.setOnRefreshListener(new TraditionListView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getBuildSiteList();
            }
        });
        lvBuildSiteList.setOnLoadListener(new TraditionListView.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                getBuildSiteList();
            }
        });
    }


    private void getBuildSiteList() {
        params.clear();
        params.put("uid", MyApplication.UID);
        params.put("token", MyApplication.TOKEN);
        params.put("key", searchStr);
        params.put("page", page + "");
        params.put("perpage", perpage + "");
        analyzeJson.requestData(HttpConstant.GongDiListUrl, params, REQUEST_SUCCESS);
    }

    private void getMessageTotal() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        analyzeJson.requestData(HttpConstant.GetMessageTotalUrl, params, MESSAGE_TOTAL_FLAG);
    }

    private void detectionVersion() {
        analyzeJson.requestData(HttpConstant.GetVersion, null, GET_VERSION);
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
    protected void onRestart() {
        getMessageTotal();
        getBuildSiteList();
        super.onRestart();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                buildSiteList = gson.fromJson(data.data, new TypeToken<List<BuildSite>>() {
                }.getType());
                if (buildSiteList.size() >= perpage) {
                    lvBuildSiteList.setCanLoadMore(true);
                } else {
                    lvBuildSiteList.setCanLoadMore(false);
                }
                if (page > 1) {
                    buildSiteAdapter.notifyDataSetChangedForLoad(buildSiteList);
                    lvBuildSiteList.onLoadMoreComplete();
                } else {
                    buildSiteAdapter.notifyDataSetChanged(buildSiteList);
                    lvBuildSiteList.onRefreshComplete();
                    analyzeJson.setShowDialog(false);
                }
                break;
            case MESSAGE_TOTAL_FLAG:
                ResponseSucceedData data2 = (ResponseSucceedData) msg.obj;
                MessageTotal total = gson.fromJson(data2.data, MessageTotal.class);
                titleBar.setRightText0AlertDotNum(total.getTolotemsgcount() + "");
                break;
            case GET_VERSION:
                ResponseSucceedData data3 = (ResponseSucceedData) msg.obj;
                upDateVersion(gson.fromJson(data3.data, Version.class));
                break;
            case REFUSE_RESULT://拒单
                hintDialog.setConfimEnable(true);
                hintDialog.setCancelable(true);
                if(hintDialog.isShowing()){
                    hintDialog.dismiss();
                }
                //刷新 重新加载
                getBuildSiteList();
                break;
            case ERROR:
            case TIMEOUT:
            case DATAERROR:
                if(null!=hintDialog&&hintDialog.isShowing()){
                    hintDialog.dismiss();
                }
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    protected void initData() {
        setisHomeActivity(true);
        setUserLogOut(new UserLogOut() {
            @Override
            public void logout() {
                finishActivity();
            }
        });
        setIsShowAnimation(false);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_build_site;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.right_igame0_relative://标题栏右侧消息提醒
                Intent intent = new Intent(BuildSiteActivity.this, MessageActivity.class);
                intent.putExtra("titleType", "全部消息");
                startActivity(intent);
                break;
            case R.id.et_build_site_search://点击搜索框
                etBuildSiteSearch.setCursorVisible(true);
                etBuildSiteSearch.setHint("请输入搜索地址");
                etBuildSiteSearch.setCompoundDrawables(getResources().getDrawable(R.drawable.search), null, null, null);
                llBuildSiteCancel.setVisibility(View.VISIBLE);
                llBuildSiteSearchTop.setVisibility(View.GONE);
                break;
            case R.id.ll_build_site_cancel://取消搜索
                etBuildSiteSearch.setCursorVisible(false);
                etBuildSiteSearch.setHint(null);
                etBuildSiteSearch.setCompoundDrawables(null, null, null, null);
                etBuildSiteSearch.setText("");
                llBuildSiteCancel.setVisibility(View.GONE);
                llBuildSiteSearchTop.setVisibility(View.VISIBLE);
                Tools.HideKeyBoard(this);
                break;
            case R.id.left_igame_relative://设置
                Intent it = new Intent(BuildSiteActivity.this, AssistSettingActivity.class);
                startActivity(it);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isActive = false;
        if (hintDialog!=null && hintDialog.isShowing()) {
            hintDialog.dismiss();
            hintDialog = null;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        searchStr = s.toString();
        getBuildSiteList();
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    /**
     * 拒单
     *
     * @param baoming_id
     */
    public void refuse(final String baoming_id, int position) {
        hintDialog = new HintDialog(this);
        hintDialog.setCanceledOnTouchOutside(false);
        hintDialog.setTitleText(buildSiteList.get(position).getRefuse_msg())
                .setDefultCancelListener().setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                params.clear();
                params.put("baoming_id", baoming_id);
                params.put(AppFlag.TOKEN, MyApplication.TOKEN);
                analyzeJson.requestData(HttpConstant.SystemSendOrder, params, REFUSE_RESULT);
                hintDialog.setConfimText("拒单中..");
                hintDialog.setConfimEnable(false);
                hintDialog.setCancleEnable(false);
            }
        });
        hintDialog.show();
    }

}
