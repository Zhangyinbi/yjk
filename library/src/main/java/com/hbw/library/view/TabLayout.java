package com.hbw.library.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.hbw.library.R;

import java.util.ArrayList;
import java.util.List;

/**
 * TabLayout
 *
 * @FileName: com.example.yangbang.miowner.view.TabItemView.java
 * @author: Yangbang
 * @date: 2015-12-18 15:58
 */
public class TabLayout extends LinearLayout implements TabItemView.OnTabItemSelectedListener {
    int position = 0;//当前tab位置,默认位置为第一个
    int defaultColor;
    OnTabSelectedListener onTabSelectedListener;
    LayoutInflater layoutInflater;
    List<TabItemView> tabItemViews = new ArrayList<>();

    public TabLayout(Context context) {
        super(context);
        this.setOrientation(HORIZONTAL);
        this.defaultColor = context.getResources().getColor(R.color.gray_959595);
        this.layoutInflater = LayoutInflater.from(context);

    }

    public TabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.setOrientation(HORIZONTAL);
        this.defaultColor = context.getResources().getColor(R.color.gray_959595);
        this.layoutInflater = LayoutInflater.from(context);
    }

    /**
     * 添加一个tabItem
     *
     * @param tabItemContent tabItem内容
     * @param position       tabItem位置
     */
    public void newTabItem(String tabItemContent, int position) {
        TabItemView tabItemView = new TabItemView(getContext());
        tabItemView.setTabItemContent(tabItemContent);
        tabItemView.setPosition(position);
        tabItemView.setOnTabItemSelectedListener(this);
        tabItemViews.add(position, tabItemView);
        this.addView(tabItemView, position);
        setOnTabSelectedListener(this.position);
        tabItemViews.get(this.position).setRefreshSelectedItem(VISIBLE);
    }

    public void setTabItemBackgroundColor(int color) {
        for (TabItemView tabItemView : tabItemViews) {
            tabItemView.setTabItemBackgroundColor(color);
        }
    }

    public void setTabLayoutBackgroundColor(int color) {
        this.setBackgroundColor(color);
    }

    /**
     * 设置TabItem文字大小
     *
     * @param size
     */
    public void setTabTextSize(int unit, float size) {
        for (TabItemView tabItemView : tabItemViews) {
            tabItemView.setTabItemSize(unit, size);
        }
        tabItemViews.get(this.position).setRefreshSelectedItem(VISIBLE);
    }

    /**
     * 设置tabItem文字颜色
     *
     * @param color
     */
    public void setTabTextColor(int color) {
        this.defaultColor = color;
        for (TabItemView tabItemView : tabItemViews) {
            tabItemView.setTabItemColor(color);
        }
        tabItemViews.get(this.position).setRefreshSelectedItem(VISIBLE);
    }

    /**
     * 设置TabItem文字选中后颜色
     *
     * @param color
     */
    public void setTabTextSelectedColor(int color) {
        for (TabItemView tabItemView : tabItemViews) {
            tabItemView.setTabItemSelectedColor(color);
        }
        tabItemViews.get(this.position).setRefreshSelectedItem(VISIBLE);
    }

    /**
     * 设置TabItem下标指示器颜色
     *
     * @param color
     */
    public void setTabIndicatorColor(int color) {
        for (TabItemView tabItemView : tabItemViews) {
            tabItemView.setIndicatorColor(color);
        }
        tabItemViews.get(this.position).setRefreshSelectedItem(VISIBLE);
    }

    /**
     * 设置tabItem消息提醒数量
     *
     * @param position
     * @param remindCount
     */
    public void setTabMsgRemind(int position, int remindCount) {
        tabItemViews.get(position).setTabItemMsgRemind(remindCount);
    }

    private void setOnTabSelectedListener(int position) {
        this.position = position;
        if (this.onTabSelectedListener != null)
            this.onTabSelectedListener.onTabSelected(position);
        for (int i = 0; i < tabItemViews.size(); i++) {
            if (i != position) {
                tabItemViews.get(i).setTabItemColor(defaultColor);
                tabItemViews.get(i).setIndicatorVisibility(GONE);
            }
        }
    }

    /**
     * 设置当前tab选中位置
     *
     * @param position
     */
    public void setCurrentPosition(int position) {
        setOnTabSelectedListener(position);
    }

    /**
     * 获取当前选中位置
     *
     * @return
     */
    public int getCurrentPosition() {
        return position;
    }

    /**
     * 设置tabItem选中监听
     *
     * @param listener
     */
    public void setOnTabSelectedListener(OnTabSelectedListener listener) {
        this.onTabSelectedListener = listener;
    }

    @Override
    public void onTabSelected(int position) {
        setOnTabSelectedListener(position);
    }

    /**
     * tabItem选中回调接口
     */
    public interface OnTabSelectedListener {
        void onTabSelected(int position);
    }
}
