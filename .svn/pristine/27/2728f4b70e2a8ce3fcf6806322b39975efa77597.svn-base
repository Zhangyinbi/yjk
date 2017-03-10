package com.example.yangbang.miowner.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.yangbang.miowner.R;


/**
 * 弹窗，选择选取图库照片或者选择相册
 *
 * @author hebiwen
 */
public class PopwChange extends PopupWindow {

    private View view;
    private TextView textView_pop_chang_camera, textView_pop_chang_photo, textView_pop_window_cancel;

    public PopwChange(Activity context, OnClickListener clickListener) {
        super(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.popwindow_picture, null);
        textView_pop_chang_camera = (TextView) view.findViewById(R.id.textView_pop_chang_camera);
        textView_pop_chang_photo = (TextView) view.findViewById(R.id.textView_pop_chang_photo);
        textView_pop_window_cancel = (TextView) view.findViewById(R.id.textView_pop_window_cancel);
        textView_pop_chang_camera.setOnClickListener(clickListener);
        textView_pop_chang_photo.setOnClickListener(clickListener);
        textView_pop_window_cancel.setOnClickListener(clickListener);
        setContentView(view);
        this.setWidth(LayoutParams.MATCH_PARENT);
        this.setHeight(LayoutParams.WRAP_CONTENT);
    }
}
