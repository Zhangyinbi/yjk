package com.example.yangbang.miowner.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yangbang.miowner.R;
import com.example.yangbang.miowner.entity.RefundAmount;
import com.hbw.library.AbsBaseAdapter;
import com.hbw.library.utils.MoneySimpleFormat;

import java.util.List;

/**
 * Created by mwy on 2016/3/30.
 */
public class RefundMoneyAdapter extends AbsBaseAdapter<RefundAmount> {

    private Context context;

    public RefundMoneyAdapter(Context context, List<RefundAmount> dataList) {
        super(dataList);
        this.context = context;
        this.dataList = dataList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.listitem_refund_money, null);
            holder = new ViewHolder();
            holder.tv_checkout_time = (TextView) convertView.findViewById(R.id.tv_checkout_time);
            holder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            holder.tv_method_format = (TextView) convertView.findViewById(R.id.tv_method_format);
            holder.tv_remark = (TextView) convertView.findViewById(R.id.tv_remark);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_checkout_time.setText(getItem(position).getCheckout_time());
        holder.tv_num.setText("退款金额 : " + MoneySimpleFormat.getSimpleType(getItem(position).getNum()));
        holder.tv_method_format.setText("退款方式 : "+getItem(position).getMethod_format());
        holder.tv_remark.setText("退款原因 : "+getItem(position).getRemark());
        return convertView;
    }

    class ViewHolder{
        private TextView tv_checkout_time;
        private TextView tv_num;
        private TextView tv_method_format;
        private TextView tv_remark;
    }
}
