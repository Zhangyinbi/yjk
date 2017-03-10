package com.xiaomizhuang.buildcaptain.view;

import android.app.Activity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hbw.library.BasePopupWindow;
import com.hbw.library.net.AnalyzeJson;
import com.hbw.library.utils.L;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;

import java.util.HashMap;

import static com.hbw.library.BaseActivity.REQUEST_SUCCESS;

/**
 * Created by Administrator on 2016/11/7.
 */

public class PopIsExit extends BasePopupWindow implements View.OnClickListener {

    private Button exit;
    private Button cancel;
    AnalyzeJson analyzeJson;
    private HashMap<String, String> mMap = new HashMap<String, String>();
    private View view_pop_material_type_translucence_layer;
    private LinearLayout ll_layout;
    Activity activity;
    public PopIsExit(Activity activity,AnalyzeJson analyzeJson) {
        super(activity);
        this.activity=activity;
        this.analyzeJson=analyzeJson;
        setIsAlpha(false);
        initView();
    }

    private void initView() {
        initPopView();
//        setAnimationStyle(R.style.PopupAnimation);//设置popwin动画
    }

    private void initPopView() {
        view_pop_material_type_translucence_layer = getContentView().findViewById(R.id.view_pop_material_type_translucence_layer);
        view_pop_material_type_translucence_layer.setOnClickListener(this);
        Animation animation1= AnimationUtils.loadAnimation(activity,R.anim.bacgroundalpha);
        view_pop_material_type_translucence_layer.startAnimation(animation1);
        exit = (Button) getContentView().findViewById(R.id.btn_exit);
        exit.setOnClickListener(this);
        cancel = (Button) getContentView().findViewById(R.id.btn_cancel);
        cancel.setOnClickListener(this);
        ll_layout = (LinearLayout) getContentView().findViewById(R.id.ll_layout);
        Animation animation= AnimationUtils.loadAnimation(activity,R.anim.popup_enter);
        ll_layout.startAnimation(animation);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_exit:
                L.e("-----------","确定");
                dismiss();
                mMap.put("token", MyApplication.TOKEN);
                analyzeJson.requestData(HttpConstant.LogOutUrl, mMap, REQUEST_SUCCESS);
                break;
            case R.id.btn_cancel:
                dismiss();
                break;
            case R.id.view_pop_material_type_translucence_layer:
                dismiss();
                break;
        }

    }

    @Override
    protected int inItContentViewId() {
        return R.layout.isexitpopuwindow;
    }
}
