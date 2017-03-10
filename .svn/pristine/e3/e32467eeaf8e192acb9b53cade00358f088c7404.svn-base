package com.example.yangbang.miowner.activity;

import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.adapter.OrderDetailAdapter;
import com.example.yangbang.miowner.entity.OrderDetail;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.view.DialogPrompt;

/**
 * Created by Youjk on 2015/9/6.
 */
public class OrderDetailActivity extends BaseActivity implements View.OnClickListener {
    public static final int GET_ORDER_SUCCESS = 888;
    private final int GET_ORDER_DETAIL = 100;//订单详情
    private final int GET_OK_ORDER = 101;//确认订单

    private ListView lvOrderDetail;//订单详情材料列表
    private RelativeLayout rlOrderDetailFooter;//底部悬浮兰
    private LinearLayout ll_order_detail_footer_count;//底部悬浮兰Linear
    private TextView tv_order_detail_freight;//运费
    private TextView tv_order_detail_material_count;//建材种数
    private TextView tvOrderDetailSubtotalNo;
    private TextView tvOrderDetailSubtotal;//合计总额
    private View headerView;//页眉
    private View footerView;//页脚

    private TextView tvOrderDetailStatus;//订单状态
    private TextView activityOrderConstructCaptainTv;//施工队长
    private TextView activityOrderConstructCaptainPhoneTv;//施工队长电话
    private TextView activityOrderConstructCaptainAddressTv;//施工队长地址
    private TextView activityOrderDesignerTv;//设计师
    private TextView activityOrderDesignerPhoneTv;//设计师电话
    private TextView activityOrderProjectManagerTv;//项目经理
    private TextView activityOrderProjectManagerPhoneTv;//项目经理电话

    private TextView tv_order_detail_footer_total;//合计
    private TextView activityOrderPlaceNumTv;//流水号
    private TextView activityOrderPlaceTimeTv;//下单时间
    private TextView activityOrderEstimateTimeTv;//预计到货时间
    private TextView activityOrderSendTimeTv;//发货时间
    private TextView activityOrderReceiptTimeTv;//收货时间

    private DialogPrompt dialogPrompt;
    private String status = "";
    private String orderID = "";
    private OrderDetail detail;
    private OrderDetailAdapter detailAdapter;


    @Override
    protected void initWidget() {
        status = getIntent().getStringExtra("status");
        orderID = getIntent().getStringExtra("OrderID");
        titleBar.setTitleText("订单详情");
        titleBar.setLeftImgDefaultBack(this);
        assignViews();
        getOrderDetail();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_order_detail;
    }

    private void assignViews() {
        lvOrderDetail = (ListView) findViewById(R.id.lv_order_detail);
//        rlOrderDetailFooter = (RelativeLayout) findViewById(R.id.rl_order_detail_footer);
//        ll_order_detail_footer_count = (LinearLayout) findViewById(R.id.ll_order_detail_footer_count);
//        tv_order_detail_freight = (TextView) findViewById(R.id.tv_order_detail_freight);
//        tv_order_detail_material_count = (TextView) findViewById(R.id.tv_order_detail_material_count);
//        tvOrderDetailSubtotalNo = (TextView) findViewById(R.id.tv_order_detail_subtotal_no);
//        tvOrderDetailSubtotal = (TextView) findViewById(R.id.tv_order_detail_subtotal);
        initHeaderView();
        initFooterView();
//        if (status_id.equals("116")) {
//            tv_order_detail_freight.setVisibility(View.GONE);
//            tv_order_detail_material_count.setVisibility(View.GONE);
//        }
    }


    private void initHeaderView() {
        headerView = LayoutInflater.from(this).inflate(R.layout.order_detail_header, null);
        tvOrderDetailStatus = (TextView) headerView.findViewById(R.id.tv_order_detail_status);
        activityOrderConstructCaptainTv = (TextView) headerView.findViewById(R.id.activity_order_construct_captain_tv);
        activityOrderConstructCaptainPhoneTv = (TextView) headerView.findViewById(R.id.activity_order_construct_captain_phone_tv);
        activityOrderConstructCaptainAddressTv = (TextView) headerView.findViewById(R.id.activity_order_construct_captain_address_tv);
        activityOrderDesignerTv = (TextView) headerView.findViewById(R.id.activity_order_designer_tv);
        activityOrderDesignerPhoneTv = (TextView) headerView.findViewById(R.id.activity_order_designer_phone_tv);
        activityOrderProjectManagerTv = (TextView) headerView.findViewById(R.id.activity_order_project_manager_tv);
        activityOrderProjectManagerPhoneTv = (TextView) headerView.findViewById(R.id.activity_order_project_manager_phone_tv);
        lvOrderDetail.addHeaderView(headerView);
    }

    private void initFooterView() {
        footerView = LayoutInflater.from(this).inflate(R.layout.order_detail_footer, null);
        tv_order_detail_footer_total = (TextView) footerView.findViewById(R.id.tv_order_detail_footer_total);
        activityOrderPlaceNumTv = (TextView) footerView.findViewById(R.id.activity_order_place_num_tv);
        activityOrderPlaceTimeTv = (TextView) footerView.findViewById(R.id.activity_order_place_time_tv);
        activityOrderEstimateTimeTv = (TextView) footerView.findViewById(R.id.activity_order_estimate_time_tv);
        activityOrderSendTimeTv = (TextView) footerView.findViewById(R.id.activity_order_send_time_tv);
        activityOrderReceiptTimeTv = (TextView) footerView.findViewById(R.id.activity_order_receipt_time_tv);
        lvOrderDetail.addFooterView(footerView);
    }


    //取得订单详情
    private void getOrderDetail() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("status", status);
        params.put("OrderID", orderID);
        analyzeJson.requestData(HttpConstant.OrderDetail, params, REQUEST_SUCCESS);
    }

    private void setOrderDetail() {
        if (detail != null) {
            tvOrderDetailStatus.setText("订单状态：" + detail.getStatus_name());
            activityOrderConstructCaptainTv.setText(detail.getBuilder_name());
            activityOrderConstructCaptainPhoneTv.setText(detail.getBuilder_mobile());
            activityOrderConstructCaptainAddressTv.setText(detail.getZx_address());
            activityOrderDesignerTv.setText(detail.getDesigner_name());
            activityOrderDesignerPhoneTv.setText(detail.getDesigner_mobile());
            activityOrderProjectManagerTv.setText(detail.getSuperior_name());
            activityOrderProjectManagerPhoneTv.setText(detail.getSuperior_mobile());
            tv_order_detail_footer_total.setText("共" + detail.getProduct().size() + "种建材 合计：" + detail.getTotalprice() + "元(含运费0元)");
            activityOrderPlaceNumTv.setText(detail.getOrderID());
            activityOrderPlaceTimeTv.setText(detail.getSend_time());
            activityOrderEstimateTimeTv.setText(detail.getExpectedtime());
            activityOrderSendTimeTv.setText(detail.getFahuo_time());
            activityOrderReceiptTimeTv.setText(detail.getShouhuo_time());

            detailAdapter = new OrderDetailAdapter(detail.getProduct(), this);
            lvOrderDetail.setAdapter(detailAdapter);
//            if (status_id.equals("117")) {
//                tv_order_detail_material_count.setText("共" + detail.getProduct().size() + "种建材");
//                if (detailAdapter != null) {
//                    detailAdapter.setDataListIsCheckAll(true);
//                }
//            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        getOrderDetail();
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                detail = gson.fromJson(data.data, OrderDetail.class);
                setOrderDetail();
                break;
        }
        return super.handleMessage(msg);
    }


    @Override
    public void onClick(View v) {
    }

}
