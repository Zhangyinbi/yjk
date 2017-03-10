package com.youjuke.miprojectmanager.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.base.MiBaseFragmentActivity;
import com.youjuke.miprojectmanager.fragment.MeterialDetailFragment;
import com.youjuke.miprojectmanager.fragment.ProjectAddMatterDetailFragment;

/**
 * Created by mwy on 2016/3/29.
 * 增项金额
 */
public class PayRecordAddMoneyActivity extends MiBaseFragmentActivity implements View.OnClickListener{

    private TextView tv_material_detail_tab;
    private TextView tv_project_add_matter_detail_tab;

    private MeterialDetailFragment mMeterialDetailFragment;
    private ProjectAddMatterDetailFragment mProjectAddMatterDetailFragment;
    private FragmentTransaction ft;

    private String baoming;
    private Bundle bundle;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("增项金额");
        titleBar.setLeftImgDefaultBack(this);

        tv_material_detail_tab = (TextView) findViewById(R.id.tv_material_detail_tab);
        tv_material_detail_tab.setOnClickListener(this);
        tv_project_add_matter_detail_tab = (TextView) findViewById(R.id.tv_project_add_matter_detail_tab);
        tv_project_add_matter_detail_tab.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        baoming = getIntent().getStringExtra("baoming_id");

        bundle = new Bundle();
        bundle.putString("baoming_id",baoming);

        //设置默认选中
        selectedFragmentById(R.id.tv_material_detail_tab);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_pay_record_add_money;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_project_add_matter_detail_tab:
                changeTabId(R.id.tv_project_add_matter_detail_tab);
                break;

            case R.id.tv_material_detail_tab:
                changeTabId(R.id.tv_material_detail_tab);
                break;

            default:
                break;
        }

    }


    /**
     *点击tab 切换效果
     * @param viewId
     */
    private void changeTabId(int viewId){
        ChangeStyle(viewId);
        selectedFragmentById(viewId);
    }

    /***
     * 根据tabid 选择fragment
     * @param viewId
     */
    private void selectedFragmentById(int viewId){
        ft = getSupportFragmentManager().beginTransaction();
        switch (viewId){

            case R.id.tv_material_detail_tab:
                if (mMeterialDetailFragment==null){
                    mMeterialDetailFragment = new MeterialDetailFragment();
                    mMeterialDetailFragment.setArguments(bundle);
                }
                ft.replace(R.id.fl_pay_record_add_money_container, mMeterialDetailFragment);
                break;
            case R.id.tv_project_add_matter_detail_tab:

                if (mProjectAddMatterDetailFragment==null){
                    mProjectAddMatterDetailFragment = new ProjectAddMatterDetailFragment();
                    mProjectAddMatterDetailFragment.setArguments(bundle);
                }
                ft.replace(R.id.fl_pay_record_add_money_container, mProjectAddMatterDetailFragment);
                break;
            default: break;
        }
        ft.commitAllowingStateLoss();
    }
    /***
     * 更改tab样式
     * @param viewId
     */
    private void ChangeStyle(int viewId) {
        if (viewId == R.id.tv_material_detail_tab) {
            tv_material_detail_tab.setTextColor(Color.WHITE);
            tv_material_detail_tab.setBackgroundColor(getResources().getColor(R.color.red_e76270));
            tv_project_add_matter_detail_tab.setTextColor(getResources().getColor(R.color.text_959595));
            tv_project_add_matter_detail_tab.setBackgroundColor(getResources().getColor(R.color.text_d3d3d3));
        } else if (viewId == R.id.tv_project_add_matter_detail_tab){
            tv_material_detail_tab.setTextColor(getResources().getColor(R.color.text_959595));
            tv_material_detail_tab.setBackgroundColor(getResources().getColor(R.color.text_d3d3d3));
            tv_project_add_matter_detail_tab.setTextColor(Color.WHITE);
            tv_project_add_matter_detail_tab.setBackgroundColor(getResources().getColor(R.color.red_e76270));
        }else{
            return;
        }

    }
}
