package com.example.yangbang.miowner.fragment;

import android.os.Message;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.activity.QuestionDetailActivity;
import com.example.yangbang.miowner.adapter.QuestionHanlderProgressAdapter;
import com.example.yangbang.miowner.entity.QuestionProgress;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.view.TraditionListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题处理进度
 *
 * @FileName: com.example.yangbang.miowner.fragment.FragmentQuestionProgress.java
 * @author: Yangbang
 * @date: 2016-06-27 17:16
 */
public class FragmentQuestionProgress extends BaseFragment {
    private TraditionListView lv_fragment_handler_progress;
    private QuestionHanlderProgressAdapter progressAdapter;
    private List<QuestionProgress> progressList = new ArrayList<>();

    @Override
    protected void initWidget() {
        lv_fragment_handler_progress = (TraditionListView) getView().findViewById(R.id.lv_fragment_handler_progress);
        setTraditionListView();
        getHandlerProgress();
    }

    private void setTraditionListView() {
        lv_fragment_handler_progress.setCanLoadMore(false);
        lv_fragment_handler_progress.setCanRefresh(false);
    }

    private void getHandlerProgress() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("id", ((QuestionDetailActivity) getActivity()).getQuestionId());
        analyzeJson.requestData(HttpConstant.QuestionHandlerProgress, params, REQUEST_SUCCESS);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_handler_progress;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                progressList = gson.fromJson(data.data, new TypeToken<List<QuestionProgress>>() {
                }.getType());
                if (progressAdapter == null) {
                    progressAdapter = new QuestionHanlderProgressAdapter(progressList, getActivity());
                    lv_fragment_handler_progress.setAdapter(progressAdapter);
                } else {
                    progressAdapter.notifyDataSetChanged(progressList);
                }
                break;
        }
        return super.handleMessage(msg);
    }
}
