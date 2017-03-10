package com.example.yangbang.miowner.fragment;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.PaymentDetailAdapter;
import com.example.yangbang.miowner.entity.PaymentDetail;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.MyEmptyView;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwy on 2016/3/29.
 * 付款记录--期款明细
 */
public class PaymentDetailFragment extends BaseFragment {

    private String baoming;
    private static final int PAYMENT_DETAIL_SUCCESS = 0x87;
    private ListView lv_fm_payment_detail;
    private List<PaymentDetail> dataList = new ArrayList<PaymentDetail>();
    private PaymentDetailAdapter adapter;
    private LinearLayout ll_payment_detail_listview_heard;
    private MyEmptyView emptyView;


    @Override
    protected void initWidget() {
        lv_fm_payment_detail = (ListView) getActivity().findViewById(R.id.lv_fm_payment_detail);
        ll_payment_detail_listview_heard = (LinearLayout) getActivity().findViewById(R.id.ll_payment_detail_listview_heard);


        adapter = new PaymentDetailAdapter(this.getActivity(),dataList);
        lv_fm_payment_detail.setAdapter(adapter);

        baoming =  MyApplication.getApp().getOwnerUser().getBaoming_id();
        params.put("token", MyApplication.getApp().getOwnerUser().getToken());
        params.put("baoming_id", baoming);
        analyzeJson.requestData(HttpConstant.PaymentRecordUrl,params,PAYMENT_DETAIL_SUCCESS);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_payment_detail;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case PAYMENT_DETAIL_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                List<PaymentDetail> temp = gson.fromJson(data.data,new TypeToken<List<PaymentDetail>>(){}.getType());

                if(null!=temp||temp.size()>0){
                    dataList.clear();
                    dataList.addAll(temp);
                }else { //如果没有数据
                    ll_payment_detail_listview_heard.setVisibility(View.GONE);
                }

                adapter.notifyDataSetChanged();
                break;
            default:
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public void setEmptyView(Message msg) {
        emptyView = new MyEmptyView(getActivity());
        emptyView.setEmptyViewImg(R.mipmap.ic_empty_orders);
        emptyView.setEmptyViewContent("暂无数据");
        ((ViewGroup)lv_fm_payment_detail.getParent()).addView(emptyView.getEmptyView());
        lv_fm_payment_detail.setEmptyView(emptyView.getEmptyView());
    }
}
