package com.youjuke.miprojectmanager.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.TextView;

import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.base.MiBaseFragmentActivity;
import com.youjuke.miprojectmanager.fragment.ContractInfoFragment;
import com.youjuke.miprojectmanager.fragment.PaymentDetailFragment;

/**
 * Created by mwy on 2016/3/29.
 * 合同金额
 */
public class PayRecordPactActivity extends MiBaseFragmentActivity implements View.OnClickListener {
    private TextView tv_contract_info;//合同信息
    private TextView tv_payment_detail;//期款明细

    private ContractInfoFragment mContractInfoFragment;
    private PaymentDetailFragment mPaymentDetailFragment;


    private FragmentTransaction ft;
    private String baoming;
    private Bundle bundle;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("合同金额");
        titleBar.setLeftImgDefaultBack(this);

        tv_contract_info = (TextView) findViewById(R.id.tv_contract_info);
        tv_contract_info.setOnClickListener(this);
        tv_payment_detail = (TextView) findViewById(R.id.tv_payment_detail);
        tv_payment_detail.setOnClickListener(this);


    }

    @Override
    protected void initData() {

        baoming = getIntent().getStringExtra("baoming_id");

        bundle = new Bundle();
        bundle.putString("baoming_id",baoming);


        //设置默认选中
        selectedFragmentById(R.id.tv_contract_info);
    }



    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_pay_record_pact;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.tv_contract_info:
                changeTabId(R.id.tv_contract_info);
                break;

            case R.id.tv_payment_detail:
                changeTabId(R.id.tv_payment_detail);
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

            case R.id.tv_contract_info:
                if (mContractInfoFragment==null){
                    mContractInfoFragment = new ContractInfoFragment();
                    mContractInfoFragment.setArguments(bundle);
                }
                ft.replace(R.id.fl_pay_record_pact_container,mContractInfoFragment);
                break;
            case R.id.tv_payment_detail:

                if (mPaymentDetailFragment==null){
                    mPaymentDetailFragment = new PaymentDetailFragment();
                    mPaymentDetailFragment.setArguments(bundle);
                }
                ft.replace(R.id.fl_pay_record_pact_container, mPaymentDetailFragment);
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
        if (viewId == R.id.tv_contract_info) {
            tv_contract_info.setTextColor(Color.WHITE);
            tv_contract_info.setBackgroundColor(getResources().getColor(R.color.red_e76270));
            tv_payment_detail.setTextColor(getResources().getColor(R.color.text_959595));
            tv_payment_detail.setBackgroundColor(getResources().getColor(R.color.text_d3d3d3));
        } else if (viewId == R.id.tv_payment_detail){
            tv_contract_info.setTextColor(getResources().getColor(R.color.text_959595));
            tv_contract_info.setBackgroundColor(getResources().getColor(R.color.text_d3d3d3));
            tv_payment_detail.setTextColor(Color.WHITE);
            tv_payment_detail.setBackgroundColor(getResources().getColor(R.color.red_e76270));
        }else{
            return;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
