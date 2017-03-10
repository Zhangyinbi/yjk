package com.xiaomizhuang.buildcaptain.fragment;

import android.graphics.Color;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.hbw.library.BaseFragment;
import com.hbw.library.entity.ResponseSucceedData;
import com.hbw.library.utils.ToastUtil;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.adapter.AssistConstructionSchedulingOneAdapter;
import com.xiaomizhuang.buildcaptain.entity.AssistConstructionSchedulingOne;
import com.xiaomizhuang.buildcaptain.entity.YanShouResult;
import com.xiaomizhuang.buildcaptain.entity.YanShouStatus;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.HintDialog;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 施工排期Fragment
 */
public class FragmentConstructScheduling extends BaseFragment implements View.OnClickListener {

    private static final int GET_YAN_SHOU_STATUS = 0x166;
    private static final int YAN_SHOU_STATUS = 0x168;

    private ExpandableListView construction_progress_expandable_listview = null;
    private TextView construction_progress_plan;
    private TextView construction_progress_actual;
    private TextView tv_shuidian_status;
    private TextView tv_nimu_status;
    private TextView tv_youqi_status;
    private TextView tv_jungong_status;
    private LinearLayout ll_yanshou_status;
    private Button btn_yan_shou_application;
    private HashMap<String, String> mMap = new HashMap<String, String>();
    private ArrayList<AssistConstructionSchedulingOne> mAssistConstructionSchedulingOnes = new ArrayList<AssistConstructionSchedulingOne>();
    private AssistConstructionSchedulingOneAdapter mAssistConstructionSchedulingOneAdapterPlan = null;
    private AssistConstructionSchedulingOneAdapter mAssistConstructionSchedulingOneAdapterActual = null;
    public static String plan_type = "0";//0或者不传为实际排期,1为计划排期
    private String bm_id;
    private YanShouStatus yanShouStatus;
    private HintDialog dialog;

    @Override
    protected int initPageLayoutID() {
        return R.layout.fragment_construct_scheduling;
    }

    @Override
    protected void initWidget() {
        bm_id = getActivity().getIntent().getStringExtra("bm_id");

        construction_progress_plan = (TextView) getActivity().findViewById(R.id.construction_progress_plan);
        construction_progress_actual = (TextView) getActivity().findViewById(R.id.construction_progress_actual);

        tv_shuidian_status = (TextView) getActivity().findViewById(R.id.tv_shuidian_status);
        tv_nimu_status = (TextView) getActivity().findViewById(R.id.tv_nimu_status);
        tv_youqi_status = (TextView) getActivity().findViewById(R.id.tv_youqi_status);
        tv_jungong_status = (TextView) getActivity().findViewById(R.id.tv_jungong_status);
        ll_yanshou_status = (LinearLayout) getActivity().findViewById(R.id.ll_yanshou_status);
        btn_yan_shou_application = (Button) getActivity().findViewById(R.id.btn_yan_shou_application);

        //初始化颜色
        construction_progress_plan.setTextColor(getResources().getColor(R.color.grey_959595));
        construction_progress_plan.setBackgroundColor(getResources().getColor(R.color.grey_dbdbdb));
        construction_progress_actual.setTextColor(getResources().getColor(R.color.white_style_ffffff));
        construction_progress_actual.setBackgroundColor(getResources().getColor(R.color.red_e76270));

        construction_progress_plan.setOnClickListener(this);
        construction_progress_actual.setOnClickListener(this);
        construction_progress_expandable_listview = (ExpandableListView) getActivity().findViewById(R.id.construction_progress_expandable_listview);
        //设置属性去掉默认向下的箭头
        construction_progress_expandable_listview.setGroupIndicator(null);
        mAssistConstructionSchedulingOneAdapterPlan = new AssistConstructionSchedulingOneAdapter(getActivity(), mAssistConstructionSchedulingOnes, bm_id);
        mAssistConstructionSchedulingOneAdapterActual = new AssistConstructionSchedulingOneAdapter(getActivity(), mAssistConstructionSchedulingOnes, bm_id);
        plan_type = "0";
        mMap.put("uid", MyApplication.UID);
        mMap.put("baoming_id", bm_id);
        mMap.put("token", MyApplication.TOKEN);
        mMap.put("plan_type", plan_type);
        analyzeJson.requestData(HttpConstant.GETPLANUrl, mMap, REQUEST_SUCCESS);
        mMap.clear();
        mMap.put("token", MyApplication.TOKEN);
        mMap.put("baoming_id", bm_id);
        analyzeJson.requestData(HttpConstant.YanShouStatus, mMap, GET_YAN_SHOU_STATUS);
    }

    public boolean handleMessage(Message msg) {
        switch (msg.what) {
            case REQUEST_SUCCESS:
                final ResponseSucceedData data = (ResponseSucceedData) msg.obj;
                mAssistConstructionSchedulingOnes = gson.fromJson(data.data, new TypeToken<ArrayList<AssistConstructionSchedulingOne>>() {
                }.getType());
                if ("1".equals(plan_type)) {
                    construction_progress_expandable_listview.setAdapter(mAssistConstructionSchedulingOneAdapterPlan);
                    mAssistConstructionSchedulingOneAdapterPlan.setNotifyDataSetChangedList(mAssistConstructionSchedulingOnes);
                } else {
                    construction_progress_expandable_listview.setAdapter(mAssistConstructionSchedulingOneAdapterActual);
                    mAssistConstructionSchedulingOneAdapterActual.setNotifyDataSetChangedList(mAssistConstructionSchedulingOnes);
                }
                break;
            case GET_YAN_SHOU_STATUS://获取验收状态
                ResponseSucceedData data2 = (ResponseSucceedData) msg.obj;
                yanShouStatus = gson.fromJson(data2.data, YanShouStatus.class);
                if (yanShouStatus != null){
                    tv_shuidian_status.setText(yanShouStatus.getYanshoudan_status().get(0).getText());
                    if (yanShouStatus.getYanshoudan_status().get(0).getStatus() == 1){//1已验收，0未验收
                        tv_shuidian_status.setTextColor(Color.parseColor("#ffffff"));
                        tv_shuidian_status.setBackgroundDrawable(
                                getActivity().getResources().getDrawable(R.drawable.shape_red_solid_stroke_radius_9));
                    }else {
                        tv_shuidian_status.setTextColor(Color.parseColor("#b2b2b2"));
                        tv_shuidian_status.setBackgroundDrawable(null);
                    }
                    tv_nimu_status.setText(yanShouStatus.getYanshoudan_status().get(1).getText());
                    if (yanShouStatus.getYanshoudan_status().get(1).getStatus() == 1){//1已验收，0未验收
                        tv_nimu_status.setTextColor(Color.parseColor("#ffffff"));
                        tv_nimu_status.setBackgroundDrawable(
                                getActivity().getResources().getDrawable(R.drawable.shape_red_solid_stroke_radius_9));
                    }else {
                        tv_nimu_status.setTextColor(Color.parseColor("#b2b2b2"));
                        tv_nimu_status.setBackgroundDrawable(null);
                    }
                    tv_youqi_status.setText(yanShouStatus.getYanshoudan_status().get(2).getText());
                    if (yanShouStatus.getYanshoudan_status().get(2).getStatus() == 1){//1已验收，0未验收
                        tv_youqi_status.setTextColor(Color.parseColor("#ffffff"));
                        tv_youqi_status.setBackgroundDrawable(
                                getActivity().getResources().getDrawable(R.drawable.shape_red_solid_stroke_radius_9));
                    }else {
                        tv_youqi_status.setTextColor(Color.parseColor("#b2b2b2"));
                        tv_youqi_status.setBackgroundDrawable(null);
                    }
                    tv_jungong_status.setText(yanShouStatus.getYanshoudan_status().get(3).getText());
                    if (yanShouStatus.getYanshoudan_status().get(3).getStatus() == 1){//1已验收，0未验收
                        tv_jungong_status.setTextColor(Color.parseColor("#ffffff"));
                        tv_jungong_status.setBackgroundDrawable(
                                getActivity().getResources().getDrawable(R.drawable.shape_red_solid_stroke_radius_9));
                    }else {
                        tv_jungong_status.setTextColor(Color.parseColor("#b2b2b2"));
                        tv_jungong_status.setBackgroundDrawable(null);
                    }
                    if (TextUtils.isEmpty(yanShouStatus.getType().trim())){
                        btn_yan_shou_application.setVisibility(View.GONE);
                    }else{
                        btn_yan_shou_application.setText(yanShouStatus.getText());
                        btn_yan_shou_application.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if (dialog == null){
                                    dialog = new HintDialog(getActivity());
                                }
                                if (dialog.isShowing()) dialog.dismiss();
                                dialog.setTitleText(yanShouStatus.getMassage())
                                        .setDefultCancelListener()
                                        .setConfirmListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                mMap.clear();
                                                mMap.put("token", MyApplication.TOKEN);
                                                mMap.put("baoming_id", bm_id);
                                                mMap.put("type", yanShouStatus.getType());
                                                analyzeJson.requestData(HttpConstant.YanShouApplication, mMap, YAN_SHOU_STATUS);
                                                dialog.dismiss();
                                            }
                                        }).show();
                            }
                        });
                    }

                }
                break;
            case YAN_SHOU_STATUS://申请验收
                ResponseSucceedData data3 = (ResponseSucceedData) msg.obj;
                YanShouResult yanShouResult = gson.fromJson(data3.data, YanShouResult.class);
                ToastUtil.show(getActivity(),yanShouResult.getReturn_msg());
                break;
        }
        return super.handleMessage(msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.construction_progress_plan:
                construction_progress_plan.setTextColor(getResources().getColor(R.color.white_style_ffffff));
                construction_progress_plan.setBackgroundColor(getResources().getColor(R.color.red_e76270));
                construction_progress_actual.setTextColor(getResources().getColor(R.color.grey_959595));
                construction_progress_actual.setBackgroundColor(getResources().getColor(R.color.grey_dbdbdb));
                ll_yanshou_status.setVisibility(View.GONE);
                plan_type = "1";
                mMap.put("uid", MyApplication.UID);
                mMap.put("baoming_id", bm_id);
                mMap.put("token", MyApplication.TOKEN);
                mMap.put("plan_type", plan_type);
                analyzeJson.requestData(HttpConstant.GETPLANUrl, mMap, REQUEST_SUCCESS);
                break;
            case R.id.construction_progress_actual:
                construction_progress_plan.setTextColor(getResources().getColor(R.color.grey_959595));
                construction_progress_plan.setBackgroundColor(getResources().getColor(R.color.grey_dbdbdb));
                construction_progress_actual.setTextColor(getResources().getColor(R.color.white_style_ffffff));
                construction_progress_actual.setBackgroundColor(getResources().getColor(R.color.red_e76270));
                ll_yanshou_status.setVisibility(View.VISIBLE);
                plan_type = "0";
                mMap.put("uid", MyApplication.UID);
                mMap.put("baoming_id", bm_id);
                mMap.put("token", MyApplication.TOKEN);
                mMap.put("plan_type", plan_type);
                analyzeJson.requestData(HttpConstant.GETPLANUrl, mMap, REQUEST_SUCCESS);
                break;
        }
    }
}
