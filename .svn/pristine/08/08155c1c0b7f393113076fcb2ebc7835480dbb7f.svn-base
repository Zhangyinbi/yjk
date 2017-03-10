package com.example.yangbang.miowner.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.fragment.FragmentConstrucProgress;
import com.example.yangbang.miowner.fragment.FragmentEventRemind;
import com.example.yangbang.miowner.fragment.FragmentMe;
import com.example.yangbang.miowner.fragment.SiteManagementFragment;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.PopTelphone;
import com.hbw.library.BaseFragmentActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity implements View.OnClickListener {
    private FrameLayout flMainContainer;
    private LinearLayout llMainTabRoot;
    private LinearLayout llMainConstructionProgress;
    private ImageView imgvMainConstructionProgress;
    private TextView tvMainConstructionProgress;
    private LinearLayout llMainEventRemind;
    private ImageView imgvMainEventRemind;
    private TextView tvMainEventRemind;
    private LinearLayout llMainSiteMonitoring;
    private ImageView imgvMainSiteMonitoring;
    private TextView tvMainSiteMonitoring;
    private LinearLayout llMainMe;
    private ImageView imgvMainMe;
    private TextView tvMainMe;
    List<Fragment> fragments = new ArrayList<>();
    List<ImageView> imageViewsTab = new ArrayList<>();
    List<TextView> textViewsTab = new ArrayList<>();
    FragmentConstrucProgress fragmentConstrucProgress;//施工进度Fragmetn
    FragmentEventRemind fragmentEventRemind;//事项提醒Fragment
    //FragmentSiteMonitoring fragmentSiteMonitoring;//现场监控Fragment
    SiteManagementFragment siteManagementFragment; //工地管理Fragment
    FragmentMe fragmentMe;//我的Fragment
    PopTelphone popTelphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initWidget() {
        titleBar.setRightImg0Res(R.mipmap.phone);
        titleBar.setRightImg0Listener(this);
        assignViews();
        initFragmemt();
    }

    @Override
    protected void initData() {
        setisHomeActivity(true);
    }

    private void initFragmemt() {
        selecterFragment(0);
    }

    /**
     * 传入对应下标显示对应Fragment
     *
     * @param position
     */
    private void selecterFragment(int position) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        tabSelectedColor(position);
        switch (position) {
            case 0:
                if (fragmentConstrucProgress == null) {
                    fragmentConstrucProgress = new FragmentConstrucProgress();
                    transaction.add(R.id.fl_main_container, fragmentConstrucProgress);
                    fragments.add(fragmentConstrucProgress);
                    titleBar.setTitleText("施工进度");
                } else {
                    transaction.show(fragmentConstrucProgress);
                    titleBar.setTitleText("施工进度");
                }
                break;
            case 1:
                if (fragmentEventRemind == null) {
                    fragmentEventRemind = new FragmentEventRemind();
                    transaction.add(R.id.fl_main_container, fragmentEventRemind);
                    fragments.add(fragmentEventRemind);
                    titleBar.setTitleText("事项提醒");
                } else {
                    transaction.show(fragmentEventRemind);
                    titleBar.setTitleText("事项提醒");
                }
                break;
            case 2:
                if (siteManagementFragment == null) {//工地管理
                    siteManagementFragment = new SiteManagementFragment();
                    transaction.add(R.id.fl_main_container, siteManagementFragment);
                    fragments.add(siteManagementFragment);
                    titleBar.setTitleText(getResources().getString(R.string.site_management));
                } else {
                    transaction.show(siteManagementFragment);
                    titleBar.setTitleText(getResources().getString(R.string.site_management));
                }
                break;
            case 3:
                if (fragmentMe == null) {
                    fragmentMe = new FragmentMe();
                    transaction.add(R.id.fl_main_container, fragmentMe);
                    fragments.add(fragmentMe);
                    titleBar.setTitleText("我的");
                } else {
                    transaction.show(fragmentMe);
                    titleBar.setTitleText("我的");
                }
                break;
        }
        transaction.commitAllowingStateLoss();
    }

    private void tabSelectedColor(int position) {
        for (ImageView imageView : imageViewsTab) {
            imageView.setEnabled(true);
        }
        imageViewsTab.get(position).setEnabled(false);
        for (TextView textView : textViewsTab) {
            textView.setTextColor(getResources().getColor(R.color.gray_959595));
        }
        textViewsTab.get(position).setTextColor(getResources().getColor(R.color.red_c30d22));
    }

    private void hideFragment(FragmentTransaction transaction) {
        for (int i = 0; i < fragments.size(); i++) {
            if (fragments.get(i) != null) {
                transaction.hide(fragments.get(i));
            }
        }
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_main;
    }

    private void assignViews() {
        flMainContainer = (FrameLayout) findViewById(R.id.fl_main_container);
        llMainTabRoot = (LinearLayout) findViewById(R.id.ll_main_tab_root);
        llMainConstructionProgress = (LinearLayout) findViewById(R.id.ll_main_construction_progress);
        imgvMainConstructionProgress = (ImageView) findViewById(R.id.imgv_main_construction_progress);
        tvMainConstructionProgress = (TextView) findViewById(R.id.tv_main_construction_progress);
        llMainEventRemind = (LinearLayout) findViewById(R.id.ll_main_event_remind);
        imgvMainEventRemind = (ImageView) findViewById(R.id.imgv_main_event_remind);
        tvMainEventRemind = (TextView) findViewById(R.id.tv_main_event_remind);
        llMainSiteMonitoring = (LinearLayout) findViewById(R.id.ll_main_site_monitoring);
        imgvMainSiteMonitoring = (ImageView) findViewById(R.id.imgv_main_site_monitoring);
        tvMainSiteMonitoring = (TextView) findViewById(R.id.tv_main_site_monitoring);
        llMainMe = (LinearLayout) findViewById(R.id.ll_main_me);
        imgvMainMe = (ImageView) findViewById(R.id.imgv_main_me);
        tvMainMe = (TextView) findViewById(R.id.tv_main_me);
        imageViewsTab.add(imgvMainConstructionProgress);
        imageViewsTab.add(imgvMainEventRemind);
        imageViewsTab.add(imgvMainSiteMonitoring);
        imageViewsTab.add(imgvMainMe);
        textViewsTab.add(tvMainConstructionProgress);
        textViewsTab.add(tvMainEventRemind);
        textViewsTab.add(tvMainSiteMonitoring);
        textViewsTab.add(tvMainMe);
        llMainConstructionProgress.setOnClickListener(this);
        llMainEventRemind.setOnClickListener(this);
        llMainSiteMonitoring.setOnClickListener(this);
        llMainMe.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_main_construction_progress:
                selecterFragment(0);
                break;
            case R.id.ll_main_event_remind:
                selecterFragment(1);
                break;
            case R.id.ll_main_site_monitoring:
                selecterFragment(2);
                break;
            case R.id.ll_main_me:
                selecterFragment(3);
                break;
            case R.id.right_igame0_relative:
                if (popTelphone == null) {
                    popTelphone = new PopTelphone(MainActivity.this, MyApplication.getApp().getOwnerUser());
                }
                popTelphone.showAtLocation(v, Gravity.BOTTOM, 0, 0);
                break;
        }
    }
}
