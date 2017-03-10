package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.net.AnalyzeJson;
import com.hbw.library.utils.ViewHolders;
import com.hbw.library.view.DialogPrompt;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.activity.ScheduleOrderDetailActivity;
import com.xiaomizhuang.buildcaptain.entity.ScheduleDetail;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.DialogAddRemark;

import java.util.HashMap;
import java.util.List;

/**
 * 排期详情订单实体类
 *
 * @FileName: com.xiaomizhuang.buildcaptain.adapter.ScheduleDetailAdapter.java
 * @author: Yangbang
 * @date: 2016-01-22 11:27
 *
 * 工程 ：
 * #0001    2016-07-13   mwy     隐藏布局中的按钮 tv_listitem_schedule_confirm
 */
public class ScheduleDetailAdapter extends AbsBaseAdapter<ScheduleDetail> {
    Context context;
    ScheduleDetail detail;
    DialogAddRemark dialogAddRemark;
    AnalyzeJson analyzeJson;
    DialogPrompt dialogPrompt;

    public ScheduleDetailAdapter(List<ScheduleDetail> dataList, Context context, AnalyzeJson analyzeJson) {
        super(dataList);
        this.context = context;
        this.analyzeJson = analyzeJson;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.listitem_schedule_order_detail, null);
        }
        detail = dataList.get(position);
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_material_name)).setText(detail.getM_name());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_order_number)).setText("订单编号：" + detail.getMaterial_budget_id() + "");
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_brand)).setText(detail.getBrand_name());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_name)).setText(detail.getDistributor_name());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_type)).setText(detail.getType_name() + "," + detail.getNorms());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_count)).setText("x" + detail.getAct_count());
        ((TextView) ViewHolders.get(convertView, R.id.tv_orders_time)).setText("材料下单时间：" + detail.getGet_order_time());
        ((TextView) ViewHolders.get(convertView, R.id.tv_pre_arrive_time)).setText("预计到货时间：" + detail.getExpectedtime());
        ((TextView) ViewHolders.get(convertView, R.id.tv_pra_arrive_time)).setText("确认收货时间：" + detail.getShouhuo_time());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_money)).setText(Float.parseFloat(detail.getAct_price()) * Float.parseFloat(detail.getAct_count()) + "元");
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm)).setVisibility(View.GONE);// < #0001
        /*((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm)).setText(detail.getOperation());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm))
        .setVisibility(Integer.parseInt(detail.getIstype() + "") == 0
        && Integer.parseInt(detail.getEdit_group()) == 4 ? View.VISIBLE : View.GONE);*/     //  > #0001
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_status)).setTextColor(context.getResources().getColor(R.color.gray_959595));
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_delay)).setText("延期" + detail.getInterval_time() + "天");
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_delay)).setVisibility(Integer.parseInt(detail.getInterval_time()) > 0 ? View.VISIBLE : View.GONE);
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_status)).setText(detail.getOrder_status());
//        switch (Integer.parseInt(detail.getStatus())) {
//            case 0://未开始
//                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_status)).setText("未完成");
//                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_status)).setTextColor(context.getResources().getColor(R.color.text_f39700));
//                break;
//            case 1://超时未完成
//                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_status)).setText("已延期" + Math.abs(Integer.parseInt(detail.getInterval_time())) + "天");
//                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_status)).setTextColor(context.getResources().getColor(R.color.red_c30d22));
//                break;
//            case 2://已完成
//                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm)).setVisibility(View.GONE);
//                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_status)).setText("已完成");
//                break;
//            case -1://完成但有延期
//                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm)).setVisibility(View.GONE);
//                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_status)).setText("已完成");
//                break;
//        }
//        switch (Integer.parseInt(detail.getOrder_code())) {
//            case 0://显示（下单）
//                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm)).setText("下单");
//                if (Integer.parseInt(detail.getEdit_group()) != 4) {
//                    ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm)).setVisibility(View.GONE);
//                }
//                break;
//            case 104://显示（复测下单）
//                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm)).setText("复测下单");
//                if (Integer.parseInt(detail.getEdit_group()) != 4) {
//                    ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm)).setVisibility(View.GONE);
//                }
//                break;
//            case 117://显示（收货|安装）
//                ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm)).setText("收货|安装");
//                if (Integer.parseInt(detail.getEdit_group()) != 4) {
//                    ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm)).setVisibility(View.GONE);
//                }
//                break;
//        }

        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_confirm)).setOnClickListener(new OnScheduleConfirmClickListener(detail));
        return convertView;
    }

    private class OnScheduleConfirmClickListener implements View.OnClickListener {
        ScheduleDetail detail;

        public OnScheduleConfirmClickListener(ScheduleDetail detail) {
            this.detail = detail;
        }

        @Override
        public void onClick(View v) {
            if (Integer.parseInt(detail.getInterval_time()) > 0) {
                if (dialogAddRemark == null) {
                    dialogAddRemark = new DialogAddRemark(context);
                }
                dialogAddRemark.show();
                dialogAddRemark.setDialogOkListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("baoming_id", detail.getBaoming_id());
                        params.put("id", detail.getId());
                        params.put("token", MyApplication.TOKEN);
                        params.put("reason", dialogAddRemark.getRemarkContent());
                        analyzeJson.requestData(HttpConstant.ScheduleOrderDetailAction, params, ScheduleOrderDetailActivity.AFFIRM_RECEIVING);
                        dialogAddRemark.dismiss();
                    }
                });
            } else {
                if (dialogPrompt == null) {
                    dialogPrompt = new DialogPrompt(context);
                }
                dialogPrompt.setPromptContent(detail.getOperation() + "？");
                dialogPrompt.setLeftBtnContent("取消");
                dialogPrompt.setRightBtnContent("确认");
                dialogPrompt.setLeftDialogPrompeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogPrompt.dismiss();
                    }
                });
                dialogPrompt.setRightDialogPrompeListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        HashMap<String, String> params = new HashMap<String, String>();
                        params.put("baoming_id", detail.getBaoming_id());
                        params.put("id", detail.getId());
                        params.put("token", MyApplication.TOKEN);
                        analyzeJson.requestData(HttpConstant.ScheduleOrderDetailAction, params, ScheduleOrderDetailActivity.AFFIRM_RECEIVING);
                        dialogPrompt.dismiss();
                    }
                });
                dialogPrompt.show();
            }

            //                switch (Integer.parseInt(detail.getStatus())) {
//                    case 0://未开始、未完成
//                        HashMap<String, String> params = new HashMap<String, String>();
//                        params.put("baoming_id", detail.getBaoming_id());
//                        params.put("id", detail.getId());
//                        params.put("token", MyApplication.TOKEN);
//                        analyzeJson.requestData(HttpConstant.ScheduleOrderDetailAction, params, ScheduleOrderDetailActivity.AFFIRM_RECEIVING);
//                        break;
//                    case 1://超时未完成
//                        if (dialogAddRemark == null) {
//                            dialogAddRemark = new DialogAddRemark(context);
//                        }
//                        dialogAddRemark.show();
//                        dialogAddRemark.setDialogOkListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                HashMap<String, String> params = new HashMap<String, String>();
//                                params.put("baoming_id", detail.getBaoming_id());
//                                params.put("id", detail.getId());
//                                params.put("token", MyApplication.TOKEN);
//                                params.put("reason", dialogAddRemark.getRemarkContent());
//                                analyzeJson.requestData(HttpConstant.ScheduleOrderDetailAction, params, ScheduleOrderDetailActivity.AFFIRM_RECEIVING);
//                                dialogAddRemark.dismiss();
//                            }
//                        });
//                        break;
//                    case 2://已完成
//                        break;
//                    case -1://完成但有延期
//                        break;
//                }

        }
    }


}
