package com.example.yangbang.miowner.fragment;

import android.os.Message;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.QuestionAdapter;
import com.example.yangbang.miowner.entity.Question;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.view.TraditionListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题Fragment
 *
 * @FileName: com.example.yangbang.miowner.fragment.FragmentQuestion.java
 * @author: Yangbang
 * @date: 2016-06-27 16:30
 */
public class FragmentQuestion extends BaseFragment {
    public static final int QUESTION_DEL = 10;
    private TraditionListView lvFragmentQuestion;

    private List<Question> questionList = new ArrayList<>();
    private QuestionAdapter questionAdapter;

    @Override
    protected void initWidget() {
        assignViews();
        initListView();
        getQuestionListInfo();
    }

    private void assignViews() {
        lvFragmentQuestion = (TraditionListView) getView().findViewById(R.id.lv_fragment_question);
    }

    private void initListView() {
        lvFragmentQuestion.setCanRefresh(false);
        lvFragmentQuestion.setCanLoadMore(false);
    }

    public void getQuestionListInfo() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        analyzeJson.requestData(HttpConstant.QuestionList, params, REQUEST_SUCCESS);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_question;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                questionList = gson.fromJson(data.data, new TypeToken<List<Question>>() {
                }.getType());
                if (questionAdapter == null) {
                    questionAdapter = new QuestionAdapter(questionList, getActivity(), analyzeJson);
                    lvFragmentQuestion.setAdapter(questionAdapter);
                } else {
                    questionAdapter.notifyDataSetChanged(questionList);
                }
                break;
            case QUESTION_DEL:
                ToastUtil.show(getActivity(), "成功删除该条问题");
                getQuestionListInfo();
                break;
        }
        return super.handleMessage(msg);
    }
}
