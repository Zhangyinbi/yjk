package com.example.yangbang.miowner.fragment;

import android.os.Message;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.ContractInfo;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.MyEmptyView;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.MoneySimpleFormat;

/**
 * Created by mwy on 2016/3/29.
 * 付款记录--合同信息
 */
public class ContractInfoFragment extends BaseFragment {

    public static final int CONTRACT_INFO_SUCCESS = 0x86;

    private TextView tv_baoming_id;             //业主编号
    private TextView  tv_qy_time;               //签约时间
    private TextView  tv_area;                  //面积
    private TextView  tv_zxtype;                //装修范围
    private TextView  tv_is_preference;         //满70㎡优惠
    private TextView  tv_front_money_fee;       //定金
    private TextView  tv_maintenance_fee;       //管理费
    private TextView  tv_taocan_type;           //套餐选择
    private TextView  tv_main_material_fee;     //预付主材款
    private TextView  tv_design_fee;            //设计费
    private TextView  tv_tax_fee;               //合同开票税金
    private TextView  tv_half_package_fee;      //半包金额
    private TextView  tv_increase_fee;          //预定增项款

    private ScrollView sl_contractInfo;
    private MyEmptyView emptyView;
    private String baoming;

    @Override
    protected void initWidget() {
        tv_baoming_id = (TextView) getActivity().findViewById(R.id.tv_baoming_id);
        tv_qy_time = (TextView) getActivity().findViewById(R.id.tv_qy_time);
        tv_area = (TextView) getActivity().findViewById(R.id.tv_area);
        tv_zxtype = (TextView) getActivity().findViewById(R.id.tv_zxtype);
        tv_is_preference = (TextView) getActivity().findViewById(R.id.tv_is_preference);
        tv_front_money_fee = (TextView) getActivity().findViewById(R.id.tv_front_money_fee);
        tv_maintenance_fee = (TextView) getActivity().findViewById(R.id.tv_maintenance_fee);
        tv_taocan_type = (TextView) getActivity().findViewById(R.id.tv_taocan_type);
        tv_main_material_fee = (TextView) getActivity().findViewById(R.id.tv_main_material_fee);
        tv_design_fee = (TextView) getActivity().findViewById(R.id.tv_design_fee);
        tv_tax_fee = (TextView) getActivity().findViewById(R.id.tv_tax_fee);
        tv_half_package_fee = (TextView) getActivity().findViewById(R.id.tv_half_package_fee);
        tv_increase_fee = (TextView) getActivity().findViewById(R.id.tv_increase_fee);
        sl_contractInfo = (ScrollView) getActivity().findViewById(R.id.sl_contractInfo);


        emptyView = new MyEmptyView(getActivity());
        emptyView.setEmptyViewImg(R.mipmap.ic_empty_orders);
        emptyView.setEmptyViewContent("暂无数据");
        ((ViewGroup)sl_contractInfo.getParent()).addView(emptyView.getEmptyView());

        baoming =  MyApplication.getApp().getOwnerUser().getBaoming_id();
        params.put("token", MyApplication.getApp().getOwnerUser().getToken());
        params.put("baoming_id",baoming);
        analyzeJson.requestData(HttpConstant.ContractInfotUrl,params,CONTRACT_INFO_SUCCESS);

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_contract_info;
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case CONTRACT_INFO_SUCCESS:
                ResponseSucceedData responseSucceedData = (ResponseSucceedData) msg.obj;
                ContractInfo contractInfo = gson.fromJson(responseSucceedData.data, ContractInfo.class);
                if (contractInfo==null||contractInfo.getBaoming_id()==0){
                    sl_contractInfo.setVisibility(ViewGroup.GONE);
                    break;
                }
                tv_baoming_id.setText("业主编号 : "+contractInfo.getBaoming_id());
                tv_qy_time.setText("签约时间 : "+contractInfo.getQy_time());
                tv_area.setText("面积 : "+contractInfo.getArea()+" ㎡");
                tv_zxtype.setText("装修范围 : "+contractInfo.getZxtype());
                tv_is_preference.setText("满70㎡优惠 : "+contractInfo.getIs_preference());
                tv_front_money_fee.setText("定金 : "+ MoneySimpleFormat.getMoneyType(contractInfo.getFront_money_fee()));
                tv_maintenance_fee.setText("管理费 : "+MoneySimpleFormat.getMoneyType(contractInfo.getMaintenance_fee()));
                tv_taocan_type.setText("套餐选择 : "+contractInfo.getTaocan_type());
                tv_main_material_fee.setText("预付主材款 : "+MoneySimpleFormat.getMoneyType(contractInfo.getMain_material_fee()));
                tv_design_fee.setText("设计费 : "+MoneySimpleFormat.getMoneyType(contractInfo.getDesign_fee()));
                tv_tax_fee.setText("合同开票税金 : "+MoneySimpleFormat.getMoneyType(contractInfo.getTax_fee()));
                tv_half_package_fee.setText("半包金额 : "+MoneySimpleFormat.getMoneyType(contractInfo.getHalf_package_fee()));
                tv_increase_fee.setText("预定增项款 : "+MoneySimpleFormat.getMoneyType(contractInfo.getIncrease_fee()));
                break;

            default:
                break;
        }
        return super.handleMessage(msg);
    }
}
