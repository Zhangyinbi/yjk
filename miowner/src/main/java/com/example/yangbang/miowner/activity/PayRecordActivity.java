package com.example.yangbang.miowner.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.base.MiBaseActivity;
import com.example.yangbang.miowner.entity.PayRecord;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.MoneySimpleFormat;

/**
 * 付款记录Activity
 *
 * @FileName: com.example.yangbang.miowner.activity.PayRecordActivity.java
 * @author: Yangbang
 * @date: 2015-12-21 15:11
 */
public class PayRecordActivity extends MiBaseActivity implements View.OnClickListener {
    private TextView tv_actually_paid_money;//实付总金额
    private TextView tv_payment_actually_paid_money;//期款实付金额
    private TextView tv_add_prepay_actually_paid_money;//增加项预付款
    private TextView tv_refund_actually_paid_money;//退款金额

    private LinearLayout ll_pay_record_pact_money_expandable;//合同金额
    private TextView tv_pay_record_pact_money;
    private ImageView imgv_pay_record_pact_arrow;

    private LinearLayout ll_pay_record_add_money_expandable;//增项金额
    private TextView tv_pay_record_add_money;
    private ImageView imgv_pay_record_add_arrow;

    private LinearLayout ll_refund_money_expandablel;//退款金额
    private TextView tv_refund_money;
    private ImageView imgv_refund_money_arrow;

    private String bm_id = "";


    @Override
    protected void initWidget() {
        titleBar.setTitleText("付款记录");
        titleBar.setLeftImgDefaultBack(this);

        tv_actually_paid_money = (TextView) findViewById(R.id.tv_actually_paid_money);
        tv_payment_actually_paid_money = (TextView) findViewById(R.id.tv_payment_actually_paid_money);
        tv_add_prepay_actually_paid_money = (TextView) findViewById(R.id.tv_add_prepay_actually_paid_money);
        tv_refund_actually_paid_money = (TextView) findViewById(R.id.tv_refund_actually_paid_money);
        ll_pay_record_pact_money_expandable = (LinearLayout) findViewById(R.id.ll_pay_record_pact_money_expandable);
        ll_pay_record_add_money_expandable = (LinearLayout) findViewById(R.id.ll_pay_record_add_money_expandable);
        ll_refund_money_expandablel = (LinearLayout) findViewById(R.id.ll_refund_money_expandable);
        tv_pay_record_pact_money = (TextView) findViewById(R.id.tv_pay_record_pact_money);
        tv_pay_record_add_money = (TextView) findViewById(R.id.tv_pay_record_add_money);
        tv_refund_money = (TextView) findViewById(R.id.tv_refund_money);
        imgv_pay_record_pact_arrow = (ImageView) findViewById(R.id.imgv_pay_record_pact_arrow);
        imgv_pay_record_add_arrow = (ImageView) findViewById(R.id.imgv_pay_record_add_arrow);
        imgv_refund_money_arrow = (ImageView) findViewById(R.id.imgv_refund_money_arrow);

        ll_pay_record_pact_money_expandable.setOnClickListener(this);
        ll_pay_record_add_money_expandable.setOnClickListener(this);
        ll_refund_money_expandablel.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        bm_id = MyApplication.getApp().getOwnerUser().getBaoming_id();
        params.put("token", MyApplication.getApp().getOwnerUser().getToken());
        params.put("baoming_id", bm_id);
        analyzeJson.requestData(HttpConstant.PaymentRecord, params, REQUEST_SUCCESS);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_pay_record;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.ll_pay_record_pact_money_expandable:
                intent.setClass(this, PayRecordPactActivity.class);
                break;
            case R.id.ll_pay_record_add_money_expandable:
                intent.setClass(this, PayRecordAddMoneyActivity.class);
                break;
            case R.id.ll_refund_money_expandable:
                intent.setClass(this, RefundMoneyActivity.class);
                break;

            default:
                break;
        }
        startActivity(intent);
    }

    @Override
    public boolean handleMessage(Message msg) {

        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                PayRecord payRecord = gson.fromJson(data.data, PayRecord.class);
                tv_actually_paid_money.setText("实付金额 : " + MoneySimpleFormat.getMoneyType(payRecord.getTotal_actual_payment()));
                tv_payment_actually_paid_money.setText("期款实付金额 : " + MoneySimpleFormat.getMoneyType(payRecord.getPayment()));
                tv_add_prepay_actually_paid_money.setText("增加项预付金额 : "
                        + MoneySimpleFormat.getMoneyType(payRecord.getTotal_advance_payment()));
                tv_refund_actually_paid_money.setText("退款 : " + MoneySimpleFormat.getMoneyType(payRecord.getTotal_refund_amount()));
                tv_pay_record_add_money.setText("增项金额 : " + MoneySimpleFormat.getMoneyType(payRecord.getZjx_total_price()));
                tv_pay_record_pact_money.setText("合同金额 : " + MoneySimpleFormat.getMoneyType(payRecord.getTotal_fee()));
                tv_refund_money.setText("退款 : " + MoneySimpleFormat.getMoneyType(payRecord.getTotal_refund_amount()));
                //如果退款为0 就让退款项不可点击
                if (payRecord.getTotal_refund_amount() == 0) {
                    ll_refund_money_expandablel.setClickable(false);
                    tv_refund_money.setTextColor(Color.parseColor("#d2d2d2"));
                    imgv_refund_money_arrow.setVisibility(View.INVISIBLE);
                }
                break;

            default:
                break;

        }
        return super.handleMessage(msg);
    }
}

