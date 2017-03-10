package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbw.library.BaseActivity;
import com.hbw.library.BaseFragmentActivity;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.fragment.AddMoneyFragment;
import com.xiaomizhuang.buildcaptain.fragment.FragmentConstructScheduling;
import com.xiaomizhuang.buildcaptain.fragment.FragmentMaterialDetail;
import com.xiaomizhuang.buildcaptain.fragment.GatherFragment;
import com.xiaomizhuang.buildcaptain.fragment.GatheringFragment;
import com.xiaomizhuang.buildcaptain.view.PopOrders;

import java.util.ArrayList;
import java.util.List;

/**
 * 工地详情Activity、施工排期+材料明细+扣款记录
 *
 * @FileName: com.xiaomizhuang.buildcaptain.activity.BuildDetailActivity.java
 * @author: Yangbang
 * @date: 2016-01-15 11:16
 */
public class BuildDetailActivity extends BaseFragmentActivity implements View.OnClickListener {
    private FrameLayout flBuildDetailContainer;
    public LinearLayout llBuildDetailTabLeft;
    private LinearLayout llBuildDetailTabConstructionProgress;
    private ImageView imgvBuildDetailTabConstructionProgress;
    private TextView tvBuildDetailTabConstructionProgress;
    private LinearLayout llBuildDetailTabMaterial;
    private ImageView imgvBuildDetailTabMaterial;
    private TextView tvBuildDetailTabMaterial;
    private LinearLayout llBuildDetailTabRecord;
    private ImageView imgvBuildDetailTabRecord;
    private TextView tvBuildDetailTabRecord;

    FragmentConstructScheduling fragmentConstructScheduling;//施工排期
    FragmentMaterialDetail fragmentMaterialDetail;//材料明细
    //FragmentDeductRecord fragmentDeductRecord;//扣款记录
    GatherFragment gatherFragment;//收款查询
    GatheringFragment gatheringFragment;
    List<Fragment> fragments = new ArrayList<>();
    List<ImageView> imageViewsFooltTab = new ArrayList<>();
    List<TextView> textViewsFooltTab = new ArrayList<>();
    PopOrders popOrders;
    private String bm_id;
    private int is_jgstatus;// 1 竣工 0 未竣工
    private int confirm_num;// 1 已收取37款 可以下单     2 未收取37款 不可以下单
    private String ownerPhone;//业主电话
    private String ownerName;//业主姓名
    private AddMoneyFragment addMoneyFragment;
    private FragmentTransaction ft;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("施工排期");
//        titleBar.setLeftImgDefaultBack(this);
        bm_id = getIntent().getExtras().getString("bm_id");
        is_jgstatus = getIntent().getExtras().getInt("is_jgstatus");
        confirm_num = getIntent().getExtras().getInt("confirm_num");
        ownerPhone = getIntent().getStringExtra("ownerPhone");
        ownerName = getIntent().getStringExtra("ownerName");
        assignViews();
        selectedFragmentByIndex(0);
    }

    public String getBm_id() {
        return bm_id;
    }

    private void assignViews() {
        flBuildDetailContainer = (FrameLayout) findViewById(R.id.fl_build_detail_container);
        llBuildDetailTabLeft = (LinearLayout) findViewById(R.id.ll_build_detail_tab_left);
        llBuildDetailTabConstructionProgress = (LinearLayout) findViewById(R.id.ll_build_detail_tab_construction_progress);
        imgvBuildDetailTabConstructionProgress = (ImageView) findViewById(R.id.imgv_build_detail_tab_construction_progress);
        tvBuildDetailTabConstructionProgress = (TextView) findViewById(R.id.tv_build_detail_tab_construction_progress);
        llBuildDetailTabMaterial = (LinearLayout) findViewById(R.id.ll_build_detail_tab_material);
        imgvBuildDetailTabMaterial = (ImageView) findViewById(R.id.imgv_build_detail_tab_material);
        tvBuildDetailTabMaterial = (TextView) findViewById(R.id.tv_build_detail_tab_material);
        llBuildDetailTabRecord = (LinearLayout) findViewById(R.id.ll_build_detail_tab_record);
        imgvBuildDetailTabRecord = (ImageView) findViewById(R.id.imgv_build_detail_tab_record);
        tvBuildDetailTabRecord = (TextView) findViewById(R.id.tv_build_detail_tab_record);
        imageViewsFooltTab.add(imgvBuildDetailTabConstructionProgress);
        imageViewsFooltTab.add(imgvBuildDetailTabMaterial);
        imageViewsFooltTab.add(imgvBuildDetailTabRecord);

        textViewsFooltTab.add(tvBuildDetailTabConstructionProgress);
        textViewsFooltTab.add(tvBuildDetailTabMaterial);
        textViewsFooltTab.add(tvBuildDetailTabRecord);

        llBuildDetailTabConstructionProgress.setOnClickListener(this);
        llBuildDetailTabMaterial.setOnClickListener(this);
        llBuildDetailTabRecord.setOnClickListener(this);
    }


    @Override
    protected void initData() {
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_build_detail;
    }

    public void selectedFragmentByIndex(int index) {
        ft = getSupportFragmentManager().beginTransaction();
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        if (index<3){
            tabSelectedColor(index);
        }
        hideFragment(ft);
        Bundle bundle = new Bundle();
        bundle.putInt("is_jgstatus",is_jgstatus);
	    bundle.putInt("confirm_num",confirm_num);
        bundle.putString("bm_id",bm_id);
        bundle.putString("ownerPhone",ownerPhone);
        bundle.putString("ownerName",ownerName);
        switch (index) {
            case 0://施工排期

                if (fragmentConstructScheduling == null) {
                    fragmentConstructScheduling = new FragmentConstructScheduling();
                    ft.add(R.id.fl_build_detail_container, fragmentConstructScheduling);
                    fragments.add(fragmentConstructScheduling);
                    titleBar.setTitleText("施工排期");
                } else {
                    ft.show(fragmentConstructScheduling);
                    titleBar.setTitleText("施工排期");
                }
                break;
            case 1://材料明细

                if (fragmentMaterialDetail == null) {
                    fragmentMaterialDetail = new FragmentMaterialDetail();
                    ft.add(R.id.fl_build_detail_container, fragmentMaterialDetail);
                    titleBar.setTitleText("材料明细");
                    fragmentMaterialDetail.setArguments(bundle);
                    fragments.add(fragmentMaterialDetail);
                } else {
                    ft.show(fragmentMaterialDetail);
                    titleBar.setTitleText("材料明细");
                }
                break;
            /*case 2://扣款记录
                if (fragmentDeductRecord == null) {
                    fragmentDeductRecord = new FragmentDeductRecord();
                    ft.add(R.id.fl_build_detail_container, fragmentDeductRecord);
                    fragments.add(fragmentDeductRecord);
                    titleBar.setTitleText("扣款记录");
                } else {
                    ft.show(fragmentDeductRecord);
                    titleBar.setTitleText("扣款记录");
                }
                break;*/
            case 2://收款查询
                if (gatherFragment == null){
                    gatherFragment = new GatherFragment();
                    ft.add(R.id.fl_build_detail_container,gatherFragment);
                    fragments.add(gatherFragment);
                }else{
                    ft.show(gatherFragment);
                }
                titleBar.setTitleText("收款查询");
                break;
            case 3:

                titleBar.setTitleText("工期收款");
                if (gatheringFragment == null){
                    gatheringFragment = new GatheringFragment();
                    ft.add(R.id.fl_build_detail_container,gatheringFragment);
                    fragments.add(gatheringFragment);
                }else{
                    ft.show(gatheringFragment);
                }
                break;
            case 4:

                titleBar.setTitleText("材料追加款");
                if (addMoneyFragment == null){
                    addMoneyFragment = new AddMoneyFragment();
                    ft.add(R.id.fl_build_detail_container,addMoneyFragment);
                    fragments.add(addMoneyFragment);
                }else{
                    ft.show(addMoneyFragment);
                }
                break;
        }
        ft.commitAllowingStateLoss();
    }

    private void tabSelectedColor(int position) {
        for (ImageView imageView : imageViewsFooltTab) {
            imageView.setEnabled(true);
        }
        imageViewsFooltTab.get(position).setEnabled(false);
        for (TextView textView : textViewsFooltTab) {
            textView.setTextColor(getResources().getColor(R.color.gray_959595));
        }
        textViewsFooltTab.get(position).setTextColor(getResources().getColor(R.color.red_c30d22));
    }

    private void hideFragment(FragmentTransaction transaction) {
        for (int i = 0; i < fragments.size(); i++) {
            if (fragments.get(i) != null) {
                transaction.hide(fragments.get(i));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_build_detail_tab_construction_progress://施工排期tab
                selectedFragmentByIndex(0);
                break;
            case R.id.ll_build_detail_tab_material://材料明细tab
                selectedFragmentByIndex(1);
                break;
            case R.id.ll_build_detail_tab_record://工期收款tab
                selectedFragmentByIndex(2);
                break;
            case R.id.right_igame0_relative://标题栏右侧消息提醒
                Intent intent = new Intent(this, MessageActivity.class);
                intent.putExtra("titleType", "全部消息");
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onBackPressed() {
            if (gatheringFragment != null&&gatheringFragment.isVisible()){
                llBuildDetailTabLeft.setVisibility(View.VISIBLE);
                titleBar.setTitleText("收款查询");
                ft = getSupportFragmentManager().beginTransaction();
                ft.hide(gatheringFragment);
                ft.show(gatherFragment);
                ft.commitAllowingStateLoss();
            } else if (addMoneyFragment!=null&&addMoneyFragment.isVisible()){
                llBuildDetailTabLeft.setVisibility(View.VISIBLE);
                titleBar.setTitleText("收款查询");
                ft = getSupportFragmentManager().beginTransaction();
                ft.hide(addMoneyFragment);
                ft.show(gatherFragment);
                ft.commitAllowingStateLoss();
            }else {
                super.onBackPressed();
            }
        /*if(getSupportFragmentManager().getBackStackEntryCount() == 0){
            super.onBackPressed();
        }else{
            getSupportFragmentManager().popBackStack();
        }*/

    }
}
