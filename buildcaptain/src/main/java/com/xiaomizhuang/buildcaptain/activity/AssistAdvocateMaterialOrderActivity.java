package com.xiaomizhuang.buildcaptain.activity;

import android.graphics.drawable.BitmapDrawable;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AssistAdvocateMaterialOrderAdapter;
import com.xiaomizhuang.buildcaptain.entity.AssistAdvocateMaterialOrder;
import com.xiaomizhuang.buildcaptain.entity.AssistMaterialOrder;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.view.HintDialog;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2016/1/19.主材下单
 */
public class AssistAdvocateMaterialOrderActivity extends BaseActivity implements View.OnClickListener {

	private ListView order_list;
	private LinearLayout order_all_select;
	private TextView order_all_money;
	private ImageView order_all_select_img;
	private Button order_submit;
	private HashMap<String, String> mMap = new HashMap<String, String>();

	private ArrayList<AssistAdvocateMaterialOrder> mAssistAdvocateMaterialOrders = new ArrayList<AssistAdvocateMaterialOrder>();//主材信息
	private AssistAdvocateMaterialOrderAdapter mAssistAdvocateMaterialOrderAdapter = null;
	private List<AssistMaterialOrder> orderlists = new ArrayList<AssistMaterialOrder>();//订单

	private boolean isAllChoose = false;//默认为未全选

	private HintDialog mHintDialog = null, mMustDialog = null;

	@Override
	protected void initWidget() {
		titleBar.setTitleText("主材数量确认");
		titleBar.setLeftImgDefaultBack(this);

		order_list = (ListView) findViewById(R.id.order_list);
		order_all_select = (LinearLayout) findViewById(R.id.order_all_select);
		order_all_money = (TextView) findViewById(R.id.order_all_money);
		order_submit = (Button) findViewById(R.id.order_submit);
		order_all_select_img = (ImageView) findViewById(R.id.order_all_select_img);

		//初始化提示框
		if (mHintDialog == null) {
			mHintDialog = new HintDialog(this);
		}
		mHintDialog.setTitleText("确认修改主材数量吗").setConfirmListener(this).setCancelListener(this);

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

		mAssistAdvocateMaterialOrderAdapter = new AssistAdvocateMaterialOrderAdapter(this, this, mAssistAdvocateMaterialOrders, orderlists,
				new AssistAdvocateMaterialOrderAdapter.TotalPrice() {
					@Override
					public void TotalPriceChange() {
						//计算总价
						float countPrice = 0;
						for (int i = 0; i < orderlists.size(); i++) {
							//判断该主材是否为选中状态
							if (mAssistAdvocateMaterialOrders.get(i).isChoose()) {
								if (orderlists.get(i).getAct_total_price() != null && !"".equals(orderlists.get(i).getAct_total_price())) {
									countPrice += Float.parseFloat(orderlists.get(i).getAct_total_price());
								}
							}
						}
						order_all_money.setText((float) (Math.round(countPrice * 100)) / 100 + "元");
						countPrice = 0;
					}
				});
		order_list.setAdapter(mAssistAdvocateMaterialOrderAdapter);

		mMap.put("uid", getIntent().getStringExtra("uid"));//工地列表传过来的id号(业主id)
		analyzeJson.requestData(HttpConstant.ZcOrderDetails, mMap, REQUEST_SUCCESS);
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
					order_all_select_img.setImageBitmap(((BitmapDrawable) getResources().getDrawable(R.mipmap.nochoose)).getBitmap());
					mAssistAdvocateMaterialOrderAdapter.notifyDataSetChanged(mAssistAdvocateMaterialOrders, orderlists);
				} else {
					isAllChoose = true;
					isAllChoose(isAllChoose);
					order_all_select_img.setImageBitmap(((BitmapDrawable) getResources().getDrawable(R.mipmap.ic_choose)).getBitmap());
					mAssistAdvocateMaterialOrderAdapter.notifyDataSetChanged(mAssistAdvocateMaterialOrders, orderlists);
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
				List<AssistMaterialOrder> orders = new ArrayList<AssistMaterialOrder>();//订单
				for (int i = 0; i < mAssistAdvocateMaterialOrders.size(); i++) {
					if (mAssistAdvocateMaterialOrders.get(i).isChoose()) {
						orders.add(orderlists.get(i));
					}
				}
				//判断勾选主材数量不能为0
				if (orders.size() == 0) {
					mMustDialog.setTitleText("请选择要修改的主材订单");
					mMustDialog.show();
					break;
				}
				//判断是否有主材未选择送货时间
				for (int i = 0; i < orders.size(); i++) {
					if (orders.get(i).getOrdertime() == null || "".equals(orders.get(i).getOrdertime())
							|| "0000-00-00".equals(orders.get(i).getOrdertime().trim()) ||
							"0000-00-00 00:00:00".equals(orders.get(i).getOrdertime().trim())) {
						mMustDialog.setTitleText("请选择送货时间");
						mMustDialog.show();
						return;
					}
				}
				if (orders.size() == 0) {
					mMustDialog.setTitleText("请选择要修改的主材订单");
					mMustDialog.show();
					break;
				}
				//将数组转换成json格式
				Gson gson = new Gson();
				mMap.put("data", gson.toJson(orders));
				analyzeJson.requestData(HttpConstant.ZcOrderFucaiUrl, mMap, 0x1010);
				break;
		}
	}

	private void isAllChoose(boolean isAllChoose) {
		//遍历主材信息,将选中属性全部设为传进来的参数
		for (int i = 0; i < mAssistAdvocateMaterialOrders.size(); i++) {
			mAssistAdvocateMaterialOrders.get(i).setIsChoose(isAllChoose);
		}
	}

	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				mAssistAdvocateMaterialOrders = gson.fromJson(data.data, new TypeToken<List<AssistAdvocateMaterialOrder>>() {
				}.getType());
				for (int i = 0; i < mAssistAdvocateMaterialOrders.size(); i++) {
					AssistMaterialOrder mAssistMaterialOrder = new AssistMaterialOrder();
					mAssistMaterialOrder.setId(mAssistAdvocateMaterialOrders.get(i).getId());
					mAssistMaterialOrder.setAct_count(mAssistAdvocateMaterialOrders.get(i).getAct_count());
					mAssistMaterialOrder.setAct_price(Float.parseFloat(mAssistAdvocateMaterialOrders.get(i).getAct_price()));

					mAssistMaterialOrder.setOrdertime(mAssistAdvocateMaterialOrders.get(i).getExpectedtime());
					mAssistMaterialOrder.setBeizhu(mAssistAdvocateMaterialOrders.get(i).getBeizhu());
					float shl = Float.parseFloat(mAssistAdvocateMaterialOrders.get(i).getShl().substring(0,
							mAssistAdvocateMaterialOrders.get(i).getShl().indexOf('%'))) / 100;
					mAssistMaterialOrder.setShl(shl);
					mAssistMaterialOrder.setShl_count((shl + 1) * Float.parseFloat(mAssistMaterialOrder.getAct_count()));
					mAssistMaterialOrder.setAct_total_price(mAssistMaterialOrder.getShl_count()
							* mAssistMaterialOrder.getAct_price() + "");
					orderlists.add(mAssistMaterialOrder);
				}
				mAssistAdvocateMaterialOrderAdapter.notifyDataSetChanged(mAssistAdvocateMaterialOrders, orderlists);
				break;
			case 0x1010://提交订单成功
				mHintDialog.dismiss();
				ToastUtil.show(this, "主材数量修改成功");
				//把数据清空
				orderlists.clear();
				mAssistAdvocateMaterialOrders.clear();
				mMap.clear();
				isAllChoose = false;
				mAssistAdvocateMaterialOrderAdapter = null;
				//重新请求
				initWidget();
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
