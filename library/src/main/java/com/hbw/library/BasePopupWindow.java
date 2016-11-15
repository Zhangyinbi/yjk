package com.hbw.library;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * BasePopupWindow
 *
 * @FileName: com.hbw.library.BasePopupWindow.java
 * @author: Yangbang
 * @date: 2016-01-13 13:53
 */
public abstract class BasePopupWindow extends PopupWindow {
    protected Activity activity;
    WindowManager.LayoutParams lp;
    boolean isAlpha = true;//是否设置透明度

    public BasePopupWindow(Activity activity) {
        this.activity = activity;
        this.lp = activity.getWindow().getAttributes();
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        setContentView(inflater.inflate(inItContentViewId(), null));
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
//        setWidth(WindowManager.LayoutParams.MATCH_PARENT);
//        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new ColorDrawable(activity.getResources().getColor(android.R.color.transparent)));
    }

    protected abstract int inItContentViewId();

    public boolean isAlpha() {
        return isAlpha;
    }

    public void setIsAlpha(boolean isAlpha) {
        this.isAlpha = isAlpha;
    }

    @Override
    public void dismiss() {

        if (isAlpha) {
            lp.alpha = 1.0f;
            activity.getWindow().setAttributes(lp);
        }
        super.dismiss();
    }

    @Override
    public void showAsDropDown(View anchor) {
        if (isAlpha) {
            lp.alpha = 0.5f;
            activity.getWindow().setAttributes(lp);
        }
        super.showAsDropDown(anchor);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff) {
        if (isAlpha) {
            lp.alpha = 0.5f;
            activity.getWindow().setAttributes(lp);
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }

    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff, int gravity) {
        if (isAlpha) {
            lp.alpha = 0.5f;
            activity.getWindow().setAttributes(lp);
        }
        super.showAsDropDown(anchor, xoff, yoff, gravity);
    }

    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        if (isAlpha) {
            lp.alpha = 0.5f;
            activity.getWindow().setAttributes(lp);
        }
        super.showAtLocation(parent, gravity, x, y);
    }
}
