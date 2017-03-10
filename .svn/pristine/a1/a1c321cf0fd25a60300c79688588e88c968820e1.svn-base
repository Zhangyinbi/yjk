package com.xiaomizhuang.buildcaptain.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.net.AnalyzeJson;
import com.hbw.library.utils.ViewHolders;
import com.hbw.library.view.DialogPrompt;
import com.xiaomizhuang.buildcaptain.R;
import com.xiaomizhuang.buildcaptain.entity.Orders;
import com.xiaomizhuang.buildcaptain.util.HttpConstant;
import com.xiaomizhuang.buildcaptain.util.MyApplication;
import com.xiaomizhuang.buildcaptain.view.DialogAddRemark;

import java.util.HashMap;
import java.util.List;

/**
 * Created by user on 2015/11/20.
 */
public class AllOrdersAdapter extends AbsBaseAdapter<Orders> {
    public static final int CONFIRM_RECEIPT = 97;
    Context context;
    AnalyzeJson analyzeJson;
    DialogPrompt dialogPrompt;
    DialogAddRemark dialogAddRemark;
    HashMap<String, String> params = new HashMap<>();

    public AllOrdersAdapter(Context context, List<Orders> dataList, AnalyzeJson analyzeJson) {
        super(dataList);
        this.context = context;
        this.analyzeJson = analyzeJson;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.all_orders_item, parent, false);
        }
        ((TextView) ViewHolders.get(convertView, R.id.tv_large_type)).setText(dataList.get(position).getGet_mt_name());
        ((TextView) ViewHolders.get(convertView, R.id.tv_orders_time)).setText("材料下单时间：" + dataList.get(position).getGet_order_time());
        ((TextView) ViewHolders.get(convertView, R.id.tv_brand)).setText(dataList.get(position).getBrand_name());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_order_number)).setText("材料订单编号：" + dataList.get(position).getId());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_schedule_name)).setText(dataList.get(position).getDistributer_name());
        ((TextView) ViewHolders.get(convertView, R.id.tv_type_model)).setText(dataList.get(position).getGet_type_name() + "," + dataList.get(position).getGet_norms());
        ((TextView) ViewHolders.get(convertView, R.id.tv_type_model_count)).setText("x" + dataList.get(position).getAct_count());
        ((TextView) ViewHolders.get(convertView, R.id.tv_beizhu)).setText(dataList.get(position).getBeizhu());
        ((TextView) ViewHolders.get(convertView, R.id.tv_price)).setText(dataList.get(position).getAct_total_price() + "元");
        ((TextView) ViewHolders.get(convertView, R.id.tv_pre_arrive_time)).setText("预计到货时间：" + dataList.get(position).getExpectedtime());
        ((TextView) ViewHolders.get(convertView, R.id.tv_pra_arrive_time)).setText("确认到货时间：" + dataList.get(position).getShouhuo_time());
//        ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setVisibility(Integer.parseInt(dataList.get(position).getIstype() + "") == 0 ? View.VISIBLE : View.GONE);
        ((TextView) ViewHolders.get(convertView, R.id.tv_beizhu)).setVisibility(TextUtils.isEmpty(dataList.get(position).getBeizhu())?View.GONE:View.VISIBLE);
        ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setVisibility(TextUtils.isEmpty(dataList.get(position).getRbutton()) ? View.GONE : View.VISIBLE);
        ((TextView) ViewHolders.get(convertView, R.id.tv_orders_state)).setText(dataList.get(position).getOrder_status());
        ((TextView) ViewHolders.get(convertView, R.id.tv_listitem_order_delay)).setText(dataList.get(position).getMove_day() > 0 ? "延期" + dataList.get(position).getMove_day() + "天" : "");
        ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setText(dataList.get(position).getRbutton());
//        if (dataList.get(position).getGoodsstatus().equals("1") || dataList.get(position).getGoodsstatus().equals("115")) {
//            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setVisibility(View.VISIBLE);v
//            ((TextView) ViewHolders.get(convertView, R.id.tv_orders_state)).setText("已下单");
//        } else if (dataList.get(position).getGoodsstatus().equals("116")) {
//            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setVisibility(View.VISIBLE);
//            ((TextView) ViewHolders.get(convertView, R.id.tv_orders_state)).setText("已接单");
//        } else if (dataList.get(position).getGoodsstatus().equals("117")) {
//            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setVisibility(View.VISIBLE);
//            ((TextView) ViewHolders.get(convertView, R.id.tv_orders_state)).setText("已发货");
//        } else if (dataList.get(position).getGoodsstatus().equals("-1")) {
//            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setVisibility(View.VISIBLE);
//            ((TextView) ViewHolders.get(convertView, R.id.tv_orders_state)).setText("已退货");
//        } else if (dataList.get(position).getGoodsstatus().equals("120")) {
//
//        } else if (dataList.get(position).getGoodsstatus().equals("118")) {
//            ((TextView) ViewHolders.get(convertView, R.id.tv_orders_state)).setText("已收货");
//            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setVisibility(View.GONE);
//        } else {
//
//        }


//        if (dataList.get(position).getGoodsstatus().equals("118")) {
//            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
//            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setText("已收货");
//            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setEnabled(false);
////            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setTextColor(context.getResources().getColor(R.color.gray_7d7d7d));
////            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setBackgroundResource(R.drawable.shape_text_gray_stroke);
//        } else {
//            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setText("确认收货");
//            ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setEnabled(true);
//        }
        ((TextView) ViewHolders.get(convertView, R.id.tv_confirm)).setOnClickListener(new OnConfirmClickListener(position));
        return convertView;
    }

    private class OnConfirmClickListener implements View.OnClickListener {
        int position;

        public OnConfirmClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if (dialogPrompt == null) {
                dialogPrompt = new DialogPrompt(context);
            }
            dialogPrompt.setPromptContent(dataList.get(position).getRbutton() + "？");
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
                    params.clear();
                    params.put("uid",MyApplication.UID);
                    params.put("order_id", dataList.get(position).getId());
                    params.put("token", MyApplication.TOKEN);
                    analyzeJson.requestData(HttpConstant.confirmReceiptUrl, params, CONFIRM_RECEIPT);
                    dialogPrompt.dismiss();
                }
            });
            dialogPrompt.show();

//            if (dataList.get(position).getMove_day() > 0) {
//                if (dialogAddRemark == null) {
//                    dialogAddRemark = new DialogAddRemark(context);
//                }
//                dialogAddRemark.show();
//                dialogAddRemark.setDialogOkListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        params.clear();
//                        params.put("id", dataList.get(position).getId());
//                        params.put("token", MyApplication.TOKEN);
//                        params.put("shouhuo_time", dataList.get(position).getShouhuo_time() + "");
//                        params.put("step", dataList.get(position).getStep() + "");
//                        params.put("bm_id", ((MaterialOrderDetailActivity) context).getBm_id() + "");
//                        params.put("mt_id", dataList.get(position).getMt_id() + "");
//                        params.put("status", dataList.get(position).getGoodsstatus() + "");
//                        params.put("move_day", dataList.get(position).getMove_day() + "");
//                        params.put("title", "");
//                        params.put("reason", dialogAddRemark.getRemarkContent());
//                        analyzeJson.requestData(HttpConstant.confirmReceiptUrl, params, CONFIRM_RECEIPT);
//                        dialogAddRemark.dismiss();
//                    }
//                });
//            } else {
//                if (dialogPrompt == null) {
//                    dialogPrompt = new DialogPrompt(context);
//                }
//                dialogPrompt.setPromptContent(dataList.get(position).getRbutton() + "？");
//                dialogPrompt.setLeftBtnContent("取消");
//                dialogPrompt.setRightBtnContent("确认");
//                dialogPrompt.setLeftDialogPrompeListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        dialogPrompt.dismiss();
//                    }
//                });
//                dialogPrompt.setRightDialogPrompeListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        params.clear();
//                        params.put("id", dataList.get(position).getId());
//                        params.put("token", MyApplication.TOKEN);
//                        params.put("shouhuo_time", dataList.get(position).getShouhuo_time() + "");
//                        params.put("step", dataList.get(position).getStep() + "");
//                        params.put("bm_id", ((MaterialOrderDetailActivity) context).getBm_id() + "");
//                        params.put("mt_id", dataList.get(position).getMt_id() + "");
//                        params.put("status", dataList.get(position).getGoodsstatus() + "");
//                        params.put("move_day", dataList.get(position).getMove_day() + "");
//                        params.put("title", "");
//                        params.put("reason", "");
//                        analyzeJson.requestData(HttpConstant.confirmReceiptUrl, params, CONFIRM_RECEIPT);
//                        dialogPrompt.dismiss();
//                    }
//                });
//                dialogPrompt.show();
//            }
        }
    }
}
