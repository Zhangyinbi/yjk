package com.example.yangbang.miowner.activity;

import com.example.yangbang.miowner.R;
import com.hbw.library.BaseActivity;

/**
 * 描述
 *
 * @FileName: com.example.yangbang.miowner.activity.AboutActivity.java
 * @author: Yangbang
 * @date: 2015-12-24 14:47
 */
public class AboutActivity extends BaseActivity {
    @Override
    protected void initWidget() {
        titleBar.setTitleText("关于我们");
        titleBar.setLeftImgDefaultBack(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_about;
    }
}
