package com.xiaomizhuang.buildcaptain.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xiaomizhuang.buildcaptain.R;

/**
 * emptyView
 *
 * @FileName: com.xiaomizhuang.buildcaptain.view.MyEmptyView.java
 * @author: Yangbang
 * @date: 2016-02-19 10:37
 */
public class MyEmptyView {
    Context context;
    View emptyView;
    ImageView img_empty_view_icon;
    TextView tv_empty_view_content;

    public MyEmptyView(Context context) {
        this.context = context;
        initEmptyView();
    }

    private void initEmptyView() {
        this.emptyView = LayoutInflater.from(context).inflate(R.layout.empty_view, null);
        img_empty_view_icon = (ImageView) emptyView.findViewById(R.id.img_empty_view_icon);
        tv_empty_view_content = (TextView) emptyView.findViewById(R.id.tv_empty_view_content);
        emptyView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
    }

    /**
     * 设置emptyView显示图片
     *
     * @param resId
     */
    public void setEmptyViewImg(int resId) {
        img_empty_view_icon.setImageResource(resId);
    }

    /**
     * 设置emptyView提示内容
     *
     * @param strContent
     */
    public void setEmptyViewContent(String strContent) {
        tv_empty_view_content.setText(strContent);
    }

    /**
     * 取得emptyview
     *
     * @return
     */
    public View getEmptyView() {
        return emptyView;
    }

    /**
     * 设置emtypView图片点击事件监听
     *
     * @param listener
     */
    public void setEmptyViewImgOnClickListener(View.OnClickListener listener) {
        if (listener != null) {
            img_empty_view_icon.setOnClickListener(listener);
        }
    }

}
