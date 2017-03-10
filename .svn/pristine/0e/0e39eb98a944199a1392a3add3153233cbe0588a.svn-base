package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.fragment.FragmentAddComment;
import com.example.yangbang.miowner.fragment.FragmentAddQuestion;
import com.hbw.library.BaseFragmentActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 添加问题与评论Activity
 *
 * @FileName: com.example.yangbang.miowner.activity.AddQuestionOrCommentActivity.java
 * @author: Yangbang
 * @date: 2016-06-24 17:14
 */
public class AddQuestionOrCommentActivity extends BaseFragmentActivity implements View.OnClickListener {
    private LinearLayout llAddQuestionCommentTab0;//问题linear
    private TextView tvAddQuestionCommentTab0;//问题
    private View viewAddQuestionCommentIndex0;//问题下标指示器
    private LinearLayout llAddQuestionCommentTab1;//评价linear
    private TextView tvAddQuestionCommentTab1;//评价
    private View viewAddQuestionCommentIndex1;//评价下标指示器
    private FrameLayout flAddQuestionCommentContent;

    int tabIndex = 0;
    List<Fragment> fragments = new ArrayList<>();
    private FragmentAddQuestion fragmentAddQuestion;//添加问题fragment
    private FragmentAddComment fragmentAddComment;//添加评论fragment

    @Override
    protected void initWidget() {
        titleBar.setTitleText("添加问题与评论");
        titleBar.setLeftImgDefaultBack(this);
        titleBar.setRightText0("发布");
        titleBar.setRightText0Listener(this);
        assignViews();
        selectedFragment(0);
    }

    private void assignViews() {
        llAddQuestionCommentTab0 = (LinearLayout) findViewById(R.id.ll_add_question_comment_tab0);
        tvAddQuestionCommentTab0 = (TextView) findViewById(R.id.tv_add_question_comment_tab0);
        viewAddQuestionCommentIndex0 = findViewById(R.id.view_add_question_comment_index0);
        llAddQuestionCommentTab1 = (LinearLayout) findViewById(R.id.ll_add_question_comment_tab1);
        tvAddQuestionCommentTab1 = (TextView) findViewById(R.id.tv_add_question_comment_tab1);
        viewAddQuestionCommentIndex1 = findViewById(R.id.view_add_question_comment_index1);
        flAddQuestionCommentContent = (FrameLayout) findViewById(R.id.fl_add_question_comment_content);
        llAddQuestionCommentTab0.setOnClickListener(this);
        llAddQuestionCommentTab1.setOnClickListener(this);
    }

    private void selectedFragment(int position) {
        tabIndex = position;
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        setIndex(position);
        switch (position) {
            case 0:
                if (fragmentAddQuestion == null) {
                    fragmentAddQuestion = new FragmentAddQuestion();
                    transaction.add(R.id.fl_add_question_comment_content, fragmentAddQuestion);
                    fragments.add(fragmentAddQuestion);
                } else {
                    transaction.show(fragmentAddQuestion);
                }
                break;
            case 1:
                if (fragmentAddComment == null) {
                    fragmentAddComment = new FragmentAddComment();
                    transaction.add(R.id.fl_add_question_comment_content, fragmentAddComment);
                    fragments.add(fragmentAddComment);
                } else {
                    transaction.show(fragmentAddComment);
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
                tvAddQuestionCommentTab0.setTextColor(getResources().getColor(R.color.red_e76170));
                viewAddQuestionCommentIndex0.setVisibility(View.VISIBLE);
                tvAddQuestionCommentTab1.setTextColor(getResources().getColor(R.color.gray_959595));
                viewAddQuestionCommentIndex1.setVisibility(View.INVISIBLE);
                break;
            case 1:
                tvAddQuestionCommentTab1.setTextColor(getResources().getColor(R.color.red_e76170));
                viewAddQuestionCommentIndex1.setVisibility(View.VISIBLE);
                tvAddQuestionCommentTab0.setTextColor(getResources().getColor(R.color.gray_959595));
                viewAddQuestionCommentIndex0.setVisibility(View.INVISIBLE);
                break;
        }
    }

    public void submitComment() {
        fragmentAddComment.submitComment();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (tabIndex == 0) {
            if (fragmentAddQuestion != null) {
                fragmentAddQuestion.myFragmentOnResume();
            }
        } else {
            if (fragmentAddComment != null) {
                fragmentAddComment.myFragmentOnResume();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (tabIndex == 0) {
            if (fragmentAddQuestion != null) {
                fragmentAddQuestion.myFragmentOnActivityResult(requestCode, resultCode, data);
            }
        } else {
            if (fragmentAddComment != null) {
                fragmentAddComment.myFragmentOnActivityResult(requestCode, resultCode, data);
            }
        }
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_add_question_comment;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_add_question_comment_tab0://问题
                selectedFragment(0);
                break;
            case R.id.ll_add_question_comment_tab1://评论
                selectedFragment(1);
                break;
            case R.id.right_text0://发布
                if (tabIndex == 0) {//代表发布问题
                    fragmentAddQuestion.submitQuestion();
                } else {//发布评论
                    fragmentAddComment.submitComment();
                }
                break;
        }
    }
}
