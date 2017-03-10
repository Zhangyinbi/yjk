package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.fragment.FragmentComment;
import com.example.yangbang.miowner.fragment.FragmentQuestion;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.BaseFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 问题评价activity
 *
 * @FileName: com.example.yangbang.miowner.activity.QuestionCommentActivity.java
 * @author: Yangbang
 * @date: 2016-06-23 14:22
 */
public class QuestionCommentActivity extends BaseFragmentActivity implements View.OnClickListener {
    private RelativeLayout imgTitleBarBack;
    private TextView tvTitleBarQuestion;//问题
    private TextView tvTitleBarComment;//评价
    private TextView tvTitleBarAdd;//添加
    private FrameLayout flQuestionCommentContent;

    int tabIndex = 0;
    List<Fragment> fragments = new ArrayList<>();
    FragmentQuestion fragmentQuestion;
    FragmentComment fragmentComment;

    @Override
    protected void initWidget() {
        assignViews();
        setFragmentIndex(0);
    }

    private void assignViews() {
        imgTitleBarBack = (RelativeLayout) findViewById(R.id.img_title_bar_back);
        tvTitleBarQuestion = (TextView) findViewById(R.id.tv_title_bar_question);
        tvTitleBarComment = (TextView) findViewById(R.id.tv_title_bar_comment);
        tvTitleBarAdd = (TextView) findViewById(R.id.tv_title_bar_add);
        flQuestionCommentContent = (FrameLayout) findViewById(R.id.fl_question_comment_content);
        imgTitleBarBack.setOnClickListener(this);
        tvTitleBarQuestion.setOnClickListener(this);
        tvTitleBarComment.setOnClickListener(this);
        tvTitleBarAdd.setOnClickListener(this);
    }

    private void setFragmentIndex(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        setTabIndex(position);
        switch (position) {
            case 0:
                if (fragmentQuestion == null) {
                    fragmentQuestion = new FragmentQuestion();
                    transaction.add(R.id.fl_question_comment_content, fragmentQuestion);
                    fragments.add(fragmentQuestion);
                } else {
                    transaction.show(fragmentQuestion);
                }
                break;
            case 1:
                if (fragmentComment == null) {
                    fragmentComment = new FragmentComment();
                    transaction.add(R.id.fl_question_comment_content, fragmentComment);
                    fragments.add(fragmentComment);
                } else {
                    transaction.show(fragmentComment);
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

    private void setTabIndex(int index) {
        switch (index) {
            case 0:
                tvTitleBarQuestion.setEnabled(false);
                tvTitleBarComment.setEnabled(true);
                break;
            case 1:
                tvTitleBarQuestion.setEnabled(true);
                tvTitleBarComment.setEnabled(false);
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (MyApplication.isPublishSuccess) {
            MyApplication.isPublishSuccess = false;
            if (fragmentQuestion != null) {
                fragmentQuestion.getQuestionListInfo();
            }
            if (fragmentComment != null) {
                fragmentComment.getCommentList();
            }
            if (PublishSuccessActivity.backFlag == 1) {
                setFragmentIndex(0);
            } else {
                setFragmentIndex(1);
            }
        }
    }

    @Override
    protected void initData() {
        setIsShowTitleBar(false);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_question_comment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_title_bar_back:
                onBackPressed();
                break;
            case R.id.tv_title_bar_question://问题
                setFragmentIndex(0);
                break;
            case R.id.tv_title_bar_comment://评价
                setFragmentIndex(1);
                break;
            case R.id.tv_title_bar_add://添加
                startActivity(new Intent(this, AddQuestionOrCommentActivity.class));
                break;
        }
    }
}
