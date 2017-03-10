package com.xiaomizhuang.buildcaptain.view;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbw.library.BasePopupWindow;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AssistProgectAddMatterOrderAdapter;
import com.xiaomizhuang.buildcaptain.entity.AssistProgectAddMatterOrder;
import com.xiaomizhuang.buildcaptain.entity.AssistProgectAddMatterSubmitOrder;

import java.util.List;

public class PopProgectAddMatterOrderOrder extends BasePopupWindow implements View.OnClickListener {

    private AssistProgectAddMatterOrder mAssistAdvocateMaterialOrder;
    private AssistProgectAddMatterSubmitOrder mAssistMaterialOrder;
    private Context context;
    private TextView order_item_all_money;
    private AssistProgectAddMatterOrderAdapter.TotalPrice mTotalPrice;
    public String numbers;//数量
    public String countPrices;//总价
    public float shsl; //损耗数量
    private AssistProgectAddMatterOrderAdapter mAssistAdvocateMaterialOrderAdapter;
    private List<AssistProgectAddMatterOrder> dataList;
    private List<AssistProgectAddMatterSubmitOrder> orderlists;
    private EditText order_item_remark;
    private TextView tv_shsl; //损耗数量
    private float shl; //损耗率

    public PopProgectAddMatterOrderOrder(Activity context, List<AssistProgectAddMatterOrder> dataList, List<AssistProgectAddMatterSubmitOrder> orderlists, int postition,
                                         AssistProgectAddMatterOrderAdapter.TotalPrice mTotalPrice,
                                         AssistProgectAddMatterOrderAdapter mAssistAdvocateMaterialOrderAdapter) {
        super(context);
        this.context = context;
        this.mAssistAdvocateMaterialOrder = dataList.get(postition);
        this.mAssistMaterialOrder = orderlists.get(postition);
        this.mTotalPrice = mTotalPrice;
        this.mAssistAdvocateMaterialOrderAdapter = mAssistAdvocateMaterialOrderAdapter;
        this.dataList = dataList;
        this.orderlists = orderlists;
        initView();
    }

    private void initView() {
        initPopView();
        setAnimationStyle(R.style.PopupAnimation);//设置popwin动画
    }

    @Override
    protected int inItContentViewId() {
        return R.layout.pop_material_order_view;
    }

    private void initPopView() {
        LinearLayout order_item_close_LinearLayout = (LinearLayout) getContentView().findViewById(R.id.order_item_close_LinearLayout);//关闭窗口
        TextView order_item_title = (TextView) getContentView().findViewById(R.id.order_item_title);
        TextView order_item_price = (TextView) getContentView().findViewById(R.id.order_item_price);
        TextView order_item_unit = (TextView) getContentView().findViewById(R.id.order_item_unit);
        TextView order_item_serial_number = (TextView) getContentView().findViewById(R.id.order_item_serial_number);//订单编号
        order_item_remark = (EditText) getContentView().findViewById(R.id.order_item_remark);//备注
        TextView order_item_brand = (TextView) getContentView().findViewById(R.id.order_item_brand);
        TextView order_item_type = (TextView) getContentView().findViewById(R.id.order_item_type);
        TextView order_item_norms = (TextView) getContentView().findViewById(R.id.order_item_norms);
        order_item_all_money = (TextView) getContentView().findViewById(R.id.order_item_all_money);//总价
        Button order_submit = (Button) getContentView().findViewById(R.id.order_submit);//确定
        LinearLayout order_item_remark_LinearLayout = (LinearLayout) getContentView().findViewById(R.id.order_item_remark_LinearLayout);
        TextView beizhu = (TextView) getContentView().findViewById(R.id.order_item_beizhu);//备注

        final TextView tv_shl = (TextView) getContentView().findViewById(R.id.tv_shl);  //损耗率
        tv_shsl = (TextView) getContentView().findViewById(R.id.tv_shsl); //损耗数量  损耗数量=（数量*损耗率）+ 数量

        //设置自定义AddAndSubView属性
        AddAndSubView order_item_addandsubview = (AddAndSubView) getContentView().findViewById(R.id.order_item_addandsubview);
        order_item_addandsubview.setButtonBgDrawable(context.getResources().getDrawable(R.mipmap.add), context.getResources().getDrawable(R.mipmap.line));
        order_item_addandsubview.getEditText().setBackgroundColor(context.getResources().getColor(R.color.background_dcdcdc));
        LinearLayout.LayoutParams viewLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        viewLayoutParams.setMargins(6, 0, 12, 0);
        order_item_addandsubview.getEditText().setLayoutParams(viewLayoutParams);
        order_item_addandsubview.setButtonLayoutParm(40, 40);
        order_item_addandsubview.setEditTextLayoutHeight(40);
        order_item_addandsubview.setEditTextMinimumWidth(70);
        order_item_addandsubview.setEditTextLayoutWidth(70);

        EditText editText = order_item_addandsubview.getEditText();
        Button addButton = order_item_addandsubview.getAddButton();
        Button subButton = order_item_addandsubview.getSubButton();

        //设置数据
        order_item_remark_LinearLayout.setVisibility(LinearLayout.GONE);
        order_item_title.setText(mAssistAdvocateMaterialOrder.getName());
//        order_item_serial_number.setText("订单编号: " + mAssistAdvocateMaterialOrder.getId());
        order_item_unit.setText(mAssistAdvocateMaterialOrder.getUnit());
        order_item_price.setText(mAssistAdvocateMaterialOrder.getPrice());
        order_item_brand.setText(mAssistAdvocateMaterialOrder.getBrand_name());
        order_item_type.setText(mAssistAdvocateMaterialOrder.getMtype_name());
        order_item_norms.setText(mAssistAdvocateMaterialOrder.getNorms());
        tv_shl.setText(mAssistAdvocateMaterialOrder.getShl());

        shl = Float.parseFloat(
                mAssistAdvocateMaterialOrder.getShl()
                        .substring(0,mAssistAdvocateMaterialOrder.getShl().indexOf('%')))/100;
        tv_shsl.setText(Integer.parseInt(mAssistMaterialOrder.getCount())*(1+shl)+"");
        shsl = Integer.parseInt(mAssistMaterialOrder.getCount())*(1+shl);

        order_item_all_money.setText(shsl * Float.parseFloat(mAssistAdvocateMaterialOrder.getPrice()) + "元");
//        order_item_remark.setText(mAssistAdvocateMaterialOrder.getBeizhu());
        beizhu.setText(mAssistAdvocateMaterialOrder.getBeizhu());

        numbers = mAssistMaterialOrder.getCount();
        countPrices = mAssistAdvocateMaterialOrder.getPrice();

        if (!"".equals(mAssistMaterialOrder.getCount())) {
            editText.setText(mAssistMaterialOrder.getCount());
        }

        //设置事件
        order_item_close_LinearLayout.setOnClickListener(this);
        order_submit.setOnClickListener(this);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                if (!"".equals(text.toString()) && text.toString() != null) {
                    //设置订单数据（数量,总价）
                    numbers = text.toString();
                    shsl = Float.parseFloat(text.toString())*(1+shl);
                    tv_shsl.setText(shsl+"");
                    countPrices = shsl * Float.parseFloat(mAssistAdvocateMaterialOrder.getPrice()) + "";
                    order_item_all_money.setText(shsl * Float.parseFloat(mAssistAdvocateMaterialOrder.getPrice()) + "元");
                } else {
                    //如果数量为null,将总价设置为null，数量设置为0
                    numbers = "0";
                    countPrices = "0";
                    shsl= 0;
                    tv_shsl.setText(shsl+"");
                    order_item_all_money.setText("0元");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.order_item_close_LinearLayout://关闭窗口
                this.dismiss();
                break;
            case R.id.order_submit://确定
                this.dismiss();
                mAssistMaterialOrder.setCount(numbers);
//                mAssistMaterialOrder.setBeizhu(order_item_remark.getText().toString());
                mAssistMaterialOrder.setTotalprice(countPrices);
                mAssistAdvocateMaterialOrderAdapter.notifyDataSetChanged(dataList,orderlists);
                mTotalPrice.TotalPriceChange();
                break;
        }
    }
}