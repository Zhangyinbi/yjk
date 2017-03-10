package com.example.yangbang.miowner.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.fragment.FragmentQuestionInfo;
import com.example.yangbang.miowner.fragment.FragmentQuestionProgress;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.BaseFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题描述Activity
 *
 * @FileName: com.example.yangbang.miowner.activity.QuestionDetailActivity.java
 * @author: Yangbang
 * @date: 2016-06-24 11:01
 */
public class QuestionDetailActivity extends BaseFragmentActivity implements View.OnClickListener {
    private LinearLayout llQuestionDetailTab0;
    private TextView tvQuestionDetailTab0;
    private View viewQuestionDetailTabIndex0;
    private LinearLayout llQuestionDetailTab1;
    private TextView tvQuestionDetailTab1;
    private View viewQuestionDetailTabIndex1;
    private FrameLayout flQuestionDetailContent;

    List<Fragment> fragments = new ArrayList<>();
    private FragmentQuestionInfo fragmentQuestionInfo;//问题信息
    private FragmentQuestionProgress fragmentQuestionProgress;//问题处理进度
    String id = "";

    @Override
    protected void initWidget() {
        titleBar.setTitleText("问题详情");
        titleBar.setLeftImgDefaultBack(this);
        id = getIntent().getStringExtra("id");
        assignViews();
        selectedFragment(0);
    }

    private void assignViews() {
        llQuestionDetailTab0 = (LinearLayout) findViewById(R.id.ll_question_detail_tab0);
        tvQuestionDetailTab0 = (TextView) findViewById(R.id.tv_question_detail_tab0);
        viewQuestionDetailTabIndex0 = findViewById(R.id.view_question_detail_tab_index0);
        llQuestionDetailTab1 = (LinearLayout) findViewById(R.id.ll_question_detail_tab1);
        tvQuestionDetailTab1 = (TextView) findViewById(R.id.tv_question_detail_tab1);
        viewQuestionDetailTabIndex1 = findViewById(R.id.view_question_detail_tab_index1);
        flQuestionDetailContent = (FrameLayout) findViewById(R.id.fl_question_detail_content);
        llQuestionDetailTab0.setOnClickListener(this);
        llQuestionDetailTab1.setOnClickListener(this);
    }

    public String getQuestionId() {
        return id;
    }

    private void selectedFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        setIndex(position);
        switch (position) {
            case 0:
                if (fragmentQuestionInfo == null) {
                    fragmentQuestionInfo = new FragmentQuestionInfo();
                    transaction.add(R.id.fl_question_detail_content, fragmentQuestionInfo);
                    fragments.add(fragmentQuestionInfo);
                } else {
                    transaction.show(fragmentQuestionInfo);
                }
                break;
            case 1:
                if (fragmentQuestionProgress == null) {
                    fragmentQuestionProgress = new FragmentQuestionProgress();
                    transaction.add(R.id.fl_question_detail_content, fragmentQuestionProgress);
                    fragments.add(fragmentQuestionProgress);
                } else {
                    transaction.show(fragmentQuestionProgress);
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void hideFragment(FragmentTransaction transaction) {
        for (int i = 0; i < fragments.size(); i++) {
            if (fragments.get(i) != null) {
                transaction.hide(fragments.get(i));
            }
        }
    }

    private void setIndex(int index) {
        switch (index) {
            case 0:
                tvQuestionDetailTab0.setTextColor(getResources().getColor(R.color.red_e76170));
                viewQuestionDetailTabIndex0.setVisibility(View.VISIBLE);
                tvQuestionDetailTab1.setTextColor(getResources().getColor(R.color.gray_959595));
                viewQuestionDetailTabIndex1.setVisibility(View.INVISIBLE);
                break;
            case 1:
                tvQuestionDetailTab1.setTextColor(getResources().getColor(R.color.red_e76170));
                viewQuestionDetailTabIndex1.setVisibility(View.VISIBLE);
                tvQuestionDetailTab0.setTextColor(getResources().getColor(R.color.gray_959595));
                viewQuestionDetailTabIndex0.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_question_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_question_detail_tab0://问题信息
                selectedFragment(0);
                break;
            case R.id.ll_question_detail_tab1://处理进度
                selectedFragment(1);
                break;
        }
    }
}
