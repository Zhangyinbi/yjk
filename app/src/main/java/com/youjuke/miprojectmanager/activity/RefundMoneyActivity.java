package com.youjuke.miprojectmanager.activity;

import android.os.Message;
import android.widget.ListView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.entity.ResponseSucceedData;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.RefundMoneyAdapter;
import com.youjuke.miprojectmanager.base.MiBaseActivity;
import com.youjuke.miprojectmanager.entity.RefundAmount;
import com.youjuke.miprojectmanager.util.HttpConstant;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by mwy on 2016/3/29.
 * 退款金额
 */
public class RefundMoneyActivity extends MiBaseActivity {

    private ListView lvRefundMoney;
    private RefundMoneyAdapter adapter;
    private List<RefundAmount> dataList = new LinkedList<RefundAmount>();
    private String baoming;
    private static final int REFUND_MONEY_SUCCESS = 0x91;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("退款金额");
        titleBar.setLeftImgDefaultBack(this);

        lvRefundMoney = (ListView) findViewById(R.id.lv_refund_money);

        adapter = new RefundMoneyAdapter(this, dataList);
        lvRefundMoney.setAdapter(adapter);

        baoming = getIntent().getStringExtra("baoming_id");
        params.put("token", HttpConstant.token);
        params.put("baoming_id", baoming);
        analyzeJson.requestData(HttpConstant.RefundAmountListUrl, params, REFUND_MONEY_SUCCESS);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_refund_money;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {

            case REFUND_MONEY_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                List<RefundAmount> temp = gson.fromJson(data.data, new TypeToken<List<RefundAmount>>() {
                }.getType());
                //如果没有数据
                if(null==temp||temp.size()==0){
                    break;
                }
                dataList.clear();
                dataList.addAll(temp);
                adapter.notifyDataSetChanged();
                break;

            default:
                break;

        }

        return super.handleMessage(msg);
    }
}
