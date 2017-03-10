package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.hbw.library.BaseActivity;

/**
 * 发布成功activity
 *
 * @FileName: com.example.yangbang.miowner.activity.PublishSuccessActivity.java
 * @author: Yangbang
 * @date: 2016-06-28 20:31
 */
public class PublishSuccessActivity extends BaseActivity implements View.OnClickListener {
    public static int PUBLISH_QUESTION_SUCCESS = 1;//发布问题成功
    public static int PUBLISH_COMMENT_SUCCESS = 2;//发布评价成功
    private TextView tvPublishSuccessContinue;//继续
    private TextView tvPublishSuccessCheck;//查看
    private int flag = 2;
    public static int backFlag = 0;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("发布成功");
        titleBar.setLeftImgDefaultBack(this);
        assignViews();
        flag = getIntent().getIntExtra("flag", 2);
        if (flag == 1) {
            tvPublishSuccessContinue.setText("继续发布");
            tvPublishSuccessCheck.setText("查看问题");
        } else {
            tvPublishSuccessContinue.setText("继续评价");
            tvPublishSuccessCheck.setText("查看评价");
        }
    }

    private void assignViews() {
        tvPublishSuccessContinue = (TextView) findViewById(R.id.tv_publish_success_continue);
        tvPublishSuccessCheck = (TextView) findViewById(R.id.tv_publish_success_check);
        tvPublishSuccessContinue.setOnClickListener(this);
        tvPublishSuccessCheck.setOnClickListener(this);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_publish_success;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_publish_success_continue:
                finishActivity();
                break;
            case R.id.tv_publish_success_check:
                backFlag = flag;
                Intent intent = new Intent(this, QuestionCommentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
        }
    }
}
