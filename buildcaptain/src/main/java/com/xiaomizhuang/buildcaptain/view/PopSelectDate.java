package com.xiaomizhuang.buildcaptain.view;

import android.app.Activity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hbw.library.BasePopupWindow;
import com.xiaomizhuang.buildcaptain.R;

/**
 * 日期选择pop
 *
 * @FileName: com.xiaomizhuang.buildcaptain.view.PopSelectDate.java
 * @author: Yangbang
 * @date: 2016-01-13 14:26
 */
public class PopSelectDate extends BasePopupWindow implements View.OnClickListener {
    private TextView tvPopSelectAllDate;
    private TextView tvPopSelectDate;
    private LinearLayout llPopSelectDataContent;
    private DatePicker dpPopSelectDate;
    private TextView tvPopSelectDataAffirm;
    private View view_pop_select_date_translucence_layer;
    private OnAffirmDataListener onAffirmDataListener;
    private ImageView imageView;

    public PopSelectDate(Activity activity, ImageView imageView) {
        super(activity);
        this.imageView = imageView;
        initPop();
    }

    private void initPop() {
        setIsAlpha(false);
        assignViews();
    }

//    if (isSelect.get(position)) {
//        tv_item.setTextColor(context.getResources().getColor(R.color.white));
//        tv_item.setBackgroundResource(R.drawable.shape_red_solid_stroke);
//    } else {
//        tv_item.setTextColor(context.getResources().getColor(R.drawable.selector_white_text_bg));
//        tv_item.setBackgroundResource(R.drawable.selector_red_bg);
//    }


    private void assignViews() {
        tvPopSelectAllDate = (TextView) getContentView().findViewById(R.id.tv_pop_select_all_date);
        tvPopSelectDate = (TextView) getContentView().findViewById(R.id.tv_pop_select_date);
        llPopSelectDataContent = (LinearLayout) getContentView().findViewById(R.id.ll_pop_select_data_content);
        dpPopSelectDate = (DatePicker) getContentView().findViewById(R.id.dp_pop_select_date);
        tvPopSelectDataAffirm = (TextView) getContentView().findViewById(R.id.tv_pop_select_data_affirm);
        view_pop_select_date_translucence_layer = getContentView().findViewById(R.id.view_pop_select_date_translucence_layer);
        tvPopSelectAllDate.setOnClickListener(this);
        tvPopSelectDate.setOnClickListener(this);
        tvPopSelectDataAffirm.setOnClickListener(this);
        view_pop_select_date_translucence_layer.setOnClickListener(this);
        tvPopSelectAllDate.setTextColor(activity.getResources().getColor(R.color.white));
        tvPopSelectAllDate.setBackgroundResource(R.drawable.shape_red_solid_stroke);
    }

    public void setOnAffirmDataListener(OnAffirmDataListener onAffirmDataListener) {
        this.onAffirmDataListener = onAffirmDataListener;
    }

    @Override
    public void dismiss() {
        imageView.setImageResource(R.mipmap.ic_build_greyarrowdown);
        super.dismiss();
    }

    @Override
    protected int inItContentViewId() {
        return R.layout.pop_select_date;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_pop_select_all_date://全部时间
                tvPopSelectAllDate.setTextColor(activity.getResources().getColor(R.color.white));
                tvPopSelectAllDate.setBackgroundResource(R.drawable.shape_red_solid_stroke);
                tvPopSelectDate.setTextColor(activity.getResources().getColor(R.color.gray_7d7d7d));
//                tvPopSelectDate.setTextColor(activity.getResources().getColor(R.drawable.selector_white_text_bg));
                tvPopSelectDate.setBackgroundResource(R.drawable.selector_red_bg);
                llPopSelectDataContent.setVisibility(View.GONE);
                if (onAffirmDataListener != null) {
                    onAffirmDataListener.onAffirmData("");
                }
                dismiss();
                break;
            case R.id.tv_pop_select_date://选择时间
                tvPopSelectDate.setTextColor(activity.getResources().getColor(R.color.white));
                tvPopSelectDate.setBackgroundResource(R.drawable.shape_red_solid_stroke);
                tvPopSelectAllDate.setTextColor(activity.getResources().getColor(R.color.gray_7d7d7d));
//                tvPopSelectAllDate.setTextColor(activity.getResources().getColor(R.drawable.selector_white_text_bg));
                tvPopSelectAllDate.setBackgroundResource(R.drawable.selector_red_bg);
                if (llPopSelectDataContent.getVisibility() == View.GONE) {
                    llPopSelectDataContent.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.tv_pop_select_data_affirm://确定时间
                if (onAffirmDataListener != null) {
                    String month = (dpPopSelectDate.getMonth() + 1) + "";
                    String day = dpPopSelectDate.getDayOfMonth() + "";
                    if (month.length() == 1) {
                        month = "0" + month;
                    }
                    if (day.length() == 1) {
                        day = "0" + day;
                    }
                    onAffirmDataListener.onAffirmData(dpPopSelectDate.getYear() + "-" + month + "-" + day);
                }
                dismiss();
                break;
            case R.id.view_pop_select_date_translucence_layer:
                dismiss();
                break;
        }
    }

    public interface OnAffirmDataListener {
        void onAffirmData(String data);
    }

}
