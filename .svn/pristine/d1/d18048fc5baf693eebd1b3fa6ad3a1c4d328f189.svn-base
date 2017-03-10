package com.youjuke.miprojectmanager.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.hbw.library.utils.Tools;
import com.youjuke.miprojectmanager.R;
import com.youjuke.miprojectmanager.adapter.AssistProgectAddMatterOrderAdapter;
import com.youjuke.miprojectmanager.entity.AssistProgectAddMatterOrder;
import com.youjuke.miprojectmanager.entity.AssistProgectAddMatterSubmitOrder;
import com.youjuke.miprojectmanager.util.HttpConstant;
import com.youjuke.miprojectmanager.view.HintDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2016/1/19.工程增加项类
 */
public class AssistProgectAddMatterOrderActivity extends BaseActivity implements View.OnClickListener {

    private ListView order_list;
    private LinearLayout order_all_select;
    private TextView order_all_money;
    private ImageView order_all_select_img;
    private Button order_submit;
    private HashMap<String, String> mMap = new HashMap<String, String>();

    private ArrayList<AssistProgectAddMatterOrder> mAssistProgectAddMatterOrders = new ArrayList<AssistProgectAddMatterOrder>();
    private AssistProgectAddMatterOrderAdapter mAssistProgectAddMatterOrderAdapter = null;
    private List<AssistProgectAddMatterSubmitOrder> orderlists = new ArrayList<AssistProgectAddMatterSubmitOrder>();//订单

    private boolean isAllChoose = false;//默认为未全选

    private HintDialog mHintDialog = null, mMustDialog = null;

    private String id,bm_id;

    private EditText materialTypeEdittext;
    private LinearLayout materialTypeText;
    private LinearLayout materialTypeSearch;
    private String typekey = "";//搜素关键字 为空位查询全部
    private RelativeLayout material_type_RelativeLayout;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("增加工程项下单");
        titleBar.setLeftImgDefaultBack(this);

        id = getIntent().getStringExtra("id");
        bm_id = getIntent().getStringExtra("bm_id");

        order_list = (ListView) findViewById(R.id.order_list);
        order_all_select = (LinearLayout) findViewById(R.id.order_all_select);
        order_all_money = (TextView) findViewById(R.id.order_all_money);
        order_submit = (Button) findViewById(R.id.order_submit);
        order_all_select_img = (ImageView) findViewById(R.id.order_all_select_img);

        //搜素框
        materialTypeEdittext = (EditText) findViewById(R.id.material_type_edittext);
        materialTypeText = (LinearLayout) findViewById(R.id.material_type_text);
        materialTypeSearch = (LinearLayout) findViewById(R.id.material_type_search);
        material_type_RelativeLayout = (RelativeLayout) findViewById(R.id.material_type_RelativeLayout);
        material_type_RelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        materialTypeEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                typekey = s.toString();
                //查询材料类型
                HashMap<String, String> mMap = new HashMap<String, String>();
                mMap.put("id", id);
                mMap.put("token", HttpConstant.token);
                mMap.put("title", typekey);
                analyzeJson.requestData(HttpConstant.AddProjectListUrl, mMap, REQUEST_SUCCESS);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        materialTypeEdittext.setOnClickListener(this);
        materialTypeText.setOnClickListener(this);

        //初始化提示框
        if (mHintDialog == null) {
            mHintDialog = new HintDialog(this);
        }
        mHintDialog.setTitleText("确认下单吗").setConfirmListener(this).setCancelListener(this);

        if (mMustDialog == null) {
            mMustDialog = new HintDialog(this);
        }
        mMustDialog.setConfirmListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMustDialog.dismiss();
            }
        }).setCancelHint();
        order_all_select.setOnClickListener(this);
        order_submit.setOnClickListener(this);

        mAssistProgectAddMatterOrderAdapter = new AssistProgectAddMatterOrderAdapter(this,this, mAssistProgectAddMatterOrders, orderlists,
                new AssistProgectAddMatterOrderAdapter.TotalPrice() {
                    @Override
                    public void TotalPriceChange() {
                        //计算总价
                        float countPrice = 0;
                        for (int i = 0; i < orderlists.size(); i++) {
                            //判断该主材是否为选中状态
                            if (mAssistProgectAddMatterOrders.get(i).isChoose()) {
                                if (orderlists.get(i).getTotalprice() != null && !"".equals(orderlists.get(i).getTotalprice())) {
                                    countPrice += Float.parseFloat(orderlists.get(i).getTotalprice());
                                }
                            }
                        }
                        order_all_money.setText((float)(Math.round(countPrice*100))/100 + "元");
                        countPrice = 0;
                    }
                });
        order_list.setAdapter(mAssistProgectAddMatterOrderAdapter);

        mMap.put("id", id);
        mMap.put("title", typekey);
        mMap.put("token", HttpConstant.token);
        analyzeJson.requestData(HttpConstant.AddProjectListUrl, mMap, REQUEST_SUCCESS);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_assist_advocate_material_order;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_all_select://全选
                if (isAllChoose) {
                    isAllChoose = false;
                    isAllChoose(isAllChoose);
                    order_all_select_img.setImageBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.nochoose)).getBitmap());
                    mAssistProgectAddMatterOrderAdapter.notifyDataSetChanged(mAssistProgectAddMatterOrders, orderlists);
                } else {
                    isAllChoose = true;
                    isAllChoose(isAllChoose);
                    order_all_select_img.setImageBitmap(((BitmapDrawable) getResources().getDrawable(R.drawable.ic_choose)).getBitmap());
                    mAssistProgectAddMatterOrderAdapter.notifyDataSetChanged(mAssistProgectAddMatterOrders, orderlists);
                }
                break;
            case R.id.hintdialog_cancel://关闭表单提醒框
                mHintDialog.dismiss();
                break;
            case R.id.order_submit:
                mHintDialog.show();
                break;
            case R.id.hintdialog_confirm://提交主材订单
                mHintDialog.dismiss();
                List<AssistProgectAddMatterSubmitOrder> orders = new ArrayList<AssistProgectAddMatterSubmitOrder>();//订单
                for (int i = 0; i < mAssistProgectAddMatterOrders.size(); i++) {
                    if (mAssistProgectAddMatterOrders.get(i).isChoose()) {

	                    AssistProgectAddMatterSubmitOrder order = orderlists.get(i);
                        //损耗率 去掉% 返回float类型
	                    String shlStr = order.getShl().substring(0, order.getShl().indexOf('%'));
	                    float shl = Float.parseFloat(shlStr);
	                    order.setShl(shl+"");
	                    orders.add(order);
                    }
                }
                //判断勾选主材数量不能为0
                if (orders.size() == 0) {
                    mMustDialog.setTitleText("请选择要下单的增加项");
                    mMustDialog.show();
                    break;
                }
                //将数组转换成json格式
                mMap.clear();
                Gson gson = new Gson();
                mMap.put("token", HttpConstant.token);
                mMap.put("datas", gson.toJson(orders));
                analyzeJson.requestData(HttpConstant.AddProjectOrderUrl, mMap, 0x1010);
                break;
            case R.id.material_type_edittext:
                materialTypeEdittext.setHint(R.string.order_search);
                materialTypeEdittext.setCompoundDrawables(getResources().getDrawable(R.drawable.search), null, null, null);
                materialTypeText.setVisibility(View.VISIBLE);
                materialTypeSearch.setVisibility(View.GONE);
                break;
            case R.id.material_type_text://取消搜索
                materialTypeEdittext.setHint(null);
                materialTypeEdittext.setCompoundDrawables(null, null, null, null);
                typekey = "";
                materialTypeEdittext.setText("");
                materialTypeText.setVisibility(View.GONE);
                materialTypeSearch.setVisibility(View.VISIBLE);
                Tools.HideKeyBoard(this);

                break;
        }
    }

    private void isAllChoose(boolean isAllChoose) {
        //遍历主材信息,将选中属性全部设为传进来的参数
        for (int i = 0; i < mAssistProgectAddMatterOrders.size(); i++) {
            mAssistProgectAddMatterOrders.get(i).setIsChoose(isAllChoose);
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                //把数据清空
                orderlists.clear();
                isAllChoose = false;
                //解析数据
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                mAssistProgectAddMatterOrders = gson.fromJson(data.data, new TypeToken<List<AssistProgectAddMatterOrder>>() {
                }.getType());
                for (int i = 0; i < mAssistProgectAddMatterOrders.size(); i++) {
                    //默认为1个
                    AssistProgectAddMatterSubmitOrder mAssistProgectAddMatterSubmitOrder = new AssistProgectAddMatterSubmitOrder();
                    mAssistProgectAddMatterSubmitOrder.setId(mAssistProgectAddMatterOrders.get(i).getId());
                    mAssistProgectAddMatterSubmitOrder.setBaoming_id(bm_id);
                    mAssistProgectAddMatterSubmitOrder.setContent("");
                    mAssistProgectAddMatterSubmitOrder.setFid(id);
                    mAssistProgectAddMatterSubmitOrder.setMt_id(mAssistProgectAddMatterOrders.get(i).getMt_id());
                    mAssistProgectAddMatterSubmitOrder.setCount("1");
                    mAssistProgectAddMatterSubmitOrder.setPrice(mAssistProgectAddMatterOrders.get(i).getPrice());

                    mAssistProgectAddMatterSubmitOrder.setShl(mAssistProgectAddMatterOrders.get(i).getShl());
	                float shl = Float.parseFloat(mAssistProgectAddMatterSubmitOrder.getShl().substring(0,
			                mAssistProgectAddMatterSubmitOrder.getShl().indexOf('%')))/100;
                    mAssistProgectAddMatterSubmitOrder.setShl_count(shl + 1+"");
                    mAssistProgectAddMatterSubmitOrder.setTotalprice(
                            Float.parseFloat(mAssistProgectAddMatterSubmitOrder.getShl_count())
                                    *Float.parseFloat(mAssistProgectAddMatterOrders.get(i).getPrice())+"");

                    orderlists.add(mAssistProgectAddMatterSubmitOrder);
                }
                mAssistProgectAddMatterOrderAdapter.notifyDataSetChanged(mAssistProgectAddMatterOrders, orderlists);
                break;
            case 0x1010://提交订单成功
                mHintDialog.dismiss();
                ToastUtil.show(this, "下单成功");
                //把数据清空
                orderlists.clear();
                mAssistProgectAddMatterOrders.clear();
                mMap.clear();
                isAllChoose = false;
                mAssistProgectAddMatterOrderAdapter = null;
                finishActivity();
                Intent it = new Intent(AssistProgectAddMatterOrderActivity.this,QuantitiesActivity.class);
                it.putExtra("bm_id",bm_id);
                startActivity(it);
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mHintDialog != null) {
            mHintDialog.dismiss();
        }
        if (mMustDialog != null) {
            mMustDialog.dismiss();
        }
    }

}
