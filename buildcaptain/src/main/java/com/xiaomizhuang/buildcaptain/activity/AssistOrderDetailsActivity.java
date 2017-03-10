package com.xiaomizhuang.buildcaptain.activity;

import android.content.Intent;
import android.os.Message;
import android.text.Editable;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ArithUtil;
import com.hbw.library.utils.ToastUtil;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.AssistBrandManufacturers;
import com.xiaomizhuang.buildcaptain.entity.AssistConstructionStage;
import com.xiaomizhuang.buildcaptain.entity.AssistMaterialType;
import com.xiaomizhuang.buildcaptain.entity.AssistOrderDetails;
import com.xiaomizhuang.buildcaptain.entity.AssistSelectUnit;
import com.xiaomizhuang.buildcaptain.entity.AssistSpecificationsModels;
import com.xiaomizhuang.buildcaptain.entity.OrderLastPick;
import com.xiaomizhuang.buildcaptain.util.AppFlag;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.DateDialog;
import com.xiaomizhuang.buildcaptain.view.HintDialog;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.HashMap;

/**
 * Created by user on 2015/11/20.订单详情
 */
public class AssistOrderDetailsActivity extends BaseActivity implements View.OnClickListener {
	private LinearLayout orderDetailsTypeLinearLayout;
	private LinearLayout orderDetailsModerLinearLayout;
	private LinearLayout orderDetailsDeliveryTimeLinearLayout;
	private LinearLayout order_details_construction_stage_LinearLayout;
	private LinearLayout order_details_brand_LinearLayout;
	private LinearLayout order_details_unit_LinearLayout;
	private LinearLayout order_details_two;
	private TextView orderDetailsDeliveryTime;
	private TextView orderDetailsTotalMoney;
	private TextView order_details_construction_stage;
	private TextView orderDetailsBrand;
	private TextView orderDetailsType;
	private TextView orderDetailsModer;
	private TextView orderDetailsUnit;
	private TextView orderDetailsPrice;
	private RelativeLayout order_details_one;
	private ImageButton orderDetailsLine;
	private EditText orderDetailsNumber;
	private EditText order_details_remark;
	private ImageButton orderDetailsAdd;
	private Button orderDetailsSubmit;
	private DateDialog mDateDialog = null;
	private DatePicker mDatePicker = null;

	public static int year = 0, monthOfYear = 0, dayOfMonth = 0;
	public static final int REQUEST_SUCCESS_ORDER = 6666;// 下单成功
	public static final int BRANDMANUFACTURERS = 63;//品牌商家
	public static final int MATERIALTYPE = 4399;//材料类型
	public static final int SPECIFICATIONSMODELS = 5173;//规格型号
	public static final int SELECTUNIT = 9999;//单位
	public static final int CONSTRUCTIONSTAGE = 51;//施工阶段

	private AssistOrderDetails mAssistOrderDetails = null;

	private AssistConstructionStage massistConstructionStage = null;//施工阶段
	private AssistBrandManufacturers massistBrandManufacturers = null;//品牌商家
	private AssistMaterialType massistMaterialType = null;//材料类型
	private AssistSpecificationsModels massistSpecificationsModels = null;//规格型号
	private AssistSelectUnit mAssistSelectUnit = null;//单位

	private double number = 0;//数量
	private HintDialog mHintDialog = null, mMustDialog = null;
	private String id, fid;//小类ID 大类ID
	private String baomingid, msg_id;
	private String bm_id;//报名id

	@Override
	protected void initWidget() {
		titleBar.setTitleText("订单详情");
		titleBar.setLeftImgDefaultBack(this);

		//获取上个界面传递过来的参数
		id = getIntent().getStringExtra(AppFlag.ID);//小类ID
		fid = getIntent().getStringExtra(AppFlag.FID);//大类ID
		baomingid = getIntent().getStringExtra(AppFlag.BAOMINGID);
		msg_id = getIntent().getStringExtra(AppFlag.MSGID);
		bm_id = MyApplication.getApp().getBuildSite().getUid();
		//请求上次下单记录内容 默认选中
		HashMap<String, String> mMap = new HashMap<String, String>();
		mMap.put(AppFlag.UID, baomingid);
		mMap.put("mt_id", id);
		mMap.put("bm_id",bm_id);
		mMap.put(AppFlag.TOKEN, MyApplication.TOKEN);

		analyzeJson.requestData(HttpConstant.MaterialPlanOrderFucaiLastUrl, mMap, REQUEST_SUCCESS);

		orderDetailsBrand = (TextView) findViewById(R.id.order_details_brand);
		orderDetailsType = (TextView) findViewById(R.id.order_details_type);
		orderDetailsModer = (TextView) findViewById(R.id.order_details_moder);
		orderDetailsUnit = (TextView) findViewById(R.id.order_details_unit);
		orderDetailsPrice = (TextView) findViewById(R.id.order_details_price);
		orderDetailsLine = (ImageButton) findViewById(R.id.order_details_line);
		orderDetailsNumber = (EditText) findViewById(R.id.order_details_number);
		orderDetailsAdd = (ImageButton) findViewById(R.id.order_details_add);
		orderDetailsDeliveryTime = (TextView) findViewById(R.id.order_details_delivery_time);
		orderDetailsTotalMoney = (TextView) findViewById(R.id.order_details_total_money);
		orderDetailsSubmit = (Button) findViewById(R.id.order_details_submit);
		orderDetailsTypeLinearLayout = (LinearLayout) findViewById(R.id.order_details_type_LinearLayout);
		orderDetailsModerLinearLayout = (LinearLayout) findViewById(R.id.order_details_moder_LinearLayout);
		orderDetailsDeliveryTimeLinearLayout = (LinearLayout) findViewById(R.id.order_details_delivery_time_LinearLayout);
		order_details_one = (RelativeLayout) findViewById(R.id.order_details_one);
		order_details_two = (LinearLayout) findViewById(R.id.order_details_two);
		//施工阶段
		order_details_construction_stage_LinearLayout = (LinearLayout) findViewById(R.id.order_details_construction_stage_LinearLayout);
		order_details_construction_stage = (TextView) findViewById(R.id.order_details_construction_stage);
		//品牌商家
		order_details_brand_LinearLayout = (LinearLayout) findViewById(R.id.order_details_brand_LinearLayout);
		//单位
		order_details_unit_LinearLayout = (LinearLayout) findViewById(R.id.order_details_unit_LinearLayout);
		//备注
		order_details_remark = (EditText) findViewById(R.id.order_details_remark);
		initDialog();
		orderDetailsDeliveryTimeLinearLayout.setOnClickListener(this);
		orderDetailsTypeLinearLayout.setOnClickListener(this);
		orderDetailsModerLinearLayout.setOnClickListener(this);
		orderDetailsLine.setOnClickListener(this);
		orderDetailsAdd.setOnClickListener(this);
		orderDetailsSubmit.setOnClickListener(this);
		order_details_construction_stage_LinearLayout.setOnClickListener(this);
		order_details_brand_LinearLayout.setOnClickListener(this);
		order_details_unit_LinearLayout.setOnClickListener(this);

		//如果小类是保洁的话 数量就是该工地的面积 且不可增减
		if (id != null && "8736".equals(id.trim())){
			number = MyApplication.getApp().getBuildSite().getArea();
			orderDetailsNumber.setFocusable(false);
			orderDetailsLine.setEnabled(false);
			orderDetailsAdd.setEnabled(false);
		}else{
			orderDetailsNumber.setFocusable(true);
			orderDetailsLine.setEnabled(true);
			orderDetailsAdd.setEnabled(true);
		}

		orderDetailsNumber.setText(number + "");
		//orderDetailsNumber.setInputType(InputType.TYPE_NULL);//禁止数量软键盘弹出
		orderDetailsNumber.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (mAssistSelectUnit==null) return;
				if ((!"".equals(s.toString().trim()))) {
                    if (Double.valueOf(s.toString()) >= 10000) {
                        mMustDialog.setTitleText("数量不能大于4位数");
                        mMustDialog.show();
                        orderDetailsNumber.setText("9999");
                        number = 9999;
                        orderDetailsTotalMoney.setText(Double.parseDouble(mAssistSelectUnit.getAct_price()) * number + "元");
                    }else if(Double.valueOf(s.toString()) < 0){
                        number = 0;
                        orderDetailsNumber.setText("0");
                        orderDetailsTotalMoney.setText(Double.parseDouble(mAssistSelectUnit.getAct_price()) * number + "元");
                    }else {
                        number = Double.parseDouble(s.toString());
                        orderDetailsTotalMoney.setText(Double.parseDouble(mAssistSelectUnit.getAct_price()) * number + "元");
                    }
				}else {
					number = 0;
                    orderDetailsNumber.setText("0");
                    orderDetailsNumber.setSelection(orderDetailsNumber.getText().length());
					orderDetailsTotalMoney.setText(Double.parseDouble(mAssistSelectUnit.getAct_price()) * number + "元");
				}
			}

			@Override
			public void afterTextChanged(Editable s) {

			}
		});

	}

	@Override
	protected void initData() {

	}

	private void initDialog() {
		//获取到系统的时间
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int monthOfYear = calendar.get(Calendar.MONTH);
		int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
		int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		if (mDateDialog == null) {
			mDateDialog = new DateDialog(this);
		}
		mDateDialog.setOrderDetailsTimePickerVisible(View.GONE);
		mDateDialog.setCancelListener(this).setConfirmListener(this);
		mDatePicker = mDateDialog.setDatePickerChangedListener(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

			}
		});
		mDatePicker.setMinDate(calendar.getTimeInMillis());
		//初始化提示框
		if (mHintDialog == null) {
			mHintDialog = new HintDialog(this);
		}
		if (mMustDialog == null) {
			mMustDialog = new HintDialog(this);
		}
		mHintDialog.setTitleText("确认下单之后无法修改订单内容").setConfirmListener(this).setCancelListener(this);
		mMustDialog.setConfirmListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mMustDialog.dismiss();
			}
		}).setCancelHint();
	}

	@Override
	protected int initPageLayoutID() {
		return R.layout.activity_order_details;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			/** 品牌商家 */
			case R.id.order_details_brand_LinearLayout:
				Intent order_details_brand_LinearLayout_it = new Intent(AssistOrderDetailsActivity.this,
						AssistBrandManufacturersActivity.class);
				order_details_brand_LinearLayout_it.putExtra(AppFlag.MTID, id);
				order_details_brand_LinearLayout_it.putExtra(AppFlag.FID, fid);
				order_details_brand_LinearLayout_it.putExtra("bm_id", bm_id);
				startActivityForResult(order_details_brand_LinearLayout_it, BRANDMANUFACTURERS);
				break;
			/** 选择材料类型 */
			case R.id.order_details_type_LinearLayout:
				if (massistBrandManufacturers == null || massistBrandManufacturers.getId() == null
						|| "".equals(massistBrandManufacturers.getId())) {
					mMustDialog.setTitleText("请先选择品牌厂家");
					mMustDialog.show();
					break;
				}
				Intent it = new Intent(AssistOrderDetailsActivity.this, AssistMaterialTypeActivity.class);
				it.putExtra("fid", id);
				it.putExtra("dl_id", fid);
				it.putExtra("brand", massistBrandManufacturers.getId());
				startActivityForResult(it, MATERIALTYPE);
				break;
			/** 选择规格型号 */
			case R.id.order_details_moder_LinearLayout:
				if (massistMaterialType == null || massistMaterialType.getId() == null
						|| "".equals(massistMaterialType.getId())) {
					mMustDialog.setTitleText("请先选择材料类型");
					mMustDialog.show();
					break;
				}
				Intent intent = new Intent(AssistOrderDetailsActivity.this, AssistSpecificationsModelsActivity.class);
				intent.putExtra(AppFlag.MTYPEID, massistMaterialType.getId());
				intent.putExtra("fid", id);
				intent.putExtra("dl_id", fid);
				intent.putExtra("bm_id", bm_id);
				intent.putExtra("brand", massistBrandManufacturers.getId());
				startActivityForResult(intent, SPECIFICATIONSMODELS);
				break;
			/** 选择单位 */
			case R.id.order_details_unit_LinearLayout:
				if (massistSpecificationsModels == null || massistSpecificationsModels.getNorms() == null
						|| "".equals(massistSpecificationsModels.getNorms())) {
					mMustDialog.setTitleText("请先选择规格型号");
					mMustDialog.show();
					break;
				}
				Intent unit = new Intent(AssistOrderDetailsActivity.this, AssistSelectUnitActivity.class);
				unit.putExtra(AppFlag.MTYPEID, massistMaterialType.getId());
				unit.putExtra("fid", id);
				unit.putExtra("dl_id", fid);
				unit.putExtra("brand", massistBrandManufacturers.getId());
				unit.putExtra("norms", massistSpecificationsModels.getNorms());
				startActivityForResult(unit, SELECTUNIT);
				break;
			case R.id.order_details_delivery_time_LinearLayout://显示时间选择框
				mDateDialog.show();
				break;
			case R.id.order_details_cancel://关闭时间选择框
				mDateDialog.dismiss();
				break;
			case R.id.order_details_confirm://关闭时间选择框and赋值送货时间
				mDateDialog.dismiss();
				AssistOrderDetailsActivity.year = mDatePicker.getYear();
				AssistOrderDetailsActivity.monthOfYear = mDatePicker.getMonth();
				AssistOrderDetailsActivity.dayOfMonth = mDatePicker.getDayOfMonth();
				orderDetailsDeliveryTime.setTextColor(getResources().getColor(R.color.text_000000));
				orderDetailsDeliveryTime.setText(mDatePicker.getYear() + "年" + (mDatePicker.getMonth() + 1) + "月" + mDatePicker.getDayOfMonth() + "日");
				break;
			case R.id.order_details_construction_stage_LinearLayout://施工阶段
				Intent construction_stage_it = new Intent(AssistOrderDetailsActivity.this, AssistConstructionStageActivity.class);
				startActivityForResult(construction_stage_it, CONSTRUCTIONSTAGE);
				break;
			case R.id.order_details_line://减少数量
				if (massistSpecificationsModels == null) {
					mMustDialog.setTitleText("请先选择单位");
					mMustDialog.show();
					break;
				}
				if (number > 0) {
					number--;
					orderDetailsNumber.setText(number + "");
					orderDetailsTotalMoney.setText(ArithUtil.mul(Double.parseDouble(mAssistSelectUnit.getAct_price()),number) + "元");
				}
				break;
			case R.id.order_details_add://增加数量
				if (mAssistSelectUnit == null) {
					mMustDialog.setTitleText("请先选择单位");
					mMustDialog.show();
					break;
				}
				if (number >= 0) {
					number++;
					orderDetailsNumber.setText(number + "");
					orderDetailsTotalMoney.setText(ArithUtil.mul(Double.parseDouble(mAssistSelectUnit.getAct_price()),number) + "元");
				}
				break;
			case R.id.order_details_submit:
				if (massistBrandManufacturers == null) {
					mMustDialog.setTitleText("请先选择品牌厂家");
					mMustDialog.show();
					break;
				}
				if (massistMaterialType == null) {
					mMustDialog.setTitleText("请先选择材料类型");
					mMustDialog.show();
					break;
				}
				if (massistSpecificationsModels == null) {
					mMustDialog.setTitleText("请先选择规格型号");
					mMustDialog.show();
					break;
				}
				if (mAssistSelectUnit == null) {
					mMustDialog.setTitleText("请先选择单位");
					mMustDialog.show();
					break;
				}
				if (number == 0) {
					mMustDialog.setTitleText("订单数量不能为零个");
					mMustDialog.show();
					break;
				}
				if (year == 0) {
					mMustDialog.setTitleText("请先选择送货时间");
					mMustDialog.show();
					break;
				}
				if (massistConstructionStage == null) {
					mMustDialog.setTitleText("请先选择施工阶段");
					mMustDialog.show();
					break;
				}
				mHintDialog.show();
				break;
			case R.id.hintdialog_cancel://关闭表单提醒框
				mHintDialog.dismiss();
				break;
			case R.id.hintdialog_confirm://提交表单
				//提交订单
				HashMap<String, String> mMap = new HashMap<String, String>();
				mMap.put(AppFlag.BAOMINGID, baomingid);
				mMap.put("mt_id", id);
				if (msg_id!=null){
					mMap.put("msg_id", msg_id);
				}
				mMap.put("act_price", mAssistSelectUnit.getAct_price());
				mMap.put("act_count", number + "");
				mMap.put("act_total_price", Double.parseDouble(mAssistSelectUnit.getAct_price()) * number + "");
				mMap.put("material_id", mAssistSelectUnit.getMaterial_id());
				mMap.put("sendtime", year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
				mMap.put("token", MyApplication.TOKEN);
				mMap.put("step", massistConstructionStage.getStep());
				mMap.put("brand_id", massistBrandManufacturers.getId());
				mMap.put("beizhu", order_details_remark.getText().toString().trim());
				analyzeJson.requestData(HttpConstant.MaterialPlanOrderFucaiUrl, mMap, REQUEST_SUCCESS_ORDER);
				mHintDialog.dismiss();
				break;
		}
	}

	@Override
	public boolean handleMessage(Message msg) {
		switch (msg.what) {
			case REQUEST_SUCCESS:
				ResponseSucceedData data = (ResponseSucceedData) msg.obj;
				//如果上次有下单记录 默认选中上次下单记录
				if (data != null && !"[]".equals(data.data.trim())) {
					OrderLastPick orderLastPick = gson.fromJson(data.data, OrderLastPick.class);
					orderDetailsBrand.setText(orderLastPick.getBrand());
					orderDetailsBrand.setTextColor(getResources().getColor(R.color.text_000000));
					orderDetailsType.setText(orderLastPick.getType());
					orderDetailsType.setTextColor(getResources().getColor(R.color.text_000000));
					orderDetailsModer.setText(orderLastPick.getNorms());
					orderDetailsModer.setTextColor(getResources().getColor(R.color.text_000000));
					orderDetailsUnit.setText(orderLastPick.getUnit());
					orderDetailsUnit.setTextColor(getResources().getColor(R.color.text_000000));
					orderDetailsPrice.setText(orderLastPick.getPrice()+"");

					if (massistBrandManufacturers == null) {
						massistBrandManufacturers = new AssistBrandManufacturers();
					}
					massistBrandManufacturers.setId(orderLastPick.getBrand_id() + "");
					if (massistMaterialType == null) {
						massistMaterialType = new AssistMaterialType();
					}
					massistMaterialType.setId(orderLastPick.getType_id()+"");
					massistMaterialType.setName(orderLastPick.getType());
					if (massistSpecificationsModels == null){
						massistSpecificationsModels = new AssistSpecificationsModels();
					}
					massistSpecificationsModels.setNorms(orderLastPick.getNorms());
					if (mAssistSelectUnit ==  null){
						mAssistSelectUnit = new AssistSelectUnit();
					}
					mAssistSelectUnit.setUnit(orderLastPick.getUnit());
					mAssistSelectUnit.setAct_price(orderLastPick.getPrice()+"");
					mAssistSelectUnit.setMaterial_id(orderLastPick.getMaterial_id()+"");
					orderDetailsTotalMoney.setText(Double.parseDouble(mAssistSelectUnit.getAct_price()) * number + "元");
				}
				break;
			case REQUEST_SUCCESS_ORDER://下单成功，2秒后跳转到订单详情
				order_details_one.setVisibility(View.GONE);
				order_details_two.setVisibility(View.VISIBLE);

				new Thread(new Runnable() {
					public void run() {
						try {
							Thread.sleep(500);
							/*Intent it = new Intent(AssistOrderDetailsActivity.this, MaterialOrderDetailActivity.class);
							it.putExtra("bm_id", baomingid);
							startActivity(it);*/
							//清除本类数据 public static int year = 0, monthOfYear = 0, dayOfMonth = 0, hourOfDay = 0, minute = 0;
							AssistOrderDetailsActivity.year = 0;
							AssistOrderDetailsActivity.monthOfYear = 0;
							AssistOrderDetailsActivity.dayOfMonth = 0;
							finishActivity();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}).start();
				break;
		}
		return super.handleMessage(msg);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			/** 选择品牌商家 */
			case BRANDMANUFACTURERS:
				massistBrandManufacturers = (AssistBrandManufacturers) data.getSerializableExtra(AppFlag.BRANDMANUFACTURERS);
				if (massistBrandManufacturers.getBrand_name() == null) {
					massistBrandManufacturers = null;
					orderDetailsBrand.setText("请选择品牌厂家");
					orderDetailsBrand.setTextColor(getResources().getColor(R.color.line_d2d2d2));
				} else {
					orderDetailsBrand.setText(massistBrandManufacturers.getBrand_name());
					orderDetailsBrand.setTextColor(getResources().getColor(R.color.text_000000));
				}
				//品牌厂家改变之后其余信息全部清除
				massistMaterialType = null;
				orderDetailsType.setText("请选择材料类型");
				orderDetailsType.setTextColor(getResources().getColor(R.color.line_d2d2d2));
				mAssistSelectUnit = null;
				orderDetailsUnit.setText("请选择单位");
				orderDetailsUnit.setTextColor(getResources().getColor(R.color.line_d2d2d2));
				massistSpecificationsModels = null;
				orderDetailsModer.setText("请选择规格类型");
				orderDetailsModer.setTextColor(getResources().getColor(R.color.line_d2d2d2));
				orderDetailsPrice.setText("");
				orderDetailsTotalMoney.setText("");
				orderDetailsNumber.setInputType(InputType.TYPE_NULL);//禁止数量软键盘弹出
				break;
			/** 选择材料类型 */
			case MATERIALTYPE:
				massistMaterialType = (AssistMaterialType) data.getSerializableExtra(AppFlag.ASSISTMATERIALTYPE);
				if (massistMaterialType.getName() == null) {
					//没选择清空材料类型
					massistMaterialType = null;
					orderDetailsType.setText("请选择材料类型");
					orderDetailsType.setTextColor(getResources().getColor(R.color.line_d2d2d2));
				} else {
					orderDetailsType.setText(massistMaterialType.getName());
					orderDetailsType.setTextColor(getResources().getColor(R.color.text_000000));
				}
				//重选清空规格型号
				massistSpecificationsModels = null;
				mAssistSelectUnit = null;
				orderDetailsModer.setText("");
				orderDetailsUnit.setText("请选择单位");
				orderDetailsUnit.setTextColor(getResources().getColor(R.color.line_d2d2d2));
				orderDetailsPrice.setText("");
				orderDetailsTotalMoney.setText("");
				orderDetailsModer.setText("请选择规格类型");
				orderDetailsModer.setTextColor(getResources().getColor(R.color.line_d2d2d2));
				orderDetailsNumber.setInputType(InputType.TYPE_NULL);//禁止数量软键盘弹出
				break;
			/** 选择规格类型 */
			case SPECIFICATIONSMODELS:
				massistSpecificationsModels = (AssistSpecificationsModels) data.getSerializableExtra(AppFlag.ASSISTSPECIFICATIONSMODELS);
				if (massistSpecificationsModels.getNorms() == null) {
					//没选择清空原来规格型号信息
					orderDetailsModer.setText("请选择规格类型");
					orderDetailsModer.setTextColor(getResources().getColor(R.color.line_d2d2d2));
					massistSpecificationsModels = null;
				} else {
					orderDetailsModer.setText(massistSpecificationsModels.getNorms());
					orderDetailsModer.setTextColor(getResources().getColor(R.color.text_000000));
				}
				mAssistSelectUnit = null;
				orderDetailsUnit.setText("请选择单位");
				orderDetailsUnit.setTextColor(getResources().getColor(R.color.line_d2d2d2));
				orderDetailsPrice.setText("");
				orderDetailsTotalMoney.setText("");
				orderDetailsNumber.setInputType(InputType.TYPE_NULL);//禁止数量软键盘弹出
				break;
			/** 选择单位价格 */
			case SELECTUNIT:
				mAssistSelectUnit = (AssistSelectUnit) data.getSerializableExtra("selectunit");
				if (mAssistSelectUnit.getUnit() == null) {
					mAssistSelectUnit = null;
					orderDetailsUnit.setText("请选择单位" +"");
					orderDetailsUnit.setTextColor(getResources().getColor(R.color.line_d2d2d2));
					orderDetailsPrice.setText("");
					orderDetailsTotalMoney.setText("");
					orderDetailsNumber.setInputType(InputType.TYPE_NULL);//禁止数量软键盘弹出
				} else {
					//选了规格型号之后算价格
					if (mAssistSelectUnit.getAct_price() == null || "".equals(mAssistSelectUnit.getAct_price())) {
						ToastUtil.show(this, "单位价格为空,材料员填写的材料价格有误");
					} else {
						DecimalFormat df = new DecimalFormat("0.00");
						orderDetailsTotalMoney.setText(df.format(Double.parseDouble(mAssistSelectUnit.getAct_price()) * number) + "元");
						orderDetailsUnit.setText(mAssistSelectUnit.getUnit());
						orderDetailsUnit.setTextColor(getResources().getColor(R.color.text_000000));
						orderDetailsPrice.setText(mAssistSelectUnit.getAct_price() + "元");
						//开启软键盘
						orderDetailsNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
						//设置光标位置
						CharSequence text = orderDetailsNumber.getText();
						if (text instanceof Spannable) {
							Spannable spanText = (Spannable) text;
							Selection.setSelection(spanText, text.length());
						}
					}
				}
				break;
			/** 选择施工阶段 */
			case CONSTRUCTIONSTAGE:
				massistConstructionStage = (AssistConstructionStage) data.getSerializableExtra(AppFlag.CONSTRUCTIONSTAGE);
				if (massistConstructionStage.getName() == null) {
					//没选择清空施工阶段
					massistConstructionStage = null;
					order_details_construction_stage.setText("请选择施工阶段");
					order_details_construction_stage.setTextColor(getResources().getColor(R.color.line_d2d2d2));
				} else {
					order_details_construction_stage.setText(massistConstructionStage.getName());
					order_details_construction_stage.setTextColor(getResources().getColor(R.color.text_000000));
				}

				break;

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mHintDialog != null) {
			mHintDialog.dismiss();
		}
		if (mDateDialog != null) {
			mDateDialog.dismiss();
		}
		if (mMustDialog != null) {
			mMustDialog.dismiss();
		}
	}
}
