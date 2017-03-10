package com.xiaomizhuang.buildcaptain.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.DigitsKeyListener;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.AssistOrderDetailsActivity;
import com.xiaomizhuang.buildcaptain.entity.AssistAdvocateMaterialOrder;
import com.xiaomizhuang.buildcaptain.entity.AssistMaterialOrder;
import com.xiaomizhuang.buildcaptain.view.AddAndSubView;
import com.xiaomizhuang.buildcaptain.view.DateDialog;
import com.xiaomizhuang.buildcaptain.view.PopMaterialOrder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by user on 2016/1/19.
 */
public class AssistAdvocateMaterialOrderAdapter extends AbsBaseAdapter<AssistAdvocateMaterialOrder> {

    private int index = -1;//记录点击的列表项位置
    private Context context;
    private List<AssistMaterialOrder> orderlists;//订单
    private TotalPrice mTotalPrice;//数量改变监听对象
    private Activity activity;

    public AssistAdvocateMaterialOrderAdapter(Activity activity, Context context, List<AssistAdvocateMaterialOrder> dataList, List<AssistMaterialOrder> orderlists, TotalPrice mTotalPrice) {
        super(dataList);
        this.context = context;
        this.orderlists = orderlists;
        this.mTotalPrice = mTotalPrice;
        this.activity = activity;
    }

    @Override
    public View getView( int position, View v, ViewGroup parent) {
        //条目数量不会太多，所以这里不考虑重用了，因为重用会导致数据错乱
        v = View.inflate(context, R.layout.list_item_material_order, null);
        LinearLayout order_item_choose_LinearLayout = (LinearLayout) v.findViewById(R.id.order_item_choose_LinearLayout);
        ImageView order_item_choose_img = (ImageView) v.findViewById(R.id.order_item_choose_img);
        TextView order_item_title = (TextView) v.findViewById(R.id.order_item_title);
        TextView order_item_price = (TextView) v.findViewById(R.id.order_item_price);
        TextView order_item_unit = (TextView) v.findViewById(R.id.order_item_unit);
        TextView order_item_serial_number = (TextView) v.findViewById(R.id.order_item_serial_number);
        LinearLayout order_item_details_LinearLayout = (LinearLayout) v.findViewById(R.id.order_item_details_LinearLayout);
        //2016.2.26增加日期选择
        LinearLayout order_details_delivery_time_LinearLayout = (LinearLayout) v.findViewById(R.id.order_details_delivery_time_LinearLayout);
        order_details_delivery_time_LinearLayout.setVisibility(LinearLayout.VISIBLE);
        TextView order_details_delivery_time = (TextView) v.findViewById(R.id.order_details_delivery_time);
        order_details_delivery_time_LinearLayout.setOnClickListener(new MyOnClickListener(position));

        //设置自定义AddAndSubView属性
        AddAndSubView order_item_addandsubview = (AddAndSubView) v.findViewById(R.id.order_item_addandsubview);
	    order_item_addandsubview.setInputType(InputType.TYPE_NUMBER_FLAG_SIGNED);
	    order_item_addandsubview.setKeyListener(DigitsKeyListener.getInstance("0123456789."));
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
        order_item_title.setText(dataList.get(position).getMaterial_name());
        order_item_serial_number.setText("订单编号: " + dataList.get(position).getId());
        if(orderlists.get(position).getOrdertime() != null || "".equals(orderlists.get(position).getOrdertime())){
            order_details_delivery_time.setText(orderlists.get(position).getOrdertime());
        }

        order_item_unit.setText(dataList.get(position).getUnit());
        order_item_price.setText(dataList.get(position).getAct_price());


        if (!"".equals(orderlists.get(position).getAct_count())) {
            editText.setText(orderlists.get(position).getAct_count());
        }
        if (dataList.get(position).isChoose()) {
            order_item_choose_img.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.ic_choose)).getBitmap());
        } else {
            order_item_choose_img.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.nochoose)).getBitmap());
        }
        //设置事件
        order_item_choose_LinearLayout.setOnTouchListener(new mOnTouchListener(position));
        order_item_choose_LinearLayout.setOnClickListener(new mOnClickListener(position, order_item_choose_img));
        addButton.setOnTouchListener(new mOnTouchListener(position));
        subButton.setOnTouchListener(new mOnTouchListener(position));
        editText.setOnTouchListener(new mOnTouchListener(position));
        editText.addTextChangedListener(new mTextWatcher(position));
        order_item_details_LinearLayout.setOnClickListener(new order_item_details_LinearLayout_listener(this, position));
        editText.clearFocus();
        if (index != -1 && index == position) {
            // 如果当前的行下标和点击事件中保存的index一致，手动为EditText设置焦点。
            editText.requestFocus();
        }
        return v;
    }

    public void notifyDataSetChanged(List<AssistAdvocateMaterialOrder> dataList, List<AssistMaterialOrder> orderlists) {
        if (dataList != null && dataList.size() > 0) {
            this.dataList = dataList;
        } else {
            this.dataList = new ArrayList<AssistAdvocateMaterialOrder>();
        }
        if (orderlists != null && orderlists.size() > 0) {
            this.orderlists = orderlists;
        } else {
            this.orderlists = new ArrayList<AssistMaterialOrder>();
        }
        notifyDataSetChanged();
        //全选与不全选也要调用重新计算总价
        mTotalPrice.TotalPriceChange();
    }

    //list每项数量发生变化监听接口,用于计算总价
    public interface TotalPrice {
        void TotalPriceChange();
    }

    public List<AssistMaterialOrder> getOrderlists() {
        return orderlists;
    }

    public void setOrderlists(List<AssistMaterialOrder> orderlists) {
        this.orderlists = orderlists;
    }

    class mTextWatcher implements TextWatcher {

        private int position;

        public mTextWatcher(int position) {
            this.position = position;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence text, int start, int before, int count) {
            if (index != -1 && index == position) {
                // 在这个地方添加你的保存文本内容的代码，如果不保存，你就等着重新输入吧
                // 而且不管你输入多少次，也不会有用的，因为getView全清了～～
                if (!"".equals(text.toString()) && text.toString() != null) {
//                    Log.e("TextWatcher-->", "count = " + text.toString() +
//                            ",price = " + dataList.get(position).getAct_price() +
//                            ",countPrice = " + Integer.parseInt(text.toString()) * Float.parseFloat(dataList.get(position).getAct_price()) +
//                            ",position = " + position);
                    //设置订单数据（数量,总价）
                    orderlists.get(position).setAct_count(text.toString());

                    float shl = orderlists.get(position).getShl();
                    float shsl = (shl+1)*Float.parseFloat(text.toString());
                    orderlists.get(position).setShl_count((shsl));
                    orderlists.get(position).setAct_total_price(Float.parseFloat(dataList.get(position).getAct_price())*shsl+"");
                } else {
                    //如果数量为null,将总价设置为null，数量设置为0
                    orderlists.get(position).setAct_count("0");
                    orderlists.get(position).setAct_total_price("0");
                }
                mTotalPrice.TotalPriceChange();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    class mOnTouchListener implements View.OnTouchListener {

        private int position;

        public mOnTouchListener(int position) {
            this.position = position;
        }

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            // 在TOUCH的UP事件中，要保存当前的行下标，因为弹出软键盘后，整个画面会被重画
            // 在getView方法的最后，要根据index和当前的行下标手动为EditText设置焦点
            if (event.getAction() == MotionEvent.ACTION_UP) {
                index = position;
            }
            return false;
        }
    }

    class mOnClickListener implements View.OnClickListener {

        private int position;
        private ImageView order_item_choose_img;

        public mOnClickListener(int position, ImageView order_item_choose_img) {
            this.position = position;
            this.order_item_choose_img = order_item_choose_img;
        }

        @Override
        public void onClick(View v) {
            if (index != -1 && index == position) {
                if (dataList.get(position).isChoose()) {
                    dataList.get(position).setIsChoose(false);
                    order_item_choose_img.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.nochoose)).getBitmap());
                } else {
                    dataList.get(position).setIsChoose(true);
                    order_item_choose_img.setImageBitmap(((BitmapDrawable) context.getResources().getDrawable(R.mipmap.ic_choose)).getBitmap());
                }
                //选中与不选中同意要计算总价
                mTotalPrice.TotalPriceChange();
            }
        }
    }

    class order_item_details_LinearLayout_listener implements View.OnClickListener {

        private AssistAdvocateMaterialOrderAdapter mAssistAdvocateMaterialOrderAdapter;
        private int position;

        public order_item_details_LinearLayout_listener(AssistAdvocateMaterialOrderAdapter mAssistAdvocateMaterialOrderAdapter, int position) {
            this.mAssistAdvocateMaterialOrderAdapter = mAssistAdvocateMaterialOrderAdapter;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            PopMaterialOrder popTelphone = new PopMaterialOrder(activity, dataList, orderlists, position, mTotalPrice, mAssistAdvocateMaterialOrderAdapter);
            popTelphone.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        }
    }

    //显示时间选择框
    class MyOnClickListener implements View.OnClickListener {

        private int position;
        private DateDialog mDateDialog = null;
        private DatePicker mDatePicker = null;

        public MyOnClickListener(int position) {
            this.position = position;
            //获取到系统的时间
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int monthOfYear = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
            int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            if (mDateDialog == null) {
                mDateDialog = new DateDialog(context);
            }
            mDateDialog.setOrderDetailsTimePickerVisible(View.GONE);
            mDateDialog.setCancelListener(this).setConfirmListener(this);
            mDatePicker = mDateDialog.setDatePickerChangedListener(year, monthOfYear, dayOfMonth, new DatePicker.OnDateChangedListener() {
                @Override
                public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {

                }
            });
            mDatePicker.setMinDate(calendar.getTimeInMillis());
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.order_details_delivery_time_LinearLayout:
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
                    orderlists.get(position).setOrdertime(mDatePicker.getYear() + "-" + (mDatePicker.getMonth() + 1) + "-" + mDatePicker.getDayOfMonth());
                    notifyDataSetChanged(dataList, orderlists);
                    break;
            }

        }
    }

}
