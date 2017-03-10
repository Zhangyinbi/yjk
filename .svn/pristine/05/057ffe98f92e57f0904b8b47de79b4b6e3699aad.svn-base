package com.youjuke.miprojectmanager.fragment;

import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.MoneySimpleFormat;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.MeterialDetailAdatper;
import com.youjuke.miprojectmanager.entity.AddMaterial;
import com.youjuke.miprojectmanager.util.HttpConstant;
import com.youjuke.miprojectmanager.view.MyEmptyView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mwy on 2016/3/29.
 */
public class MeterialDetailFragment extends BaseFragment {

    private ListView lvFmMeterialDetail;
    private TextView tv_total_price;        //主材增项总额
    private TextView tv_total_price_pay;       //主材增项实付总额
    private MyEmptyView emptyView;

    private List<AddMaterial> dataList = new ArrayList<AddMaterial>();

    private String baoming;
    private static final int METERIAL_DETAIL_SUCCESS = 0x89;
    private MeterialDetailAdatper adapter;

    @Override
    protected void initWidget() {
        tv_total_price = (TextView) getActivity().findViewById(R.id.tv_total_price);
        tv_total_price_pay = (TextView) getActivity().findViewById(R.id.tv_total_price_pay);

        lvFmMeterialDetail = (ListView) getActivity().findViewById(R.id.lv_fm_meterial_detail);
        adapter = new MeterialDetailAdatper(getActivity(),dataList);
        lvFmMeterialDetail.setAdapter(adapter);


        baoming =  getArguments().getString("baoming_id");
        params.put("token", HttpConstant.token);
        params.put("baoming_id", baoming);
        analyzeJson.requestData(HttpConstant.AddMaterialListUrl, params, METERIAL_DETAIL_SUCCESS);
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragemnt_meterial_detail;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case METERIAL_DETAIL_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                List<AddMaterial> temp = gson.fromJson(data.data,new TypeToken<List<AddMaterial>>(){}.getType());

                if(null!=temp&&temp.size()>0){
                    dataList.clear();
                    dataList.addAll(temp);
                    tv_total_price.setText("主材增项总额 : " + MoneySimpleFormat.getYuanType(dataList.get(0).getTotal_price()));
                    tv_total_price_pay.setText("主材增项实付总额 : "
                            +MoneySimpleFormat.getYuanType(dataList.get(0).getTotal_price_pay()));
                }else{ //如果没有数据
                    ((ViewGroup)tv_total_price.getParent()).setVisibility(View.GONE);
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
        emptyView.setEmptyViewImg(R.drawable.ic_empty_orders);
        emptyView.setEmptyViewContent("暂无数据");
        ((ViewGroup)lvFmMeterialDetail.getParent()).addView(emptyView.getEmptyView());
        lvFmMeterialDetail.setEmptyView(emptyView.getEmptyView());
    }
}
