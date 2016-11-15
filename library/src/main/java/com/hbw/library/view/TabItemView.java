package com.hbw.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hbw.library.R;


/**
 * TabItemView
 *
 * @FileName: com.example.yangbang.miowner.view.TabItemView.java
 * @author: Yangbang
 * @date: 2015-12-18 16:31
 */
public class TabItemView extends LinearLayout implements View.OnClickListener {
    int selectedColor;//tab选中颜色
    int position;
    OnTabItemSelectedListener onTabItemSelectedListener;
    View tabItemView;
    LayoutInflater layoutInflater;
    private RelativeLayout rl_svs_re_tab_root;
    private TextView tvSvsReTabContent;
    private TextView tvSvsReTabHint;
    private View viewSvsReTabIndicator;

    public TabItemView(Context context) {
        super(context);
        this.selectedColor = context.getResources().getColor(R.color.red_e76170);
        this.setOrientation(HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        this.setLayoutParams(layoutParams);
        this.layoutInflater = LayoutInflater.from(context);
        initView();
    }

    public TabItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.selectedColor = context.getResources().getColor(R.color.red_e76170);
        this.setOrientation(HORIZONTAL);
        LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1);
        this.setLayoutParams(layoutParams);
        this.layoutInflater = LayoutInflater.from(context);
        initView();
    }

    private void initView() {
        tabItemView = layoutInflater.inflate(R.layout.supervision_report_tab_item, this);
        rl_svs_re_tab_root = (RelativeLayout) tabItemView.findViewById(R.id.rl_svs_re_tab_root);
        tvSvsReTabContent = (TextView) tabItemView.findViewById(R.id.tv_svs_re_tab_content);
        tvSvsReTabHint = (TextView) tabItemView.findViewById(R.id.tv_svs_re_tab_hint);
        viewSvsReTabIndicator = tabItemView.findViewById(R.id.view_svs_re_tab_indicator);
        setTabItemOnClickListener();
    }

    /**
     * 设置tabItem位置
     *
     * @param position
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * 取得TabItem位置
     *
     * @return
     */
    public int getPosition() {
        return position;
    }

    /**
     * 设置tabItem内容
     *
     * @param tabContent
     */
    public void setTabItemContent(String tabContent) {
        this.tvSvsReTabContent.setText(tabContent);
    }

    /**
     * 设置tabItem文字颜色
     *
     * @param color
     */
    public void setTabItemColor(int color) {
        this.tvSvsReTabContent.setTextColor(color);
    }

    /**
     * 设置tabItem文字大小
     *
     * @param size
     */
    public void setTabItemSize(int unit, float size) {
        this.tvSvsReTabContent.setTextSize(unit, size);
    }

    /**
     * 设置tabItem选中文字颜色
     *
     * @param color
     */
    public void setTabItemSelectedColor(int color) {
        this.selectedColor = color;
    }


    /**
     * 设置tabItem背景颜色
     *
     * @param color
     */
    public void setTabItemBackgroundColor(int color) {
        if (color != 0) {//为0则默认为白色
            this.rl_svs_re_tab_root.setBackgroundColor(color);
        }
    }

    /**
     * 设置下标指示器是否可见
     *
     * @param visibility
     */
    public void setIndicatorVisibility(int visibility) {
        this.viewSvsReTabIndicator.setVisibility(visibility);
    }

    /**
     * 刷新选中TabItem
     *
     * @param IndicatorVisibility
     */
    public void setRefreshSelectedItem(int IndicatorVisibility) {
        this.viewSvsReTabIndicator.setVisibility(IndicatorVisibility);
        this.tvSvsReTabContent.setTextColor(selectedColor);
    }

    /**
     * 设置tabItem下标指示器颜色
     *
     * @param color
     */
    public void setIndicatorColor(int color) {
        this.viewSvsReTabIndicator.setBackgroundColor(color);
    }

    /**
     * 设置tabItem消息提醒数量
     *
     * @param remindCount
     */
    public void setTabItemMsgRemind(int remindCount) {
        if (remindCount > 0) {
            tvSvsReTabHint.setVisibility(VISIBLE);
            tvSvsReTabHint.setText(remindCount + "");
        } else if (remindCount > 99) {
            tvSvsReTabHint.setVisibility(VISIBLE);
            tvSvsReTabHint.setText("99");
        } else {
            tvSvsReTabHint.setVisibility(GONE);
        }
    }

    private void setTabItemOnClickListener() {
        tabItemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tvSvsReTabContent.setTextColor(selectedColor);
                viewSvsReTabIndicator.setVisibility(VISIBLE);
                onTabItemSelectedListener.onTabSelected(getPosition());
            }
        });
    }

    public void setOnTabItemSelectedListener(OnTabItemSelectedListener listener) {
        this.onTabItemSelectedListener = listener;
    }

    /**
     * tabItem选中回调接口
     */
    public interface OnTabItemSelectedListener {
        void onTabSelected(int position);
    }

    @Override
    public void onClick(View v) {

    }
}
