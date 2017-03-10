package com.example.yangbang.miowner.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.OwnerProjectSchedul;
import com.example.yangbang.miowner.util.HttpConstant;
import com.example.yangbang.miowner.util.MyApplication;
import com.example.yangbang.miowner.view.ColorfulRingProgressView;
import com.hbw.library.BaseActivity;
import com.hbw.library.entity.ResponseSucceedData;

import java.util.HashMap;

/**
 * Created by user on 2015/12/21.工程排期&欢迎界面
 */
public class OwnerProjectSchedulActivity extends BaseActivity implements View.OnClickListener {

    private TextView projectSchedulDays;
    private TextView projectSchedulProjectphase;
    private TextView projectSchedulStartDay;
    private TextView projectSchedulEndDay;
    private ColorfulRingProgressView project_schedul_crpv = null;
    private Button project_schedul_button;
    private HashMap<String, String> mMap = new HashMap<String, String>();
    private ObjectAnimator anim = null;

    @Override
    protected void initWidget() {
        titleBar.setTitleText("工程排期");
        mMap.put("token", MyApplication.getApp().getOwnerUser().getToken());
        analyzeJson.requestData(HttpConstant.PROJECT_SCHEDULING_URL, mMap, REQUEST_SUCCESS);

        projectSchedulDays = (TextView) findViewById(R.id.project_schedul_days);
        projectSchedulProjectphase = (TextView) findViewById(R.id.project_schedul_projectphase);
        projectSchedulStartDay = (TextView) findViewById(R.id.project_schedul_start_day);
        projectSchedulEndDay = (TextView) findViewById(R.id.project_schedul_end_day);

        project_schedul_crpv = (ColorfulRingProgressView) findViewById(R.id.project_schedul_crpv);
	    project_schedul_button = (Button) findViewById(R.id.project_schedul_button);
        Intent intent = getIntent();
        //如果是从“我的”界面 进入 显示返回按钮 隐藏施工进度按钮
        String from = intent.getStringExtra("from");
        if (from !=null && "FragmentMe".equals(from)){
            titleBar.setLeftImgDefaultBack(this);
            project_schedul_button.setVisibility(View.GONE);
        }else {
            project_schedul_button.setOnClickListener(this);
        }

    }

    @Override
    protected void initData() {

    }

    @Override
    protected int initPageLayoutID() {
        return R.layout.activity_owner_project_schedul;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.project_schedul_button:
                startActivity(new Intent(OwnerProjectSchedulActivity.this, MainActivity.class));
                break;
            case R.id.project_schedul_crpv:
                anim.start();
                break;
        }
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                OwnerProjectSchedul mOwnerProjectSchedul = gson.fromJson(data.data, OwnerProjectSchedul.class);
                project_schedul_crpv.setmTitleText(mOwnerProjectSchedul.getProportion());
                //后台传过来是用360度来表示进度，但是我是用100来表示进度,所以要转换下
                project_schedul_crpv.setPercent(Float.parseFloat(mOwnerProjectSchedul.getSum())/360*100);

                projectSchedulDays.setText("已完成:" + mOwnerProjectSchedul.getDays() + "天");
                projectSchedulProjectphase.setText("当前阶段:" + mOwnerProjectSchedul.getProjectphase());
                projectSchedulStartDay.setText("开工时间:" + mOwnerProjectSchedul.getStart_day());
                projectSchedulEndDay.setText("结束时间:" + mOwnerProjectSchedul.getEnd_day());

                anim = ObjectAnimator.ofFloat(project_schedul_crpv, "percent", 0, ((ColorfulRingProgressView) project_schedul_crpv).getPercent());
                anim.setInterpolator(new LinearInterpolator());
                anim.setDuration(2000);
                anim.start();
                project_schedul_crpv.setOnClickListener(this);
                break;
        }
        return super.handleMessage(msg);
    }

}
