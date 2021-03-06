package com.xiaomizhuang.buildcaptain.activity;

import android.graphics.Color;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AllOrdersAdapter;
import com.xiaomizhuang.buildcaptain.adapter.AssistTypeOrderAdapter;
import com.xiaomizhuang.buildcaptain.entity.MaterialType;
import com.xiaomizhuang.buildcaptain.entity.Orders;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.MyEmptyView;
import com.xiaomizhuang.buildcaptain.view.PopMaterialType;
import com.xiaomizhuang.buildcaptain.view.PopSelectDate;

import java.util.ArrayList;
import java.util.List;

/**
 * 材料明细Fragment
 *
 * @FileName: com.xiaomizhuang.buildcaptain.fragment.FragmentMaterialDetail.java
 * @author: Yangbang
 * @date: 2016-01-15 11:48
 */
public class MaterialOrderDetailActivity extends BaseActivity implements View.OnClickListener, AssistTypeOrderAdapter.OnTypeClicklistener, PopSelectDate.OnAffirmDataListener {
    private static final int GET_ASSIST_MATERIRL_TYPE = 90;//辅材材料类型
    private static final int GET_MAIN_MATERIRL_TYPE = 92;//主材材料类型
    private static final int GET_ORDERS_DETAIL = 91;//订单明细
    private static final int GET_ORDERS_TIME_QUERY = 93;//时间筛选
    private LinearLayout llFmMaterialDetailHeaderAssist;
    private LinearLayout llFmMaterialDetailHeaderAssistType;
    private TextView tvFmMaterialDetailHeaderAssist;
    private ImageView imgvFmMaterialDetailHeaderAssist;
    private LinearLayout llFmMaterialDetailHeaderAssistTime;
    private TextView tvFmMaterialDetailHeaderAssistTime;
    private ImageView imgvFmMaterialDetailHeaderAssistTime;
    private LinearLayout llFmMaterialDetailHeaderMainType;
    private TextView tvFmMaterialDetailHeaderMainType;
    private ImageView imgvFmMaterialDetailHeaderMainType;
    private LinearLayout llFmMaterialDetailHeaderAssistMoney;
    private TextView tvFmMaterialDetailHeaderAssistMoney;
    private TextView tvFmMaterialDetailHeaderAssistOkMoney;
    private ListView lvFmMaterialDetail;

    private AllOrdersAdapter ordersAdapter;
    private List<Orders> ordersList = new ArrayList<>();
    private PopSelectDate popSelectDate;
    private PopMaterialType popAssistMaterialType;
    private PopMaterialType popMainMaterialType;
    private int materialType = 2;//材料类型,2代表辅材，1代表主材
    private String materialTypeId = "0";//材料类型id
    private String filterDate = "";//筛选日期
    private String bm_id;
    private MyEmptyView emptyView;
    private List<MaterialType> materialTypes=null;
    private TextView tvHint;

    @Override
    protected void initWidget() {
        bm_id = getIntent().getStringExtra("bm_id");
        materialType = getIntent().getIntExtra("materialType", 2);
        if (materialType == 1) {
            titleBar.setTitleText("主材明细");
        } else {
            titleBar.setTitleText("辅材明细");
        }
        titleBar.setLeftImgDefaultBack(this);
        assignViews();
        getAssistMaterialType();
        getMainMaterialType();
        setMaterialType(materialType);
    }

    @Override
    protected void initData() {

    }

    private void assignViews() {
        llFmMaterialDetailHeaderAssist = (LinearLayout) findViewById(R.id.ll_fm_material_detail_header_assist);
        llFmMaterialDetailHeaderAssistType = (LinearLayout) findViewById(R.id.ll_fm_material_detail_header_assist_type);
        tvFmMaterialDetailHeaderAssist = (TextView) findViewById(R.id.tv_fm_material_detail_header_assist);
        imgvFmMaterialDetailHeaderAssist = (ImageView) findViewById(R.id.imgv_fm_material_detail_header_assist);
        llFmMaterialDetailHeaderAssistTime = (LinearLayout) findViewById(R.id.ll_fm_material_detail_header_assist_time);
        tvFmMaterialDetailHeaderAssistTime = (TextView) findViewById(R.id.tv_fm_material_detail_header_assist_time);
        imgvFmMaterialDetailHeaderAssistTime = (ImageView) findViewById(R.id.imgv_fm_material_detail_header_assist_time);
        llFmMaterialDetailHeaderMainType = (LinearLayout) findViewById(R.id.ll_fm_material_detail_header_main_type);
        tvFmMaterialDetailHeaderMainType = (TextView) findViewById(R.id.tv_fm_material_detail_header_main_type);
        imgvFmMaterialDetailHeaderMainType = (ImageView) findViewById(R.id.imgv_fm_material_detail_header_main_type);
        llFmMaterialDetailHeaderAssistMoney = (LinearLayout) findViewById(R.id.ll_fm_material_detail_header_assist_money);
        tvFmMaterialDetailHeaderAssistMoney = (TextView) findViewById(R.id.tv_fm_material_detail_header_assist_money);
        tvFmMaterialDetailHeaderAssistOkMoney = (TextView) findViewById(R.id.tv_fm_material_detail_header_assist_ok_money);
        lvFmMaterialDetail = (ListView) findViewById(R.id.lv_fm_material_detail);
        tvHint = (TextView) findViewById(R.id.tv_hint);
        imgvFmMaterialDetailHeaderMainType.setColorFilter(Color.BLACK);
        llFmMaterialDetailHeaderAssistType.setOnClickListener(this);
        llFmMaterialDetailHeaderAssistTime.setOnClickListener(this);
        llFmMaterialDetailHeaderMainType.setOnClickListener(this);
        ordersAdapter = new AllOrdersAdapter(this, ordersList, analyzeJson);
        lvFmMaterialDetail.setAdapter(ordersAdapter);

    }
    public String getBm_id() {
        return bm_id;
    }

    /**
     * 取得辅材类型
     */
    private void getAssistMaterialType() {
        params.clear();
        params.put("uid", MyApplication.UID);
        params.put("token", MyApplication.TOKEN);
        analyzeJson.requestData(HttpConstant.DaLeiUrl, params, GET_ASSIST_MATERIRL_TYPE);
    }

    /**
     * 取得主材类型
     */
    private void getMainMaterialType() {
        params.clear();
        params.put("uid", MyApplication.UID);
        params.put("token", MyApplication.TOKEN);
        analyzeJson.requestData(HttpConstant.MainMaterialType, params, GET_MAIN_MATERIRL_TYPE);
    }

    /**
     * 取得材料订单列表
     */
    private void getMaterialOrderList() {
        params.clear();
        params.put("token", MyApplication.TOKEN);
        params.put("number", materialTypeId + "");
        params.put("bm_id", bm_id);
        params.put("orderdate", filterDate + "");
        params.put("m_type", materialType + "");
        analyzeJson.requestData(HttpConstant.OrderPlanDetailUrl, params, GET_ORDERS_DETAIL);
    }

    /**
     * 设置材料类型
     *
     * @param type 2代表辅材，1代表主材
     */
    public void setMaterialType(int type) {
        this.materialType = type;
        if (type == 1) {
            llFmMaterialDetailHeaderAssist.setVisibility(View.GONE);
            llFmMaterialDetailHeaderAssistMoney.setVisibility(View.GONE);
            llFmMaterialDetailHeaderMainType.setVisibility(View.VISIBLE);
        } else if (type == 2) {
            llFmMaterialDetailHeaderAssist.setVisibility(View.VISIBLE);
            llFmMaterialDetailHeaderAssistMoney.setVisibility(View.VISIBLE);
            llFmMaterialDetailHeaderMainType.setVisibility(View.GONE);
        }
        getMaterialOrderList();
    }


    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_material_order_detail;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_fm_material_detail_header_assist_type://辅材类型
//                llFmMaterialDetailHeaderAssistType.setEnabled(false);
                if (popAssistMaterialType == null){
                    ToastUtil.show(this,"请稍后");
                   break;
                }
                popAssistMaterialType.showAsDropDown(v);
                setPopuWindow(popAssistMaterialType);//解决两个PopupWindow切换问题
                imgvFmMaterialDetailHeaderAssist.setImageResource(R.mipmap.ic_build_greyarrowup);
                break;
            case R.id.ll_fm_material_detail_header_assist_time://辅材时间筛选
                if (popSelectDate == null) {
                    popSelectDate = new PopSelectDate(this, imgvFmMaterialDetailHeaderAssistTime);
                    popSelectDate.setOnAffirmDataListener(this);
                }
                setPopuWindow(popSelectDate);//解决两个PopupWindow切换问题
                popSelectDate.showAsDropDown(v);
                imgvFmMaterialDetailHeaderAssistTime.setImageResource(R.mipmap.ic_build_greyarrowup);
                break;
            case R.id.ll_fm_material_detail_header_main_type://主材类型
                if (null==materialTypes||materialTypes.size()==0){
                    ToastUtil.show(this,"请重试");
                }else {
                popMainMaterialType.showAsDropDown(v);
                imgvFmMaterialDetailHeaderMainType.setImageResource(R.mipmap.ic_build_greyarrowup);
                imgvFmMaterialDetailHeaderMainType.setColorFilter(Color.BLACK);
                }
                break;
        }
    }

    /**
     * @param popupwindow
     *onTouch返回false表示事件不拦截，点击外部区域popupwindow小时
     */
    private void setPopuWindow(PopupWindow popupwindow) {
        popupwindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                checkPointInCtrlBtn(event);//强制让被点击的视图捕获点击事件
                return false;
            }
        });
    }


    /**
     * @param event 点击事件
     * 设置两个布局能直接拦截up事件
     */
    private void checkPointInCtrlBtn(MotionEvent event) {
        int[] typeLocation = new int[2];
        int[] dataLocation = new int[2];
        llFmMaterialDetailHeaderAssistType.getLocationOnScreen(typeLocation);
        llFmMaterialDetailHeaderAssistTime.getLocationOnScreen(dataLocation);
        if (event.getRawX()>typeLocation[0]&&event.getRawX()<typeLocation[0]+llFmMaterialDetailHeaderAssistType.getMeasuredWidth()
                &&event.getRawY()>typeLocation[1]&&event.getRawY()<typeLocation[1]+llFmMaterialDetailHeaderAssistType.getMeasuredHeight()){
            llFmMaterialDetailHeaderAssistType.performClick();
        }else if (event.getRawX()>dataLocation[0]&&event.getRawX()<dataLocation[0]+llFmMaterialDetailHeaderAssistTime.getMeasuredWidth()
                &&event.getRawY()>dataLocation[1]&&event.getRawY()<dataLocation[1]+llFmMaterialDetailHeaderAssistTime.getMeasuredHeight())
        {
            llFmMaterialDetailHeaderAssistTime.performClick();
        }
    }

    @Override
    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case GET_ASSIST_MATERIRL_TYPE:
                if (popAssistMaterialType == null) {
                    ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                    List<MaterialType> materialTypes = gson.fromJson(data.data, new TypeToken<List<MaterialType>>() {
                    }.getType());
                    MaterialType assistDetails = new MaterialType();
                    assistDetails.setId("0");
                    assistDetails.setBm_id("4938");
                    assistDetails.setImg("");
                    assistDetails.setName("全部");
                    materialTypes.add(0, assistDetails);
                    popAssistMaterialType = new PopMaterialType(this, materialTypes, 2, imgvFmMaterialDetailHeaderAssist);
                    popAssistMaterialType.getAdapter().setOnTypeClicklistener(this);
                }
                break;
            case GET_MAIN_MATERIRL_TYPE:
                if (popMainMaterialType == null) {
                    ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                    materialTypes = gson.fromJson(data.data, new TypeToken<List<MaterialType>>() {
                    }.getType());
                    MaterialType assistDetails = new MaterialType();
                    assistDetails.setId("0");
                    assistDetails.setBm_id("4938");
                    assistDetails.setImg("");
                    assistDetails.setName("全部");
                    materialTypes.add(0, assistDetails);
                    popMainMaterialType = new PopMaterialType(this, materialTypes, 1,imgvFmMaterialDetailHeaderMainType);
                    popMainMaterialType.getAdapter().setOnTypeClicklistener(this);

                }
                break;
            case GET_ORDERS_DETAIL:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                ordersList = gson.fromJson(data.data, new TypeToken<List<Orders>>() {}.getType());
                ordersAdapter.notifyDataSetChanged(ordersList);
                if (ordersList == null && ordersList.size() == 0){


                }else {
                    tvHint.setVisibility(View.GONE);
                }
                if (ordersList != null && ordersList.size() > 0 && materialType == 2) {
                    llFmMaterialDetailHeaderAssistMoney.setVisibility(View.VISIBLE);
                    tvFmMaterialDetailHeaderAssistMoney.setText(ordersList.get(0).getOrder_msg());
                    tvFmMaterialDetailHeaderAssistOkMoney.setText(ordersList.get(0).getQr_order_msg());
                } else {
                    llFmMaterialDetailHeaderAssistMoney.setVisibility(View.GONE);
                }
                break;
            case AllOrdersAdapter.CONFIRM_RECEIPT:
//                ResponseSucceedData data1 = (ResponseSucceedData) msg.obj;
                ToastUtil.show(this, "操作成功");
                getMaterialOrderList();
                break;
            case BaseActivity.NONET:
                tvHint.setText("请连接网络");
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public void onClilck(MaterialType assistDetails) {
        materialTypeId = assistDetails.getId();
        getMaterialOrderList();
        if (popAssistMaterialType.isShowing()) {
            popAssistMaterialType.dismiss();
            tvFmMaterialDetailHeaderAssist.setText(assistDetails.getName());
        }
        if (popMainMaterialType.isShowing()) {
            popMainMaterialType.dismiss();
            tvFmMaterialDetailHeaderMainType.setText(assistDetails.getName());
        }
    }

    @Override
    public void onAffirmData(String data) {
        imgvFmMaterialDetailHeaderAssistTime.setImageResource(R.mipmap.ic_build_greyarrowdown);
        if (TextUtils.isEmpty(data)) {
            tvFmMaterialDetailHeaderAssistTime.setText("全部时间");
        } else {
            tvFmMaterialDetailHeaderAssistTime.setText(data);
        }
        filterDate = data;
        getMaterialOrderList();
    }

    @Override
    public void setEmptyView(Message msg) {
	    //因为这个页面加载时有时会访问多个接口，而这个方法是在handleMessage后运行
	    //避免listView前面的接口访问完，listview还没访问完，还没返回数据，就把emptyView显示出来
	    //所以可以用msg.what 判断加载listview的接口是否访问过 才把emptyView 加载出来
	    if (msg.what==GET_ORDERS_DETAIL){
		    emptyView = new MyEmptyView(this);
		    emptyView.setEmptyViewImg(R.mipmap.ic_empty_orders);
		    emptyView.setEmptyViewContent("暂无订单");
		    ((ViewGroup) lvFmMaterialDetail.getParent()).addView(emptyView.getEmptyView());
		    lvFmMaterialDetail.setEmptyView(emptyView.getEmptyView());
	    }else{
		    setHadEmptyView(false);
	    }
    }
}
